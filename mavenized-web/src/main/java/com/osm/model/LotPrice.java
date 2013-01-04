package com.osm.model;

import java.io.Serializable;
import java.util.Date;
import org.joda.time.LocalDateTime;

public class LotPrice implements Serializable {

    private static final long serialVersionUID = 1L;
    private LotPriceKey lotPriceKey;
    private double pricePerUnit;
    private LocalDateTime dtCreated;
    private LocalDateTime dtModified;
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

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = LocalDateTime.fromDateFields(dtCreated);
    }

    public LocalDateTime getDtModified() {
        return dtModified;
    }

    public void setDtModified(Date dtModified) {
        this.dtModified = LocalDateTime.fromDateFields(dtModified);
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public void setDtModified(LocalDateTime dtModified) {
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
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(lotPriceKey)
                .addValue(pricePerUnit)
                .addValue(updatedBy)
                .addValue(dtCreated)
                .addValue(dtModified)
                .toString();
    }
}