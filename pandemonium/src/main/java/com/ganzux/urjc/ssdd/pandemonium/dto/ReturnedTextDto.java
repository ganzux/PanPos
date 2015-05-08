package com.ganzux.urjc.ssdd.pandemonium.dto;

/**
 * Method that represent a DTO (Data Transfer Object) with
 * the attributes of the response to a virus scan request.
 * @author ganzux
 */
public class ReturnedTextDto {

	///////////////////////////////////////////////////////////////
	//                        Attributes                         //
	///////////////////////////////////////////////////////////////
	private String call;
	private String company;
	private Boolean viruses;
	private int analysis_time;
	///////////////////////////////////////////////////////////////
	//                        /Attributes                        //
	///////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////
	//               Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
	public String getCall() {
		return call;
	}
	public void setCall(String call) {
		this.call = call;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Boolean getViruses() {
		return viruses;
	}
	public void setViruses(Boolean viruses) {
		this.viruses = viruses;
	}
	public int getAnalysis_time() {
		return analysis_time;
	}
	public void setAnalysis_time(int analysis_time) {
		this.analysis_time = analysis_time;
	}
	public ReturnedTextDto(String call, String company, Boolean viruses,
			int analysis_time) {
		super();
		this.call = call;
		this.company = company;
		this.viruses = viruses;
		this.analysis_time = analysis_time;
	}
	public ReturnedTextDto() {}
	@Override
	public String toString() {
		return "ReturnedTextDto [call=" + call + ", company=" + company
				+ ", viruses=" + viruses + ", analysis_time=" + analysis_time
				+ "]";
	}
	///////////////////////////////////////////////////////////////
	//              /Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
}