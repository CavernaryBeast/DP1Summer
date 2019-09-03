
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository				sponsorRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private UserAccountService				userAccountService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	public Sponsor create() {

		Sponsor res;
		UserAccount ua;
		Authority auth;

		res = new Sponsor();
		ua = this.userAccountService.create();

		auth = new Authority();
		auth.setAuthority(Authority.SPONSOR);
		ua.addAuthority(auth);

		res.setUserAccount(ua);

		return res;
	}

	public Collection<Sponsor> findAll() {
		final Collection<Sponsor> res = this.sponsorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Sponsor findOne(final int id) {
		Assert.isTrue(id != 0);
		final Sponsor res = this.sponsorRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Sponsor save(final Sponsor sponsor) {

		Assert.notNull(sponsor);
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		sponsor.getUserAccount().setPassword(encoder.encodePassword(sponsor.getUserAccount().getPassword(), null));

		if (sponsor.getPhoneNumber() != null) {

			final String editedPhone = this.configurationParametersService.checkPhoneNumber(sponsor.getPhoneNumber());
			sponsor.setPhoneNumber(editedPhone);
		}

		final Sponsor saved = this.sponsorRepository.save(sponsor);

		return saved;
	}

	/**
	 * This method finds the logged user that is using the application. Apart from this,
	 * it checks that the user is an Administrator
	 * 
	 * @return The logged user, an instance of Sponsor
	 */
	public Sponsor findByPrincipal() {

		Sponsor res;
		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);

		res = this.findByUserAccountId(ua.getId());

		final boolean hasAuthority = this.actorService.checkAuthority(res, Authority.SPONSOR);
		Assert.isTrue(hasAuthority);

		return res;
	}

	public Sponsor findByUserAccountId(final int id) {

		Assert.isTrue(id != 0);

		final Sponsor res = this.sponsorRepository.findByUserAccountId(id);
		Assert.notNull(res);

		return res;
	}

}
