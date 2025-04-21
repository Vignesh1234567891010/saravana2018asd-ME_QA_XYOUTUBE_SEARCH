package demo.pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchPage extends BasePage {
    private final Wrappers action;

    public SearchPage(WebDriver driver){
        super(driver);
        action = new Wrappers(driver);
    }

    private final By contents = By.xpath("//div[@id='contents']//ytd-video-renderer");

    private final By contentViews = By.xpath(".//child::span[contains(text(),'views')]");

    public boolean validatePage(String text){
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains(text);
    }

    public long scrollingPageUntilViewsLessThan10Cr(){
        long totalViews = 0;
        Set<String> seenViews = new HashSet<>();

        while (totalViews < 100000000){
            List<WebElement> videoContents = action.getElements(contents);

            for (WebElement videoContent : videoContents){
                try {
                    WebElement viewElement = videoContent.findElement(contentViews);
                    String viewText = action.getText(viewElement);
                    if(seenViews.contains(viewText)){
                        continue;
                    }
                    seenViews.add(viewText);
                    long views = action.parseView(viewText);
                    totalViews += views;
                    if(totalViews  >= 100000000){
                        break;
                    }
                }catch (Exception e){
                    continue;
                }
                action.scrollToElement(videoContent);
            }

            action.scrollDown(1000);
        }
        return totalViews;

//        while (totalViews < 100000000){
//            List<WebElement> viewElements = action.getElements(views);
//
//            for (WebElement viewElement : viewElements){
//                action.scrollToElement(viewElement);
//                String viewText = action.getText(viewElement);
//                if(seenViews.contains(viewText)){
//                    continue;
//                }
//                seenViews.add(viewText);
//
//                long views = action.parseView(viewText);
//                totalViews += views;
//                if(totalViews >= 100000000){
//                    break;
//                }
//
//            }
//
//            action.scrollDown(1000);
//        }
//        return totalViews;
    }
}
