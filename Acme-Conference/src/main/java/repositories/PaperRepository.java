
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

	@Query("select distinct s.paper from Conference c join c.submissions s where c.id = ?1 and s.paper.cameraReady = 1 and s.paper.id NOT IN (select a.paper.id from Activity a where a.type like 'PRESENTATION' and a.id IN (select a1.id from Conference c1 join c1.activities a1 where c1.id= ?1 ))")
	Collection<Paper> findPapersSubmissionsAcceptedWithPaperCameraReadyFromConference(int conferenceId);

}
