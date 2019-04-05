package com.auto.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        monochrome = true,
        plugin =    {
                "pretty", "html:target/serenity-reports/serenity-html-report",
                "json:target/serenity-reports/SerenityTestReport.json",
                "rerun:target/serenity-reports/rerun.txt"},
        features = {"src/test/resources/features/insurancePage.feature"},
        glue = {"com/auto/steps"}
)
public class InsurancePageRunner {
}
