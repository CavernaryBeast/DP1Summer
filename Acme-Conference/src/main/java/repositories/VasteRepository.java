
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Vaste;

@Repository
public interface VasteRepository extends JpaRepository<Vaste, Integer> {

	@Query("select v from Vaste v where v.conference.id = ?1")
	Collection<Vaste> getVastesByConferencetId(int conferenceId);

	@Query("select v from Vaste v where v.isFinal = 1 and v.conference.id = ?1")
	Collection<Vaste> getPublicVastes(int conferenceId);

	@Query("select avg(1.0*(select distinct count(v) from Vaste v where v.isFinal = 1 and v.conference.id = c1.id)), stddev(1.0*(select distinct count(v) from Vaste v where v.isFinal = 1 and v.conference.id = c1.id))  from Conference c1")
	String getPublishedVastesPerConferenceStats();

	@Query("select 1.0*count(v1)/(select count(v2) from Vaste v2) from Vaste v1 where v1.isFinal = 1")
	String getRatioOfPublishedVastesVSTotalVastes();

	@Query("select 1.0*count(v1)/(select count(v2) from Vaste v2) from Vaste v1 where v1.isFinal = 0")
	String getRatioOfUnpublishedVastesVSTotalVastes();

}
