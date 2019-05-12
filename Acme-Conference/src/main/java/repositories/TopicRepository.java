
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

	@Query("select topic from Topic topic where topic.name = ?1")
	Topic findByName(String name);

}
