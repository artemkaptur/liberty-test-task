package com.epam.petstore.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPetWithoutBodyTest extends BaseTest{

    @Test
    public void addEmptyPetTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore();
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseMessage = addNewPetToTheStoreResponse.body().jsonPath().getString("message");

        assertEquals(statusCode, 500, String.format("Status is not 500, but - %s", statusCode));
        assertTrue(addNewPetToTheStoreResponse.time() < 3000, "Too long response time");
        assertEquals(responseMessage, "something bad happened");
    }

}
