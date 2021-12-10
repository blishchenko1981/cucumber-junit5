package com.cydeo.runner;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/restAPI")
@ConfigurationParameter(key= GLUE_PROPERTY_NAME, value = "com.cydeo.steps")
@ConfigurationParameter(key= PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME  ,
        value = "pretty, json:target/cucumber.json, html:target/cucumber.html" )
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@hello")
public class TestRunner {



}
