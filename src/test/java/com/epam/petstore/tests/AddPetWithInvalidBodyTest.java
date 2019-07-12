package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.petstore.constants.AssertionErrorMessages.STATUS_CODE_IS_NOT_ERRORED;
import static com.epam.petstore.constants.AssertionErrorMessages.TO_LONG_RESPONSE_TIME;
import static com.epam.petstore.constants.AssertionErrorMessages.WRONG_RESPONSE_CONTENT_TYPE;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static java.lang.String.format;

public class AddPetWithInvalidBodyTest extends BaseTest {

    @Autowired
    private Pet petToBeAdded;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Flaky
    @Severity(CRITICAL)
    @Description("Checking that it's impossible to create pet with invalid request body")
    public void addPetWithInvalidBodyTest() throws JsonProcessingException {

        int randomNumberForSpoilRequestBody = 13;
        Response addNewPetToTheStoreResponse = addNewPetToTheStore(objectMapper.writeValueAsString(petToBeAdded)
                .substring(randomNumberForSpoilRequestBody));
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseMessage = addNewPetToTheStoreResponse.body().jsonPath().getString("message");
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, serverErrorStatusCode, format(STATUS_CODE_IS_NOT_ERRORED, statusCode));
        softAssert.assertEquals(responseContentType, applicationJsonContentType,
                format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType));
        softAssert.assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME);
        softAssert.assertEquals(responseMessage, serverErrorMessage);
        softAssert.assertAll();
    }

}
