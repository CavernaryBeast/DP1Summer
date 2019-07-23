
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ConfigurationParameters;

@Repository
public interface ConfigurationParametersRepository extends JpaRepository<ConfigurationParameters, Integer> {

	@Query("select sysName from ConfigurationParameters confParams")
	String getSysName();

	@Query("select banner from ConfigurationParameters confParams")
	String getBanner();

	@Query("select message from ConfigurationParameters confParams")
	String getMessage();

	@Query("select messageEs from ConfigurationParameters confParams")
	String getMessageEs();

	@Query("select countryCode from ConfigurationParameters confParams")
	String getCountryCode();

	@Query("select makes from ConfigurationParameters confParams join confParams.creditCardMakes makes")
	String getDefaultCountry();

	@Query("select confParams.creditCardMakes from ConfigurationParameters confParams")
	Collection<String> getCreditCardMakes();

	@Query("select confParams from ConfigurationParameters confParams")
	ConfigurationParameters getConfigurationParameters();

}
