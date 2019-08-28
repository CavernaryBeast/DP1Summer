
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

	@Query("select sub from Submission sub join sub.reviewers revs where revs.id = ?1 AND sub not in (select sub1 from Report r join r.submission sub1 where r.reviewer.id = ?1")
	Collection<Submission> findSubmissionsToReview(int reviewerId);
	@Query("select distinct s from Conference c join c.submissions s where c.id = ?1 and s.status like 'ACCEPTED'")
	Collection<Submission> findAcceptedSubmissionsFromConference(int conferenceId);

	@Query("select min( 1.0 * (select distinct count(s) from Conference c join c.submissions s where c.id = c1.id)) , max(1.0*(select distinct count(s) from Conference c join c.submissions s where c.id = c1.id)),  avg(1.0*(select distinct count(s) from Conference c join c.submissions s where c.id = c1.id)), stddev(1.0*(select distinct count(s) from Conference c join c.submissions s where c.id = c1.id))  from Conference c1")
	String getSubmissionsPerConferenceStats();

	@Query("select distinct s from Conference c join c.submissions s where c.id = ?1 and s.status like 'REJECTED'")
	Collection<Submission> findRejectedSubmissionsFromConference(int conferenceId);

	@Query("select distinct s.paper from Conference c join c.submissions s where c.id = ?1 and s.paper.cameraReady = 1 and s.paper.id NOT IN (select a.paper.id from Activity a where a.type like 'PRESENTATION' and a.id IN (select a1.id from Conference c1 join c1.activities a1 where c1.id= ?1 ))")
	Collection<Submission> findSubmissionsAcceptedWithPaperCameraReadyFromConference(int conferenceId);

}
