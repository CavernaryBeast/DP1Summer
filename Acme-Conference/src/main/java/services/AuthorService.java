
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Author;
import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository				authorRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private UserAccountService				userAccountService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	public Author create() {

		Author res;
		UserAccount ua;
		Authority auth;

		res = new Author();
		ua = this.userAccountService.create();

		auth = new Authority();
		auth.setAuthority(Authority.AUTHOR);
		ua.addAuthority(auth);

		res.setUserAccount(ua);

		return res;
	}

	public Collection<Author> findAll() {

		final Collection<Author> res = this.authorRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Author findOne(final int id) {

		Assert.isTrue(id != 0);

		final Author res = this.authorRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Author save(final Author author) {

		Assert.notNull(author);
		Author saved;

		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		author.getUserAccount().setPassword(encoder.encodePassword(author.getUserAccount().getPassword(), null));

		//		//Creaci�n del author
		//		if (author.getId() == 0) {
		//
		//			Md5PasswordEncoder encoder;
		//			encoder = new Md5PasswordEncoder();
		//
		//			author.getUserAccount().setPassword(encoder.encodePassword(author.getUserAccount().getPassword(), null));
		//		}

		if (author.getPhoneNumber() != null) {

			final String editedPhone = this.configurationParametersService.checkPhoneNumber(author.getPhoneNumber());
			author.setPhoneNumber(editedPhone);
		}

		saved = this.authorRepository.save(author);

		return saved;
	}

	public void delete(final Author author) {

		Assert.notNull(author);

		this.authorRepository.delete(author);
	}

	public Author findByPrincipal() {

		Author res;
		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);

		res = this.findByUserAccountId(ua.getId());

		final boolean hasAuthority = this.actorService.checkAuthority(res, Authority.AUTHOR);
		Assert.isTrue(hasAuthority);

		return res;
	}

	public Author findByUserAccountId(final int id) {

		Assert.isTrue(id != 0);

		final Author res = this.authorRepository.findByUserAccountId(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<Author> findSubmittedAuthorByConferenceId(final int conferenceId) {

		Assert.isTrue(conferenceId != 0);
		final Collection<Author> res = this.authorRepository.findSubmittedAuthorByConferenceId(conferenceId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Author> findRegisteredAuthorByConferenceId(final int conferenceId) {

		Assert.isTrue(conferenceId != 0);

		final Collection<Author> res = this.authorRepository.findRegisteredAuthorByConferenceId(conferenceId);
		Assert.notNull(res);

		return res;
	}

}
