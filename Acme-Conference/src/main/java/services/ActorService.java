
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Author;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;

	@Autowired
	private AuthorService	authorService;


	public Actor create() {

		final Actor res = new Actor();

		return res;
	}

	public Collection<Actor> findAll() {

		final Collection<Actor> res = this.actorRepository.findAll();

		Assert.notNull(res);

		return res;
	}

	public Collection<Actor> findAllAuthors() {

		final Collection<Actor> res = this.actorRepository.findAllAuthors();

		Assert.notNull(res);

		return res;
	}

	public Actor findOne(final int id) {

		Assert.isTrue(id != 0);

		final Actor res = this.actorRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	/**
	 *
	 * @param actor
	 *            El actor cuya autoridad queremos comprobar
	 * @param authority
	 *            Autoridad que queremos comprobar que el actor posee
	 * @return True si el actor posee la autoridad pasada como parámetro o false si no la posee
	 */
	public boolean checkAuthority(final Actor actor, final String authority) {

		Assert.notNull(actor);
		Assert.notNull(authority);

		final UserAccount ua = actor.getUserAccount();
		final Collection<Authority> authorities = ua.getAuthorities();

		Assert.notEmpty(authorities);

		final Authority aux = new Authority();
		aux.setAuthority(authority);

		return authorities.contains(aux);
	}

	/**
	 * Este método sirve para sacar el actor logeado en el sistema
	 * Se usa como método auxiliar para sacar los distintos tipos de actores del sistema y hacer comprobaciones
	 * respecto a sus authorities
	 *
	 * @return El actor logeado en el sistema
	 */
	public Actor findByPrincipal() {

		Actor res;
		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		res = this.findByUserAccountId(ua.getId());
		System.out.println(res);
		return res;
	}

	public Actor findByUserAccountId(final int id) {

		Assert.isTrue(id != 0);

		final Actor res = this.actorRepository.findByUserAccountId(id);
		Assert.notNull(res);

		return res;
	}

	//----------------------------------------------------------------------------

	public Actor findActorLogged() {
		Actor result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.actorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		//		Assert.isTrue(result.getUserAccount().getStatusAccount());
		return result;
	}

	public void checkUserLoginAdministrator(final Actor actor) {
		final Authority auth = new Authority();
		auth.setAuthority(Authority.ADMINISTRATOR);
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		Assert.isTrue(authorities.contains(auth));
	}

	public Collection<Actor> findSubmittedByConferenceId(final int conferenceId) {

		Assert.isTrue(conferenceId != 0);

		final Collection<Author> aux = this.authorService.findSubmittedAuthorByConferenceId(conferenceId);

		final Collection<Actor> res = new ArrayList<>();

		for (final Author author : aux)
			res.add(author);

		Assert.notNull(res);

		return res;
	}
	public Collection<Actor> findRegisteredByConferenceId(final int conferenceId) {

		Assert.isTrue(conferenceId != 0);

		final Collection<Author> aux = this.authorService.findRegisteredAuthorByConferenceId(conferenceId);

		final Collection<Actor> res = new ArrayList<>();

		for (final Author author : aux)
			res.add(author);

		Assert.notNull(res);

		return res;
	}

}
