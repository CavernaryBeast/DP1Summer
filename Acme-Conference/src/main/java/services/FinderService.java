
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Author;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private AuthorService		authorService;


	public Finder create() {

		final Finder res = new Finder();

		res.setKeyword(null);
		res.setCategory(null);
		res.setStartDate(null);
		res.setEndDate(null);
		res.setFee(null);

		return res;
	}

	public Finder findOne(final int finderId) {

		Assert.isTrue(finderId != 0);

		final Finder res = this.finderRepository.findOne(finderId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Finder> findAll() {

		final Collection<Finder> res = this.finderRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Finder save(final Finder finder) {

		Assert.notNull(finder);

		if (finder.getId() != 0) {
			final Author principal = this.authorService.findByPrincipal();
			Assert.isTrue(principal.getFinder().getId() == finder.getId());
		}

		return this.finderRepository.save(finder);
	}

}
