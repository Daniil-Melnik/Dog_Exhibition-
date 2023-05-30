package DogExhebition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    
    public static void main(String [] args){
        String regex = "^[А-Яа-я]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("Андрей");
        System.out.println("Андрей" +" : "+ matcher.matches());
    }
}
