package com.ganzux.urjc.ssdd.postealo.dto;


/**
 * Class that represents a Text Entity
 * @author ganzux
 */
public class Text {

	///////////////////////////////////////////////////////////////
	//                        Attributes                         //
	///////////////////////////////////////////////////////////////
	private long id;
	
	private String callId;
	
	private String company;
	
	private String text;
	///////////////////////////////////////////////////////////////
	//                        /Attributes                        //
	///////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////
	//               Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
	public Text() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Text(long id, String company, String text) {
		super();
		this.id = id;
		this.company = company;
		this.text = text;
	}
	public Text(String company, String text) {
		super();
		this.company = company;
		this.text = text;
	}
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public Text(String callId, String company, String text) {
		super();
		this.callId = callId;
		this.company = company;
		this.text = text;
	}
	@Override
	public String toString() {
		return "Text [id=" + id + ", callId=" + callId + ", company=" + company
				+ ", text=" + text + "]";
	}
	///////////////////////////////////////////////////////////////
	//              /Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
}