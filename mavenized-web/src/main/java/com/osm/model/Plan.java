package com.osm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.joda.time.LocalDateTime;

public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;
    private int projectId;
    private int planId;
    private String name;
    private String description;
    private String filename;
    private LocalDateTime dtCreated;
    private LocalDateTime dtModified;
    private String updatedBy;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
    public int hashCode() {
        return Objects.hash(projectId, planId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plan other = (Plan) obj;
        return Objects.equals(projectId, other.projectId)
                && Objects.equals(planId, other.planId);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .addValue(projectId)
                .addValue(planId)
                .addValue(name)
                .addValue(description)
                .addValue(filename)
                .addValue(dtCreated)
                .addValue(dtModified)
                .addValue(updatedBy)
                .toString();
    }
}