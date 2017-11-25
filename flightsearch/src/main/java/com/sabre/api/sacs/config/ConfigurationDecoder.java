package com.sabre.api.sacs.config;

/**
 * Interface for decoding encrypted configuration properties.
 */
public interface ConfigurationDecoder {

    /**
     * Decodes encoded string.
     * @param encoded encoded string.
     * @return decoded string.
     */
	String decode(String encoded);
}
