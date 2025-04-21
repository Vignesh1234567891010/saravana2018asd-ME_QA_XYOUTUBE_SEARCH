package demo.pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilmsPage extends BasePage{
    private final Wrappers action;

    public FilmsPage(WebDriver driver){
        super(driver);
        this.action = new Wrappers(driver);
    }

    private final By containerTitle = By.xpath("//div[@id='title-text']//child::span[contains(text(),'Top selling')]");

    private final By movieCards = By.xpath("//ytd-grid-movie-renderer[contains(@class,'yt-horizontal-list-renderer')]");

    private final By arrowButton = By.xpath("//div[@id='right-arrow']//child::button");

    private final By ratingSpan = By.xpath(".//p[text()='A' or contains(text(),'U/A') or text()='U']");

    private final By category = By.xpath(".//span[contains(@class,'grid-movie-renderer-metadata')]");

    public boolean isTitlePresent(String text){
        if(action.isElementPresented(containerTitle)) {
            String titleText = action.getText(containerTitle);
            return titleText.equals(text);
        }
        return false;
    }

    public void scrollTopSellingSectionRight(){

        action.scrollItemUsingArrow(arrowButton, movieCards);
    }

    public List<WebElement> getTopSellingMovies(){

        return action.getElements(movieCards);
    }

    public boolean isRatedA(WebElement movieCard){
        WebElement rating = movieCard.findElement(ratingSpan);
        String text = action.getText(rating);
        return text.equals("A") || text.contains("U/A");
    }

    public boolean knownCategory(WebElement movieCard){
        List<WebElement> metadata = movieCard.findElements(category);
        for (WebElement line : metadata){
            String text = action.getText(line).toLowerCase();
            if(text.contains("comedy") || text.contains("drama") || text.contains("animation")){
                return true;
            }
        }
        return false;
    }
















}
