package com.thanglv.hibernate.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author thanglv on 3/22/2021
 * @project NBD
 */

@Entity
public class Authority implements GrantedAuthority, Serializable {

    @Id
    @NotNull
    @Size(max = 50)
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "authority_api", joinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")},
            inverseJoinColumns = {@JoinColumn(name = "api_id", referencedColumnName = "id")})
    private Set<ApiInfo> apiInfos = new HashSet<>();

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public Set<ApiInfo> getApiInfos() {
        return apiInfos;
    }

    public void setApiInfos(Set<ApiInfo> apiInfos) {
        this.apiInfos = apiInfos;
    }
}
