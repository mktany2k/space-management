package com.osm.model;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    private int projectId;
    private String name;
    private String description;
    private String parser;
    private String unit;
    private LocalDateTime dtCreated;
    private LocalDateTime dtModified;
    private String updatedBy;
    private Set<Plan> plans = Sets.newHashSet();

    @Id
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public void setParser(String parser) {
        this.parser = parser;
    }

    public String getParser() {
        return parser;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Column(name = "created_date", nullable = false, updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    @Column(name = "modified_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getDtModified() {
        return dtModified;
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

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

    @Transient
    public Set<Plan> getPlans() {
        return plans;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(projectId)
                .addValue(name)
                .addValue(description)
                .addValue(parser)
                .addValue(unit)
                .addValue(updatedBy)
                .addValue(dtCreated)
                .addValue(dtModified)
                .addValue(plans)
                .toString();
    }
}