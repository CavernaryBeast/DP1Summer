
package services;

import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	@Autowired
	private SponsorService			sponsorService;


	public Sponsorship create() {
		this.sponsorService.findByPrincipal();
		final Sponsorship res = new Sponsorship();
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
		this.sponsorService.findByPrincipal();
		Sponsorship saved;
		saved = this.sponsorshipRepository.save(sponsorship);
		Assert.notNull(saved);
		return sponsorship;
	}

	public void delete(final Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		this.sponsorService.findByPrincipal();
		this.sponsorshipRepository.delete(sponsorship);
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

}
