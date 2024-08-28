/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.nms.security.ejb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.*;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.nms.security.impl.DateResolverBean;

@Stateless
public class ServiceBean implements ServiceRemote, ServiceLocal {

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	@Inject
	private Logger logger;

	@Inject
	private DateResolverBean dateResolver;

	/* (non-Javadoc)
	 * @see com.ericsson.oss.tor.com.ericsson.nms.security.api.Service#getDate()
	 */
	@Override
	public String resolveDate() {
		this.logger.debug("In EJB - resolving date");
		final DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(this.dateResolver.resolveCurrentDate());
	}

}
