
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select min(c.comments.size), max(c.comments.size), avg(c.comments.size), stddev(c.comments.size) from Conference c")
	String findAvgMinMaxStddevConferenceComments();

	@Query("select min(a.comments.size), max(a.comments.size), avg(a.comments.size), stddev(a.comments.size) from Activity a")
	String findAvgMinMaxStddevActivityComments();

}
