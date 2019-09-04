
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c join c.father f where f.id=?1")
	Collection<Category> findChildren(Integer id);

	@Query("select c.father from Category c where c.id=?1")
	Category findFather(Integer id);

	@Query("select c from Category c where c.father is null")
	Category findRoot();

	@Query("select min(c.conferences.size), max(c.conferences.size), avg(c.conferences.size), stddev(c.conferences.size) from Category c")
	String findAvgMinMaxStddevConferencesPerCategory();

}
