
public class Person {
    //data fields
    private String firstName;
    private String lastName;

    //constructors
    public Person(){
        this("","");
    }
    public Person(String firstName,String lastName){
        setPerson(firstName,lastName);
    }

    //setters
    public void setPerson(String firstName,String lastName){
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setFirstName(String firstName){
        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
        }else{
            this.firstName = "";
        }

    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);
        }else{
            this.lastName = "";
        }
    }

    //getters
    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //method toString
    public String toString(){
        return lastName+" ,"+firstName.charAt(0)+" ";
    }

    //method equals to check if the names provided equals the one already have
    public boolean equals( Object o){
        if(o instanceof String){
            return this.firstName.equals(((Person)o).firstName) && this.lastName.equals(((Person)o).lastName);
        }
        return false;
    }
}
