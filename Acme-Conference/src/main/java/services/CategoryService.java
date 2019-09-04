
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Conference;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository		categoryRepository;

	@Autowired
	private AdministratorService	administratorService;


	public Category create() {

		final Category res = new Category();

		return res;
	}

	public Collection<Category> findAll() {

		final Collection<Category> res = this.categoryRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Category findOne(final int id) {

		Assert.isTrue(id != 0);

		final Category res = this.categoryRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<Category> findChildren(final int id) {

		Assert.isTrue(id != 0);

		final Collection<Category> res = this.categoryRepository.findChildren(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<Category> findAllChildren(final int id) {

		Assert.isTrue(id != 0);

		final Collection<Category> children = this.categoryRepository.findChildren(id);
		for (final Category category : children)
			children.addAll(this.categoryRepository.findChildren(category.getId()));

		Assert.notNull(children);

		return children;
	}

	public Category findFather(final int id) {

		Assert.isTrue(id != 0);

		final Category res = this.categoryRepository.findFather(id);
		Assert.notNull(res);

		return res;
	}

	public Category findRoot() {

		final Category res = this.categoryRepository.findRoot();
		Assert.notNull(res);

		return res;
	}

	public Category save(final Category c) {

		Assert.notNull(c);

		this.administratorService.findByPrincipal();

		return this.categoryRepository.save(c);
	}

	public void delete(final Category c) {

		Assert.notNull(c);
		Assert.isTrue(c.getId() != 0);

		Assert.isTrue(!c.getName().equals("CATEGORY"));
		Assert.isTrue(!c.getFather().equals(null));

		this.administratorService.findByPrincipal();

		//If the deleted category was referenced by some conference, we need to set the category of all that conferences with the root category

		final Collection<Category> categories = this.findAllChildren(c.getId());

		categories.add(c);

		final Collection<Conference> conferences = new ArrayList<Conference>();

		for (final Category category : categories)
			conferences.addAll(category.getConferences());

		final Category father = this.findFather(c.getId());
		father.setConferences(conferences);

		this.deleteAll(categories);
	}

	private void deleteAll(final Collection<Category> categories) {

		Assert.notNull(categories);

		for (final Category c : categories)
			this.categoryRepository.delete(c);
	}

	public String findAvgMinMaxStddevConferencesPerCategory() {

		final String res = this.categoryRepository.findAvgMinMaxStddevConferencesPerCategory();
		Assert.notNull(res);

		return res;
	}

}
