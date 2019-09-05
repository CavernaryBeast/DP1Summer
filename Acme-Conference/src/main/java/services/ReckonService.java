
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ReckonRepository;
import domain.Conference;
import domain.Reckon;

@Service
@Transactional
public class ReckonService {

	//Managed Repository-----------------------------------------------------

	@Autowired
	ReckonRepository		reckonRepository;

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

	public Reckon create() {
		Reckon result;
		this.actorService.findByPrincipal();
		result = new Reckon();
		result.setIsFinal(false);
		return result;
	}

	public Collection<Reckon> findAll() {
		Collection<Reckon> result;
		result = this.reckonRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Reckon findOne(final int reckonId) {
		Assert.isTrue(reckonId != 0);
		Reckon result;
		result = this.reckonRepository.findOne(reckonId);
		Assert.notNull(result);
		return result;

	}

	public Reckon findOneEditable(final int reckonId) {
		Assert.isTrue(reckonId != 0);
		Reckon result;
		result = this.reckonRepository.findOne(reckonId);
		Assert.notNull(result);
		Assert.isTrue(result.getIsFinal().equals(false), "The reckon was already in final mode");
		return result;
	}

	public Reckon save(final Reckon reckon) {
		Assert.notNull(reckon);
		final Reckon result;
		this.administratorService.findByPrincipal();
		if (reckon.getIsFinal()) {
			if (reckon.getId() != 0) {
				final Reckon savedReckon = this.findOne(reckon.getId());
				Assert.isTrue(savedReckon.getIsFinal().equals(false), "The reckon was already in final mode");
			}
			final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd  hh:mm");
			final Date now = new Date(System.currentTimeMillis() - 1);
			reckon.setPublicationMoment(now);
		}

		result = this.reckonRepository.save(reckon);

		return result;
	}
	public void delete(final int reckonId) {
		Assert.notNull(reckonId);
		Assert.isTrue(reckonId != 0);
		Assert.isTrue(this.reckonRepository.exists(reckonId));
		//	this.conferenceService.checkAuditId(auditId);
		this.actorService.findByPrincipal();
		final Reckon reckonEnBaseDeDatos = this.findOne(reckonId);
		Assert.isTrue(!reckonEnBaseDeDatos.getIsFinal(), "The reckon was already in final mode");
		this.reckonRepository.delete(reckonEnBaseDeDatos);
	}

	//Ancilliary Methods-----------------------------------------------------

	public Reckon reconstruct(final Reckon reckon, final int conferenceId, final BindingResult binding) {
		final Reckon result;

		if (reckon.getId() == 0) {
			//			result = reckon;
			final DateFormat dateFormat = new SimpleDateFormat("yyMM/dd-");
			final Date now = new Date(System.currentTimeMillis() - 1);
			final String ticker = dateFormat.format(now).toString() + RandomStringUtils.randomNumeric(5);

			//			final int year = Calendar.getInstance().get(Calendar.YEAR);
			//			final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			//			final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			//			
			//	ticker = ""
			reckon.setTicker(ticker);
			//	this.auditService.checkAuditId(auditId);
			final Conference conference = this.conferenceService.findOne(conferenceId);
			reckon.setConference(conference);
		} else {
			final Reckon original = this.reckonRepository.findOne(reckon.getId());
			reckon.setId(original.getId());
			reckon.setVersion(original.getVersion());
			reckon.setTicker(original.getTicker());
			reckon.setConference(original.getConference());
		}
		this.validator.validate(reckon, binding);

		return reckon;
	}

	public Collection<Reckon> getReckonsByConferencetId(final int conferencetId) {
		Collection<Reckon> result;
		result = this.reckonRepository.getReckonsByConferencetId(conferencetId);
		Assert.notNull(result, "The list of the reckons by auditId can't be null");
		return result;
	}

	public Collection<Reckon> getPublicReckons(final int auditId) {
		Collection<Reckon> result;
		result = this.reckonRepository.getPublicReckons(auditId);
		Assert.notNull(result, "The list of the reckons by auditId can't be null");
		return result;
	}

	public Collection<Reckon> getAllPublicReckons() {
		Collection<Reckon> result;
		result = this.reckonRepository.getAllPublicReckons();
		Assert.notNull(result, "The list of the reckons can't be null");
		return result;
	}

	public Collection<Reckon> getAllUnPublicReckons() {
		Collection<Reckon> result;
		result = this.reckonRepository.getAllUnPublicReckons();
		Assert.notNull(result, "The list of the reckons can't be null");
		return result;
	}

	public String getReckonsPerConferenceStats() {
		String result;
		this.administratorService.findByPrincipal();
		result = this.reckonRepository.getReckonsPerConferenceStats();
		Assert.notNull(result);
		return result;
	}

	public double getRatioPublishedVSTotal() {

		double res;
		this.administratorService.findByPrincipal();

		final Collection<Reckon> total = this.findAll();
		final Collection<Reckon> published = this.getAllPublicReckons();

		final double totalSize = total.size();
		final double publishedSize = published.size();
		res = publishedSize / totalSize;

		return res;
	}

	public double getRatioUnPublishedVSTotal() {

		double res;
		this.administratorService.findByPrincipal();

		final Collection<Reckon> total = this.findAll();
		final Collection<Reckon> unpublished = this.getAllUnPublicReckons();

		final double totalSize = total.size();
		final double unpublishedSize = unpublished.size();
		res = unpublishedSize / totalSize;

		return res;
	}

}
