package com.osm.model;

import com.google.common.base.Objects;

public class LotPriceKey extends LotKey {

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

    @Override
    public int hashCode() {
        final int hashCode = super.hashCode();
        return hashCode + priceId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(getProjectId())
                .addValue(getPlanId())
                .addValue(getLotId())
                .addValue(getPriceId())
                .omitNullValues()
                .toString();
    }
}