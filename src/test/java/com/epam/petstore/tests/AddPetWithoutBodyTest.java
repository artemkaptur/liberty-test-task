package com.epam.petstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.epam.petstore.constants.AssertionErrorMessages.STATUS_CODE_IS_NOT_ERRORED;
import static com.epam.petstore.constants.AssertionErrorMessages.TO_LONG_RESPONSE_TIME;
import static com.epam.petstore.constants.AssertionErrorMessages.WRONG_RESPONSE_CONTENT_TYPE;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPetWithoutBodyTest extends BaseTest{

    @Test
    @Severity(CRITICAL)
    @Description("Checking that it's impossible to create pet without request body")
    public void addPetWithoutBodyTest() {

        Response addNewPetToTheStoreResponse = addNewPetToTheStore();
        int statusCode = addNewPetToTheStoreResponse.statusCode();
        String responseMessage = addNewPetToTheStoreResponse.body().jsonPath().getString("message");
        String responseContentType = addNewPetToTheStoreResponse.contentType();

        assertEquals(statusCode, serverErrorStatusCode, format(STATUS_CODE_IS_NOT_ERRORED, statusCode));
        assertEquals(responseContentType, applicationJsonContentType,
                format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME);
        assertEquals(responseMessage, serverErrorMessage);
    }

}
