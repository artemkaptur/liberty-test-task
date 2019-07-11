package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import com.epam.petstore.model.PetCategory;
import com.epam.petstore.model.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.epam.petstore.model.PetStatus.VACCINATED;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddNewPetToTheStoreTest extends BaseTest {

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
    public void addNewPetToTheStoreTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(petToBeAdded);
        int statusCode = addNewPetToTheStoreResponse.statusCode();

        assertEquals(statusCode, 200, String.format("Status is not 200, but - %s", statusCode));
        assertTrue(addNewPetToTheStoreResponse.time() < 3000, "Too long response time");
        assertEquals(petToBeAdded, addNewPetToTheStoreResponse.as(Pet.class), "Pets aren't equal");
    }

}
