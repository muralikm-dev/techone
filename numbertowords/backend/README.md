# Number to Words Converter

A Spring Boot application that converts numerical values to their word equivalents, such as `123.45` to `ONE HUNDRED AND TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS`.

## Features

- Converts numbers into words, supporting values up to QUINTILLION.
- RESTful API that accepts numerical input and returns the word equivalent.
- Supports both whole numbers and decimal values.
- Easy to deploy as a standalone Java application.
- Fully tested using JUnit

## Prerequisites
* Java and Maven

## Installation
### Run the following commands in the VSS terminal window
* Git clone https://github.com/muralikm-dev/techone.git
* mvn clean install

## Running the Application
### Using Maven
* mvn spring-boot:run

### Using JAR file
#### Build the project
* mvn clean package
#### Run the JAR file
* java -jar target/number-to-words-converter-0.0.1.jar

## Testing the Application
* mvn test

