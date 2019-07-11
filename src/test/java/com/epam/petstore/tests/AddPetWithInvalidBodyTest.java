package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import com.epam.petstore.model.PetCategory;
import com.epam.petstore.model.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.epam.petstore.model.PetStatus.VACCINATED;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPetWithInvalidBodyTest extends BaseTest {

    private Pet petToBeAdded = new Pet.Builder()
            .setId(Integer.valueOf(randomNumeric(6)))
            .setPetCategory(new PetCategory(1, "Cat"))
            .setName("John")
            .setPhotoUrls(new ArrayList<String>() {
                {
                    add("https://www.myowesomecats.com/white");
                    add("https://www.myowesomecats.com/grey");
                }
            })
            .setTags(new ArrayList<Tag>() {
                {
                    add(new Tag(1, "tag1"));
                    add(new Tag(2, "tag2"));
                }
            })
            .setStatus(VACCINATED).build();

    @Test
    public void addNewPetToTheStoreTest() throws JsonProcessingException {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(new ObjectMapper().writeValueAsString(petToBeAdded)
                .substring(13));
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseMessage = addNewPetToTheStoreResponse.body().jsonPath().getString("message");

        assertEquals(statusCode, 500, String.format("Status is not 500, but - %s", statusCode));
        assertTrue(addNewPetToTheStoreResponse.time() < 3000, "Too long response time");
        assertEquals(responseMessage, "something bad happened");
    }

}
