# Book Management System

A Java application for managing a collection of books with search, modify, and data persistence capabilities.

## Overview

The Book Management System is a command-line application that allows users to:
- Load book data from text files
- Search for books by author, publisher, or publication year
- Modify book information (title, edition, year, author names, publisher)
- Display book information in a structured format
- Save book data to text files

## Features

- **Book Data Management**: Store and retrieve information about books including titles, authors, editions, publication years, publishers, and ISBN numbers.
- **ISBN Validation**: Validates ISBN numbers according to the specified format (XX-XXXX-XXX-X).
- **Search Functionality**: Search for books using various criteria:
  - Author's first or last name
  - Publisher 
  - Publication year
- **Data Modification**: Modify book details as needed.
- **Data Persistence**: Save and load book data from text files.

## Project Structure

The project consists of three main Java classes:

1. **Book.java**: Represents a book with properties like title, edition, year, ISBN, authors, and publisher.
2. **Person.java**: Represents an author with first name and last name attributes.
3. **Main.java**: Contains the application logic, user interface, and file I/O operations.

## Data Format

The application expects book data in the following format in text files:

```
Title
Edition Year NumberOfAuthors
FirstName1 LastName1
[FirstName2 LastName2]
...
Publisher
ISBN

```

Where:
- `Title`: The book's title
- `Edition`: The edition number (1-10)
- `Year`: The publication year (1900-2025)
- `NumberOfAuthors`: The number of authors for this book
- `FirstNameX LastNameX`: Author names (repeated based on NumberOfAuthors)
- `Publisher`: The book's publisher
- `ISBN`: The book's ISBN in format XX-XXXX-XXX-X

## Usage

### Running the Application

1. Compile the Java files:
   ```
   javac Main.java Book.java Person.java
   ```

2. Run the application:
   ```
   java Main
   ```

### Main Menu Options

Upon running the application, you'll be presented with a menu:

1. Search for a book
2. Change data for a book
3. Display information about books
4. Quit (and save data)

### Search Options

When selecting search (option 1), you can search by:

1. Author's first or last name
2. Publisher
3. Year of publishing

### Change Options

When selecting change (option 2), you can modify:

1. Title
2. Edition
3. Year of publishing
4. Author's first name
5. Author's last name
6. Publisher

## Requirements

- Java Development Kit (JDK) 8 or higher
- Text editor or IDE for Java development

## Implementation Details

### Book Class

The `Book` class includes:
- Data fields for book properties
- Constructors for different initialization scenarios
- Setter methods with validation
- Getter methods
- ISBN validation method
- String representation method

### Person Class

The `Person` class includes:
- Data fields for first and last names
- Constructors
- Setter methods with proper capitalization
- Getter methods
- String representation method
- Equality comparison method

### Main Class

The `Main` class includes:
- User interface through console menus
- Methods for searching and modifying books
- File I/O methods for loading and saving book data

## ISBN Validation

The system validates ISBN numbers according to a specific format (XX-XXXX-XXX-X) and checksum algorithm. Books with invalid ISBNs will not be added to the collection.

## Limitations

- Maximum of 50 books can be stored at once
- Maximum of 6 authors per book
- Publication years must be between 1900 and 2025
- Edition numbers must be between 1 and 10

## Future Enhancements

Potential improvements for future versions:
- Graphical user interface
- Database integration for more efficient storage
- Additional search criteria
- Enhanced ISBN validation for multiple formats
- Support for more book properties (e.g., genre, page count)
- Export capabilities to different file formats
