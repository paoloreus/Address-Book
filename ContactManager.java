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
}
