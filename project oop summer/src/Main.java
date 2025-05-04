import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Create an array to store books
                Book[] books = new Book[50];

                // Define the file path
                String filePath = "/Users/tonydawra/Downloads/java intellij/project oop/src/bookdatawithisbn.txt";

                // Call method to input data for each book
                inputBooksDataFromFile(books, filePath);

                // Display all books entered by the user
                displayBooks(books);

                // Main menu loop
                while (true) {
                        // Display main menu and get user choice
                        int choice = mainMenu();

                        // Exit the program if user chooses option 4 and call method to output book data to a file
                        if (choice == 4) {
                                System.out.print("Enter the file name for book data: ");
                                String fileName = scanner.nextLine();
                                outputBooksDataIntoFile(books, fileName);
                                System.out.println("Thank you for using the application.");
                                break;
                        }

                        // Handle user choice
                        switch (choice) {
                                case 1:
                                        // Display search menu and get user choice
                                        int searchChoice = searchMenu();
                                        // Perform search based on user's choice
                                        search(searchChoice, books);
                                        break;
                                case 2:
                                        // Allow user to change data for a specific book
                                        changeBook(books);
                                        break;
                                case 3:
                                        // Display all books again
                                        displayBooks(books);
                                        break;
                                default:
                                        System.out.println("Invalid choice. Please try again.");
                        }
                }
        }

        // Method to display all books in the array
        public static void displayBooks(Book[] books) {
                for (int i = 0; i < books.length; i++) {
                        if (books[i] != null) {
                                System.out.println(books[i].toString());
                                System.out.println();
                        }
                }
        }

        // Method to display the main menu and return user's choice
        public static int mainMenu() {
                Scanner scanner = new Scanner(System.in);
                System.out.println();
                System.out.println("Choose one of the options:");
                System.out.println("1. To search for a book");
                System.out.println("2. To change data for a book");
                System.out.println("3. To display the information about a book");
                System.out.println("4. To quit");
                System.out.print("Enter your choice: ");
                return scanner.nextInt();
        }

        // Method to display the search menu and return user's choice
        public static int searchMenu() {
                Scanner scanner = new Scanner(System.in);
                System.out.println();
                System.out.println("Choose one of the search options:");
                System.out.println("1. To search by author's first or last name");
                System.out.println("2. To search by publisher");
                System.out.println("3. To search by year of publishing");
                System.out.print("Enter your choice: ");
                return scanner.nextInt();
        }

        // Method to perform search based on user's choice
        public static void search(int searchChoice, Book[] books) {
                Scanner scanner = new Scanner(System.in);

                // Handle different search options
                switch (searchChoice) {
                        case 1:
                                // Search by author's first or last name
                                System.out.print("Enter author's first or last name: ");
                                String authorName = scanner.nextLine();

                                // Convert first character of author's name to uppercase if it's lowercase
                                if (Character.isLowerCase(authorName.charAt(0))) {
                                        authorName = Character.toUpperCase(authorName.charAt(0)) + authorName.substring(1);
                                }

                                // Array to store search results by author name
                                Book[] result1 = new Book[books.length];
                                int count1 = 0;

                                for (int i = 0; i < books.length; i++) {
                                        if (books[i] != null) {
                                                Person[] authors = books[i].getAuthors();
                                                boolean found = false;
                                                for (int j = 0; j < authors.length && !found; j++) {
                                                        if (authors[j] != null && (authors[j].getFirstName().equals(authorName) || authors[j].getLastName().equals(authorName))) {
                                                                result1[count1++] = books[i];
                                                                found = true;
                                                        }
                                                }
                                        }
                                }

                                // Display search results
                                if (count1 > 0) {
                                        displayBooks(result1);
                                } else {
                                        System.out.println("The book was not found.");
                                }
                                break;

                        case 2:
                                // Search by publisher
                                System.out.print("Enter publisher: ");
                                String publisher = scanner.nextLine();
                                publisher=publisher.toLowerCase();

                                // Array to store search results by publisher
                                Book[] result2 = new Book[books.length];
                                int count2 = 0;

                                for (int i = 0; i < books.length; i++) {
                                        if (books[i] != null && books[i].getPublisher().equals(publisher)) {
                                                result2[count2++] = books[i];
                                        }
                                }

                                // Display search results
                                if (count2 > 0) {
                                        displayBooks(result2);
                                } else {
                                        System.out.println("The book was not found.");
                                }
                                break;

                        case 3:
                                // Search by year of publishing
                                System.out.print("Enter year of publishing: ");
                                int year = scanner.nextInt();

                                // Array to store search results by year
                                Book[] result3 = new Book[books.length];
                                int count3 = 0;

                                for (int i = 0; i < books.length; i++) {
                                        if (books[i] != null && books[i].getYear() == year) {
                                                result3[count3++] = books[i];
                                        }
                                }

                                // Display search results
                                if (count3 > 0) {
                                        displayBooks(result3);
                                } else {
                                        System.out.println("The book was not found.");
                                }
                                break;

                        default:
                                System.out.println("Invalid choice. Please try again.");
                }
        }

        // Method to display the change menu and return user's choice
        public static int changeMenu() {
                Scanner scanner = new Scanner(System.in);
                System.out.println();
                System.out.println("Choose one of the change options:");
                System.out.println("1. To change the title");
                System.out.println("2. To change the edition");
                System.out.println("3. To change the year of publishing");
                System.out.println("4. To change the author's first name");
                System.out.println("5. To change the author's last name");
                System.out.println("6. To change the publisher");
                System.out.print("Enter your choice: ");
                return scanner.nextInt();
        }

        // Method to allow user to change data for a specific book
        public static void changeBook(Book[] books) {
                Scanner scanner = new Scanner(System.in);

                // Prompt user for the title of the book to change
                System.out.print("Enter the title of the book to change: ");
                String titleToChange = scanner.nextLine();

                titleToChange=titleToChange.toLowerCase();

                // Find the index of the book to change
                int indexToChange = -1;
                for (int i = 0; i < books.length; i++) {
                        if (books[i] != null && books[i].getTitle().equals(titleToChange)) {
                                indexToChange = i;
                                break;
                        }
                }

                // If book not found, display message and return
                if (indexToChange == -1) {
                        System.out.println("The book was not found.");
                        return;
                }

                // Display change menu and get user choice
                int changeChoice = changeMenu();

                // Handle different change options
                switch (changeChoice) {
                        case 1:
                                // Change title
                                System.out.print("Enter new title: ");
                                String newTitle = scanner.nextLine();
                                books[indexToChange].setTitle(newTitle);
                                System.out.println("Book information updated:");
                                System.out.println(books[indexToChange].toString());
                                break;

                        case 2:
                                // Change edition
                                System.out.print("Enter new edition: ");
                                int newEdition = scanner.nextInt();
                                scanner.nextLine();
                                books[indexToChange].setEdition(newEdition);
                                System.out.println("Book information updated:");
                                System.out.println(books[indexToChange].toString());
                                break;

                        case 3:
                                // Change year of publishing
                                System.out.print("Enter new year of publishing: ");
                                int newYear = scanner.nextInt();
                                scanner.nextLine();
                                books[indexToChange].setYear(newYear);
                                System.out.println("Book information updated:");
                                System.out.println(books[indexToChange].toString());
                                break;

                        case 4:
                                // Change author's first name
                                int i = displayAuthors(books[indexToChange].getAuthors());
                                System.out.print("Enter the author's number to change (1-" + i + "): ");
                                int authorIndexFirst = scanner.nextInt() - 1;
                                scanner.nextLine();

                                if (authorIndexFirst >= 0 && authorIndexFirst < i) {
                                        System.out.print("Enter new first name: ");
                                        String newFirstName = scanner.nextLine();
                                        books[indexToChange].getAuthors()[authorIndexFirst].setFirstName(newFirstName);

                                        System.out.println("Book information updated:");
                                        System.out.println(books[indexToChange].toString());
                                } else {
                                        System.out.println("Invalid author number.");
                                }
                                break;

                        case 5:
                                // Change author's last name
                                int j = displayAuthors(books[indexToChange].getAuthors());
                                System.out.print("Enter the author's number to change (1-" + j + "): ");
                                int authorIndexLast = scanner.nextInt() - 1;
                                scanner.nextLine();

                                if (authorIndexLast >= 0 && authorIndexLast < j) {
                                        System.out.print("Enter new last name: ");
                                        String newLastName = scanner.nextLine();
                                        books[indexToChange].getAuthors()[authorIndexLast].setLastName(newLastName);

                                        System.out.println("Book information updated:");
                                        System.out.println(books[indexToChange].toString());
                                } else {
                                        System.out.println("Invalid author number.");
                                }
                                break;

                        case 6:
                                // Change publisher
                                System.out.print("Enter new publisher: ");
                                String newPublisher = scanner.nextLine();
                                books[indexToChange].setPublisher(newPublisher);
                                System.out.println("Book information updated:");
                                System.out.println(books[indexToChange].toString());
                                break;

                        default:
                                System.out.println("Invalid choice. Please try again.");
                }
        }

        // Method to display authors and return the number of authors
        public static int displayAuthors(Person[] authors) {
                System.out.println("Authors:");

                int count = 0;
                for (int i = 0; i < authors.length; i++) {
                        if (authors[i] != null) {
                                System.out.println((i + 1) + ". " + authors[i].getFirstName() + " " + authors[i].getLastName());
                                count++;
                        }
                }
                return count;
        }

        // Method to input book data from a file
        public static void inputBooksDataFromFile(Book[] books, String fileName) {
                try {
                        File file = new File(fileName);
                        Scanner fileScanner = new Scanner(file);

                        int bookIndex = Book.getTotalNumberOfBooks();

                        // Read book data from the file
                        while (fileScanner.hasNextLine() && bookIndex < books.length) {
                                // Read each book's data
                                String title = fileScanner.nextLine();
                                //System.out.println("Title: " + title);  // Debug statement

                                int edition = fileScanner.nextInt();
                                //System.out.println("Edition: " + edition);  // Debug statement

                                int year = fileScanner.nextInt();
                               // System.out.println("Year: " + year);  // Debug statement

                                int authorCount = fileScanner.nextInt();
                                //System.out.println("Author Count: " + authorCount);  // Debug statement
                                fileScanner.nextLine(); // Consume the newline

                                Person[] authors = new Person[authorCount];
                                for (int i = 0; i < authorCount; i++) {
                                        String firstName = fileScanner.next();
                                        //System.out.println("Author First Name: " + firstName);  // Debug statement
                                        String lastName = fileScanner.nextLine();
                                        lastName=lastName.trim();
                                       // System.out.println("Author Last Name: " + lastName);  // Debug statement
                                        authors[i] = new Person(firstName, lastName);
                                }

                                String publisher = fileScanner.nextLine();
                                //System.out.println("Publisher: " + publisher);  // Debug statement

                                String isbn = fileScanner.nextLine();
                                //System.out.println("ISBN: " + isbn);  // Debug statement
                                fileScanner.nextLine();

                                if(Book.validateISBN(isbn)) {
                                        // Create new book object and add it to the array
                                        books[bookIndex++] = new Book(title, edition, year, authors, isbn, publisher);
                                }else{
                                        System.out.println("title: " + title + ", edition: " + edition + ", year: " + year + ", isbn: " + isbn + ", and publisher: " + publisher);
                                        for (int i = 0; i < authorCount; i++) {
                                                System.out.println("author "+(i+1)+": " + authors[i].getLastName()+" " + authors[i].getFirstName());
                                        }
                                        System.out.println("this book will not be added");
                                        System.out.println();
                                }
                        }

                        fileScanner.close();
                } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + fileName);
                } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                }

}

        // Method to output book data to a file
        public static void outputBooksDataIntoFile(Book[] books, String fileName) {
                try {
                        PrintWriter writer = new PrintWriter(fileName);

                        // Write book data to the file
                        for (int i = 0; i < books.length; i++) {
                                if (books[i] != null) {
                                        writer.println(books[i].getTitle());
                                        writer.println(books[i].getEdition());
                                        writer.println(books[i].getYear());
                                        writer.println(books[i].getAuthors().length);
                                        Person[] authors = books[i].getAuthors();
                                        for (int j = 0; j < authors.length; j++) {
                                                if (authors[j] != null) {
                                                        writer.println(authors[j].getFirstName());
                                                        writer.println(authors[j].getLastName());
                                                }
                                        }
                                        writer.println(books[i].getPublisher());
                                        writer.println(books[i].getISBN());
                                        writer.println();
                                }
                        }

                        writer.close();
                } catch (FileNotFoundException e) {
                        System.out.println("Error writing to file: " + fileName);
                }
        }

}
