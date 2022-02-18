package com.cydeo.steps;

import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class OdclasSteps {


    @Given("User on the webPage")
    public void User_on_the_webPage(){

        System.out.println("First step");
        Driver.getDriver().get("https://ok.ru/");


    }

    @When("User provide correct credentials and click login button")
    public void userProvideCorrectCredentialsAndClickLoginButton() {

        WebElement email = Driver.getDriver().findElement(By.xpath("//input[@type='text']"));
        email.sendKeys("zar84var");

        WebElement password = Driver.getDriver().findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("intel0305");

       WebElement loginBTN =  Driver.getDriver().findElement(By.xpath("//input[@value='Log in to OK']"));
        loginBTN.click();
        BrowserUtil.waitFor(3);
    }

    @And("On home page click {string} icon")
    public void onHomePageClickMessageIcon(String icon) {
        System.out.println("icon = " + icon);
        if (icon.equals("Message")){
        WebElement messageIcon = Driver.getDriver().findElement(By.xpath("//ul/li[1]/div/div[1][@class='toolbar_nav_i_ic']"));
        messageIcon.click();
        BrowserUtil.waitFor(6);
    }
    if(icon.equals("Guests")){

        WebElement guestsIcon = Driver.getDriver().findElement(By.xpath("//li[@data-l='t,guests']"));
        guestsIcon.click();
        BrowserUtil.waitFor(6);

    }

    }

    @Then("User shoul be able to get all senders as a list")
    public void userShoulBeAbleToGetAllSendersAsAList() {

        List<WebElement> listOFMails = Driver.getDriver().findElements(By.xpath("//msg-parsed-text"));

        LinkedList<String> allNames = new LinkedList<>();
        for (WebElement email : listOFMails) {

            allNames.add(email.getText());
        }


        System.out.println("allNames = " + allNames);
    }

    @Then("User should be able to get all Guests as a list")
    public void userShouldBeAbleToGetAllGuestsAsAList() {

//div[@class="ellip"]

        List<WebElement> listOfGuests = Driver.getDriver().findElements(By.xpath("//div[@class=\"ellip\"]"));

        System.out.print("Every guest: ");
        for (WebElement everyGuest : listOfGuests) {
            System.out.print(  everyGuest.getText() + ", ");
        }


    }
}
