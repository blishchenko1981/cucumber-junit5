package com.cydeo.utility;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class BrowserUtil {
    // a method to pause the thead certain seconds
    public static void waitFor(int second){
        try {
            Thread.sleep(second *1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    // method will check for visibility of elements within the time given
    public static boolean checkVisibilityOfElement(By locator, int  timeToWait){

        boolean result = false;

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWait);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            result = true;

        }catch (
                TimeoutException e){
            System.out.println("e.getMessage() = " + e.getMessage());

            System.out.println("we did not see the error mellage element");
        }


        return result;
    }

    /**
     * A utility method to get the texts out of list of web elements
     * @param lstOfWebElements the target list of weblement
     * @return the text inside those web element as List<String>
     */
    public static List<String> getAllText(List<WebElement> lstOfWebElements ){

        List<String> allTextLst = new ArrayList<>();
        for (WebElement eachElement : lstOfWebElements) {
            allTextLst.add(  eachElement.getText()  );
        }

        return  allTextLst ;

    }

}
