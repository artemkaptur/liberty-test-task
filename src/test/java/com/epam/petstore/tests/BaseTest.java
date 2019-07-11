package com.epam.petstore.tests;

import com.epam.petstore.model.Pet;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public abstract class BaseTest {

    protected RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://petstore.swagger.io")
                .setBasePath("/v2/pet")
                .setContentType(JSON)
                .addHeader("api_key", "sa88gg17cb12")
                .log(ALL).build();
    }

    protected Response addNewPetToTheStore(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .post()
                .then().extract().response();
    }

    protected Response addNewPetToTheStore() {
        return given(requestSpecification)
                .post()
                .then().extract().response();
    }

}
