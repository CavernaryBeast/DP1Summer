
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

	@Query("select distinct s from Submission s where s.author.id = ?1")
	Collection<Submission> findOwn(int id);

	@Query("select distinct s from Conference c join c.submissions s where c.id = ?1 and s.status like 'UNDER-REVIEW'")
	Collection<Submission> findUnderReviewSubmissionsFromConference(int conferenceId);

	@Query("select distinct s from Conference c join c.submissions s where c.id = ?1 and s.paper.cameraReady = 1 and s.paper.id NOT IN (select a.paper.id from Activity a where a.type like 'PRESENTATION' and a.id IN (select a1.id from Conference c1 join c1.activities a1 where c1.id= ?1 ))")
	Collection<Submission> findSubmissionsAcceptedWithPaperCameraReadyFromConference(int conferenceId);

}
