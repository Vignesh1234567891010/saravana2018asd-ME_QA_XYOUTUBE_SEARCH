package demo.pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YouTubeHomePage extends BasePage{
    private final Wrappers action;

    public YouTubeHomePage(WebDriver driver){
        super(driver);
        this.action = new Wrappers(driver);
    }

    @FindBy(name = "search_query")
    private WebElement searchBox;

    @FindBy(xpath = "//a[text()='About']")
    private WebElement aboutLink;

    @FindBy(xpath = "//yt-formatted-string[contains(text(),'Film') or contains(text(),'Movie')]")
    private WebElement filmsTab;

    @FindBy(xpath = "//yt-formatted-string[contains(text(),'Music')]")
    private WebElement musicTab;

    @FindBy(xpath = "//yt-formatted-string[contains(text(),'News')]")
    private WebElement newsTab;



    public void search(String query){
        action.type(searchBox, query);
        action.pressEnter();
        action.waitForPageLoad();
    }

    public void clickAbout(){
        action.scrollToElement(aboutLink);
        action.click(aboutLink);
        action.waitForPageLoad();
    }

    public void goToFilms(){
        action.scrollToElement(filmsTab);
        action.click(filmsTab);
        action.waitForPageLoad();
    }

    public void goToMusic(){
        action.scrollToElement(musicTab);
        action.click(musicTab);
        action.waitForPageLoad();
    }

    public void goToNews(){
        action.scrollToElement(newsTab);
        action.click(newsTab);
        action.waitForPageLoad();
    }
}
