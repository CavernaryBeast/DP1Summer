
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Administrator;
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;

	@Autowired
	private ActorService			actorService;


	public Administrator create() {

		final Administrator admin = new Administrator();

		return admin;
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

		final Administrator saved = this.save(admin);

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

}
