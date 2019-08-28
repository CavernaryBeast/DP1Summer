
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	@Query("select min( 1.0 * (select distinct count(r) from Conference c join c.registrations r where c.id = c1.id)) , max(1.0*(select distinct count(r) from Conference c join c.registrations r where c.id = c1.id)),  avg(1.0*(select distinct count(r) from Conference c join c.registrations r where c.id = c1.id)), stddev(1.0*(select distinct count(r) from Conference c join c.registrations r where c.id = c1.id))  from Conference c1")
	String getRegistrationsPerConferenceStats();

	@Query("select distinct r from Registration r where r.author.id = ?1")
	Collection<Registration> findOwn(int id);
}
