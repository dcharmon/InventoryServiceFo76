# Time Log

| Week   | Work Type     | Task                                                               | Hours | Notes |
|--------|--------------|--------------------------------------------------------------------|-------|-------|
| Week 2 | Indie Project | Set up repo, problem statement, reflection                        | 3     |       |
| Week 3 | Indie Project | Project plan, MVP stories, screen design, reflection             | 8     |       |
| Week 3 | Class - EJ    | Log4j + JUnit exercises                                           | 3.5   | Branching, logger refactor, puzzle exercise |
| Week 4 | Class - EJ    | Hibernate lecture, DAO overview, and demo                         | 4.75  | Hibernate concepts and walkthrough |
| Week 4 | Indie Project | DB design, Hibernate setup, `UserArmorPiece` entity/DAO, JSP pages | 8     | Implemented add armor piece workflow |
| Week 5 | Class - EJ    | Hibernate relationships (OneToMany), code cleanup, unit testing improvements | 3.5   | Relationship mapping concepts and examples |
| Week 5 | Indie Project | Refactor entities to use `@ManyToOne`, update controllers and JSP views     | 5     | Implemented ArmorType and ArmorSlot relationships |
| Week 6 | Class - EJ    | AWS account setup and deployment exercise                         | 3     | Initial AWS environment configuration |
| Week 6 | Indie Project | Deploy project to AWS Elastic Beanstalk and connect to RDS       | 1     | Initial cloud deployment for indie project |
| Week 7 | Class - EJ    | Security and authentication concepts, Cognito overview           | 1.5   |       |
| Week 7 | Indie Project | Add Log4J logging to servlet controllers                         | 1.5   | Logging added across controllers |
| Week 8 | Class - EJ    | Web services intro, REST concepts, API design                    | 3.5   |       |
| Week 8 | Indie Project | Refactor ManyToOne DAO tests, update armor forms to use dropdowns for ArmorType/ArmorSlot | 5.5   | DAO tests updated, add/edit forms refactored |
| Week 9 | Class - EJ    | RESTful web services                        | 2     |       |
| Week 9 | Indie Project | Configure Apache proxy and enable HTTPS on Elastic Beanstalk     | 2     | Switched from nginx to Apache proxy |
| Week 9 | Indie Project | Implement Cognito user persistence with `AppUser`                | 2     | AppUser entity and session integration |
| Week 9 | Indie Project | Add `LegendaryEffect` entity, update `UserArmorPiece` with star1-4 fields, update JSPs and DAO tests | 2     | Full legendary effects workflow |
| Week 9 | Indie Project | Add armor base resistance feature and improve test coverage       | 4     | Resistance fields on entity, updated seed data |
| Week 9 | Indie Project | Add loadout feature with view and add functionality              | 3     | `Loadout` entity, ManyToMany, `addLoadout.jsp`, `viewLoadouts.jsp`, live resistance totals |
| Week 10 | Indie Project | Add power armor to database, expand legendary effects, add set bonus tables | 2.5   | |
| Week 10 | Indie Project | Add power armor entity layer, CRUD controllers, and JSPs for PA pieces and frames | 6 | PaSlot, PaType, UserPaFrame, UserPaPiece entities and full CRUD |
| Week 10 | Indie Project | Add loadout type (STANDARD/POWER_ARMOR) and update Loadout entity and controllers | 2 | |
| Week 11 | Indie Project | Refactor loadout add/edit pages with custom JSP tags, sticky summary table, loadout.js and loadout.css | 5 | slotTable.tag, summaryRow.tag, live resistance totals |
| Week 11 | Indie Project | Add legendary effects page using team API integration | 3 | ViewLegendaryEffects servlet, LegendaryEffectDao, viewLegendaryEffects.jsp |
| Week 11 | Indie Project | Add navigation, logout, and UI improvements | 5 | navbar.jsp, LogOut servlet, jumbotron index layout |
| Week 12 | Indie Project | Refactor star dropdown logic to armorPiece.js, fix editUserArmorPiece.jsp, move DAOs to instance fields | 3 | |
| Week 12 | Indie Project | Validate PA slot uniqueness per frame, add loadout type filter buttons | 2 | |
| Week 13 | Indie Project | Fix DataTables issues, refactor loadout type selector to button group, fix JS execution order | 3 | Consolidated listeners into single document.ready block |
| Week 14 | Indie Project | Add specific error pages and configure web.xml error mappings | 1 | 404.jsp, 500.jsp, error.jsp styling fix |
| Week 14 | Indie Project | Add CSV export feature for loadouts | 1.5 | ExportLoadout servlet, file download via HTTP response streaming |
| Week 14 | Indie Project | Add DeleteLoadout servlet and fix Hibernate cascade delete issue | 1.5 | Native SQL workaround for bidirectional ManyToMany cascade |
| Week 14 | Indie Project | Add Java enums, externalize DB credentials, update README and project plan | 2 | LoadoutType, WeightClass, SlotGroup enums, Maven filtering for hibernate.cfg.xml |
| Week 15 | Indie Project | Run QAPlug code quality analysis and fix findings | 3 | Fixed encapsulation, design-for-extension, magic numbers, cyclomatic complexity warnings |
| Week 15 | Indie Project | Refactor LoadoutHelper utility class and fix slotGroup comparison bug | 2 | Extracted shared logic from AddLoadout and EditLoadout, fixed String vs enum comparison |