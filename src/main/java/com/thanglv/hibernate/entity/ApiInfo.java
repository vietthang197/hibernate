package com.thanglv.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author thanglv on 4/27/2021
 * @project hibernate
 */

@Entity
public class ApiInfo {
    @Id
    private Long id;

    private String url;

    private String name;

    private String action;

    private String method;

    @ManyToOne
    @JsonIgnoreProperties(value = "apiInfos", allowSetters = true)
    private ApiGroup apiGroup;

    public ApiInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ApiGroup getApiGroup() {
        return apiGroup;
    }

    public void setApiGroup(ApiGroup apiGroup) {
        this.apiGroup = apiGroup;
    }
}
