package demo.pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class MusicPage extends BasePage {
    private final Wrappers action;

    public MusicPage(WebDriver driver){
        super(driver);
        this.action = new Wrappers(driver);
    }

    private final By pageTitle = By.xpath("(//yt-formatted-string[text()='Music'])[2]");

    private final By indiaBiggestHitsContainer = By.xpath("//span[text()=\"India's Biggest Hits\"]");

    private final By indiaBiggestHitsContainerShowMoreButton = By.xpath("//span[text()=\"India's Biggest Hits\"]//ancestor-or-self::div[@id='dismissible']//descendant-or-self::div[contains(@class,'button-container')]//child::button[@aria-label='Show more']");

    private final By musicCards = By.xpath("//span[text()=\"India's Biggest Hits\"]//ancestor-or-self::div[@id='rich-shelf-header-container']//following-sibling::div[@id='contents-container']//div[@id='contents']//ytd-rich-item-renderer");

    private final By musicCardTitles = By.xpath(".//child::h3");

    private final By musicCardsBadge = By.xpath(".//child::div[@class='badge-shape-wiz__text']");

    public boolean isPageTitlePresent(String text){
        if (action.isElementPresented(pageTitle)){
            String pageTitleText = action.getText(pageTitle);
            return pageTitleText.contains(text);
        }
        return false;
    }

    public void scrollToIndiaBiggestHitsContainer(){
        action.scrollToElement(indiaBiggestHitsContainer);
    }

    public void clickOnShowMoreButton(){
        action.click(indiaBiggestHitsContainerShowMoreButton);
    }

    public void scrollToLastMusicCard(WebElement lastMusic){
        action.scrollToElement(lastMusic);
    }



    public List<WebElement> getIndiaBiggestHitsMusicCards(){
        return action.getElements(musicCards);
    }




    public String getMusicTitle(WebElement music){
        return music.findElement(musicCardTitles).getAttribute("title");
    }

    public int getBadgeCount(WebElement music){
        WebElement badge = music.findElement(musicCardsBadge);
        String text = badge.getText();
        System.out.println(text);
        return Integer.parseInt(text.replaceAll("[^0-9]",""));
    }


}
