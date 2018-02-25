package com.alvaroartieda.foodies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by radu on 25/02/2018.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = Gourmand.class,
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Gourmand.class, name = "gourmand"),
        @JsonSubTypes.Type(value = Chef.class, name = "chef")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Long id;
    private String name;
    private String description;
    private Role role;

    public User(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
