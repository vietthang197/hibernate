package com.thanglv.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author thanglv on 4/27/2021
 * @project hibernate
 */

@Entity
public class ApiGroup {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "apiGroup") // OneToMany da co san lazy fetching
    private Set<ApiInfo> apiInfos = new HashSet<>();

    public ApiGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApiInfo> getApiInfos() {
        return apiInfos;
    }

    public void setApiInfos(Set<ApiInfo> apiInfos) {
        this.apiInfos = apiInfos;
    }
}
