package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddNewPetToTheStoreTest extends BaseTest {

    @Autowired
    private Pet petToBeAdded;

    @Test
    public void addNewPetToTheStoreTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(petToBeAdded);
        int statusCode = addNewPetToTheStoreResponse.statusCode();

        assertEquals(statusCode, successStatusCode, String.format("Status is not 200, but - %s", statusCode));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, "Too long response time");
        assertEquals(petToBeAdded, addNewPetToTheStoreResponse.as(Pet.class), "Pets aren't equal");
    }

}
