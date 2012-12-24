package com.osm.model;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.osm.util.Constants;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    private String projectId;
    private String name;
    private String description;
    private String parser;
    private String unit;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String updatedBy;
    private Set<Plan> plans = Sets.newHashSet();//temporarily disable.

    @Id
    @GeneratedValue(generator = Constants.HibernateGenerator.NAME)
    @GenericGenerator(name = Constants.HibernateGenerator.NAME, strategy = Constants.HibernateGenerator.STRATEGY)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
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
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime dtCreated) {
        this.creationDate = dtCreated;
    }

    @Column(name = "modified_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime dtModified) {
        this.modificationDate = dtModified;
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
                .addValue(creationDate)
                .addValue(modificationDate)
                .addValue(plans)
                .toString();
    }
}