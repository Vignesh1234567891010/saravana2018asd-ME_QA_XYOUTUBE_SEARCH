package demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutPage extends BasePage{

    public AboutPage(WebDriver driver){
        super(driver);
    }

    private final By aboutDescriptions = By.xpath("//section[@class='ytabout__content']//p[contains(@class,'lb-font-color-text-primary')]");

    private final By aboutDescriptionTitle = By.xpath("//section[@class='ytabout__content']//h1");

    public String getAboutDescription(){
        action.waitForPageLoad();
        return action.getDescription(aboutDescriptions);
    }

    public boolean isAboutDescriptionTitleVisible(String text){
        action.waitForPageLoad();
        return action.getText(aboutDescriptionTitle).contains(text);
    }
}
