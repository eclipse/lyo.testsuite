package org.eclipse.lyo.testsuite.oslcv2.cm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.lyo.testsuite.oslcv2.InvalidateOSLCPropertiesRdfTestBase;
import org.eclipse.lyo.testsuite.server.util.OSLCConstants;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class InvalidateOSLCPropertiesRdfXmlTest extends InvalidateOSLCPropertiesRdfTestBase {

	public InvalidateOSLCPropertiesRdfXmlTest(String thisUrl) {
		super(thisUrl);
	}

	@Parameters
	public static Collection<Object[]> getAllDescriptionUrls()
			throws IOException {
		
		staticSetup();
		
		ArrayList<String> capabilityURLsUsingRdfXml = new ArrayList<String>();
		String useThisCapability = setupProps.getProperty("useThisCapability");
		
		if ( useThisCapability != null ) {
			capabilityURLsUsingRdfXml.add(useThisCapability);
		}
		else {
			ArrayList<String> serviceUrls = getServiceProviderURLsUsingRdfXml(setupProps.getProperty("baseUri"),
				onlyOnce);
			String [] types = getCreateTemplateTypes();
			capabilityURLsUsingRdfXml = getCapabilityURLsUsingRdfXml(OSLCConstants.CREATION_PROP,
						serviceUrls, useDefaultUsageForCreation, types);
		}
		
		return toCollection(capabilityURLsUsingRdfXml);
	}	

	@Override
    public String getContentType() {
	    return OSLCConstants.CT_RDF;
    }

	@Override
    public String getCreateContent() throws IOException {
	    return getCreateContent(rdfXmlCreateTemplate);
    }
	
	@Override
    public String getUpdateContent(String resourceUri) throws IOException {
	    return getUpdateContent(resourceUri, rdfXmlUpdateTemplate);
    }
}