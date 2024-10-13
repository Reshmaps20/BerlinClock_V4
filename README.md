# Berlin Clock
<img src="berlin-clock-image.png" alt="Berlin Clock" width="300" />

## About this Kata
This short and simple Kata should be performed using Test Driven Development (TDD).
## Rules
The Berlin Clock (Mengenlehreclock or Berlin Uhr) is a clock that tells the time using a series of illuminated coloured blocks, as you can see in the picture for this project.

- The **top lamp** blinks to show **seconds**; it is illuminated on **even seconds** and off on **odd seconds**.

- The next two rows represent **hours**:
    - The **upper row** represents **5-hour blocks** and is made up of **4 red lamps**.
    - The **lower row** represents **1-hour blocks** and is also made up of **4 red lamps**.

- The final two rows represent **minutes**:
    - The **upper row** represents **5-minute blocks** and is made up of **11 lamps**—every third lamp is **red**, while the rest are **yellow**.
    - The **bottom row** represents **1-minute blocks** and is made up of **4 yellow lamps**.

## Requirements

- **Java** : 1.8
- **Springboot** : 2.7
- **Maven** : For Dependency management
- **JUnit** : 5.x

## How to Build the Application

- Clone this repository:
   ```bash
   git clone https://github.com/Reshmaps20/BerlinClockTDD1
- Build the project and run the tests by running
    ```bash
    mvn clean install
- The **Model Classes** used in the project are generated from the **OpenAPI** specification during the build process. Running `mvn clean install` will regenerate the models as part of the build.

## Sample Input and Output

### Sample Input

- File: `src/main/resources/sample-input.json`
- Example:
  ```json
  {
    "time": {
      "hours": "23",
      "minutes": "59",
      "seconds": "59"
    }
  }

### Sample Output

- File: `src/main/resources/sample-output.json`
- Example:
  ```json
  {
  "digitalTime": "23:59:59",
  "detailedBerlinTime": {
    "secondsLamp": "O",
    "topFiveHourLamps": "RRRR",
    "bottomOneHourLamps": "RRRO",
    "topFiveMinuteLamps": "YYRYYRYYRYY",
    "bottomOneMinuteLamps": "YYYY"
  },
  "berlinTime": "O RRRR RRRO YYRYYRYYRYY YYYY"
  }
## Test reports
- Once after successful build of
  `mvn clean install`, navigate to target folder of the project root directory
- **Jacoco code coverage report :** Code Coverage report will be availabe in `target\site\jacoco` folder. View the report by launching **index.html**
- **pi test coverage report:** Mutation Coverage report will be avilabe in `target\pit-reports` folder. View the report by launchig **index.html**