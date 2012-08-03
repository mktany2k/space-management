package com.scwcd.enterprise.sql.hbm;

import java.io.Serializable;

public class LotPriceKey extends LotKey implements Serializable {

	private static final long serialVersionUID = 1001L;

	private int priceId;

	public LotPriceKey() {
	}

	public LotPriceKey(final int priceId) {
		this.priceId = priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getPriceId() {
		return priceId;
	}

	public int hashCode() {
		final int hashCode = super.hashCode();
		return hashCode + priceId;
	}

	@Override
	public String toString() {
		final int projectId = getProjectId();
		final int planId = getPlanId();
		final int lotId = getLotId();
		StringBuilder sbString = new StringBuilder();
		sbString.append("LotPriceKey[");
		sbString.append(projectId).append(", ");
		sbString.append(planId).append(", ");
		sbString.append(lotId).append(", ");
		sbString.append(priceId).append("]");
		return sbString.toString();
	}
}