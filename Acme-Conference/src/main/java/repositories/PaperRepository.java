
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Paper;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer> {

	@Query("select distinct s.paper from Submission s where s.author MEMBER OF s.paper.authors and s.author.id = ?1")
	Collection<Paper> getAllConferencesByKeyword(int authorId);

}
