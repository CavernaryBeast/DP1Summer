
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Administrator;
import domain.Author;
import domain.Conference;
import domain.ConfigurationParameters;
import domain.Submission;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository			administratorRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private AuthorService					authorService;

	@Autowired
	private SubmissionService				submissionService;

	@Autowired
	private ConferenceService				conferenceService;

	@Autowired
	private UserAccountService				userAccountService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	public Administrator create() {

		Administrator res;
		UserAccount ua;
		Authority auth;

		res = new Administrator();
		ua = this.userAccountService.create();

		auth = new Authority();
		auth.setAuthority(Authority.ADMINISTRATOR);
		ua.addAuthority(auth);

		res.setUserAccount(ua);

		return res;
	}

	public Collection<Administrator> findAll() {

		final Collection<Administrator> res = this.administratorRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Administrator findOne(final int id) {

		Assert.isTrue(id != 0);

		final Administrator res = this.administratorRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Administrator save(final Administrator admin) {

		Assert.notNull(admin);

		this.findByPrincipal();

		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		admin.getUserAccount().setPassword(encoder.encodePassword(admin.getUserAccount().getPassword(), null));

		if (admin.getPhoneNumber() != null) {

			final String editedPhone = this.configurationParametersService.checkPhoneNumber(admin.getPhoneNumber());
			admin.setPhoneNumber(editedPhone);
		}

		final Administrator saved = this.administratorRepository.save(admin);

		return saved;
	}

	/**
	 * This method finds the logged user that is using the application. Apart from this,
	 * it checks that the user is an Administrator
	 * 
	 * @return The logged user, an instance of Administrator
	 */
	public Administrator findByPrincipal() {

		Administrator res;
		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);

		res = this.findByUserAccountId(ua.getId());

		final boolean hasAuthority = this.actorService.checkAuthority(res, Authority.ADMINISTRATOR);
		Assert.isTrue(hasAuthority);

		return res;
	}

	public Administrator findByUserAccountId(final int id) {

		Assert.isTrue(id != 0);

		final Administrator res = this.administratorRepository.findByUserAccountId(id);
		Assert.notNull(res);

		return res;
	}

	public void computeScore() {
		this.findByPrincipal();
		final Collection<Author> authors;
		final Map<Author, Map<String, Integer>> matchesPerActor = new HashMap<>();
		authors = this.authorService.findAll();//Seleccionamos los authors
		final ConfigurationParameters cf = this.configurationParametersService.getConfigurationParameters();
		final Collection<String> voidWords = cf.getVoidWords();//Obtenemos todas las void Words
		final Collection<String> frequentWords = new HashSet<String>();//Aquí almacenaremos todas las frequentWords encontradas entre todos los actores

		//Paso 1: Buscaremos todas las buzzWords

		final Collection<Conference> conferencesOrganisedInLast12MonthsOrInFuture = this.conferenceService.getConferencesorganizedInLast12MonthsOrInFuture();//Obtenemos las conferencias
		for (final Conference c : conferencesOrganisedInLast12MonthsOrInFuture) {
			final String[] palabrasDelTítulo = c.getTitle().trim().split("\\s+");//Separamos las palabras del título
			final String[] palabrasDelSummary = c.getSummary().trim().split("\\s+");//Separamos las palabras del summary
			for (final String word : palabrasDelTítulo)
				if (!voidWords.contains(word))
					frequentWords.add(word);//Si esta palabra del título no es una void word, entonces es una frequent Word

			for (final String word : palabrasDelSummary)
				if (!voidWords.contains(word))
					frequentWords.add(word);//Si esta palabra del resumen no es una void word, entonces es una frequent Word
		}

		//Paso 2: Calculamos el número de veces que aparece cada frequentWord
		final Map<String, Integer> numeroVecesQueApareceFrequentWord = new HashMap<>();
		for (final String frequentword : frequentWords) {
			Integer countMatches = 0;

			for (final Conference c : conferencesOrganisedInLast12MonthsOrInFuture) {
				final String[] palabrasDelTítulo = c.getTitle().trim().split("\\s+");//Separamos las palabras del título
				final String[] palabrasDelSummary = c.getSummary().trim().split("\\s+");//Separamos las palabras del summary
				final Set<String> palabrasTítulo = new HashSet<>(Arrays.asList(palabrasDelTítulo));
				final Set<String> palabrasSummary = new HashSet<>(Arrays.asList(palabrasDelSummary));
				//Por cada frequent,contamos cuantas veces aparece en el Título y en el summary de cada conference			
				for (final String p : palabrasTítulo)
					if (p.equals(frequentword))
						countMatches++;

				for (final String p : palabrasSummary)
					if (p.equals(frequentword))
						countMatches++;

			}
			numeroVecesQueApareceFrequentWord.put(frequentword, countMatches);
		}

		//Paso 3:Calculamos las buzzWords-> Ej: Si la frequent word más alta es 6, la frontera de las buzzWords es 6 - 0,2*6 =4,8. Pues así
		final Map<String, Integer> mapNumeroVecesQueApareceFrequentWordOrdenado = AdministratorService.sortByValue(numeroVecesQueApareceFrequentWord);
		final List<Entry<String, Integer>> numeroVecesQueApareceFrequentWordOrdenado = new ArrayList<>(AdministratorService.sortByValue(numeroVecesQueApareceFrequentWord).entrySet());
		final Entry<String, Integer> limiteMayor = numeroVecesQueApareceFrequentWordOrdenado.get(0); //Esta sería la palabra que más se repite
		final Integer maximoValor = limiteMayor.getValue();
		final Double límiteBuzzWord = maximoValor - 0.2 * maximoValor;
		final Collection<String> frequentWordsList = mapNumeroVecesQueApareceFrequentWordOrdenado.keySet();

		final Collection<String> buzzWords = new HashSet<>();
		for (final String frequentWord : frequentWordsList) {
			final Integer value = mapNumeroVecesQueApareceFrequentWordOrdenado.get(frequentWord);
			if (value > límiteBuzzWord)
				buzzWords.add(frequentWord);//Si supera el límite, es una buzzWord
		}

		//Paso 4: Calculamos las puntuaciones de los authors

		final Map<Author, Integer> puntuacionPorAuthor = new HashMap<>();

		for (final Author a : authors) {
			final Collection<Submission> submissionsFromAuthor = this.submissionService.findSubmissionsFromAuthor(a.getId());//Obtenemos las submissions del author
			Matcher matchesPaperTitle, matchesPaperSummary;
			Integer countMatches = 0;
			if (!submissionsFromAuthor.isEmpty())
				//Si no está vacía,calculamos la cantidad de veces que contiene la palarbra
				for (final Submission s : submissionsFromAuthor) {

					matchesPaperTitle = this.patternBuzzWords(buzzWords).matcher(s.getPaper().getTitle().toLowerCase());
					matchesPaperSummary = this.patternBuzzWords(buzzWords).matcher(s.getPaper().getSummary().toLowerCase());
					while (matchesPaperTitle.find())
						countMatches++;
					while (matchesPaperSummary.find())
						countMatches++;
					//					
					//					
					//					
					//					final String[] palabrasDelTítulo = s.getPaper().getTitle().trim().split("\\s+");//Separamos las palabras del título
					//					final String[] palabrasDelSummary = s.getPaper().getSummary().trim().split("\\s+");//Separamos las palabras del summary
					//					final Set<String> palabrasTítulo = new HashSet<>(Arrays.asList(palabrasDelTítulo));
					//					final Set<String> palabrasSummary = new HashSet<>(Arrays.asList(palabrasDelSummary));
					//					//Por cada frequent,contamos cuantas veces aparece en el Título y en el summary de cada submission			
					//					for (final String p : palabrasTítulo)
					//						if (p.equals(frequentword))
					//							countMatches++;
					//
					//					for (final String p : palabrasSummary)
					//						if (p.equals(frequentword))
					//							countMatches++;
				}
			puntuacionPorAuthor.put(a, countMatches);
		}

		final Map<Author, Integer> puntuacionPorAuthorSorted = AdministratorService.sortByValue(puntuacionPorAuthor);

		final List<Entry<Author, Integer>> puntuacionPorAuthormatchesSorted = new ArrayList<>(AdministratorService.sortByValue(puntuacionPorAuthor).entrySet());

		final Author biggestPuntuationOne = puntuacionPorAuthormatchesSorted.get(0).getKey();
		final Integer biggestPuntuation = puntuacionPorAuthorSorted.get(biggestPuntuationOne);
		biggestPuntuationOne.setPuntuation(biggestPuntuation / biggestPuntuation);
		this.authorService.save(biggestPuntuationOne);

		for (int i = 1; i < puntuacionPorAuthormatchesSorted.size(); i++) {
			final Author authorI = puntuacionPorAuthormatchesSorted.get(i).getKey();
			final Integer puntuation = puntuacionPorAuthorSorted.get(authorI) / biggestPuntuation;
			authorI.setPuntuation(puntuation);
			this.authorService.save(authorI);
		}

	}
	private Pattern patternBuzzWords(final Collection<String> buzzWords) {
		String pattern = "";
		for (final String bw : buzzWords)
			pattern = pattern + (bw.toLowerCase() + "|");
		pattern = pattern.substring(0, pattern.length() - 1);
		final Pattern result = Pattern.compile(pattern);
		return result;
	}

	//	private Pattern patternTitleSubmissionWords(Author a) {
	//		final ConfigurationParameters cf = this.configurationParametersService.getConfigurationParameters();
	//		final Collection<String> title = cf.getVoidWords();
	//		String pattern = "";
	//		for (final String kw : voidWords)
	//			pattern = pattern + (kw.toLowerCase() + "|");
	//		pattern = pattern.substring(0, pattern.length() - 1);
	//		final Pattern result = Pattern.compile(pattern);
	//		return result;
	//	}

	private static <K, V> Map<K, V> sortByValue(final Map<K, V> map) {
		final List<Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Object>() {

			@Override
			@SuppressWarnings("unchecked")
			public int compare(final Object o1, final Object o2) {
				return ((Comparable<V>) ((Map.Entry<K, V>) (o1)).getValue()).compareTo(((Map.Entry<K, V>) (o2)).getValue());
			}
		});

		final Map<K, V> result = new LinkedHashMap<>();
		for (final Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
			final Map.Entry<K, V> entry = it.next();
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

}
