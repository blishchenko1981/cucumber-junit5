package com.cydeo.steps;

import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before("@db")
    public void setupDBconnection() {
        System.out.println("this is hooks class running");

        String url = ConfigReader.read("sp.database.url");
        String username = ConfigReader.read("sp.database.username");
        String password = ConfigReader.read("sp.database.password");
        DB_Util.createConnection(url, username, password);

    }


    @After("@db")
    public void destroyConnection() {
        DB_Util.destroy();
    }


    @After
    public void resetRestAssured() {
        RestAssured.reset();
    }


    // we can set up hook class that contain method that run before and after each scenario
    // or even when we learn tags

    @Before("@ui")
    public void setup(){
        System.out.println("THIS IS ADD BEFORE HOOKS CLASS");
        //set up implicit wait
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Driver.getDriver().manage().window().maximize();
    }

    @After("@ui")
    public void tearDown(Scenario scenario){

        // check if scenario failed or not
        if(scenario.isFailed()){
            // this is how we take screenshot in selenium

            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "whatever you want");


        }

        System.out.println("THIS IS ADD AFTER HOOKS CLASS");

        Driver.closeBrowser();
    }


}