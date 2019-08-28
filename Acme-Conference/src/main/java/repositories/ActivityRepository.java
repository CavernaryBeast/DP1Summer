
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	@Query("select distinct a from Conference c join c.activities a where c.id = ?1 and a.type like 'PRESENTATION'")
	Collection<Activity> findPresentationsFromConference(int conferenceId);

	@Query("select distinct a from Conference c join c.activities a where c.id = ?1 and a.type like 'TUTORIAL'")
	Collection<Activity> findTutorialsFromConference(int conferenceId);

	@Query("select distinct a from Conference c join c.activities a where c.id = ?1 and a.type like 'PANEL'")
	Collection<Activity> findPanelsFromConference(int conferenceId);

}
