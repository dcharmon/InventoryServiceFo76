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
#### This week my focus is the "Add Armor Piece" user story

- [x] First cut at database design for armor and power armor
- [x] Create local dev version of the database
- [x] Create `UserArmorPiece` entity (regular armor)
- [x] Create config files for DB connection (dev and test)
- [x] Create a test version of the database for unit testing
- [x] Create unit tests for `UserArmorPieceDao`
- [x] Create a class for Hibernate SessionFactory management
- [x] Create JSP form for adding an armor piece
- [x] Create JSP to display user's added armor pieces
- [x] Create controller to route to "Add Armor" page
- [x] Create `index.jsp`
- [x] Create `editArmor.jsp` (form for editing regular armor piece)
- [x] Update weekly reflection/time log

### Week 5 - Class topic is Hibernate Entity Relationships
#### This week my focus is improving the data model by introducing entity relationships

- [x] Refactor `UserArmorPiece` to use `@ManyToOne` relationships
- [x] Create `ArmorType` entity
- [x] Create `ArmorSlot` entity
- [x] Map relationships between:
  - `UserArmorPiece` → `ArmorType`
  - `UserArmorPiece` → `ArmorSlot`
- [x] Update `AddUserArmorPiece` controller to load related entities before saving
- [x] Update `EditUserArmorPiece` controller to work with entity relationships
- [x] Update JSP views to display related entity data instead of IDs
- [x] Update weekly reflection/time log

### Week 6 - Class topic is Deployment to AWS
#### This week my focus is setting up the user interface structure
- [x] Create project DB on AWS.
- [x] Update project config files for AWS as needed
- [x] Deploy project to AWS
- [x] Update journal/time log and reflection

### Week 7 - Class topic is Security and Authentication
#### Checkpoint 2 is Due: Database designed and created, at least one DAO with full CRUD (create, read, update, delete) implemented with Hibernate, DAO is fully unit tested, Log4J is implemented (no System.out.printlns)

- [x] Add Log4J logging to servlet controllers
- [x] Update weekly reflection/time log

### Week 8 - Class topic is Web Services Intro

- [x] Update DAO tests for `ManyToOne` mapping
- [x] Update Add/Edit armor forms to use dropdowns for `ArmorType` and `ArmorSlot`
- [x] Add armor weight class to `ArmorType` and update view
- [x] Set up Authentication in indie project
- [x] Add deployed link to indie project list in student repo

### Week 9 (Start of the team project) - Class topic is RESTFul Web Services
#### Checkpoint 3 is Due: Deployed to AWS, at least one JSP that displays data from the database is implemented, authentication implemented, add AWS deployed app link to indie project list in student repo.

- [x] Configure Apache proxy and enable HTTPS on Elastic Beanstalk
- [x] Implement Cognito authentication flow and supporting classes
- [x] Implement Cognito user persistence with `AppUser`
- [x] Implement user separation for armor pieces
- [x] Create `LegendaryEffect` entity and register with Hibernate (main and test)
- [x] Add star1-4 legendary effect fields to `UserArmorPiece` entity
- [x] Update `AddUserArmorPiece` servlet to load and save legendary effects
- [x] Update add/view armor JSPs to display legendary effect dropdowns and columns
- [x] Update `UserArmorPieceDaoTest` to use `cleandb.sql` records and fix insert method
- [x] Add armor base resistance feature and improve test coverage
- [x] Add `Loadout` entity with `ManyToMany` relationship to `UserArmorPiece`
- [x] Add `ViewLoadouts` and `AddLoadout` servlets
- [x] Add `viewLoadouts.jsp` with resistance totals per loadout
- [x] Add `addLoadout.jsp` with live resistance totals and one-per-slot enforcement
- [x] Update `index.jsp` to show nav links only when logged in
- [x] Register `Loadout` in Hibernate config (main and test)
- [x] Add `LoadoutDaoTest` and update `UserArmorPieceDaoTest` for new seed data
- [x] Update weekly reflection/time log

### Week 10 - Work Week

- [x] Add power armor entity layer - PaSlot, PaType, PaBaseResistance, UserPaFrame, UserPaPiece
- [x] Add CRUD controllers for UserPaPiece and UserPaFrame
- [x] Add power armor JSPs - addUserPaPiece, editUserPaPiece, userPaPieces, addUserPaFrame, editUserPaFrame
- [x] Add loadout type (STANDARD/POWER_ARMOR) to loadout table, entity, and controllers
- [x] Update Loadout to include paFrames via loadout_pa_frame join table
- [x] Register all new PA entities in hibernate.cfg.xml
- [x] Update weekly reflection/time log

### Week 11 - Work Week

- [x] Refactor loadout add/edit pages with custom JSP tags (slotTable.tag, summaryRow.tag)
- [x] Add sticky summary table with live resistance totals to add/edit loadout pages
- [x] Extract JS functions to loadout.js and add loadout.css
- [x] Add navigation, logout, and UI improvements (navbar.jsp, LogOut servlet)
- [x] Add legendary effects page using team API integration
- [x] Refactor entities to use proper Hibernate associations per checkpoint feedback
- [x] Update weekly reflection/time log

### Week 12 - Team Project Presentations

- [x] Add legendary effect dropdowns to editUserArmorPiece.jsp
- [x] Extract star dropdown logic to armorPiece.js
- [x] Refactor move DAOs to instance fields across controllers
- [x] Validate PA slot uniqueness per frame before insert/update
- [x] Add loadout type filter buttons and reposition Add Loadout button
- [x] Update weekly reflection/time log

### Week 13 - Class Topic is Asynchronous Messaging

- [x] Fix DataTables initialization issues
- [x] Refactor loadout type selector to button group
- [x] Move updateTypeSection to loadout.js
- [x] Fix JS execution order by wrapping listeners in document.ready
- [x] Update weekly reflection/time log

### Week 14 - Individual Project Code Reviews

- [x] Add specific error pages (404.jsp, 500.jsp) and fix error.jsp styling
- [x] Configure error mappings in web.xml
- [x] Add CSV export feature for loadouts (ExportLoadout servlet)
- [x] Add DeleteLoadout servlet
- [x] Add Java enums for LoadoutType, WeightClass, SlotGroup
- [x] Fix Hibernate cascade delete error using native SQL in GenericDao
- [x] Externalize database credentials using Maven filtering
- [x] Add V2 improvements list to README
- [x] Update weekly reflection/time log

### Week 15

- [x] Update README with final technology list
- [ ] Code quality check
  - [x] Run QAPlug analysis and document before/after findings report
  - [x] Fix encapsulation warnings - add private final to dao fields
  - [x] Fix design-for-extension warnings - add final to all servlet classes
  - [x] Fix utility class warnings - add private constructors to CognitoJWTParser and SessionFactoryProvider
  - [x] Replace magic numbers with named constants across servlet controllers
  - [x] Reduce cyclomatic complexity by extracting helper methods
  - [x] Extract LoadoutHelper utility class to eliminate code duplication
  - [x] Fix slotGroup String vs enum comparison bug uncovered during refactoring
- [ ] Finalize all documentation
- [ ] Weekly journal entry

### Week 16
- [ ] Final Presentation
- [ ] Create video, add video link to readme.md
- [ ] Weekly journal entry
- [ ] Final touches before code complete