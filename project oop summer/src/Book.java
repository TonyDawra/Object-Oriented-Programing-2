import java.util.regex.Pattern;

public class Book {
    //data fields
    private String title;
    private int edition; //(0,10 default 1)
    private int year; //(1900,2025 default 2025)
    private String ISBN;
    private Person [] authors=new Person[6];
    private String publisher;
    private static int totalNumberOfBooks = 0;


    //constructor
    public Book(String title,Person[]authors,String publisher){
        this(title,1,2025,authors,"",publisher);
        totalNumberOfBooks++;
    }
    public Book(String title,int edition,Person[]authors,String ISBN,String publisher){
        this(title,edition, 2025,authors,ISBN, publisher);
        totalNumberOfBooks++;
    }
    public Book(String title,int edition,int year,Person[]authors,String ISBN,String publisher){
        setBook(title,edition,year,authors,ISBN,publisher);
        totalNumberOfBooks++;
    }

    //setters
    public void setBook(String title,int edition,int year,Person[]authors,String ISBN, String publisher){
        setTitle(title);
        setEdition(edition);
        setYear(year);
        setAuthors(authors);
        setISBN(ISBN);
        setPublisher(publisher);
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title.toLowerCase();;
        }else {
            this.title="Title not decided yet";
        }
    }

    public void setEdition(int edition) {
        if (edition >= 1 && edition <= 10) {
            this.edition = edition;
        } else {
            this.edition = 1;
        }
    }

    public void setYear(int year) {
        if (year >= 1900 && year <= 2025) {
            this.year = year;
        }else {
            this.year = 2025;
        }
    }
    public void setISBN(String ISBN) {
        if (validateISBN(ISBN)) {
            this.ISBN = ISBN;
        } else {
            this.ISBN="00-0000-000-0";
        }
    }


    public void setAuthors(Person[] authors) {
        for (int i=0;i< authors.length;i++){
            this.authors[i]=authors[i];
        }

    }

    public void setPublisher(String publisher) {
        if (publisher != null && !publisher.isEmpty()) {
            this.publisher = publisher.toLowerCase();
        }else{
            this.publisher="No publisher";
        }
    }

    //getters
    public static int getTotalNumberOfBooks() {
        return totalNumberOfBooks;
    }
    public String getTitle() {
        return title;
    }

    public int getEdition() {
        return edition;
    }

    public int getYear() {
        return year;
    }

    public Person[] getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }
    public String getISBN(){
        return ISBN;
    }

    //method to validate the ISBN if the format is correct
    public static boolean validateISBN(String ISBN) {
        String regex = "^\\d{2}-\\d{4}-\\d{3}-(\\d{1,3}|X)$";

        if (!Pattern.matches(regex, ISBN)) {
            return false;
        }
        String isbnDigits = ISBN.replace("-", "");
        int sum = 0;
        for (int i = 0; i < isbnDigits.length(); i++) {
            char c = isbnDigits.charAt(i);
            int value = (c == 'X') ? 10 : Character.getNumericValue(c);
            sum += value * (10 - i);
        }

        return sum % 11 != 0;
    }

    //method toString
    public String toString(){
        String authorsStr = "";
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                authorsStr += authors[i].toString();
            }
        }
        return authorsStr + ",title: " + title + " ,publisher: " + publisher + " ,ISBN: "+ISBN+" ,edition: " + edition + " ,year: " + year;
    }
}
