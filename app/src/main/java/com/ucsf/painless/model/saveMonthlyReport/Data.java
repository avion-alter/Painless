package com.ucsf.painless.model.saveMonthlyReport;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("report_id")
	private String reportId;

	@SerializedName("pat_id")
	private String patId;

	public String getReportId(){
		return reportId;
	}

	public String getPatId(){
		return patId;
	}
}