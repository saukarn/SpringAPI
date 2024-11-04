package com.conduent.siebel.usps.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.conduent.siebel.usps.address.usps.OauthRequest;
import com.conduent.siebel.usps.address.usps.OauthResponse;

@Service
public class OauthService {

	
	 //private  WebClient webClient = null;

		/*
		 * public OauthService(WebClient.Builder webClientBuilder) { this.webClient =
		 * webClientBuilder.baseUrl("https://api.usps.com").build(); }
		 */
  
	   
	   public OauthResponse getOauthToken(OauthRequest req) {
		   String baseUrl = "https://api.usps.com/oauth2/v3/token"; 	
		   OauthResponse response = WebClient.create(baseUrl).post()
				   .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				   .bodyValue("{\"client_id\": \"SgZ1N2e5CLnZGqf7w0P7KGvLQtfv84WU\", \"client_secret\": \"    \"client_secret\": \"qbg3UMcBysIIpJOG\",\r\n"
				   		+ "\", \"grant_type\": \"client_credentials\"}")
				   .retrieve()
				   .bodyToMono(OauthResponse.class).block();
		   			
		   return response;	    
		   
	   }
	    
		/*
		 * public Mono<String> getAccessToken() { return this.webClient.post()
		 * .uri("/oauth2/v3/token") .header("Content-Type", "application/json")
		 * .bodyValue("{\"client_id\": \"fjhdfhskjffdsf\", \"client_secret\": \"sdfsdfsdfdsfsfd\", \"grant_type\": \"client_credentials\"}"
		 * ) .retrieve() .bodyToMono(OauthResponse.class)
		 * .map(TokenResponse::getAccessToken); }
		 */
}
