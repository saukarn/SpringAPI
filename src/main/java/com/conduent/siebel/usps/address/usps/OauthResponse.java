package com.conduent.siebel.usps.address.usps;

public class OauthResponse {
	
	String access_token;
	String token_type;
	String issued_at;
	String  expires_in;
	String status;
	String scope;
	String issuer;
	String client_id;
	String application_name;
	String api_products;
	String public_key;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getIssued_at() {
		return issued_at;
	}
	public void setIssued_at(String issued_at) {
		this.issued_at = issued_at;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getApi_products() {
		return api_products;
	}
	public void setApi_products(String api_products) {
		this.api_products = api_products;
	}
	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}
	@Override
	public String toString() {
		return "OauthResponse [access_token=" + access_token + ", token_type=" + token_type + ", issued_at=" + issued_at
				+ ", expires_in=" + expires_in + ", status=" + status + ", scope=" + scope + ", issuer=" + issuer
				+ ", client_id=" + client_id + ", application_name=" + application_name + ", api_products="
				+ api_products + ", public_key=" + public_key + ", getAccess_token()=" + getAccess_token()
				+ ", getToken_type()=" + getToken_type() + ", getIssued_at()=" + getIssued_at() + ", getExpires_in()="
				+ getExpires_in() + ", getStatus()=" + getStatus() + ", getScope()=" + getScope() + ", getIssuer()="
				+ getIssuer() + ", getClient_id()=" + getClient_id() + ", getApplication_name()="
				+ getApplication_name() + ", getApi_products()=" + getApi_products() + ", getPublic_key()="
				+ getPublic_key() + "]";
	}
	
	
}
