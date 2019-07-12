package com.epam.petstore.tests;

import com.epam.petstore.allure.AllureRestAssured;
import com.epam.petstore.model.Pet;
import com.epam.petstore.spring.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.annotation.PostConstruct;

import static io.restassured.RestAssured.given;

@ContextConfiguration(classes = AppConfig.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected RequestSpecification requestSpecification;
    @Value("${success.status.code}")
    protected int successStatusCode;
    @Value("${server.error.status.code}")
    protected int serverErrorStatusCode;
    @Value("${server.error.message}")
    protected String serverErrorMessage;
    @Value("${application.json.contenttype}")
    protected String applicationJsonContentType;
    @Value("${success.max.responsetime}")
    protected int maxResponseTime;

    @PostConstruct
    private void setAllureRestAssuredFilter() {
        RestAssured.replaceFiltersWith(new AllureRestAssured());
    }

    protected Response addNewPetToTheStore(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .post()
                .then().extract().response();
    }

    protected Response addNewPetToTheStore(String requestBody) {
        return given(requestSpecification)
                .body(requestBody)
                .post()
                .then().extract().response();
    }

    protected Response addNewPetToTheStore() {
        return given(requestSpecification)
                .post()
                .then().extract().response();
    }

}
