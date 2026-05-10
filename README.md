# Daniel Harmon Individual Project

### Problem Statement

Managing gear across different sets is a common challenge in inventory heavy games. Players often end up relying on spreadsheets or external notes to keep track of what they own, which pieces belong to which set, and how close they are to completing a full loadout. For my project, I’d like to build a web application that lets players log in, track their gear, organize sets, and identify missing pieces for their loadout, using Fallout 76 as the example game.



### Project Technologies/Techniques

* Security/Authentication
  * AWS Cognito
* Database
  * MySQL 8.4.0
* ORM Framework
  * Hibernate 6.4.3
* Dependency Management
  * Maven
* CSS
  * Bootstrap 3.3.7
* Front End
  * jQuery 3.6.0
  * DataTables 1.10.24
  * JSTL 1.2 / JSP
* Data Validation
  * HTML5 required field validation
  * Server-side servlet validation
* Logging
  * Log4J2 2.25.1
* Hosting
  * AWS Elastic Beanstalk
* Unit Testing
  * JUnit 4.12 / JUnit Jupiter 5
* JSON/Token Parsing
  * Auth0 Java JWT 3.4.1
  * org.json - JSON parsing library
* IDE
  * IntelliJ IDEA
* Web Services consumed using Java
  * FO76 Inventory API (team project REST API)
  * Jackson 2.15.2 for JSON deserialization
  * Apache Commons IO 2.11.0 for HTTP response handling
* Code Quality
  * QAPlug (Checkstyle / PMD) run on the project in Week 15 to identify and address best practice violations

### Design

* [User Stories](DesignDocuments/userStories.md)
* [Screen Design](DesignDocuments/screen.md)


### [Project Plan](ProjectPlan.md)

### Time Log

#### [TimeLog](TimeLog.md)

## V2 Improvements

### Features
- Implement armor set bonus detection — display set bonuses when a user has enough pieces of an armor type in a loadout (data already exists in `armor_set_bonus` table)
- Loadout comparison — select two loadouts and view resistance totals side by side
- Search/filter loadouts by name on the viewLoadouts page
- Show loadout created date on viewLoadouts page

### Technical
- Replace DataTables initialization with a compatible implementation — current table structure causes _DT_CellIndex errors
- Replace native SQL in deleteLoadout with a proper Hibernate cascade configuration using CascadeType.REMOVE

### UI
- Add success/error flash messages after saving, editing, or deleting a loadout
