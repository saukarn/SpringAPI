package com.conduent.siebel.usps.address.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}

	@Bean(name = "AddressVerify")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema addressVerifySchema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("AddressVerify");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace("urn:callcenter.eppic.tps.com");
		defaultWsdl11Definition.setSchema(addressVerifySchema);
		return defaultWsdl11Definition;

	}

	@Bean
	public XsdSchema addressVerifySchema() {
		return new SimpleXsdSchema(new ClassPathResource("AddressVerify.xsd"));
	}
	
	/*
	 * @Bean public WebClient.Builder webClientBuilder() { return
	 * WebClient.builder(); }
	 */
	


}
