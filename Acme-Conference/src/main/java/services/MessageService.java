
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Message;
import repositories.MessageRepository;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;


	//El sender se asignara mas adelante
	public Message create() {

		Message mes;
		Date moment;

		mes = new Message();
		moment = new Date(System.currentTimeMillis() - 1);

		mes.setMoment(moment);

		return mes;
	}

	public Collection<Message> findAll() {

		final Collection<Message> res = this.messageRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Message findOne(final int id) {

		Assert.isTrue(id != 0);
		final Message res = this.messageRepository.findOne(id);

		return res;
	}

	public Message save(final Message mes) {

		Assert.notNull(mes);

		Message saved;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		mes.setMoment(moment);

		saved = this.messageRepository.save(mes);

		return saved;
	}

}
