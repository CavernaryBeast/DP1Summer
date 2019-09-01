
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.ConfigurationParameters;
import repositories.ConfigurationParametersRepository;

@Service
@Transactional
public class ConfigurationParametersService {

	@Autowired
	private ConfigurationParametersRepository	configurationParametersRepository;

	@Autowired
	private AdministratorService				administratorService;


	public ConfigurationParameters create() {

		ConfigurationParameters confParams;

		confParams = new ConfigurationParameters();

		return confParams;
	}

	public ConfigurationParameters save(final ConfigurationParameters confParams) {

		Assert.notNull(confParams);
		Assert.isTrue(confParams.getId() != 0);

		this.administratorService.findByPrincipal();

		final ConfigurationParameters saved = this.configurationParametersRepository.save(confParams);

		return saved;
	}

	public ConfigurationParameters getConfigurationParameters() {

		this.administratorService.findByPrincipal();

		final ConfigurationParameters res = this.configurationParametersRepository.getConfigurationParameters();
		Assert.notNull(res);

		return res;
	}

	public String getSysName() {

		final String res = this.configurationParametersRepository.getSysName();
		Assert.notNull(res);

		return res;
	}

	public String getBanner() {

		final String res = this.configurationParametersRepository.getBanner();
		Assert.notNull(res);

		return res;
	}

	public String getMessage() {

		final String res = this.configurationParametersRepository.getMessage();
		Assert.notNull(res);

		return res;
	}

	public String getMessageEs() {

		final String res = this.configurationParametersRepository.getMessageEs();
		Assert.notNull(res);

		return res;
	}

	public String getCountryCode() {

		final String res = this.configurationParametersRepository.getCountryCode();
		Assert.notNull(res);

		return res;
	}

	public String getDefaultCountry() {

		final String res = this.configurationParametersRepository.getDefaultCountry();
		Assert.notNull(res);

		return res;
	}

	public Collection<String> getCreditCardMakes() {

		final Collection<String> res = this.configurationParametersRepository.getCreditCardMakes();
		Assert.notEmpty(res);

		return res;
	}

	public Collection<String> getCreditCardMakes2() {

		final Collection<String> res = this.configurationParametersRepository.getCreditCardMakes();
		Assert.notEmpty(res);

		return res;
	}

	public String checkPhoneNumber(String phoneNumber) {

		if (!phoneNumber.startsWith("+") && phoneNumber.length() > 4)
			phoneNumber = this.getConfigurationParameters().getCountryCode() + " " + phoneNumber;

		return phoneNumber;
	}

}
