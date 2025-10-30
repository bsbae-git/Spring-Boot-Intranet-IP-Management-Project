# Jules IPAM: Spring Boot Intranet IP Management System

## 1. Final Summary Report

This document marks the successful completion of the Jules IPAM project. All core functionalities as requested through the 14 development stages have been implemented, tested, and verified. The system is now stable and ready for deployment.

### Key Features Implemented:
- **Network Management**: Register CIDR-based network blocks. The system automatically calculates and populates all IP addresses within the block.
- **IP Allocation & Release**: Administrators can allocate available IPs to users with a specific purpose and release them back to the available pool.
- **Dynamic IP Search**: Search and filter the IP list by various criteria such as IP address, status, or assigned user. Pagination is supported.
- **Security**: Robust security model implemented using Spring Security, featuring:
    - **Authentication**: Secure login via form-based authentication.
    - **Role-Based Access Control (RBAC)**: Pre-defined `ADMIN` and `USER` roles to control access to different APIs.
- **Stability & Concurrency**: Implemented pessimistic locking (`FOR UPDATE`) to prevent race conditions during concurrent IP allocation requests.
- **Standardized API**: All API responses are standardized for consistency, and a global exception handler provides clear error messages.
- **Validation**: Server-side validation is in place for API inputs to ensure data integrity.

## 2. Getting Started

### Prerequisites
- Java 17 or higher
- Apache Maven 3.6 or higher

### Build Instructions
To build the project and create an executable JAR file, run the following command from the project root directory:
```bash
mvn clean install
```
This command will compile the code, run tests, and package the application into `target/ipam-0.0.1-SNAPSHOT.jar`.

### How to Run the Application
Once the project is built, you can run the application using the following command:
```bash
java -jar target/ipam-0.0.1-SNAPSHOT.jar
```
The application will start on the default port `8080`.

## 3. User Accounts & Access

### Default Admin Account
A default administrator account is created on startup. You can use this account to manage networks and IP allocations.
- **Username**: `admin`
- **Password**: `password`

### Creating Additional Accounts
To add more user accounts, you can insert records directly into the `"user"` table by modifying the `src/main/resources/data.sql` file. Be sure to generate a new BCrypt hash for each password for security.

## 4. Core API Endpoints

All endpoints are protected and require authentication.

| Method | Endpoint                 | Required Role | Description                                       |
|--------|--------------------------|---------------|---------------------------------------------------|
| `POST` | `/api/networks`          | `ADMIN`       | Registers a new network block from a CIDR.        |
| `POST` | `/api/ips/allocate`      | `ADMIN`       | Allocates a specific IP address to a user.        |
| `POST` | `/api/ips/release`       | `ADMIN`       | Releases a previously allocated IP address.       |
| `GET`  | `/api/ips`               | `USER`, `ADMIN` | Searches for IP addresses with filter criteria.   |

A basic frontend example for consuming these APIs can be found in `src/main/resources/static/index.html`.
