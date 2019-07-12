package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static java.lang.String.format;
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
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        assertEquals(statusCode, serverErrorStatusCode, format("Status is not 500, but - %s", statusCode));
        assertEquals(responseContentType, applicationJsonContentType,
                format("Response content type is not %s, but - %s", applicationJsonContentType, responseContentType));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, "Too long response time");
        assertEquals(responseMessage, "something bad happened");
    }

}
