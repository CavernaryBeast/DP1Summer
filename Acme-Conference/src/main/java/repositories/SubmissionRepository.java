
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

}
