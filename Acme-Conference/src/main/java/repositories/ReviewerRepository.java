
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

	@Query("select rev from Author rev where rev.userAccount.id = ?1")
	Reviewer findByUserAccountId(int id);

	@Query("select distinct r from Reviewer r where r.id NOT IN(select r1.id from Submission s join s.reviewers r1 where s.id = ?1)")
	Collection<Reviewer> selectAvailableAuthorsToAssingToSubmission(int submissionId);

}
