
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int id);

	@Query("select a from Actor a join a.userAccount ua join ua.authorities auths where 'AUTHOR' member of auths")
	Collection<Actor> findAllAuthors();

	@Query("select author from Conference conf join conf.submissions subs join subs.author author where conf.id = ?1")
	Collection<Actor> findSubmittedByConferenceId(int conferenceId);

	@Query("select author from Conference conf join conf.registrations regs join reg.author author where conf.id = ?1")
	Collection<Actor> findRegisteredByConferenceId(int conferenceId);

}
