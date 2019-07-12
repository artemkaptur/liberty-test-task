package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.epam.petstore.constants.AssertionErrorMessages.*;
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

        assertEquals(statusCode, successStatusCode, format(STATUS_CODE_IS_NOT_SUCCESSFUL, statusCode));
        assertEquals(responseContentType, applicationJsonContentType,
                format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME);
        assertEquals(petToBeAdded, addNewPetToTheStoreResponse.as(Pet.class), WRONG_PET_WAS_CREATED);
    }

}
