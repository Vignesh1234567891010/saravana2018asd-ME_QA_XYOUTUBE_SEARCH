package demo;

import demo.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
        ChromeDriver driver;
        Wrappers action;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */

        @Test(priority = 1)
        public void testCase01(){

                System.out.println("Start Test Case: TestCase01");
                action = new Wrappers(driver);
                action.navigateTo("https://www.youtube.com/");
                YouTubeHomePage homePage = new YouTubeHomePage(driver);
                AboutPage aboutPage = new AboutPage(driver);

                Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/", "Url MisMatch");
                System.out.println("Test Step: Successfully Navigated to Url");

                homePage.clickAbout();
                Assert.assertTrue(aboutPage.isAboutDescriptionTitleVisible("About"),"AboutDescription Not Visible");
                System.out.println("About Description: "+aboutPage.getAboutDescription());

                System.out.println("End Test: TestCase01");
                System.out.println();

        }

        @Test(priority = 2)
        public void testCase02(){
                System.out.println("Start Test Case: TestCase02");
                action = new Wrappers(driver);
                action.navigateTo("https://www.youtube.com/");
                YouTubeHomePage homePage = new YouTubeHomePage(driver);
                FilmsPage filmsPage = new FilmsPage(driver);
                SoftAssert softAssert = new SoftAssert();

                Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/", "Url MisMatch");
                System.out.println("Test Step: Successfully Navigated to Url");

                homePage.goToFilms();
                Assert.assertTrue(filmsPage.isTitlePresent("Top selling"),"Test Step: Top selling title is not present");
                System.out.println("Test Step: Title is present in the Page");

                filmsPage.scrollTopSellingSectionRight();
                List<WebElement> movies = filmsPage.getTopSellingMovies();

                WebElement lastMovie = movies.get(movies.size()-1);

                softAssert.assertTrue(filmsPage.isRatedA(lastMovie),"Test Step: Last Movie is not Rated as A or U/A");
                if(filmsPage.isRatedA(lastMovie)){
                        System.out.println("Test Step: Last Movie is Rated as A or U/A");
                }

                softAssert.assertTrue(filmsPage.knownCategory(lastMovie), "Test Step: There is no known category");
                if (filmsPage.knownCategory(lastMovie)){
                        System.out.println("Test Step: There is a known category");
                }

                softAssert.assertAll();
                System.out.println();

        }

        @Test(priority = 3)
        public void testCase03(){

                SoftAssert softAssert = new SoftAssert();
                System.out.println("Start Test Case: TestCase03");
                action = new Wrappers(driver);
                action.navigateTo("https://www.youtube.com/");
                YouTubeHomePage homePage = new YouTubeHomePage(driver);

                Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/", "Url MisMatch");
                System.out.println("Test Step: Successfully Navigated to Url");

                homePage.goToMusic();

                MusicPage musicPage = new MusicPage(driver);
                Assert.assertTrue(musicPage.isPageTitlePresent("Music"),"Test Step: Title is not present");
                System.out.println("Test Step: Title is present in the Page");

                musicPage.scrollToIndiaBiggestHitsContainer();
                musicPage.clickOnShowMoreButton();

                List<WebElement> musicCards = musicPage.getIndiaBiggestHitsMusicCards();

                WebElement lastMusic = musicCards.get(musicCards.size()-1);
                musicPage.scrollToLastMusicCard(lastMusic);
                String lastMusicTitle = musicPage.getMusicTitle(lastMusic);
                System.out.println("Test Step: The name of the playlist on the most right is: "+lastMusicTitle);

                int badgeCount = musicPage.getBadgeCount(lastMusic);
                softAssert.assertTrue(badgeCount <= 50, "The Badge Count is not less than 50");
                System.out.println("Test Step: The Badge Count is less than equal to 50");
                softAssert.assertAll();
                System.out.println();
        }

        @Test(priority = 4)
        public void testCase04(){
                System.out.println("Start Test Case: TestCase04");
                action = new Wrappers(driver);
                action.navigateTo("https://www.youtube.com/");

                YouTubeHomePage homePage = new YouTubeHomePage(driver);
                Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/","Unable to navigate to valid Url");
                System.out.println("Test Step Successfully Navigated to the Url");

                homePage.goToNews();

                NewsPage newsPage = new NewsPage(driver);
                Assert.assertTrue(newsPage.validatePage("News"), "You are not in the News page");
                System.out.println("Test Step: Successfully Navigated to News Page");

                newsPage.scrollToLatestNewsPosts();

                List<WebElement> visibleNewsCards = newsPage.getLatestVisibleNewsPostCards();

                for(int i=0; i<visibleNewsCards.size();i++){
                        WebElement visibleNewsCard = visibleNewsCards.get(i);
                        System.out.println("Test Step: News Header for "+i+": "+newsPage.getLatestVisibleNewsPostHeader(visibleNewsCard));
                        System.out.println("Test Step: News Body for "+i+": "+newsPage.getLatestVisibleNewsPostBody(visibleNewsCard));
                        System.out.println();
                }

                System.out.println("Test Step: Sum of likes of all the visible news Card is: "+newsPage.getSumOfLikeCountForVisiblePosts(visibleNewsCards));
                System.out.println();
        }

        @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class, priority = 5)
        public void testCase05(String keyword){
                System.out.println("Start Test Case: TestCase05");
                driver.get("https://www.youtube.com/");
                YouTubeHomePage homePage = new YouTubeHomePage(driver);

                Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/", "Url MisMatch");
                System.out.println("Test Step: Successfully Navigated to Url");

                homePage.search(keyword);

                SearchPage searchPage = new SearchPage(driver);
                Assert.assertTrue(searchPage.validatePage(keyword), "Unbale to Navigate to searched page");
                System.out.println("Test Step: Successfully Navigated to Searched Page "+keyword);

                long viewCount = searchPage.scrollingPageUntilViewsLessThan10Cr();
                System.out.println(keyword+" page has "+viewCount+" views");
                System.out.println();
        }


        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}