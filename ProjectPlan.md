# Project Plan

### Week 2
- [X] Create project repository on GitHub
- [X] Create project structure in intellij and push
- [X] Add link to list of indie projects in student repo.
- [X] Complete Problem Statement
- [X] Weekly reflection/time log

### Week 3

- [X] List technologies, versions and how they will be used
- [x] Write project plan
- [X] Document user stories and select MVP stories
- [x] Confirm MVP stories meet Ent Java indie project objectives
- [x] Design screens - make sure all MVP user stories are covered
- [x] Update journal/time log/reflection

### Week 4 - Class topic is Hibernate and DAOs
#### This week my focus is the “Add Armor Piece” user story

- [ ] Research possible Web Services/APIs to use
- [ ] First cut at database design for armor and power armor
- [ ] Create local dev version of the database
- [ ] Create `ArmorPiece` entity (regular armor)
- [ ] Create `ArmorPieceDao` class with CRUD methods
- [ ] Create config files for DB connection (dev and test)
- [ ] Create a test version of the database for unit testing
- [ ] Create unit tests for `ArmorPieceDao`
- [ ] Create a class for Hibernate SessionFactory management
- [ ] Create JSP form for adding an armor piece
- [ ] Create JSP to display user's added armor pieces
- [ ] Create controller to route to "Add Armor" page
- [ ] Create controller to process add armor requests
- [ ] Update weekly reflection/time log

### Week 5 - Class topic is Hibernate
#### This week my focus is the “Add Power Armor” and “View Collections” user stories

- [ ] Create `PowerArmorPiece` entity (power armor table)
- [ ] Create `PowerArmorPieceDao` class with CRUD methods
- [ ] Create `LegendaryEffect` entity
- [ ] Create `LegendaryEffectDao` with basic CRUD
- [ ] Create `PowerArmorFrame` entity to support frame naming
- [ ] Update dev database with frame and effect relationships
- [ ] Create unit tests for `PowerArmorPieceDao`
- [ ] Create JSP form for adding a power armor piece
- [ ] Add dropdowns or inputs for up to 4 legendary effects
- [ ] Create JSP to display user's power armor pieces
- [ ] Create controller to manage “Add Power Armor” form display
- [ ] Create controller to handle form submission
- [ ] Create controller for “My Armor” page to show both regular and power armor
- [ ] Add search and sort functionality to “My Armor” page
- [ ] Update weekly reflection/time log

### Week 6 - Class topic is Deployment to AWS and Individual Project Reviews
#### This week my focus is setting up the user interface structure and getting peer feedback

- [ ] Create `index.jsp` (landing page with welcome and links to login/register)
- [ ] Create `nav.jsp` (navigation bar include file)
- [ ] Create `login.jsp` (form for user login)
- [ ] Create `register.jsp` (form for user registration)
- [ ] Create `editArmor.jsp` (form for editing regular armor piece)
- [ ] Create `editPowerArmor.jsp` (form for editing power armor piece)
- [ ] Create `error.jsp` (display generic user-friendly error messages)
- [ ] Include `nav.jsp` in existing JSPs (e.g., addArmor, addPowerArmor, myArmor)
- [ ] Participate in peer review session
- [ ] Evaluate suggestions and make a list of any changes to implement
- [ ] Revisit screen design for Add Armor / Add Loadout based on usability
- [ ] Update journal/time log and reflection

### Week 7 - Class topic is Security and Authentication
#### Checkpoint 2 is Due: Database designed and created, at least one DAO with full CRUD (create, read, update, delete) implemented with Hibernate, DAO is fully unit tested, Log4J is implemented (no System.out.printlns)

- [ ] Double-check all checkpoint 2 items (above) are complete and visible in github.
- [ ] Set up Authentication in indie project (more tasks coming for this)
- [ ] Create project DB on AWS.
- [ ] Update project config files for AWS as needed
- [ ] Deploy project to AWS
- [ ] Add deployed link to indie project list in student repo

### Week 8 - Class topic is Web Services Intro
#### 

- [ ] If no useful API is found, define mock API
-
### Week 9 (Start of the team project) - Class topic is RESTFul Web Services
#### Checkpoint 3 is Due: Deployed to AWS, at least one JSP that displays data from the database is implemented, authentication implemented, add AWS deployed app link to indie project list in student repo.
- [ ] Double-check all checkpoint 3 items (above) are complete and visible in github.

### Week 10 - Work Week
#### This week my focus is the team project and polishing core functionality, testing, and beginning loadout-related features.

- [ ] Team Project
- [ ] Refactor controllers and DAOs for clarity and consistency
- [ ] Add client-side validation to armor and power armor forms
- [ ] Begin implementation of “Add Loadout” feature:
    - [ ] Design database tables for loadouts
    - [ ] Create `Loadout` entity
    - [ ] Create `LoadoutDao` with basic CRUD
    - [ ] Create JSP form to create new loadout
    - [ ] Add controller for displaying loadout form
    - [ ] Add controller for handling loadout submission
- [ ] Add success/error messages for form submissions
- [ ] Improve styling/layout of forms and results pages
- [ ] Update journal/time log and reflection

### Week 11 - Work Week
#### This week my focus is the team work and completing loadout functionality, enhancing interactivity, and writing tests.

- [ ] Team Project
- [ ] Complete “Add Loadout” feature if not done
- [ ] Implement “View Loadouts” page:
    - [ ] Create JSP for viewing all loadouts (with filters/toggles)
    - [ ] Create controller to retrieve loadouts by user
- [ ] Begin work on “Edit Loadout”:
    - [ ] Pre-fill form with existing loadout data
    - [ ] Update DAO to support updating loadouts
- [ ] Add delete functionality for loadouts
- [ ] Create unit tests for `LoadoutDao`
- [ ] Update weekly reflection and time log

### Week 12 - Team Project Presentations
####

- [ ] Team Project
- [ ] Update weekly reflection and time log

### Week 13 - Class Topic is Asynchronous Messaging
####

- [ ] Time for unfinished tasks
- [ ] Update weekly reflection and time log

### Week 14 - Individual Project Code Reviews
####

- [ ] Code Review
- [ ] Update weekly reflection and time log

### Week 15
- [ ] Implement Feedback from Week 14 review
- [ ] Final Presentation
- [ ] Create video, add video link to readme.md
- [ ] Finalize all documentation
- [ ] Code quality check
- [ ] Weekly journal entry

### Week 16
- [ ] Weekly journal entry
- [ ] Final touches before code complete
