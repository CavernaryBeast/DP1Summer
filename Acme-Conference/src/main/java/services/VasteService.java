
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

import repositories.VasteRepository;
import domain.Conference;
import domain.Vaste;

@Service
@Transactional
public class VasteService {

	//Managed Repository-----------------------------------------------------

	@Autowired
	VasteRepository			vasteRepository;

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

	public Vaste create() {
		Vaste result;
		this.actorService.findByPrincipal();
		result = new Vaste();
		result.setIsFinal(false);
		return result;
	}

	public Collection<Vaste> findAll() {
		Collection<Vaste> result;
		result = this.vasteRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Vaste findOne(final int vasteId) {
		Assert.isTrue(vasteId != 0);
		Vaste result;
		result = this.vasteRepository.findOne(vasteId);
		Assert.notNull(result);
		return result;

	}

	public Vaste findOneEditable(final int vasteId) {
		Assert.isTrue(vasteId != 0);
		Vaste result;
		result = this.vasteRepository.findOne(vasteId);
		Assert.notNull(result);
		Assert.isTrue(result.getIsFinal().equals(false), "The vaste was already in final mode");
		return result;
	}

	public Vaste save(final Vaste vaste) {
		Assert.notNull(vaste);
		final Vaste result;
		this.administratorService.findByPrincipal();
		if (vaste.getIsFinal()) {
			if (vaste.getId() != 0) {
				final Vaste savedVaste = this.findOne(vaste.getId());
				Assert.isTrue(savedVaste.getIsFinal().equals(false), "The vaste was already in final mode");
			}
			final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd  hh:mm");
			final Date now = new Date(System.currentTimeMillis() - 1);
			vaste.setPublicationMoment(now);
		}

		result = this.vasteRepository.save(vaste);

		return result;
	}
	public void delete(final int vasteId) {
		Assert.notNull(vasteId);
		Assert.isTrue(vasteId != 0);
		Assert.isTrue(this.vasteRepository.exists(vasteId));
		//	this.conferenceService.checkAuditId(auditId);
		this.actorService.findByPrincipal();
		final Vaste vasteEnBaseDeDatos = this.findOne(vasteId);
		Assert.isTrue(!vasteEnBaseDeDatos.getIsFinal(), "The vaste was already in final mode");
		this.vasteRepository.delete(vasteEnBaseDeDatos);
	}

	//Ancilliary Methods-----------------------------------------------------

	public Vaste reconstruct(final Vaste vaste, final int conferenceId, final BindingResult binding) {
		final Vaste result;

		if (vaste.getId() == 0) {
			//			result = vaste;
			final DateFormat dateFormat = new SimpleDateFormat("yy-");

			final DateFormat dateFormat2 = new SimpleDateFormat("-MMdd");
			final String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(2);
			final Date now = new Date(System.currentTimeMillis() - 1);
			final String part1ticker = dateFormat.format(now).toString();
			final String part2ticker = dateFormat2.format(now).toString();
			final String ticker = part1ticker + randomAlphaNumeric + part2ticker;

			//			final int year = Calendar.getInstance().get(Calendar.YEAR);
			//			final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			//			final int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			//			
			//	ticker = ""
			vaste.setTicker(ticker);
			//	this.auditService.checkAuditId(auditId);
			final Conference conference = this.conferenceService.findOne(conferenceId);
			vaste.setConference(conference);
		} else {
			final Vaste original = this.vasteRepository.findOne(vaste.getId());
			vaste.setId(original.getId());
			vaste.setVersion(original.getVersion());
			vaste.setTicker(original.getTicker());
			vaste.setConference(original.getConference());
		}
		this.validator.validate(vaste, binding);

		return vaste;
	}

	public Collection<Vaste> getVastesByConferencetId(final int conferencetId) {
		Collection<Vaste> result;
		result = this.vasteRepository.getVastesByConferencetId(conferencetId);
		Assert.notNull(result, "The list of the vastes by auditId can't be null");
		return result;
	}

	public Collection<Vaste> getPublicVastes(final int auditId) {
		Collection<Vaste> result;
		result = this.vasteRepository.getPublicVastes(auditId);
		Assert.notNull(result, "The list of the vastes by auditId can't be null");
		return result;
	}

	public String getPublishedVastesPerConferenceStats() {
		final String res = this.vasteRepository.getPublishedVastesPerConferenceStats();
		Assert.notNull(res);
		return res;
	}

	public String getRatioOfPublishedVastesVSTotalVastes() {
		final String res = this.vasteRepository.getRatioOfPublishedVastesVSTotalVastes();
		Assert.notNull(res);
		return res;
	}

	public String getRatioOfUnpublishedVastesVSTotalVastes() {
		final String res = this.vasteRepository.getRatioOfUnpublishedVastesVSTotalVastes();
		Assert.notNull(res);
		return res;
	}

}
