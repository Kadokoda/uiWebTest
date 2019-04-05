package com.auto.pageobjects;

import com.auto.helpers.Constants;
import com.auto.helpers.Wait;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Date;
import java.util.List;

public class InsurancePage extends PageObject {
    private static Wait wait = new Wait();
    private static final String TEXT_CONTAIN_STRING = "//*[text()[contains(.,'%s')]]";
    private static final String TEXT_CONTAIN_STRING_CALENDAR = "//td[text()[contains(.,'%s')]]";
    private static final String SEE_MORE = "SEE MORE";
    private static final String INSURANCE_TAB = "//a[@href='#Insurance']";
    private static final String TRAVEL_TAB = "//a[@href='#Travel']";
    private static final String SHOW_RESULT_BUTTON = "//button[@name='product-form-submit']//link[@class='rippleJS']";
    private static final String NO_OF_RESULT = "//h5[contains(text(),'plans found')]";
    private static final String DETAIL_DESTINATION_DROP_DOWN = "//div[@class='select-component']";
    private static final String PERSONAL_ACCIDENT_SLIDER = "//div[@class='slider-handle min-slider-handle round']";
    private static final String LEFT_PANEL_SLIDER = "//div[@class='slider slider-horizontal']";
    private static final String LEFT_PANEL_DATE_PICKER = "//div[@class='input-group input-medium date-picker']";
    private static final String CARDS_RESULT = "//div[@class='card-brand']";
    private static final int FIRST_CARD = 1;
    private static final int TRAVEL_START_DATE = 0;
    private static final int TRAVEL_END_DATE = 1;
    private static final int PERSONAL_ACCIDENT_TRACKING_SLIDER = 0;

    private static final String SORT_LOW_HIGH_OPTION = TEXT_CONTAIN_STRING;
    private static final String FILTER_CRITERIA = TEXT_CONTAIN_STRING;

    @FindBy(xpath = INSURANCE_TAB)
    WebElementFacade insuranceTab;

    @FindBy(xpath = TRAVEL_TAB)
    WebElementFacade travelTab;

    @FindBy(xpath = SHOW_RESULT_BUTTON)
    WebElementFacade showResultButton;

    @FindBy(xpath = NO_OF_RESULT)
    WebElementFacade noOfResult;

    @FindBy(xpath = DETAIL_DESTINATION_DROP_DOWN)
    WebElementFacade detailDestinationDropdown;

    public void clickTravelTab(){
        insuranceTab.click();
        travelTab.click();
    }

    public void clickShowMyResultButton(){
        showResultButton.click();
    }

    public String getNoOfResult(){
        return noOfResult.getText();
    }

    public void filterInsurer(String insurer){
        wait.start(Constants.ONE_SECOND);
        this.find(By.xpath(String.format(FILTER_CRITERIA,insurer))).click();
    }

    public void filterPersonalAccident(int percentage){
        WebDriver driver = this.getDriver();
        this.find(By.xpath(String.format(TEXT_CONTAIN_STRING,SEE_MORE))).click();
        List<WebElementFacade> slider = this.findAll(By.xpath(PERSONAL_ACCIDENT_SLIDER));
        List<WebElementFacade> sliderTracking = this.findAll(By.xpath(LEFT_PANEL_SLIDER));

        int width = sliderTracking.get(PERSONAL_ACCIDENT_TRACKING_SLIDER).getSize().getWidth();
        int move = percentage*width/100;

        Actions action= new Actions(driver);
        action.clickAndHold(slider.get(PERSONAL_ACCIDENT_TRACKING_SLIDER));
        action.moveByOffset(move,0);
        action.release().build().perform();

    }

    public void selectRadioButton(String sort){
        wait.start(Constants.ONE_SECOND);
        this.find(By.xpath(String.format(TEXT_CONTAIN_STRING,sort))).click();
    }

    public void selectDestinationCountry(String destination){
        detailDestinationDropdown.click();
        this.find(By.xpath
                (String.format(TEXT_CONTAIN_STRING,destination))).click();
    }

    public void selectTravelStartDate(Date date){
        this.findAll(By.xpath(LEFT_PANEL_DATE_PICKER)).get(TRAVEL_START_DATE).click();
        this.find(By.xpath(String.format(TEXT_CONTAIN_STRING_CALENDAR,date.getDate()))).click();
    }

    public String getFirstCardResult(){
//        return this.find(By.xpath(String.format(CARDS_RESULT,FIRST_CARD))).getText();
        return this.findAll(By.xpath(CARDS_RESULT)).get(FIRST_CARD).getText();
    }
}
