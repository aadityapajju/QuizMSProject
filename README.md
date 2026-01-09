Quiz Application – Microservices API Documentation

This repository contains a Spring Boot microservices–based Quiz Application integrated using Spring Cloud Eureka (Service Discovery) and Spring Cloud Gateway (API Gateway).

🧩 Architecture Overview

The system follows a microservices architecture:

Eureka Server – Service discovery,
API Gateway – Single entry point for all client requests,
Question Service – Manages quiz questions,
Quiz Service – Manages quizzes and scoring,
All services register themselves with Eureka and are accessed via the API Gateway.

🛠️ Tech Stack

Java 17,
Spring Boot,
Spring Data JPA,
Spring Cloud (Eureka, Gateway),
MySQL,
REST APIs,
🌐 Service Registry (Eureka)

Eureka Dashboard: http://localhost:8761

Registered services:

APIGATEWAY,
QUESTION-SERVICE,
QUIZ-SERVICE,
🚪 API Gateway.

All APIs should be accessed via the API Gateway.

Gateway Base URL

http://localhost:9999
📘 Question Service APIs

Service Name: QUESTION-SERVICE

1️⃣ Get Welcome Message
GET /question/hello
2️⃣ Get All Questions
GET /question/allQuestions
3️⃣ Get Questions by Category
GET /question/category/{category}

Example

GET /question/category/Java
4️⃣ Add a New Question
POST /question/addQuestions

Request Body

{
  "category": "Java",
  "difficultyLevel": "Easy",
  "questionTitle": "Which keyword is used to inherit a class in Java?",
  "option1": "extends",
  "option2": "implements",
  "option3": "super",
  "option4": "this",
  "rightAnswer": "extends"
}
📗 Quiz Service APIs

Service Name: QUIZ-SERVICE

1️⃣ Create Quiz
POST /quiz/create

Request Parameters

quizTitle
category
numQ

Example

POST /quiz/create?quizTitle=JavaTest&category=Java&numQ=5
2️⃣ Get Quiz Questions (Without Answers)
GET /quiz/get/{quizId}

Returns questions wrapped using QuestionWrapper (answers are hidden).

3️⃣ Submit Quiz & Calculate Score
POST /quiz/submit/{quizId}

Request Body

[
  { "id": 1, "response": "extends" },
  { "id": 2, "response": "5" },
  { "id": 3, "response": "ArrayList" }
]

Response

Score (Integer)
📦 Database Tables

Question Table:

id,category,difficultyLevel,
questionTitle,
option1,
option2,
option3,
option4,
rightAnswer.

Quiz Table: 
id,
title,
List.
