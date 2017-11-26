package com.sabre.api.sacs.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sabre.api.sacs.config.SacsConfiguration;
import com.sabre.api.sacs.rest.common.GenericRestGetCall;
import com.sabre.api.sacs.rest.common.context.SharedContext;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarRequest;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarResponse;

/**
 * Activity to use in workflow. It runs the LeadPriceCalendarActivity.
 */
@RestController
public class LeadPriceCalendar {

	@Autowired
	private SacsConfiguration config;

	@Autowired
	private GenericRestGetCall<LeadPriceCalendarRequest> call;
	final Logger LOG = LogManager.getLogger(LeadPriceCalendar.class);

	public LeadPriceCalendarResponse doCalendarPricing(SharedContext context, String origin, String destination,
			String departureDate, int lengthOfStay, String minFare, String maxFare) {
		LeadPriceCalendarRequest request = new LeadPriceCalendarRequest.Builder().origin(origin)
				.destination(destination).lengthOfStay(lengthOfStay).minFare(minFare).maxFare(maxFare)
				.pointOfSaleCountry("US").build();
		final String endpoint = config.getRestProperty("environment") + "/v2/shop/flights/fares";
		LOG.debug("URL: " + endpoint);
		call.setUrl(endpoint);
		call.setRequest(request);
		return call.doCall(LeadPriceCalendarResponse.class, context);
	}

}
