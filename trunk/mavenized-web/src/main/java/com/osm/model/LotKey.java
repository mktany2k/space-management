package com.osm.model;

import java.io.Serializable;
import java.util.Objects;

public class LotKey implements Serializable {

    private static final long serialVersionUID = 1001L;
    private int projectId;
    private int planId;
    private int lotId;

    public LotKey() {
    }

    public LotKey(final int projectId, final int planId, final int lotId) {
        this.projectId = projectId;
        this.planId = planId;
        this.lotId = lotId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getLotId() {
        return lotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, planId, lotId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        LotKey other = (LotKey) obj;
        return Objects.equals(projectId, other.projectId)
                && Objects.equals(planId, other.planId)
                && Objects.equals(lotId, other.lotId);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(projectId)
                .addValue(planId)
                .addValue(lotId)
                .toString();
    }
}