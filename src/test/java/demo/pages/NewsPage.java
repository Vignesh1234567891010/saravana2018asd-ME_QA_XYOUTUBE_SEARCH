package demo.pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NewsPage extends BasePage {
    private final Wrappers action;

    public NewsPage(WebDriver driver){
        super(driver);
        action = new Wrappers(driver);
    }

    private final By pageTitle = By.xpath("//div[@id='page-header']//child::span[text()='News']");

    private final By latestNewsPosts = By.xpath("//span[text()='Latest news posts']");

    private final By newsCards = By.xpath("//span[text()='Latest news posts']//ancestor-or-self::div[@id='rich-shelf-header-container']//following-sibling::div[@id='contents-container']//ytd-rich-item-renderer");

    private final By newsCardsHeader = By.xpath(".//child::a[@id='author-text']//span");

    private final By newsCardsBody = By.xpath(".//child::div[@id='body']//yt-formatted-string[@id='home-content-text']//span[not(text()=' | ') and not(text()=' ') and not(contains(text(),'Link'))]");

    private final By likeCount = By.xpath(".//child::span[@id='vote-count-middle']");

    public boolean validatePage(String text){
        if(action.isElementPresented(pageTitle)){
            String pageTitleText = action.getText(pageTitle);
            return pageTitleText.contains(text);
        }
        return false;
    }

    public void scrollToLatestNewsPosts(){
        action.scrollToElement(latestNewsPosts);
    }

    public List<WebElement> getLatestVisibleNewsPostCards(){
        List<WebElement> visiblePosts = new ArrayList<>();
        for(WebElement newsCard : action.getElements(newsCards)){
            if(action.isElementPresented(newsCard)){
                visiblePosts.add(newsCard);
            }
        }
        return visiblePosts;
    }

    public String getLatestVisibleNewsPostHeader(WebElement newsCard){
        WebElement header = newsCard.findElement(newsCardsHeader);
        return action.getText(header);
    }

    public String getLatestVisibleNewsPostBody(WebElement newsCard){
        WebElement body = newsCard.findElement(newsCardsBody);
        return action.getText(body);
    }

    public int getSumOfLikeCountForVisiblePosts(List<WebElement> visibleNewsCards){
         int totalLikes = 0;
         for(WebElement visibleNewsCard : visibleNewsCards){
             WebElement likeElement = visibleNewsCard.findElement(likeCount);
             if(!action.isElementPresented(likeElement)){
                 totalLikes += 0;
                 continue;
             }
             String text = action.getText(likeElement);
             int count = Integer.parseInt(text);
             totalLikes += count;
         }
         return totalLikes;
    }




}
