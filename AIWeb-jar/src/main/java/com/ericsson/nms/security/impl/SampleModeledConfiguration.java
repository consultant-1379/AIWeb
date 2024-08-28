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
package com.ericsson.nms.security.impl;

import com.ericsson.oss.itpf.sdk.modeling.annotation.DefaultValue;
import com.ericsson.oss.itpf.sdk.modeling.annotation.DefaultValues;
import com.ericsson.oss.itpf.sdk.modeling.config.annotation.ConfigurationParameterScope;
import com.ericsson.oss.itpf.sdk.modeling.config.annotation.ModeledConfigurationDefinition;
import com.ericsson.oss.itpf.sdk.modeling.config.annotation.ModeledConfigurationParameter;

@ModeledConfigurationDefinition
public class SampleModeledConfiguration {
	
	@ModeledConfigurationParameter(name = "testNumber", description = "sample test property")
	@DefaultValue("123456")
	public int intProp;

	@ModeledConfigurationParameter(name = "intArrayProp", description = "sample test property")
	@DefaultValues({ @DefaultValue("11"), @DefaultValue("12"), @DefaultValue("13"), @DefaultValue("14")})
	public int[] intArrayProp;

	@ModeledConfigurationParameter(name = "boolProp", description = "sample test property", scope = ConfigurationParameterScope.GLOBAL)
	@DefaultValue("true")
	public boolean boolProp;

	@ModeledConfigurationParameter(name = "stringArrayProp", description = "sample test property")
	@DefaultValues({ @DefaultValue("orange"), @DefaultValue("pear"), @DefaultValue("mango"), @DefaultValue("chicken"), @DefaultValue("duck")})
	public String[] stringArrayProp;

	@ModeledConfigurationParameter(name = "stringProp", description = "sample test property", scope = ConfigurationParameterScope.GLOBAL)
	@DefaultValue("apple")
	public String stringProp;
}
