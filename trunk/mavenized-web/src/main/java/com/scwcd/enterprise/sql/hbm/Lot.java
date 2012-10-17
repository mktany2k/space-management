package com.scwcd.enterprise.sql.hbm;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class Lot implements Serializable {

    private static final long serialVersionUID = 1001L;
    private LotKey lotKey;
    private String lot;
    private String name;
    private String description;
    private String tenant;
    private double size;
    private String accountCode;
    private String image;
    private Date dtCreated;
    private Date dtModified;
    private String updatedBy;

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getLot() {
        return lot;
    }

    public LotKey getLotKey() {
        return lotKey;
    }

    public void setLotKey(LotKey lotKey) {
        this.lotKey = lotKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getTenant() {
        return tenant;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
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
    private Plan plan;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    private Set<LotPrice> lotPrice;

    public void setLotPrice(Set<LotPrice> lotPrice) {
        this.lotPrice = lotPrice;
    }

    public Set<LotPrice> getLotPrice() {
        return lotPrice;
    }

    @Override
    public String toString() {
        StringBuilder sbString = new StringBuilder();
        sbString.append("Lot[");
        sbString.append(lotKey).append(", ");
        sbString.append(name).append(", ");
        sbString.append(description).append(", ");
        sbString.append(accountCode).append(", ");
        sbString.append(image).append(", ");
        sbString.append(size).append(", ");
        sbString.append(updatedBy).append(", ");
        sbString.append(dtCreated).append(", ");
        sbString.append(dtModified).append(", ");
        sbString.append(hashCode()).append(", ");
        sbString.append(plan).append(", ");
        sbString.append(lotPrice).append("]");
        return sbString.toString();
    }

    @Override
    public int hashCode() {
        return lotKey.hashCode();
    }

    public int getHashCode() {
        return hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Lot)) {
            return false;
        }

        final Lot instance = (Lot) object;
        return hashCode() == instance.hashCode();
    }

    public String getSummary() {
        return StringUtils.abbreviate(description, 83);
    }
}