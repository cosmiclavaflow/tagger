package com.music.tagger.persistence.entity;

import com.music.tagger.persistence.entity.superclass.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity(name = "Privilege")
@Table(name = "privileges")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Privilege extends NamedEntity {

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Privilege {" +
                "id=" + getId() +
                ", name=" + name +
                '}';
    }
}
