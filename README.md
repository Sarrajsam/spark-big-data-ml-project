# 🚀 Big Data Processing with Apache Spark (Scala)

## 🎓 Project Overview

This project was developed as part of my coursework at the University of Dundee. It demonstrates how to process and analyze data using Apache Spark and Scala.

The project includes multiple tasks such as data processing, machine learning, and pattern discovery.

---

## 🚀 Key Features

* Character counting using Scala
* Frequent pattern mining using FP-Growth
* Decision Tree classification (Iris dataset)
* K-Means clustering (Iris dataset)
* Data processing using Apache Spark

---

## 🛠️ Technologies Used

* Scala
* Apache Spark
* Spark MLlib
* sbt

---

## ⚙️ Project Components

### 1. CharCount

Reads a text file and counts character occurrences.

### 2. FP-Growth

Analyzes transaction data (Bakery dataset) to find frequent itemsets and association rules.

### 3. Decision Tree

Classifies Iris flower species using a Decision Tree model.

### 4. K-Means

Groups Iris data into clusters based on similarity.

---

## 📂 Project Structure

```text
.
├── build.sbt
├── README.md
├── data/
│   ├── Bakery.csv
│   ├── iris.csv
│   └── input.txt
└── src/
    └── main/
        └── scala/
            ├── CharCount.scala
            ├── FPGrowthApp.scala
            ├── DecisionTreeIris.scala
            └── KMeansIris.scala
```

---

## ▶️ How to Run

1. Install Apache Spark and sbt
2. Place datasets in the `data/` folder
3. Run the project:

```bash
sbt run
```

---

## 💡 What I Learned

* Working with Apache Spark
* Building machine learning models in Scala
* Processing large datasets
* Structuring data engineering projects

---

## 👤 Author

Sarraj Alsammak
University of Dundee

---

> This project demonstrates my ability to work with big data tools and machine learning using Scala and Spark.
