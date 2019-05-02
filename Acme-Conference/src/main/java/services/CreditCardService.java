
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.CreditCard;
import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository			creditCardRepository;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	public CreditCard create() {

		CreditCard card;

		final Actor principal = this.actorService.findByPrincipal();

		card = new CreditCard();

		card.setOwner(principal);

		return card;
	}

	public Collection<CreditCard> findAll() {

		final Collection<CreditCard> res = this.creditCardRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public CreditCard findOne(final int id) {

		Assert.isTrue(id != 0);

		final CreditCard res = this.creditCardRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public CreditCard save(final CreditCard card) {

		Assert.notNull(card);

		CreditCard saved;

		final Collection<String> makes = this.configurationParametersService.getCreditCardMakes();
		Assert.isTrue(makes.contains(card.getMake()));

		saved = this.creditCardRepository.save(card);

		return saved;
	}

	public void delete(final CreditCard card) {

		Assert.notNull(card);

		final Actor principal = this.actorService.findByPrincipal();

		Assert.isTrue(card.getOwner().equals(principal));

		this.creditCardRepository.delete(card);
	}

	public Collection<CreditCard> findOwn() {

		final Actor principal = this.actorService.findByPrincipal();

		final Collection<CreditCard> res = this.creditCardRepository.findOwn(principal.getId());
		Assert.notEmpty(res);

		return res;
	}

}
