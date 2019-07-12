package com.epam.petstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.testng.annotations.Test;

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

        assertEquals(statusCode, serverErrorStatusCode, format("Status is not 500, but - %s", statusCode));
        assertEquals(responseContentType, applicationJsonContentType,
                format("Response content type is not %s, but - %s", applicationJsonContentType, responseContentType));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, "Too long response time");
        assertEquals(responseMessage, serverErrorMessage);
    }

}
