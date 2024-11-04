package com.conduent.siebel.usps.address.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.conduent.siebel.usps.address.expection.AddressException;

//import com.conduent.mdes.token.management.exception.MdesException;
//import com.conduent.mdes.token.management.util.TokenManagementUtil;

public class AddressVerifyUtil {
	
	static Logger logger = LoggerFactory.getLogger(AddressVerifyUtil.class);
	
	public static String extractSoapMessage(MessageContext messageContext) {
		SoapMessage soapMessage = (SoapMessage) messageContext.getRequest();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			soapMessage.writeTo(out);
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		} 
		logger.trace("out value:-", out);
		return out.toString();
	}
	
	public static String extractXmlMessage(String xml, String tagName, String newTagName) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			ByteArrayInputStream in = new ByteArrayInputStream(xml.toString().getBytes());
			Document document = builder.parse(in);
			NodeList nodes = document.getElementsByTagName(tagName);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter stringWriter = new StringWriter();
			StreamResult streamResult = new StreamResult(stringWriter);
			
			if (nodes!=null && nodes.getLength()>0) {
				streamResult.getWriter().append("<" + newTagName + ">");
				for (int i=0; i<nodes.getLength(); i++) {
					if (nodes.item(i).getNodeType()!=Node.TEXT_NODE) {
						NodeList chNodes = nodes.item(i).getChildNodes();
						for (int j=0; j<chNodes.getLength(); j++) {
							transformer.transform(new DOMSource(chNodes.item(j)), streamResult);
						}
					}
				}
				streamResult.getWriter().append("</" + newTagName + ">");
				return stringWriter.getBuffer().toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
			}
			return "";
		} catch(Exception e) {
			logger.warn("Error Occured while extracting message from xml", e);
			String addrErrorMessage = createErrorMessage("USPS Address Service", "INTERNAL_ERROR", "20004", "Error Occured while Extracting Xml using Tag Name = "+ tagName, "false");
			throw new AddressException(addrErrorMessage);

		}		
	}
	
	public static String createErrorMessage(String source, String reasonCode, String errorCode, String description, String recoverable) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<Errors>");
		buffer.append("<Error>");
		buffer.append("<Source>"+source+"</Source>");
		buffer.append("<ReasonCode>"+reasonCode+"</ReasonCode>");
		buffer.append("<ErrorCode>"+errorCode+"</ErrorCode>");
		buffer.append("<Description>"+description+"</Description>");
		buffer.append("<Recoverable>"+recoverable+"</Recoverable>");
		buffer.append("</Error>");
		buffer.append("</Errors>");
		return buffer.toString();
	}

}
