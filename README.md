# Java Service Validation · JUnit Testing

Three small Java services for managing contacts, tasks and appointments in memory. The project focuses on turning requirements into field validation and tests, including boundary values, rejected input, duplicate IDs and service operations.

**Available here:** the full source, JUnit tests and a Maven build. This is a testing project with no user interface, database or external course framework.

## What the project demonstrates

The domain classes validate their own data, while the services manage records by ID using `HashMap` storage. That separation keeps the validation in one place and lets the tests exercise both individual objects and service behavior.

| Domain | Main rules and operations |
|---|---|
| Contact | Immutable ID; name and address length limits; exactly ten phone digits; add, retrieve, delete and update fields |
| Task | Immutable ID; name and description length limits; add, retrieve, delete and update fields |
| Appointment | Immutable ID; non-null date that is not in the past when assigned; description length limit; add, retrieve and delete |

Duplicate IDs and missing-record operations are rejected. The tests cover valid inputs as well as values at and beyond the permitted boundaries. Empty strings remain allowed where the original requirements specified only a maximum length and non-null value.

## Run the tests

Install **JDK 17 or newer** and **Apache Maven 3.9 or newer**. From the repository root, run:

```bash
mvn test
```

Maven downloads the pinned build plugins and JUnit dependencies on the first run. No Eclipse project setup is required. Test reports are written to `target/surefire-reports/`.

The source is in [src/main/java](src/main/java), and the tests are in [src/test/java](src/test/java). The original class names are retained so the relationship between a service and its tests is easy to follow.

## Portfolio cleanup

The original suite contains 85 tests across Contact, Task and Appointment. This edition adds four appointment-date regression tests, bringing the suite to **89 tests**.

Verified on October 1, 2026 with Java 17 and Maven 3.9.11: **89 tests passed, zero failures, zero errors and zero skipped tests**. No new coverage measurement was taken.

The focused code change concerns `Date`, which is mutable. Previously, changing a date supplied to an appointment, or returned by its getter, could change the stored appointment without passing validation. The appointment now copies dates on assignment and retrieval. The added tests check both paths, the constructor, and preservation of the previous value after a rejected update.

## Original test results

These screenshots show the original Eclipse run: **85 tests, zero errors and zero failures**, with **82.9% overall instruction coverage**. They are historical evidence, not a coverage measurement of the updated edition.

![Original JUnit run with 85 passing tests](docs/images/java-junit-test-results.png)

![Original coverage summary across the three service domains](docs/images/java-junit-coverage-summary.png)

## Scope and limitations

The services store records only for the lifetime of the process. They are not persistent or designed for concurrent access. Appointment dates are checked when assigned; an appointment naturally becomes a past appointment as time passes. There is no scheduling, reminder or conflict-detection system.

The project demonstrates a small validation and testing design. It does not claim that passing these tests or achieving a particular coverage percentage establishes correctness in every circumstance.

## Background and contribution

This work began as my SNHU CS-320 software testing coursework. I implemented the Contact, Task and Appointment classes and their services, then developed the JUnit tests around the required behavior and input constraints.

The portfolio edition preserves that implementation with the date-copying fix, additional regression tests and a standalone build. AI assistance was used for the portfolio review, cleanup, documentation and test preparation. Course instructions and the original repository history are not included.
