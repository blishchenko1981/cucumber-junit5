package com.cydeo.steps;

import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@db")
    public void setupDBconnection(){
        System.out.println("this is hooks class running");

        String url = ConfigReader.read("sp.database.url");
        String username = ConfigReader.read("sp.database.username");
        String password = ConfigReader.read("sp.database.password");
        DB_Util.createConnection(url, username, password);

    }


    @After
    public void destroyConnection(){
        DB_Util.destroy();
    }

    }


