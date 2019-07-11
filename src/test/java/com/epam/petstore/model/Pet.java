package com.epam.petstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Pet {

    @JsonProperty("id")
    private int id;
    @JsonProperty("category")
    private PetCategory category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("status")
    private PetStatus status;

    public Pet() {

    }

    public Pet(int id, PetCategory category, String name, List<String> photoUrls, List<Tag> tags, PetStatus
            status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    private Pet(Builder builder) {
        setId(builder.id);
        setCategory(builder.category);
        setName(builder.name);
        setPhotoUrls(builder.photoUrls);
        setTags(builder.tags);
        setStatus(builder.status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PetCategory getCategory() {
        return category;
    }

    public void setCategory(PetCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    public static final class Builder {

        private int id;
        private PetCategory category;
        private String name;
        private List<String> photoUrls;
        private List<Tag> tags;
        private PetStatus status;

        public Builder() {
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setPetCategory(PetCategory category) {
            this.category = category;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPhotoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public Builder setTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder setStatus(PetStatus status) {
            this.status = status;
            return this;
        }

        public Pet build() {
            return new Pet(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id &&
                Objects.equals(category, pet.category) &&
                Objects.equals(name, pet.name) &&
                Objects.equals(photoUrls, pet.photoUrls) &&
                Objects.equals(tags, pet.tags) &&
                Objects.equals(status, pet.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, category, name, photoUrls, tags, status);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }

}
