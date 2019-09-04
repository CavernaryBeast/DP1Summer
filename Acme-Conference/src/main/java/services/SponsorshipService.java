
package services;

import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SponsorshipRepository;
import domain.CreditCard;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	@Autowired
	private SponsorService			sponsorService;

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private Validator				validator;


	public Sponsorship create() {
		final Sponsor sponsor = this.sponsorService.findByPrincipal();
		final Sponsorship res = new Sponsorship();
		final CreditCard creditCard = new CreditCard();
		res.setCreditCard(creditCard);
		res.setSponsor(sponsor);
		return res;
	}

	public Collection<Sponsorship> findAll() {
		this.sponsorService.findByPrincipal();
		final Collection<Sponsorship> res = this.sponsorshipRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Sponsorship findOne(final int id) {
		this.sponsorService.findByPrincipal();
		Assert.isTrue(id != 0);
		Assert.notNull(id);
		Assert.isTrue(this.exist(id));
		final Sponsorship res = this.sponsorshipRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Sponsorship save(final Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		final Sponsor sponsor = this.sponsorService.findByPrincipal();
		final Sponsorship saved;
		if (sponsorship.getId() == 0) {
			final CreditCard creditCard = sponsorship.getCreditCard();
			creditCard.setOwner(sponsor);
			final CreditCard savedC = this.creditCardService.save(creditCard);
			sponsorship.setCreditCard(savedC);
		} else {
			final CreditCard c = sponsorship.getCreditCard();
			c.setOwner(sponsor);
			this.creditCardService.save(c);

		}
		saved = this.sponsorshipRepository.save(sponsorship);
		Assert.notNull(saved);
		return saved;
	}
	public void delete(final int sponsorshipId) {
		Assert.notNull(sponsorshipId);
		Assert.isTrue(sponsorshipId != 0);
		this.sponsorService.findByPrincipal();
		this.sponsorshipRepository.delete(sponsorshipId);
	}

	public boolean exist(final int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);
		final boolean res = this.sponsorshipRepository.exists(id);
		Assert.notNull(res);

		return res;
	}

	public Sponsorship randomSponsorShip(final Collection<Sponsorship> sponsorships) {
		Assert.notNull(sponsorships);
		Assert.isTrue(!sponsorships.isEmpty());
		Sponsorship result;
		final Random r = new Random();
		final int i = r.nextInt(sponsorships.size());
		result = (Sponsorship) sponsorships.toArray()[i];
		return result;
	}

	public Collection<Sponsorship> findSponsorshipsFromConferenceId(final int conferenceId) {
		Assert.notNull(conferenceId);
		Assert.isTrue(conferenceId != 0);
		Collection<Sponsorship> result;
		result = this.sponsorshipRepository.findSponsorshipsFromConferenceId(conferenceId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Sponsorship> findSponsorshipsFromSponsorId(final int sponsorId) {
		Assert.notNull(sponsorId);
		Assert.isTrue(sponsorId != 0);
		Collection<Sponsorship> result;
		result = this.sponsorshipRepository.findSponsorshipsFromSponsorId(sponsorId);
		Assert.notNull(result);
		return result;
	}

	public Sponsorship reconstruct(final Sponsorship sponsorship, final BindingResult binding) {
		Sponsorship original;
		final Sponsor principal = this.sponsorService.findByPrincipal();

		if (sponsorship.getId() == 0)
			sponsorship.setSponsor(principal);
		else {
			original = this.findOne(sponsorship.getId());
			sponsorship.setId(original.getId());
			sponsorship.setVersion(original.getVersion());
			sponsorship.setSponsor(original.getSponsor());
		}
		this.validator.validate(sponsorship, binding);
		return sponsorship;

	}

}
