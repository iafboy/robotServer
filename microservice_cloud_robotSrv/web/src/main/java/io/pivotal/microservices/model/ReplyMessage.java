package io.pivotal.microservices.model;


import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ReplyMessage")
public class ReplyMessage {
	protected boolean isSucc;
	protected String mesage;
	public boolean isSucc() {
		return isSucc;
	}
	public void setSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	@Override
	public String toString() {
		return isSucc + " [" + mesage + "]" ;
	}
}
