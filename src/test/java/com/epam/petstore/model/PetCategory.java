package com.epam.petstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetCategory {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

}
