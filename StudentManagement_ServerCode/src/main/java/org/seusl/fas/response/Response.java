package org.seusl.fas.response;

import java.io.Serializable;
import java.util.Map;

public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> head;
	private Object body;
	private String responseTime;
	//private LeftPanelVO leftPanel;
	public Map<String, String> getHead() {
		return head;
	}
	public void setHead(Map<String, String> head) {
		this.head = head;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
}
