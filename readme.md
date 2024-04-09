# Localization

## Overview
This JavaFX application demonstrates localization by allowing users to select different languages (English, Farsi, Japanese) and dynamically updating UI components according to the selected language. Users can input their first name, last name, and email, and save the data to a MySQL database.

## Features
- Select different languages from a ComboBox.
- Dynamically update UI components (labels, buttons, prompts) based on the selected language.
- Input fields for first name, last name, and email.
- Save data to a MySQL database with table names based on the selected language.

## Backlog
- The application encountered compatibility issues on Mac devices, preventing it from running smoothly. Further investigation and testing are required to identify and resolve the compatibility issues specific to Mac.
- There were several challenges faced with the database connector and JavaFX integration. The process of establishing a connection to the database and ensuring its seamless integration with JavaFX components proved to be complex and problematic.
- To overcome challenges and understand the implementation better, I referred to online resources that provided solutions or insights into similar localization and database integration problems. Leveraging these resources helped in gaining a deeper understanding of the concepts involved and finding potential solutions.
- Setting up the database posed challenges due to forgetting the process. Refreshing the memory on how to set up and configure the database would have been beneficial to streamline the setup process.

## How to Run
1. Ensure you have Java 8 or higher installed on your system.
2. Set up a MySQL database named `homework`.
3. Adjust the database connection details (`jdbcUrl`, username, password) in the code if necessary.
4. Compile and run the `Localization` class.
5. Select a language from the dropdown, input your data, and click the Save button to save it to the database.

## Requirements
- Java 8 or higher
- MySQL database

## Dependencies
- JavaFX (included in Java 8 and later)
- MySQL Connector/J (for JDBC connection to MySQL)

## Author
Artur Gol.
