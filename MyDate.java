/*
* Name: Paolo Tous      -- ID: 101325245
* Name: Yukina Ishiguro -- ID: 101274311
* Name: Satabdi Sangma  -- ID: 101287632

 */

package comp2130_assignment2;


public class MyDate {

private int day;
private int month;
private int year;

public MyDate(int d, int m, int y){
    day = d;
    month = m;
    year = y;
}

public int getDay(){
    return day;
}

public void setDay(int d){
    day = d;
}

public int getMonth(){
    return month;
}

public String getMonthShortForm(){
    if(month == 1){ return "Jan"; }
    else if(month == 2){ return "Feb"; }
    else if(month == 3){ return "Mar"; }
    else if(month == 4){ return "Apr"; }
    else if(month == 5){ return "May"; }
    else if(month == 6){ return "Jun"; }
    else if(month == 7){ return "Jul"; }
    else if(month == 8){ return "Aug"; }
    else if(month == 9){ return "Sep"; }
    else if(month == 10){return "Oct"; }
    else if(month == 11){return "Nov"; }
    else if(month == 12){return "Dec"; }
    
    else {
        return "Not Applicable";
    }
}

public String getMonthLongForm(){
      if(month == 1){ return "January"; }
    else if(month == 2){ return "February"; }
    else if(month == 3){ return "March";    }
    else if(month == 4){ return "April";    }
    else if(month == 5){ return "May";      }
    else if(month == 6){ return "June";     }
    else if(month == 7){ return "July";     }
    else if(month == 8){ return "August";   }
    else if(month == 9){ return "September";}
    else if(month == 10){return "October";  }
    else if(month == 11){return "November"; }
    else if(month == 12){return "December"; }
    
    else {
        return "Not Applicable";
    }
    
}

public void setMonth(int m){
    month = m;
}

public int getYear(){
    return year;
}

public void setYear(int y){
    year = y;
}

@Override
public String toString(){
    String s = "";
    
    s += "Date of Birth: ";
    s += getMonthLongForm() + " " + day + ", " + year;
    return s;
}
}
