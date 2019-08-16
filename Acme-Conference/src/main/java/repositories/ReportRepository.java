
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select report from Report report join report.reviewer reviewer where reviewer.userAccount.id = ?1")
	Collection<Report> findByReviewer(int id);

	@Query("select report from Report report join report.comments comments where comments.id = ?1")
	Report findByComment(int commentId);

	@Query("select distinct r from Report r where r.submission.id = ?1")
	Collection<Report> findBySubmissionId(int submissionId);

}
