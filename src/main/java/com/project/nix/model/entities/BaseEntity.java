package com.project.nix.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = {"entityUUID"})
public abstract class BaseEntity
{
    @Column(name = "entity_uuid")
    private String entityUUID;

    @Column(name = "create_date")
    private Date createDate;

    @PrePersist
    protected void doOnCreate()
    {
        entityUUID = UUID.randomUUID().toString();
        createDate = new Date();
    }
}
