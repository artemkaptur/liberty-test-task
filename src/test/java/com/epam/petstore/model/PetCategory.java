package com.epam.petstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PetCategory {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

    public PetCategory() {
    }

    public PetCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetCategory that = (PetCategory) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "PetCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
