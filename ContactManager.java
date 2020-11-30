/*
* Name: Paolo Tous      -- ID: 101325245
* Name: Yukina Ishiguro -- ID: 101274311
* Name: Satabdi Sangma  -- ID: 101287632

 */
package comp2130_assignment2;


public class ContactManager {
    
    private int numContacts;
    private int maxContacts;
    private Contact[] contactList;
    
    
    public ContactManager(int maxContacts){
        numContacts = 0;
        this.maxContacts = maxContacts;
        contactList = new Contact[maxContacts];
        
    }
    
    public boolean addContact(String firstName, String lastName, String homePhone, String workPhone, Address homeAddress,
    String email, MyDate birthday, String notes){
        
        if(numContacts < maxContacts){
            contactList[numContacts] = new Contact(firstName, lastName, homePhone, workPhone, homeAddress, email, birthday, notes);
            numContacts++;
            return true;
        }
        
        return false;
    }
    
    public Contact[] getContacts(){
        return contactList;
    }
    
    public int getNumContacts(){
        return numContacts;
    }
    
    public Contact findContact(String firstN, String lastN){
        for(int i = 0; i < numContacts; i++){
            if(firstN.equals(contactList[i].getFirstName()) && lastN.equals(contactList[i].getLastName())){
                return contactList[i];
            }
        }
        
        return null;
    }
    
    public boolean deleteContact(String firstN, String lastN){
        
        for(int i = 0; i < numContacts; i++){
            if(firstN.equals(contactList[i].getFirstName()) && lastN.equals(contactList[i].getLastName())){
                contactList[i] = contactList[numContacts - 1];
                numContacts --;
                return true;
            }
        }
        
        return false;
    }
    
    public Contact[] findByCity(String city){
        
        Contact[] temp = new Contact[maxContacts];
        Contact[] listByCity;
        int count = 0;
        
        for(int i = 0; i < numContacts; i++){
            if(city.equals(contactList[i].getHomeAddress().city)){
                temp[count] = contactList[i];
                count++;
            }
        }
        
        listByCity = new Contact[count];
        for(int i = 0; i < count; i++){
            listByCity[i] = temp[i];
        }
        
        if(count > 0){
        return listByCity;
        }
        
        return null;
        
    }
   
   
}
