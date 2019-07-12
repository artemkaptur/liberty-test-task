package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.petstore.constants.AssertionErrorMessages.STATUS_CODE_IS_NOT_SUCCESSFUL;
import static com.epam.petstore.constants.AssertionErrorMessages.TO_LONG_RESPONSE_TIME;
import static com.epam.petstore.constants.AssertionErrorMessages.WRONG_RESPONSE_CONTENT_TYPE;
import static io.qameta.allure.SeverityLevel.MINOR;
import static java.lang.String.format;

public class AddEmptyPetTest extends BaseTest {

    @Autowired
    private Pet emptyPet;

    @Test
    @Severity(MINOR)
    @Description("Checking that it will be created empty pet with id, if we send request with empty body")
    public void addEmptyPetTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore(emptyPet);
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        Pet addedPet = addNewPetToTheStoreResponse.as(Pet.class);
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, successStatusCode, format(STATUS_CODE_IS_NOT_SUCCESSFUL, statusCode));
        softAssert.assertEquals(responseContentType, applicationJsonContentType,
                format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType));
        softAssert.assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME);
        softAssert.assertNotNull(addedPet.getId());
        softAssert.assertNull(addedPet.getCategory());
        softAssert.assertNull(addedPet.getName());
        softAssert.assertNull(addedPet.getPhotoUrls());
        softAssert.assertNull(addedPet.getTags());
        softAssert.assertNull(addedPet.getStatus());
        softAssert.assertAll();
    }

}
