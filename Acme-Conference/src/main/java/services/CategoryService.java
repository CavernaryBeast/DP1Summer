
package services;

import java.util.Collection;
import java.util.HashSet;

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

	@Autowired
	private ConferenceService		conferenceService;


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

	//	public Category findAllChildren(final int id) {
	//
	//		Assert.isTrue(id != 0);
	//
	//		final Category father = this.findFather(id);
	//		final Category fatherToSave = this.findAllChildrenAlt(id, father);
	//
	//		return fatherToSave;
	//	}
	//	public Category findAllChildrenAlt(final int id, final Category father) {
	//
	//		Assert.isTrue(id != 0);
	//
	//		final Category first = this.findOne(id);
	//
	//		for (final Conference conf : first.getConferences()) {
	//			first.getConferences().remove(conf);
	//			father.getConferences().add(conf);
	//		}
	//
	//		final Collection<Category> aux = this.categoryRepository.findChildren(id);
	//		if (!aux.isEmpty())
	//			for (final Category category : aux)
	//				//father.getConferences().addAll(category.getConferences());
	//				this.findAllChildrenAlt(category.getId(), father);
	//		else
	//			this.categoryRepository.delete(first);
	//		if (this.categoryRepository.exists(first.getId()))
	//			this.categoryRepository.delete(first);
	//
	//		return father;
	//	}

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

		if (c.getFather() == null)
			Assert.isTrue(c.getName().equals("CONFERENCE"));

		this.administratorService.findByPrincipal();

		return this.categoryRepository.save(c);
	}

	public void delete(final Category category) {

		final Collection<Conference> conferences = new HashSet<>();

		final Category parent = this.findFather(category.getId());

		final Collection<Conference> aux = this.delete(category, conferences);

		parent.setConferences(aux);
		this.save(parent);
	}

	public Collection<Conference> delete(final Category category, final Collection<Conference> conferences) {

		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);
		Assert.isTrue(!category.getName().equals("CONFERENCE"));

		final Collection<Category> childs = this.findChildren(category.getId());
		//		final Category parent = category.getFather();

		//All the conferences that have this category
		conferences.addAll(this.conferenceService.findConferencesByCategoryId(category.getId()));

		//all conferences that have a child category of this category
		if (!childs.isEmpty()) {
			final Collection<Category> copy = new HashSet<>();
			copy.addAll(childs);
			for (final Category c : copy) {
				conferences.addAll(this.conferenceService.findConferencesByCategoryId(category.getId()));
				childs.remove(c);
				this.delete(c, conferences);
			}
		}
		this.categoryRepository.delete(category);
		return conferences;
	}

	public String findAvgMinMaxStddevConferencesPerCategory() {

		final String res = this.categoryRepository.findAvgMinMaxStddevConferencesPerCategory();
		Assert.notNull(res);

		return res;
	}

}
