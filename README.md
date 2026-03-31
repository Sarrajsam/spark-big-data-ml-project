# 🚀 Big Data Processing with Apache Spark (Scala & AWS)

## 🎓 Project Overview
This project was developed as part of my coursework at the University of Dundee. It demonstrates large-scale data processing and machine learning using Apache Spark, Scala, and AWS EMR.

The project includes three main tasks:
- Frequent Pattern Mining
- Classification
- Clustering

It uses real datasets to show how distributed computing frameworks can process data efficiently and generate meaningful insights.

---

## 🚀 Key Features
- Distributed data processing with Apache Spark
- Scala-based Spark applications
- FP-Growth for market basket analysis
- Decision Tree classification on Iris dataset
- K-Means clustering on Iris dataset
- File-based and scalable project structure
- Designed to run locally or in cloud-based Spark environments

---

## 🛠️ Technologies Used
- Scala
- Apache Spark
- Spark MLlib
- AWS EMR
- AWS S3
- sbt

---

## ⚙️ Project Components

### 1. Character Counting
A simple Scala program that reads a text file and counts character occurrences while ignoring whitespace.

### 2. Frequent Pattern Mining
Applied FP-Growth on the Bakery dataset to discover frequent itemsets and association rules.

### 3. Classification
Built a Decision Tree classifier using the Iris dataset to predict flower species from petal and sepal measurements.

### 4. Clustering
Applied K-Means clustering to group Iris flowers into three clusters based on feature similarity.

---

## 📂 Project Structure
```text
.
├── build.sbt
├── README.md
├── data/
│   ├── Bakery.csv
│   └── iris.csv
└── src/
    └── main/
        └── scala/
            ├── CharCount.scala
            ├── FPGrowthApp.scala
            ├── DecisionTreeIris.scala
            └── KMeansIris.scala
