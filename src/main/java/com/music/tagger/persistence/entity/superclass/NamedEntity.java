package com.music.tagger.persistence.entity.superclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class NamedEntity extends BasicEntity {

    @Column(name = "name")
    protected String name;
}
