package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SpartanAPISteps {


    RequestSpecification givenPart;
    Response response;
    ValidatableResponse thenPart;
    int lastId;
    public Integer randomSpartanId;


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

        //setting the value of validatable response thenPart variable

        thenPart = response.then();

    }

    @Then("I should get status code {int}")
    public void i_should_get_status_code(Integer expectedStatusCode) {

        System.out.println("should get status code" + expectedStatusCode);

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
        givenPart.contentType(ContentType.JSON);

    }

    @Given("I am sending below valid spartan data")
    public void i_am_sending_below_valid_spartan_data(Map<String, Object> requestPayloadMap) {

        givenPart.body(requestPayloadMap);

    }

    @When("I send POST request to endpoint {string}")
    public void i_send_post_request_to_endpoint(String endpoint) {

        response = givenPart.when().post(endpoint);
        thenPart = response.then();
    }

    @Then("The success field value should be {string}")
    public void the_success_field_value_should_be(String message) {

        thenPart.body("success", is("A Spartan is Born!"));
    }


    @Then("the field value with this path {string} should be equal to {string}")
    public void theFieldValueWithThisPathShouldBeEqualTo(String jsonPath, String expectedValue) {

        thenPart.body(jsonPath, is(expectedValue));

    }

    @Then("the field value with this path {string} should be equal to {int}")
    public void theFieldValueWithThisPathShouldBeEqualTo(String jsonPath, int expectedValue) {

        thenPart.body(jsonPath, is(expectedValue));

    }

    @And("I have valid spartan id")
    public void iHaveValidSpartanId() {

        // get a valid spartan Id and make it available for all methods

        lastId = givenPart.get("/spartans").path("id[-1]");
        // can we just set this id into path variable in here directly
        givenPart.pathParam("id", lastId);

    }


//
//    @When("I send get request to {string} endpoint with valid id")
//    public void iSendGetRequestToEndpointWithValidId(String endPoint) {
//
//
//
//    }

    @When("I send get request to {string} endpoint")
    public void i_send_get_request_to_endpoint(String endpoint) {

        System.out.println(" i_send_get_request_to_endpoint " + endpoint);
        // here send your request and save the result into variable and make it global
        response = givenPart
                .when()
                .get(endpoint).prettyPeek();
        thenPart = response.then();

    }

    @Then("the spartan data with that id matches values in the database")
    public void theSpartanDataWithThatIdMatchesValuesInTheDatabase() {

        //select * from spartan where spartan_id = validId here

        DB_Util.runQuery("select * from spartans where spartan_id = " + lastId);
        DB_Util.displayAllData();

        thenPart.body("id", is(lastId))
                .body("name", is(DB_Util.getCellValue(1, "NAME")))
                .body("gender", is(DB_Util.getCellValue(1, "GENDER")))
                .body("phone.toString()", is(DB_Util.getCellValue(1, "PHONE")));


    }

    @When("I send delete request to {string} endpoint")
    public void iSendDeleteRequestToEndpoint(String endpoint) {
        response = givenPart.when().delete(endpoint);
        thenPart = response.then();

    }

    @And("I have valid random spartan id")
    public void iHaveRandomSpartanId() {

        // send gt request to GET/ spartans

        List<Integer> allIds = givenPart.get("/spartans").path("id");

        // get random ID at a index from 0 to allIds.size()-1

        Random random = new Random();// class from java.util package getting random number
        int randomIndex = random.nextInt(allIds.size() - 1);

        System.out.println("randomIndex = " + randomIndex);
        randomSpartanId = allIds.get(randomIndex);

        // set this ID to path
        givenPart.pathParam("id", randomSpartanId);


    }


    @When("I send put request to {string} endpoint")
    public void iSendPutRequestToEndpoint(String endpoint) {

        response = givenPart.when().put(endpoint);
        thenPart = response.then();

    }

    @When("I send patch request to {string} endpoint")
    public void iSendPatchRequestToEndpoint(String endpoint) {

        response = givenPart.when().patch(endpoint);
        thenPart = response.then();

    }

    @And("I search for spartan with the name")
    public void iSearchForSpartanWithTheName() {

    }

    @And("I search for spartan with name contains {string} and gender {string}")
    public void iSearchForSpartanWithNameContainsAndGender(String nameParam, String genderParam) {

        givenPart
                .queryParam("nameContains", nameParam)
                .queryParams("gender", genderParam);
    }


    @Then("All Names in the result should contains {string} and gender Should be {string}")
    public void allNamesInTheResultShouldContainsAndGenderShouldBe(String expectedName, String expectedGender) {

        thenPart.body("content.gender", everyItem(is(expectedGender)))
                .body("content.name", everyItem(containsStringIgnoringCase(expectedName)));


    }


    @Then("the spartan count in response should match the count in database {string} and gender should be {string}")
    public void theSpartanCountInResponseShouldMatchTheCountInDatabaseAndGenderShouldBe(String name, String gender) {

        // QUERY TO GET ALL DATA WITH NAME contains Ea (ignore case) and GENDER is Male
        // SELECT * FROM SPARTANS
        // WHERE UPPER(NAME) LIKE '%EA%' AND GENDER = 'Male'
        String query =  "SELECT count(*) FROM SPARTANS " +
                " WHERE UPPER(NAME) LIKE '%"+ name.toUpperCase()  +"%' " +
                " AND GENDER = '"+gender+"'" ;
        DB_Util.runQuery(query);
        DB_Util.displayAllData();

        thenPart.body("totalElement.toString()", is (DB_Util.getCellValue(1,1)));


    }

    @Then("The search count for name contains {string} and gender {string} should match the count in the database")
    public void theSearchCountForNameContainsAndGenderShouldMatchTheCountInTheDatabase(String name, String gender) {

        // QUERY TO GET ALL DATA WITH NAME contains Ea (ignore case) and GENDER is Male
        // SELECT * FROM SPARTANS
        // WHERE UPPER(NAME) LIKE '%EA%' AND GENDER = 'Male'
        String query =  "SELECT count(*) FROM SPARTANS " +
                " WHERE UPPER(NAME) LIKE '%"+ name.toUpperCase()  +"%' " +
                " AND GENDER = '"+gender+"'" ;

        System.out.println("query = " + query);
        DB_Util.runQuery(query) ;
        DB_Util.displayAllData();

        // verify the data match
        // thenPart.body("totalElement.toString()" ,  is(  DB_Util.getCellValue(1,1)   )     ) ;
        // or just convert the db result to number
        int expectedDBResult = Integer.parseInt(  DB_Util.getCellValue(1,1)  ) ;
        thenPart.body("totalElement", is(expectedDBResult) );

    }
}