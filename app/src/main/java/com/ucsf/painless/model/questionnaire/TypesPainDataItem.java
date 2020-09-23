package com.ucsf.painless.model.questionnaire;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TypesPainDataItem implements Serializable {

	@SerializedName("typn_name")
	private String typnName;

	@SerializedName("typn_id")
	private String typnId;

	@SerializedName("severity")
	private String severity;

	@SerializedName("selected")
	private boolean selected;

	public String getTypnName() {
		return typnName;
	}

	public void setTypnName(String typnName) {
		this.typnName = typnName;
	}

	public String getTypnId() {
		return typnId;
	}

	public void setTypnId(String typnId) {
		this.typnId = typnId;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}