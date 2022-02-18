package com.cydeo.steps;

import com.cydeo.pages.HomePage;
//import com.cydeo.utility.BrowserUtil;
//import com.cydeo.utility.ConfigReader;
//import com.cydeo.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//
//import java.util.ArrayList;
import java.util.List;

public class SpartanUIStepDefs {

HomePage homePage = new HomePage();



        @Given("user navigate home page")
        public void userNavigateToHomePage () {


            homePage.goTo();



        }

    @Then("user should see below links")
    public void userShouldSeeBelowLinks( List<String> links ) {

//        System.out.println("links = " + links);
//
//        List<WebElement> allLinks = Driver.getDriver().findElements(By.xpath("//div[@class='display-4 card']//a"));
//
//        List<String> actualLinkTexts = BrowserUtil.getAllText(allLinks);
//        System.out.println("actualLinkTexts = " + actualLinkTexts);
//
//
        Assertions.assertEquals(links, homePage.getAllLinks());
    }

}

