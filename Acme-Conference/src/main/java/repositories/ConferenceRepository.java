
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	//Cuando se aclare lo de "description" hay que a�adir esto al final de la query dentro del par�ntesis: or c.description like %?1%
	@Query("select distinct(c) from Conference c where (c.title like %?1% or c.venue like %?1%)")
	Collection<Conference> getConferencesByKeyword(String keyword);

}
