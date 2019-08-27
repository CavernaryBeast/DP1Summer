
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	//Cuando se aclare lo de "description" hay que añadir esto al final de la query dentro del paréntesis: or c.description like %?1%
	@Query("select distinct(c) from Conference c where (c.title like %?1% or c.venue like %?1% or c.summary like %?1%)and c.isFinal = 1")
	Collection<Conference> getAllConferencesByKeyword(String keyword);

	@Query("select distinct(c) from Conference c where (c.title like %?1% or c.venue like %?1% or c.summary like %?1%) and  c.startDate > CURRENT_TIMESTAMP and c.isFinal = 1")
	Collection<Conference> getForthcomingConferencesByKeyword(String keyword);

	@Query("select distinct(c) from Conference c where (c.title like %?1% or c.venue like %?1% or c.summary like %?1%) and  c.startDate < CURRENT_TIMESTAMP and c.endDate > CURRENT_TIMESTAMP and c.isFinal = 1")
	Collection<Conference> getRunningConferencesByKeyword(String keyword);

	@Query("select distinct(c) from Conference c where (c.title like %?1% or c.venue like %?1% or c.summary like %?1%) and  c.endDate < CURRENT_TIMESTAMP and c.isFinal = 1")
	Collection<Conference> getPastConferencesByKeyword(String keyword);

	@Query("select c from Conference c where datediff(c.submissionDeadline, CURRENT_DATE) between -5 and -1")
	Collection<Conference> getConferencesSubmissionDeadlineLastFiveDays();

	@Query("select c from Conference c where datediff(c.notificationDeadline, CURRENT_DATE) between 0 and 4")
	Collection<Conference> getConferencesNotificationDeadlineInLessFiveDays();

	@Query("select c from Conference c where datediff(c.cameraReadyDeadline, CURRENT_DATE) between 0 and 4")
	Collection<Conference> getConferencesCameraReadyDeadlineInLessFiveDays();

	@Query("select c from Conference c where datediff(c.startDate, CURRENT_DATE) between 0 and 4")
	Collection<Conference> getConferencesStartDateInLessFiveDays();

	@Query("select distinct(c) from Conference c where  c.isFinal = 1")
	Collection<Conference> getAllConferencesFinalMode();

	@Query("select distinct(c) from Conference c where c.submissionDeadline > CURRENT_TIMESTAMP and c.isFinal = 1")
	Collection<Conference> getConferencesSubmissionDeadlineNotElapsed();

	@Query("select distinct c from Conference c join c.submissions s where s.id = ?1")
	Conference getConferenceFromSubmissionId(int submissionId);

	@Query("select distinct c from Conference c join c.submissions s where s.status like 'UNDER-REVIEW'")
	Collection<Conference> getConferencesWithUnderReviewSubmissions();

	@Query("select distinct c from Conference c join c.submissions s where s.paper.cameraReady = 1 and s.paper.id NOT IN (select a.paper.id from Activity a where a.type like 'PRESENTATION' and a.id IN (select a1.id from Conference c1 join c1.activities a1 ))")
	Collection<Conference> getConferencesAvailablePapersForPresentations();

	@Query("select distinct c from Conference c join c.activities a where a.id = ?1")
	Conference getConferenceFromActivityId(int activityId);

	@Query("select min(c.fee), max(c.fee), avg(c.fee), stddev(c.fee) from Conference c")
	String getFeesPerConferenceStats();

	@Query("select min((c.endDate - c.startDate)/1000000), max((c.endDate - c.startDate)/1000000), avg((c.endDate - c.startDate)/1000000), stddev((c.endDate - c.startDate)/1000000) from Conference c")
	String getDaysPerConferenceStats();

}
