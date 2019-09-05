
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.FoletRepository;
import domain.Conference;
import domain.Folet;

@Service
@Transactional
public class FoletService {

	//Managed Repository-----------------------------------------------------

	@Autowired
	FoletRepository			foletRepository;

	//Supporting Services----------------------------------------------------

	@Autowired
	ActorService			actorService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	ConferenceService		conferenceService;

	@Autowired
	private Validator		validator;


	//CRUD Methods-----------------------------------------------------------

	public Folet create() {
		Folet result;
		this.actorService.findByPrincipal();
		result = new Folet();
		result.setIsFinal(false);
		return result;
	}

	public Collection<Folet> findAll() {
		Collection<Folet> result;
		result = this.foletRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Folet findOne(final int foletId) {
		Assert.isTrue(foletId != 0);
		Folet result;
		result = this.foletRepository.findOne(foletId);
		Assert.notNull(result);
		return result;
	}

	public Folet save(final Folet folet) {
		Assert.notNull(folet);
		final Folet result;
		this.administratorService.findByPrincipal();
		if (folet.getIsFinal()) {
			if (folet.getId() != 0) {
				final Folet savedFolet = this.findOne(folet.getId());
				Assert.isTrue(savedFolet.getIsFinal().equals(false), "The folet was already in final mode");
			}
			final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd  hh:mm");
			final Date now = new Date(System.currentTimeMillis() - 1);
			folet.setPublicationMoment(now);
		}

		result = this.foletRepository.save(folet);

		return result;
	}
	public void delete(final int foletId, final int conferenceId) {
		Assert.notNull(foletId);
		Assert.isTrue(foletId != 0);
		Assert.isTrue(this.foletRepository.exists(foletId));
		//	this.conferenceService.checkAuditId(auditId);
		this.actorService.findByPrincipal();
		final Folet foletEnBaseDeDatos = this.findOne(foletId);
		Assert.isTrue(!foletEnBaseDeDatos.getIsFinal());
		this.foletRepository.delete(foletEnBaseDeDatos);
	}

	//Ancilliary Methods-----------------------------------------------------

	public Folet reconstruct(final Folet folet, final int conferenceId, final BindingResult binding) {
		final Folet result;

		if (folet.getId() == 0) {
			//			result = folet;
			final DateFormat dateFormat = new SimpleDateFormat("yy-MMdd-");
			final Date now = new Date(System.currentTimeMillis() - 1);
			final String ticker = dateFormat.format(now).toString();

			//			final int year = Calendar.getInstance().get(Calendar.YEAR);
			//			final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			//			final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			//			
			//	ticker = ""
			folet.setTicker(ticker);
			//	this.auditService.checkAuditId(auditId);
			final Conference conference = this.conferenceService.findOne(conferenceId);
			folet.setConference(conference);
		} else {
			final Folet original = this.foletRepository.findOne(folet.getId());
			folet.setId(original.getId());
			folet.setVersion(original.getVersion());
			folet.setTicker(original.getTicker());
			folet.setConference(original.getConference());
		}
		this.validator.validate(folet, binding);

		return folet;
	}

	public Collection<Folet> getFoletsByConferencetId(final int auditId) {
		Collection<Folet> result;
		result = this.foletRepository.getFoletsByConferencetId(auditId);
		Assert.notNull(result, "The list of the folets by auditId can't be null");
		return result;
	}

}
