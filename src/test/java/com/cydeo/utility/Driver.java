package com.cydeo.utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver obj ;

    private Driver(){ }

    /**
     * Return obj with only one WebDriver instance
     * @return same WebDriver if exists , new one if null
     */
    public static WebDriver getDriver(){
        // read the browser type you want to launch from properties file
        String browserName = ConfigReader.read("browser") ;

        if(obj == null){

            // according to browser type set up driver correctly
            switch (browserName ){

//                case "remote-chrome":
//                 try {
//                     // assign your grid server address
//                     String gridAddress = "54.235.53.73";
//                     URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
//                     DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//                     desiredCapabilities.setBrowserName("chrome");
//                     driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
//                 }catch (Exception e){
//                     e.printStackTrace();
//                 }
//                break;

                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    obj = new ChromeDriver();
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    obj = new FirefoxDriver();
                    break;
                // other browsers omitted
                default:
                    obj = null ;
                    System.out.println("UNKNOWN BROWSER TYPE!!! " + browserName);
            }
            return obj ;



        }else{
//            System.out.println("You have it just use existing one");
            return obj ;

        }

    }

    /**
     * Quitting the browser and setting the value of
     * WebDriver instance to null because you can re-use already quitted driver
     */
    public static void closeBrowser(){

        // check if we have WebDriver instance or not
        // basically checking if obj is null or not
        // if not null
        // quit the browser
        // make it null , because once quit it can not be used
        if(obj != null ){
            obj.close();
            obj.quit();
            // so when ask for it again , it gives us not quited fresh driver
            obj = null ;
        }

    }
}
