/*
* Name: Paolo Tous      -- ID: 101325245
* Name: Yukina Ishiguro -- ID: 101274311
* Name: Satabdi Sangma  -- ID: 101287632

 */
package comp2130_assignment2;


public class Address {
    
    public String streetInfo1;
    public String streetInfo2;
    public String city;
    public String postalCode;
    public String province;
    public String country;
    
    public Address(String st1, String st2, String city, String postCode, String prov, String country){
        streetInfo1 = st1;
        streetInfo2 = st2;
        this.city = city;
        postalCode = postCode;
        province = prov;
        this.country = country;
    }
    
    
    @Override
    public String toString(){
        String s = "";
        
        s += "Address: ";
        s += "Street: " + streetInfo1 + " " + streetInfo2 + "\n";
        s += "City: " + city + "\n";
        s += "Postal Code: " + postalCode + "\n";
        s += "Province: " + province + "\n";
        s += "Country: " + country;
        return s;
    }
}
