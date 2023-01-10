package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class) 
@CucumberOptions(tags=("@Address1"),dryRun=true,features="src\\test\\resources\\Features",glue="com.stepdefnition")


public class TestRunnerClass {
	
}
