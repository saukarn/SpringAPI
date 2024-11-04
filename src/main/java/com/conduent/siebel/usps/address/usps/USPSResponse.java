package com.conduent.siebel.usps.address.usps;

public class USPSResponse {

	String firm;
    USPSAddressObject address;
    AdditionalInfo additionalInfo;
    Object corrections;
    Object matches;
	public String getFirm() {
		return firm;
	}
	public void setFirm(String firm) {
		this.firm = firm;
	}
	public USPSAddressObject getAddress() {
		return address;
	}
	public void setAddress(USPSAddressObject address) {
		this.address = address;
	}
	public AdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(AdditionalInfo additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public Object getCorrections() {
		return corrections;
	}
	public void setCorrections(Object corrections) {
		this.corrections = corrections;
	}
	public Object getMatches() {
		return matches;
	}
	public void setMatches(Object matches) {
		this.matches = matches;
	}
	@Override
	public String toString() {
		return "USPSResponse [firm=" + firm + ", address=" + address + ", additionalInfo=" + additionalInfo
				+ ", corrections=" + corrections + ", matches=" + matches + ", getFirm()=" + getFirm()
				+ ", getAddress()=" + getAddress() + ", getAdditionalInfo()=" + getAdditionalInfo()
				+ ", getCorrections()=" + getCorrections() + ", getMatches()=" + getMatches() + "]";
	}
	
	
}
