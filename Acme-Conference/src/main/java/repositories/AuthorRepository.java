
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query("select author from Author author where author.userAccount.id = ?1")
	Author findByUserAccountId(int id);

	@Query("select author from Conference conf join conf.submissions subs join subs.author author where conf.id=?1")
	Collection<Author> findSubmittedAuthorByConferenceId(int conferenceId);

	@Query("select author from Conference conf join conf.registrations regs join regs.author author where conf.id=?1")
	Collection<Author> findRegisteredAuthorByConferenceId(int conferenceId);

}
