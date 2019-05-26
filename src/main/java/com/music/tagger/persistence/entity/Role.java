package com.music.tagger.persistence.entity;

import com.music.tagger.persistence.entity.superclass.NamedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity(name = "Role")
@Table(name = "roles")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends NamedEntity {

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id",
            referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id",
            referencedColumnName = "id"))
    private List<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role {" +
                "id=" + getId() +
                ", name=" + name +
                '}';
    }
}
