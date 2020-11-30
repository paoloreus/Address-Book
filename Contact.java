/*
* Name: Paolo Tous      -- ID: 101325245
* Name: Yukina Ishiguro -- ID: 101274311
* Name: Satabdi Sangma  -- ID: 101287632

 */
package comp2130_assignment2;


public class Contact {
    
    private String firstName;
    private String lastName;
    private String homePhone;
    private String workPhone;
    private Address homeAddress;
    private String email;
    private MyDate birthday;
    private String notes;
    
    
    public Contact(String firstName, String lastName, String homePhone, String workPhone, Address homeAddress, String email,
            MyDate birthday, String notes){
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.homeAddress = homeAddress;
        this.email = email;
        this.birthday = birthday;
        this.notes = notes;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getHomePhone(){
        return homePhone;
    }
    
    public void setHomePhone(String homePhone){
        this.homePhone = homePhone;
    }
    
    public String getWorkPhone(){
        return workPhone;
    }
    
    public void setWorkPhone(String workPhone){
        this.workPhone = workPhone;
    }
    
    public Address getHomeAddress(){
        return homeAddress;
    }
    
    public void setHomeAddress(Address homeAddress){
        this.homeAddress = homeAddress;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public MyDate getBirthday(){
        return birthday;
    }
    
    public void setBirthday(MyDate birthday){
        this.birthday = birthday;
    }
    
    public String getNotes(){
        return notes;
    }
    
    public void setNotes(String notes){
        this.notes = notes;
    }
    
    
    @Override
    public String toString(){
        String s = "";
        
        s += "First Name: " + firstName + "\n";
        s += "Last Name: " + lastName + "\n";
        s += "Home Phone: " + homePhone + "\n";
        s += "Work Phone: " + workPhone + "\n";
        s += "Home Address: " + homeAddress + "\n";
        s += "Email: " + email + "\n";
        s += birthday + "\n";
        s += "Additional Notes: " + notes;
        return s;
    }
}
