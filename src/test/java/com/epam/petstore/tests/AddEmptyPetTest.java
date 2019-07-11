package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;


public class AddEmptyPetTest extends BaseTest {

    @Test
    public void addEmptyPetTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(new Pet());
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        Pet addedPet = addNewPetToTheStoreResponse.as(Pet.class);

        assertEquals(statusCode, 200, String.format("Status is not 200, but - %s", statusCode));
        assertTrue(addNewPetToTheStoreResponse.time() < 3000, "Too long response time");
        assertNotNull(addedPet.getId());
        assertNull(addedPet.getCategory());
        assertNull(addedPet.getName());
        assertNull(addedPet.getPhotoUrls());
        assertNull(addedPet.getTags());
        assertNull(addedPet.getStatus());
    }

}
