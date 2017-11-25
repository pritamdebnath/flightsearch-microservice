package com.sabre.api.sacs.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Component;

/**
 * Class reading REST and SOAP config.
 */
@Component
public class SacsConfiguration {

	private Configuration restConfig;

	/**
	 * Initializes configuration objects with values read from the properties
	 * files.
	 * 
	 * @throws ConfigurationException
	 */
	public SacsConfiguration() throws ConfigurationException {
		restConfig = new PropertiesConfiguration("SACSRestConfig.properties");
	}

	/**
	 * Returns a value for the REST configuration key.
	 * 
	 * @param key
	 *            key.
	 * @return value stored under given key.
	 */
	public String getRestProperty(String key) {
		return restConfig.getString(key);
	}

}
