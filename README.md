# 🏦 Spring Boot ATM Service API — Spring Boot REST API

> *"First, master the fundamentals. Then, build the architecture that brings them to the world."*

## The Evolution: From Console to Cloud-Ready API
Every great enterprise system starts with solid fundamentals. This project is **Phase 2** of my Java Backend Engineering journey.

In **Phase 1**, this banking system lived solely inside the terminal—a Core Java application built from the ground up to master Object-Oriented Programming (Inheritance, Abstraction, Interfaces, Collections, Exception Handling, and Multithreading).

In **Phase 2 (This Repository)**, that core engine has been refactored and elevated into a live **Spring Boot REST API**. Instead of talking to a local console via `System.out.println`, this application spins up an embedded **Apache Tomcat** server on port `8080`, exposing endpoints that allow web browsers and mobile applications to query, filter, and transact with bank accounts over HTTP in real time.

---

## Tech Stack & Architecture
* **Language:** Java 17
* **Framework:** Spring Boot (Spring Web / Spring MVC)
* **Server:** Embedded Apache Tomcat (`localhost:8080`)
* **Build Tool & Dependency Management:** Apache Maven (`pom.xml`)
* **Serialization:** Jackson (Automatic Java POJO $\leftrightarrow$ JSON conversion)

---

## Core Engineering Concepts Mastered

* **Inversion of Control (IoC) & Spring Boot Bootstrapping:** Using `@SpringBootApplication` and component scanning to manage application lifecycle automatically.
* **RESTful Routing (`@RestController` & `@GetMapping`):** Designing clean, predictable HTTP endpoints for client consumption.
* **JSON Serialization with POJOs:** Structuring encapsulated `Account` models with strict `private` fields and public Getters/Setters for seamless Jackson serialization.
* **Dynamic Path Variables (`@PathVariable`):** Extracting unique resource identifiers directly from URL paths (e.g., `/accounts/{accountNumber}`).
* **Query Filtering & Defaults (`@RequestParam`):** Building flexible search endpoints supporting multi-parameter range filtering, case-insensitive name lookups, and fallback `defaultValue` configurations.

---

## Live API Endpoints

### 1. System Health & Status
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/` | Welcome screen for the Capitec ATM Server |
| `GET` | `/status` | Checks if the ATM server is online and ready to dispense cash |

### 2. Account Lookup & Dynamic Path Routing (`@PathVariable`)
| Method | Endpoint | Description | Example URL |
| :--- | :--- | :--- | :--- |
| `GET` | `/accounts` | Returns a JSON list of all registered bank accounts | `http://localhost:8080/accounts` |
| `GET` | `/accounts/{accountNumber}` | Fetches a single account matching the exact ID | `http://localhost:8080/accounts/ACC-1002` |
| `GET` | `/accounts/{accNum}/balance` | Returns a formatted balance statement for a specific account | `http://localhost:8080/accounts/ACC-1002/balance` |
| `GET` | `/accounts/{accountNumber}/{ownerName}` | Verifies and fetches an account by both ID and Owner Name | `http://localhost:8080/accounts/ACC-1002/Siyabonga%20Mashimane` |

### 3. Search & Financial Filtering (`@RequestParam`)
| Method | Endpoint | Query Parameters | Description | Example URL |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/accounts/nsfas` | `maxBalance` | Filters accounts eligible for NSFAS (balance $\le$ cap) | `/accounts/nsfas?maxBalance=2000` |
| `GET` | `/accounts/owner` | `name` | Case-insensitive partial name search across all account holders | `/accounts/owner?name=Siyabonga` |
| `GET` | `/accounts/search` | `minBalance` | Filters accounts meeting a minimum balance threshold | `/accounts/search?minBalance=10000` |
| `GET` | `/accounts/range` | `min`, `max` (Defaults: `0` to `50000`) | Filters accounts within a specific balance bracket | `/accounts/range?min=1000&max=15000` |

---

## Roadmap & Progress

- [x] **Milestone 1:** Initialize Spring Boot project with Maven & Embedded Tomcat
- [x] **Milestone 2:** Build `@RestController` and serve `Account` POJOs as JSON
- [x] **Milestone 3:** Master `@PathVariable` & `@RequestParam` dynamic filtering
- [ ] **Milestone 4 (In Progress):** State-changing operations (`@PostMapping` & `@RequestBody`) for live deposits, withdrawals, and account creation
- [ ] **Milestone 5:** Refactor business rules into a dedicated `@Service` layer with Dependency Injection
- [ ] **Milestone 6:** Persist accounts permanently to a SQL database using **Spring Data JPA (`@Repository`)**

---

## How to Run Locally

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YOUR-USERNAME/capitec-atm-springboot-api.git](https://github.com/YOUR-USERNAME/capitec-atm-springboot-api.git)