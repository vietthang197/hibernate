package com.thanglv.hibernate.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author thanglv on 3/22/2021
 * @project NBD
 */

@Entity
public class Authority implements Serializable {

    @Id
    @NotNull
    @Size(max = 50)
    private String name;

    public Authority() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
