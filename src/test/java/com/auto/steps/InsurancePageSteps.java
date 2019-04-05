package com.auto.steps;

import com.auto.helpers.DataTableHelpers;
import com.auto.pageobjects.InsurancePage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.testng.Assert.assertTrue;

public class InsurancePageSteps {
    private InsurancePage landingPage;

    @Given("^User is on Travel Insurance page")
    public void gotoLandingPage(){
        landingPage.open();
        landingPage.clickTravelTab();
    }

    @When("^User set filter, sort and detail with following criteria$")
    public void userFilterWithConditions(DataTable dataTable) throws Throwable{
        Map<String, String> dataInput = DataTableHelpers.convertDataTableToHashMap(dataTable);
        String filterInsurer = dataInput.get("Filter_Insurer");
        String sort = dataInput.get("Sort");
        String detailDestination = dataInput.get("Detail_Destination");
        String personal_accident_percentage = dataInput.get("Filter_Personal_Accident_Percentage");
        String travelStartDateString = dataInput.get("Start_Date");
        Date travelStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(travelStartDateString);
        landingPage.filterInsurer(filterInsurer);
        landingPage.filterPersonalAccident(parseInt(personal_accident_percentage));
        landingPage.selectRadioButton(sort);
        landingPage.selectDestinationCountry(detailDestination);
        landingPage.selectTravelStartDate(travelStartDate);
    }

    @When("^User set filter with \"([^\"]*)\"$")
    public void userFilterWithDifferentInsurers(String insurer) throws Throwable{
        landingPage.filterInsurer(insurer);

    }

    @When("^User set sort with \"([^\"]*)\"$")
    public void userSortWithSortOrder(String sort) throws Throwable{
        landingPage.selectRadioButton(sort);

    }

    @Then("^User should see result display \"([^\"]*)\" result$")
    public void noOfResultShow(String result) throws Throwable{
        int expectedResult = Integer.parseInt(result);
        int actualResult = Integer.parseInt(landingPage.getNoOfResult().
                replaceAll("[^0-9]+", " ").trim());
        assertTrue(actualResult == expectedResult);

    }

    @Then("^User should see result display order with first card is \"([^\"]*)\"$")
    public void resultShowWithFirstCard(String result) throws Throwable{
        String actualResult = landingPage.getFirstCardResult();
        assertTrue(actualResult.equals(result));

    }

    @When("^User click to see the result with default choice")
    public void userClickOnShowMyResult(){
        landingPage.clickShowMyResultButton();
    }

    @Then("^User should see result display at least \"([^\"]*)\" card")
    public void userShouldSeeAtLeast3Cards(String noOfRessult) {
        int actualResult = Integer.parseInt(landingPage.getNoOfResult().
                replaceAll("[^0-9]+", " ").trim());
        int expectedResult = Integer.parseInt(noOfRessult);
        assertTrue(actualResult >= expectedResult);
    }

    @Then("^There're \"([^\"]*)\" cards are displaying")
    public void saidVerificationPageIsDisplayed(String noOfRessult) throws Throwable{
        assertTrue(landingPage.getNoOfResult().contains(noOfRessult));
    }



}
