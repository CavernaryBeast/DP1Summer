
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReviewerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Reviewer;

@Service
@Transactional
public class ReviewerService {

	@Autowired
	private ReviewerRepository				reviewerRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private UserAccountService				userAccountService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	public Reviewer create() {

		Reviewer res;
		UserAccount ua;
		Authority auth;

		res = new Reviewer();
		ua = this.userAccountService.create();

		auth = new Authority();
		auth.setAuthority(Authority.REVIEWER);
		ua.addAuthority(auth);

		res.setUserAccount(ua);

		return res;
	}

	public Collection<Reviewer> findAll() {

		final Collection<Reviewer> res = this.reviewerRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Reviewer findOne(final int id) {

		Assert.isTrue(id != 0);

		final Reviewer res = this.reviewerRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Reviewer save(final Reviewer rev) {

		Assert.notNull(rev);
		Reviewer saved;

		//Creación del author
		if (rev.getId() == 0) {

			Md5PasswordEncoder encoder;
			encoder = new Md5PasswordEncoder();

			rev.getUserAccount().setPassword(encoder.encodePassword(rev.getUserAccount().getPassword(), null));
		}

		if (rev.getPhoneNumber() != null) {

			final String editedPhone = this.configurationParametersService.checkPhoneNumber(rev.getPhoneNumber());
			rev.setPhoneNumber(editedPhone);
		}

		saved = this.reviewerRepository.save(rev);

		return saved;
	}

	public void delete(final Reviewer rev) {

		Assert.notNull(rev);

		this.reviewerRepository.delete(rev);
	}

	public Reviewer findByPrincipal() {

		Reviewer res;
		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);

		res = this.findByUserAccountId(ua.getId());

		final boolean hasAuthority = this.actorService.checkAuthority(res, Authority.REVIEWER);
		Assert.isTrue(hasAuthority);

		return res;
	}

	public Reviewer findByUserAccountId(final int id) {

		Assert.isTrue(id != 0);

		final Reviewer res = this.reviewerRepository.findByUserAccountId(id);
		Assert.notNull(res);

		return res;
	}

}
