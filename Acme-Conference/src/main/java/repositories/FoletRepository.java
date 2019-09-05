
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folet;

@Repository
public interface FoletRepository extends JpaRepository<Folet, Integer> {

	@Query("select f from Folet f where f.conference.id = ?1")
	Collection<Folet> getFoletsByConferencetId(int conferenceId);

	@Query("select f from Folet f where f.isFinal = 1 and f.conference.id = ?1")
	Collection<Folet> getPublicFolets(int conferenceId);

}
