package com.cydeo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class SpartanAPISteps {


    RequestSpecification givenPart;
    Response response;
    ValidatableResponse thenPart;


    @Given("the base_uri and base_path set")
    public void the_base_uri_and_base_path_set() {
        System.out.println("the base uri settings ");


        givenPart = given()
                .baseUri("http://54.236.150.168:8000/")
                .basePath("/api");

    }

    @When("I send request to {string} endpoint")
    public void i_send_request_to_endpoint(String endpoint) {
        System.out.println("send request to endpoint");

        response = givenPart.when()
                .get(endpoint).prettyPeek();

    }

    @Then("I should get status code {int}")
    public void i_should_get_status_code(Integer expectedStatusCode) {

        System.out.println("should get status code");
       int statusCode =  response.statusCode();
        System.out.println("statusCode = " + statusCode);

        thenPart = response.then().statusCode(expectedStatusCode);

        // Assertions.assertEquals(200, response.statusCode());
    }


    @And("The response format should be {string}")
    public void theResponseFormatShouldBeString(String expectedContentType) {

        thenPart.contentType(expectedContentType);

    }

    @And("the response payload should be Hello from Sparta")
    public void theResponsePayloadShouldBeHelloFromSparta() {

        thenPart.body(is("Hello from Sparta"));
    }

    @And("I ask for {string} response payload")
    public void iAskForJsonResponsePayload(String responseFormat) {

        if (responseFormat.equals("json")) {
            givenPart.accept(ContentType.JSON);
        } else if (responseFormat.equals("xml")) {
            givenPart.accept(ContentType.XML);
        }

    }

    @Given("I send the data in json format")
    public void i_send_the_data_in_json_format() {
       givenPart.contentType(ContentType.JSON) ;

    }
    @Given("I am sending below valid spartan data")
    public void i_am_sending_below_valid_spartan_data(Map<String,Object> requestPayloadMap) {

        givenPart.body(requestPayloadMap);

    }
    @When("I send POST request to endpoint {string}")
    public void i_send_post_request_to_endpoint(String endpoint) {

        response = givenPart.when().post(endpoint);
    }
    @Then("The success field value should be {string}")
    public void the_success_field_value_should_be(String message) {

        thenPart.body("success", is( "A Spartan is Born!" ) ) ;
    }


    @Then("the field value with this path {string} should be equal to {string}")
    public void theFieldValueWithThisPathShouldBeEqualTo(String jsonPath, String expectedValue) {

        thenPart.body(jsonPath, is(expectedValue));

    }
    @Then("the field value with this path {string} should be equal to {int}")
    public void theFieldValueWithThisPathShouldBeEqualTo(String jsonPath, int expectedValue) {

        thenPart.body(jsonPath, is(expectedValue));

    }
}
