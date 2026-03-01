#  Route Deviation Detection System (ML-Powered)

A real-time trip monitoring backend system that detects route deviations using a hybrid approach:

* Rule-based GPS monitoring
* Machine Learning anomaly detection (Isolation Forest)

This project follows a **microservice architecture** where:

* Spring Boot handles trip & GPS management
* A Flask ML service performs anomaly detection

---

##  Architecture Overview

```
Frontend
   ↓
Spring Boot Backend (Port 8080)
   ↓
PostgreSQL / MySQL Database
   ↓
ML Microservice (Flask - Port 8000)
   ↓
Isolation Forest Model
```

### Architecture Patterns Used

* Layered Architecture (Controller → Service → Repository)
* Microservice Architecture
* REST-based Inter-Service Communication
* ML-as-a-Service Pattern

---

## Features

*  Trip creation and management
*  Real-time GPS logging
*  Route polyline storage
*  Machine Learning-based deviation detection
*  Trip status update (ONGOING / COMPLETED / DEVIATED)
*  REST API integration between services

---

##  ML Model Details

* Model: **Isolation Forest**
* Features used:

  * Average speed (m/s)
  * Route length
  * Straight-line distance
  * Efficiency ratio
* Output:

  * `is_anomaly` (true/false)
  * anomaly score

The ML service is implemented using:

* Flask
* scikit-learn
* geopy
* pandas
* polyline

---

##  Tech Stack

### Backend

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Lombok

### ML Service

* Python 3.x
* Flask
* scikit-learn
* pandas
* geopy
* joblib

### Database

* MySQL / PostgreSQL

---

##  How to Run the Project

### 1️ Start ML Service

Navigate to ML directory:

```
python app.py
```

Runs on:

```
http://localhost:8000
```

---

### 2️ Start Spring Boot Backend

```
mvn spring-boot:run
```

Runs on:

```
http://localhost:8080
```

---

##  API Endpoints

###  Create Trip

```
POST /trips
```

###  Send GPS Data

```
POST /gps/{tripId}
```

###  Complete Trip & Trigger ML

```
POST /trips/{id}/complete
```

###  Manual ML Test

```
GET /ml-test/{tripId}
```

---

##  Deviation Detection Flow

1. GPS data is received.
2. Trip is fetched from DB.
3. ML service is called via REST.
4. Model predicts anomaly.
5. Trip status updated accordingly.

---

##  Project Structure

```
spring-backend/
   ├── Controller
   ├── Service
   ├── Repository
   ├── Entity

ml-service/
   ├── app.py
   ├── trainedModel.py
   ├── model.joblib
```

---

##  Future Improvements

* Dockerize both services
* Add API Gateway
* Add authentication (JWT)
* Implement async ML scoring
* Deploy to cloud (AWS / Render / Railway)

---

##  Author

Apurv Sinha
B.Tech Computer Science
ML + Backend Systems Enthusiast

---

##  Why This Project is Special

This is not a basic CRUD application.

It demonstrates:

* Real-time data processing
* ML integration in backend systems
* Microservice-based architecture
* Production-style system design

---

If you find this project useful, feel free to ⭐ the repository!
