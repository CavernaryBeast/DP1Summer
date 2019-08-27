
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RegistrationRepository;

@Transactional
@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository	registrationRepository;

	@Autowired
	private AdministratorService	administratorService;


	public String getRegistrationsPerConferenceStats() {
		String result;
		this.administratorService.findByPrincipal();
		result = this.registrationRepository.getRegistrationsPerConferenceStats();
		Assert.notNull(result);
		return result;
	}

}
