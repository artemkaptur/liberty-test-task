package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPetWithInvalidBodyTest extends BaseTest {

    @Autowired
    private Pet petToBeAdded;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addPetWithInvalidBodyTest() throws JsonProcessingException {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(objectMapper.writeValueAsString(petToBeAdded)
                .substring(13));
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseMessage = addNewPetToTheStoreResponse.body().jsonPath().getString("message");

        assertEquals(statusCode, serverErrorStatusCode, String.format("Status is not 500, but - %s", statusCode));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, "Too long response time");
        assertEquals(responseMessage, "something bad happened");
    }

}
