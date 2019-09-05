
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reckon;

@Repository
public interface ReckonRepository extends JpaRepository<Reckon, Integer> {

	@Query("select r from Reckon r where r.conference.id = ?1")
	Collection<Reckon> getReckonsByConferencetId(int conferenceId);

	@Query("select r from Reckon r where r.isFinal = 1 and r.conference.id = ?1")
	Collection<Reckon> getPublicReckons(int conferenceId);

	@Query("select avg(1.0*(select distinct count(r) from Reckon r join r.conference c where c.id = c1.id)), stddev(1.0*(select distinct count(r) from Reckon r join r.conference c where c.id = c1.id))  from Conference c1")
	String getReckonsPerConferenceStats();

	@Query("select r from Reckon r where r.isFinal = 1")
	Collection<Reckon> getAllPublicReckons();

	@Query("select r from Reckon r where r.isFinal = 0")
	Collection<Reckon> getAllUnPublicReckons();

}
