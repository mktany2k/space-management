package com.scwcd.enterprise.sql.hbm;


import java.io.Serializable;
import java.util.Date;


public class LotPrice implements Serializable {

	private static final long serialVersionUID = 1001L;
	
	private LotPriceKey lotPriceKey;
	
	private double pricePerUnit;
	
	private Date dtCreated;
	
	private Date dtModified;
	
	private String updatedBy;

	public LotPriceKey getLotPriceKey() {
		return lotPriceKey;
	}

	public void setLotPriceKey(LotPriceKey lotPriceKey) {
		this.lotPriceKey = lotPriceKey;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Date getDtModified() {
		return dtModified;
	}

	public void setDtModified(Date dtModified) {
		this.dtModified = dtModified;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		StringBuilder sbString = new StringBuilder();
		sbString.append("LotPrice[");
		sbString.append(lotPriceKey).append(", ");
		sbString.append(pricePerUnit).append(", ");
		sbString.append(updatedBy).append(", ");
		sbString.append(dtCreated).append(", ");
		sbString.append(dtModified).append(", ");
		sbString.append(hashCode()).append("]");
		return sbString.toString();
	}
}