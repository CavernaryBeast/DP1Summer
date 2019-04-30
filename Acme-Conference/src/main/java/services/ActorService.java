
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Author;
import repositories.ActorRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository actorRepository;


	public Actor create() {

		final Actor res = new Actor();

		return res;
	}

	public Collection<Actor> findAll() {

		final Collection<Actor> res = this.actorRepository.findAll();

		Assert.notNull(res);
		Assert.notEmpty(res);

		return res;
	}

	public Actor findOne(final int id) {

		Assert.isTrue(id != 0);

		final Actor res = this.actorRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public boolean checkAuthority(final Author author, final String authority) {

		Assert.notNull(author);
		Assert.notNull(authority);

		final UserAccount ua = author.getUserAccount();
		final Collection<Authority> authorities = ua.getAuthorities();

		Assert.notEmpty(authorities);

		final Authority aux = new Authority();
		aux.setAuthority(authority);

		return authorities.contains(aux);
	}

}
