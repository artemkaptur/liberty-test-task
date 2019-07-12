package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.petstore.constants.AssertionErrorMessages.*;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static java.lang.String.format;

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

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, successStatusCode, format(STATUS_CODE_IS_NOT_SUCCESSFUL, statusCode));
        softAssert.assertEquals(responseContentType, applicationJsonContentType,
                format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType));
        softAssert.assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME);
        softAssert.assertEquals(petToBeAdded, addNewPetToTheStoreResponse.as(Pet.class), WRONG_PET_WAS_CREATED);
        softAssert.assertAll();
    }

}
