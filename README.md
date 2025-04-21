# Youtube Automation Suite

## OverView:
This project involves automating YouTube to check the About Page,Films, Music and News tab and Used DataProvider for Search for various items from data-set.

## Scope of Work

### Automated the following Test Cases:

1. Go to YouTube.com and Assert you are on the correct URL. Click on "About" at the bottom of the sidebar, and print the message on the screen.

2. Go to the "Films" or "Movies" tab and in the “Top Selling” section, scroll to the extreme right. Apply a Soft Assert on whether the movie is marked “A” for Mature or not. Apply a Soft assert on the movie category to check if it exists ex: "Comedy", "Animation", "Drama".

3. Go to the "Music" tab and in the 1st section. Print the name of the playlist on the most right. Soft Assert on whether the number of tracks listed is less than or equal to 50.

4. Go to the "News" tab and print the title and body of the 1st 3 “Latest News Posts” along with the sum of the number of likes on all 3 of them. No likes given means 0.

5. Search for each of the items given in the stubs: src/test/resources/data.xlsx, and keep scrolling till the sum of each video’s views reach 10 Cr.

## Tech Stack
- **Programming Language:** Java
- **Build Tool:** Gradle
- **Testing Framework:** TestNG
- **Automation Tool:** Selenium WebDriver
- **Data Handling:** Apache POI (for Excel)
- **Assertions:** HardAssert & SoftAssert (TestNG)
- **Waits:** WebDriverWait
- **Design Pattern:** Page Object Model(POM)

## Features
- POM-based structure for better maintenance
- WebDriverWait for handling dynamic elements
- Hard and soft assertions for flexible verifications

## How To Run

### 1. Clone the Repository
```bash
    git clone git@github.com:Vignesh1234567891010/saravana2018asd-ME_QA_XYOUTUBE_SEARCH.git
```

### 2.Change to directory
    cd saravana2018asd-ME_QA_XYOUTUBE_SEARCH

### 3.Run Build command to Build the dependencies
```bash
    ./gradlew build   
   ```

### 4.Run Test
```bash
    ./gradlew test
```
