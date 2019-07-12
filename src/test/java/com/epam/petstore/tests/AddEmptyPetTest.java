package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.epam.petstore.constants.AssertionErrorMessages.STATUS_CODE_IS_NOT_SUCCESSFUL;
import static com.epam.petstore.constants.AssertionErrorMessages.TO_LONG_RESPONSE_TIME;
import static com.epam.petstore.constants.AssertionErrorMessages.WRONG_RESPONSE_CONTENT_TYPE;
import static io.qameta.allure.SeverityLevel.MINOR;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

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

        assertEquals(statusCode, successStatusCode, format(STATUS_CODE_IS_NOT_SUCCESSFUL, statusCode));
        assertEquals(responseContentType, applicationJsonContentType,
                format(WRONG_RESPONSE_CONTENT_TYPE, applicationJsonContentType, responseContentType));
        assertTrue(addNewPetToTheStoreResponse.time() < maxResponseTime, TO_LONG_RESPONSE_TIME);
        assertNotNull(addedPet.getId());
        assertNull(addedPet.getCategory());
        assertNull(addedPet.getName());
        assertNull(addedPet.getPhotoUrls());
        assertNull(addedPet.getTags());
        assertNull(addedPet.getStatus());
    }

}
