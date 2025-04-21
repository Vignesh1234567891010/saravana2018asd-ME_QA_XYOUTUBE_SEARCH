# Youtube Automation Suite

## OverView:
This project involves automating YouTube to check the About Page,Films, Music and News tab and Used DataProvider for Search for various items from data-set.

![17452157772181485877736930740312](https://github.com/user-attachments/assets/09082bb2-6690-499d-881b-8d54a846dd7e)


## Scope of Work

### Automated the following Test Cases:

1. Go to YouTube.com and Assert you are on the correct URL. Click on "About" at the bottom of the sidebar, and print the message on the screen.
![17452160195571010643696162881408](https://github.com/user-attachments/assets/a92b4894-c9d4-46de-8b68-e7294a8d06e7)



3. Go to the "Films" or "Movies" tab and in the “Top Selling” section, scroll to the extreme right. Apply a Soft Assert on whether the movie is marked “A” for Mature or not. Apply a Soft assert on the movie category to check if it exists ex: "Comedy", "Animation", "Drama".
<img width="474" alt="17452159106205274185029177256520" src="https://github.com/user-attachments/assets/92ef2321-7994-43be-aeeb-dba66f95eba9" />


4. Go to the "Music" tab and in the 1st section. Print the name of the playlist on the most right. Soft Assert on whether the number of tracks listed is less than or equal to 50.
<img width="676" alt="17452160681683349816786563652899" src="https://github.com/user-attachments/assets/f7e0a739-803b-4103-9d51-e98339c9ed7d" />


5. Go to the "News" tab and print the title and body of the 1st 3 “Latest News Posts” along with the sum of the number of likes on all 3 of them. No likes given means 0.
![17452161001165099417253826216570](https://github.com/user-attachments/assets/cba32408-a596-4e70-93f5-ef9c12e4430b)


6. Search for each of the items given in the stubs: src/test/resources/data.xlsx, and keep scrolling till the sum of each video’s views reach 10 Cr.
<img width="125" alt="17452161201428693714897805456415" src="https://github.com/user-attachments/assets/bdf245e3-3458-46f9-8f69-4245cc89edde" />


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
