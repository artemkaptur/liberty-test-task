package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class AddEmptyPetTest extends BaseTest {

    @Autowired
    private Pet emptyPet;

    @Test
    public void addEmptyPetTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(emptyPet);
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        Pet addedPet = addNewPetToTheStoreResponse.as(Pet.class);

        assertEquals(statusCode, successStatusCode, String.format("Status is not 200, but - %s", statusCode));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, "Too long response time");
        assertNotNull(addedPet.getId());
        assertNull(addedPet.getCategory());
        assertNull(addedPet.getName());
        assertNull(addedPet.getPhotoUrls());
        assertNull(addedPet.getTags());
        assertNull(addedPet.getStatus());
    }

}
