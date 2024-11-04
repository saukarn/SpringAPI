package com.conduent.siebel.usps.address.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.conduent.siebel.usps.address.jaxb.AddressVerifyRequest;
import com.conduent.siebel.usps.address.jaxb.AddressVerifyResponse;
import com.conduent.siebel.usps.address.service.AddressVerificationService;
import javax.xml.bind.JAXBElement;

@Endpoint
public class AddressVerifyEndpoint {
	
	Logger logger = LoggerFactory.getLogger(AddressVerifyEndpoint.class);
	
	private static final String NAMESPACE = "urn:callcenter.eppic.tps.com";
	@Autowired
	private AddressVerificationService service;
	
	@PayloadRoot(namespace = "urn:callcenter.eppic.tps.com", localPart = "AddressVerifyRequest")
	@ResponsePayload
	public AddressVerifyResponse getAddressCheck(@RequestPayload AddressVerifyRequest request,MessageContext messageContext) {
		
		//System.out.print("Reached into.");
		logger.info("Reached Inside Endpoint call.");
		return service.checkAddressVerify(request,messageContext);
	}
	
}
