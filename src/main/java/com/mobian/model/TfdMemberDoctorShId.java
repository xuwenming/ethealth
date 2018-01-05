/*
 * @author John
 * @date - 2017-11-28
 */

package com.mobian.model;

public class TfdMemberDoctorShId implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;

	private Integer auditType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TfdMemberDoctorShId))
			return false;
		TfdMemberDoctorShId castOther = (TfdMemberDoctorShId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getAuditType() == castOther.getAuditType()) || (this.getAuditType() != null && castOther.getAuditType() != null && this.getAuditType().equals(
				castOther.getAuditType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getAuditType() == null ? 0 : this.getAuditType().hashCode());
		return result;
	}
}

