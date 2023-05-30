package DogExhebition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    
    public static void main(String [] args){
        String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
        Pattern pattern_person_name = Pattern.compile(regex_person_name);
        String examples[] = {"Андрей Богодеев", "Андрей  Догодеев", "Андрей Богодеев-Деев", "Андрей БЛогодеев", "Андрей Богодеев 8", "Андрей Богодеев-Деев 8", "Андрей", "   ", "---", "-", "---///"};
        int i =0;
        for (String ex : examples){
            Matcher matcher = pattern_person_name.matcher(ex);
            System.out.println((i+1)+" "+ex +" : "+ matcher.matches());
            i++;
        }
        
    }
}
