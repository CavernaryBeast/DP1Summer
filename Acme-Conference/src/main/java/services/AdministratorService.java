
package services;

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
