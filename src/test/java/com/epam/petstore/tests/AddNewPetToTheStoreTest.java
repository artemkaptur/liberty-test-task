package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddNewPetToTheStoreTest extends BaseTest {

    @Autowired
    private Pet petToBeAdded;

    @Test
    @Severity(BLOCKER)
    @Description("Checking that pet's created by sending valid request body")
    public void addNewPetToTheStoreTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(petToBeAdded);
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        assertEquals(statusCode, successStatusCode, format("Status is not 200, but - %s", statusCode));
        assertEquals(responseContentType, applicationJsonContentType,
                format("Response content type is not %s, but - %s", applicationJsonContentType, responseContentType));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, "Too long response time");
        assertEquals(petToBeAdded, addNewPetToTheStoreResponse.as(Pet.class), "Pets aren't equal");
    }

}
