package com.conduent.siebel.usps.address.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest.Headers;
import org.springframework.ws.context.MessageContext;

import com.conduent.siebel.usps.address.config.WebClientConfig;
import com.conduent.siebel.usps.address.endpoint.AddressVerifyEndpoint;
import com.conduent.siebel.usps.address.jaxb.AddressVerifyRequest;
import com.conduent.siebel.usps.address.jaxb.AddressVerifyResponse;
import com.conduent.siebel.usps.address.usps.OauthRequest;
import com.conduent.siebel.usps.address.usps.OauthResponse;
import com.conduent.siebel.usps.address.usps.USPSAddressObject;
import com.conduent.siebel.usps.address.usps.USPSResponse;
import com.conduent.siebel.usps.address.util.AddressVerifyUtil;

@Service
public class AddressVerificationService {

	// private final WebClient webClient = null;

	// private WebClient webClient;

	@Autowired
	private WebClient webClient;

	private OauthService oauthService;
	Logger logger = LoggerFactory.getLogger(AddressVerificationService.class);

	@Value("${isProxyEnabled}")
	private Boolean isProxyEnabled;
	@Value("${oauth.client_id}")
	private String clientId;
	@Value("${oauth.client_secret}")
	private String clientSecret;

	public AddressVerifyResponse checkAddressVerify(AddressVerifyRequest request, MessageContext messageContext) {
		Logger logger = LoggerFactory.getLogger(AddressVerifyEndpoint.class);
		String streetAddressResp = "";
		String secondaryAddressResp = "";
		String cityResp = "";
		String stateResp = "";
		String zipcodeResp = "";
		String zipcodePlusResp = "";

		logger.info("Inside Service");

		AddressVerifyResponse addressVerifyResponse = new AddressVerifyResponse();
		String streetAdress = request.getStreetAddress();
		String streetAdress2 = request.getSecondaryAddress();
		String city = request.getCity();
		String state = request.getState();
		// String zipcode = request.getZIPCode();
		String zipcode = request.getZIPCode();

		String zipplus4 = request.getZIPPlus4();
		// adding same resaponse as request to test. Make API cal afterwards
		if (streetAdress != null && city != null && state != null) {

			// getBearer token
			String baseUrl = "https://api.usps.com/oauth2/v3/token";
			Map<String, String> requestBody = Map.of(
				    "client_id", clientId,
				    "client_secret", clientSecret,
				    "grant_type", "client_credentials"
				);

			String bearerToken = "";
			if (isProxyEnabled) {
				
				OauthResponse response = webClient.post().uri("/oauth2/v3/token").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.bodyValue(requestBody)
						.retrieve().bodyToMono(OauthResponse.class).block();
				System.out.println(response);
				String accessToken = response.getAccess_token();
				logger.info("The value of access token is :" + accessToken);

				USPSResponse uspsAddressObject = webClient.get().uri("/addresses/v3/address?streetAddress={streetAddress}&secondaryAddress={streetAdress2}&city={city}&state={state}&ZIPCode={zipcode}",
						streetAdress,streetAdress2, city, state, zipcode)
						.headers(headers -> headers.setBearerAuth(accessToken))
						.retrieve()
						.onStatus(status -> status.value() != 200, ClientResponse::createException)
						.bodyToMono(USPSResponse.class)
						.doOnSuccess(body -> logger.info("Response Body: " + body))
			            .doOnError(e -> System.out.println("Error: " + e.getMessage()))
						.block();

				streetAddressResp = uspsAddressObject.getAddress().getStreetAddress();
				secondaryAddressResp = uspsAddressObject.getAddress().getSecondaryAddress();
				cityResp = uspsAddressObject.getAddress().getCity();
				stateResp = uspsAddressObject.getAddress().getState();
				zipcodeResp = uspsAddressObject.getAddress().getZipcode();
				zipcodePlusResp = uspsAddressObject.getAddress().getZipPlus4();
			} else {
				OauthResponse response = WebClient.create(baseUrl).post().accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.bodyValue(requestBody)
						.retrieve().bodyToMono(OauthResponse.class).block();
				System.out.println(response);
				String accessToken = response.getAccess_token();
				logger.info("The value of access token is :" + accessToken);
				// bearerToken = "Bearer "+accessToken;
				// logger.info("NonProxy:The value of Brearer token is :" + bearerToken);
				WebClient.Builder builder = WebClient.builder();
				USPSResponse uspsAddressObject = builder.build()
						.get()
						.uri("https://api.usps.com/addresses/v3/address?streetAddress={streetAddress}&secondaryAddress={streetAdress2}&city={city}&state={state}&ZIPCode={zipcode}",streetAdress,streetAdress2, city, state, zipcode)
						.headers(headers -> headers.setBearerAuth(accessToken))
						.retrieve()
						.bodyToMono(USPSResponse.class)
						.block();
					logger.info("uspsAddressObject object{}:", uspsAddressObject);			


				streetAddressResp = uspsAddressObject.getAddress().getStreetAddress();
				secondaryAddressResp = uspsAddressObject.getAddress().getSecondaryAddress();
				cityResp = uspsAddressObject.getAddress().getCity();
				stateResp = uspsAddressObject.getAddress().getState();
				zipcodeResp = uspsAddressObject.getAddress().getZipcode();
				zipcodePlusResp = uspsAddressObject.getAddress().getZipPlus4();
			}
			// return response;

			/*
			 * WebClient.Builder builder = WebClient.builder(); USPSResponse
			 * uspsAddressObject= builder.build() .get() .uri(
			 * "https://api.usps.com/addresses/v3/address?streetAddress={streetAddress}&city={city}&state={state}&ZIPCode={zipcode}",
			 * streetAdress, city, state, zipcode) .headers(headers ->
			 * headers.setBearerAuth(accessToken)) .retrieve()
			 * .bodyToMono(USPSResponse.class) .block();
			 * logger.info("USPSResponse object: {}", uspsAddressObject);
			 * 
			 * String uspsAddressObject.getAddress().getStreetAddress(); String
			 * secondaryAddressResp = uspsAddressObject.getAddress().getSecondaryAddress();
			 * String cityResp = uspsAddressObject.getAddress().getCity(); String stateResp
			 * = uspsAddressObject.getAddress().getState(); String zipcodeResp =
			 * uspsAddressObject.getAddress().getZIPCode(); String zipcodePlusResp =
			 * uspsAddressObject.getAddress().getZIPPlus4();
			 */

			addressVerifyResponse.setStreetAddress(streetAddressResp);
			addressVerifyResponse.setSecondaryAddress(secondaryAddressResp);
			addressVerifyResponse.setCity(cityResp);
			addressVerifyResponse.setState(stateResp);
			addressVerifyResponse.setZIPCode(zipcodeResp);
			addressVerifyResponse.setZIPPlus4(zipcodePlusResp);

			// String soapMessageXml = AddressVerifyUtil.extractSoapMessage(messageContext);
			// logger.trace("ExtratecSoap:-", soapMessageXml);
			// String xmlMessageString = AddressVerifyUtil.extractXmlMessage(soapMessageXml,
			// zipplus4, soapMessageXml)

		}
		return addressVerifyResponse;

	}

	/*
	 * public OauthRequest setOauthReqBody(String streetAdress, String
	 * streetAdress2) {
	 * 
	 * OauthService oauthService = new
	 * 
	 * 
	 * }
	 */

}
