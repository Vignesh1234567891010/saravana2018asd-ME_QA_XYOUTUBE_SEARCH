package demo.wrappers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Wrappers(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateTo(String Url){
        driver.get(Url);
    }

    public void click(By locator){

        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(WebElement element){

        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    public void type(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void type(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",element);
        waitFor(700);
    }

    public void scrollToElement(By locator){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",element);
        waitFor(700);
    }


    public void scrollItemUsingArrow(By arrowButton, By itemsLocator){
        int maxScrolls = 5;
        for(int i=0;i<=maxScrolls;i++){
            List<WebElement> items = getElements(itemsLocator);
            if(items.size() >= 16 && items.get(items.size()-1).isDisplayed() && !driver.findElement(arrowButton).isDisplayed()){
                break;
            }
            click(arrowButton);
            waitFor(700);
        }
    }

    public String getDescription(By locator){
        List<WebElement> descriptions = getElements(locator);
        StringBuilder stringBuilder = new StringBuilder();
        for (WebElement description : descriptions) {
            stringBuilder.append(getText(description)).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public void scrollDown(int pixel){
        ((JavascriptExecutor) driver).
                executeScript("window.scrollBy(0, "+pixel+")");
        waitFor(700);
    }


    public void pressEnter(){
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
    }

    public List<WebElement> getElements(By locator){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean isElementPresented(WebElement element){
        try {
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementPresented(By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }


    public String getText(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getText();
    }

    public String getText(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element))
                .getText();
    }

    public void waitForPageLoad(){
        wait.until(driver -> ((JavascriptExecutor) driver).
                executeScript("return document.readyState").equals("complete"));
    }

    public void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibilityOfAllElement(List<WebElement> elements){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitFor(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public long parseView(String text){
        text = text.toLowerCase().replaceAll("views","").trim();

        if (text.endsWith("m")) {
            return (long)(Double.parseDouble(text.replace("m", "")) * 1000000);
        } else if (text.endsWith("k")) {
            return (long)(Double.parseDouble(text.replace("k", "")) * 1000);
        } else {
            return Long.parseLong(text.replaceAll("[^0-9]", ""));
        }
    }
}
