
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select mes from Message mes join mes.recipients rec where mes.topic.id = ?1 AND (mes.sender.id = ?2 OR rec.id = ?2)")
	Collection<Message> findByTopicId(int topicId, int id);

	@Query("select mes from Message mes join mes.recipients rec where mes.sender.id = ?1 AND rec.id = ?2")
	Collection<Message> findBySender(int senderId, int principalId);

	@Query("select mes from Message mes join mes.recipients rec where mes.sender = NULL AND rec.id = ?1")
	Collection<Message> findSystemMessages(int principalId);

	@Query("select mes from Message mes join mes.recipients rec where (mes.sender.id = ?2 OR rec.id = ?2) AND rec.id = ?1")
	Collection<Message> findByRecipientId(int recipientId, int principalId);

	@Query("select distinct mes from Message mes join mes.recipients rec where rec.id = ?1")
	Collection<Message> findOwn(int id);

	@Query("select mes from Message mes where mes.topic.id = ?1")
	Collection<Message> findByTopicId2(int id);

}
