
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RegistrationRepository;
import domain.Author;
import domain.CreditCard;
import domain.Registration;

@Transactional
@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository	registrationRepository;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;


	public Registration create() {
		this.authorService.findByPrincipal();
		final Registration res = new Registration();
		final Author principal = this.authorService.findByPrincipal();
		final CreditCard creditCard = new CreditCard();
		res.setCreditCard(creditCard);
		res.setAuthor(principal);
		return res;
	}

	public Collection<Registration> findAll() {
		final Collection<Registration> res = this.registrationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Registration findOne(final int registrationId) {
		final Author author = this.authorService.findByPrincipal();
		Assert.isTrue(registrationId != 0);
		Assert.notNull(registrationId);
		Assert.isTrue(this.registrationRepository.exists(registrationId));
		final Registration res = this.registrationRepository.findOne(registrationId);
		Assert.notNull(res);
		Assert.isTrue(res.getAuthor().equals(author), "Not the author");
		return res;
	}

	public Registration findOne2(final int registrationId) {
		Assert.isTrue(registrationId != 0);
		Assert.notNull(registrationId);
		Assert.isTrue(this.registrationRepository.exists(registrationId));
		final Registration res = this.registrationRepository.findOne(registrationId);
		Assert.notNull(res);
		return res;
	}

	public Registration save2(final Registration registration) {
		Assert.notNull(registration, "Registration Null");
		Registration saved;
		saved = this.registrationRepository.saveAndFlush(registration);
		Assert.notNull(saved, "Registration saved Null");
		return saved;
	}

	public Registration save(final Registration registration) {
		Assert.notNull(registration, "Registration Null");
		Registration saved;
		final Author principal = this.authorService.findByPrincipal();
		saved = this.registrationRepository.saveAndFlush(registration);
		Assert.notNull(saved, "Registration saved Null");
		return saved;
	}

	public Registration reconstruct(final Registration registration, final BindingResult binding) {
		this.authorService.findByPrincipal();
		final Registration original = this.findOne(registration.getId());
		registration.setId(original.getId());
		registration.setVersion(original.getVersion());
		this.validator.validate(registration, binding);
		return registration;

	}

	public String getRegistrationsPerConferenceStats() {
		String result;
		this.administratorService.findByPrincipal();
		result = this.registrationRepository.getRegistrationsPerConferenceStats();
		Assert.notNull(result);
		return result;
	}

	public boolean exist(final int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);
		final boolean res = this.registrationRepository.exists(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<Registration> findOwn() {
		final Author author = this.authorService.findByPrincipal();
		Assert.notNull(author);
		Assert.isTrue(author.getId() != 0);
		final Collection<Registration> res;
		System.out.println(author.getId());
		res = this.registrationRepository.findOwn(author.getId());
		Assert.notNull(res);
		return res;
	}

	public static boolean luhnTest(final String number) {
		int s1 = 0, s2 = 0;
		final String reverse = new StringBuffer(number).reverse().toString();
		for (int i = 0; i < reverse.length(); i++) {
			final int digit = Character.digit(reverse.charAt(i), 10);
			if (i % 2 == 0)
				s1 += digit;
			else {//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
				s2 += 2 * digit;
				if (digit >= 5)
					s2 -= 9;
			}
		}
		return (s1 + s2) % 10 == 0;
	}

	public void tryluhnTest(final String number) {
		Assert.isTrue(RegistrationService.luhnTest(number), "Not a valid Credit Card");
	}

}
