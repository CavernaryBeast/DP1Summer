
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query("select author from Author author where author.userAccount.id = ?1")
	Author findByUserAccountId(int id);

}
