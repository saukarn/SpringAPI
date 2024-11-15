package com.conduent.siebel.usps.address.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.ws.config.annotation.EnableWs;

import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@EnableWs
@Configuration
public class WebClientConfig {
	
	@Bean
	public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .proxy(proxy -> proxy
                    .type(ProxyProvider.Proxy.HTTP)
                    .host("ip address")
                    .port(port));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://api.usps.com")
                .build();
    }

}
