//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;

public class Main {
    /*

    moveEventTime( myCalendar, “01:00pm”, “Lunch with mum” )
Changes the time of the event Lunch with mum at 12:30pm from12:30pm
to 01:00pm.
     */

    /*
    [
[ “Math class at 10:00am”, “Gym at 2:00pm” ],
[ ],
[ “Dance class at 9:00am”, “CS class at 11:00am” ],
[ “Club meeting at 5:00pm” ],
[ ],
[ ],
[ “Go to the beach at 10:00am” ]
]
     */
}

void moveEventTime(ArrayList<ArrayList<String>> calendar, String newTime, String event) {
    boolean completed = false;
    boolean malformed = false;
    for(int day = 0; day < calendar.size(); day++) {
        for(int date = 0; date < calendar.get(day).size(); date++) {
            String ev = calendar.get(day).get(date);
            String[] evSplit = ev.split(" at ");
            if(evSplit.length == 2) {
                String evName = evSplit[0];
                /*
                i cant implement this until the validation function is ready. but heres some pseudocode

                String evTime = evSplit[1];

                if(!validDate(evTime)) {
                    malformed = true;
                    break;
                }
                 */
                if(evName.equals(event)) {
                    String newEv = event + " at " + newTime;
                    calendar.get(day).set(date,newEv);
                    completed = true;
                    break;
                }
            } else {
                malformed = true;
                break;
            }
        }
        if(completed) {
            break;
        }
        if(malformed) {
            System.out.println("Event string is malformed");
            break;
        }
    }
}

void main() {
    ArrayList<ArrayList<String>> calendar = new ArrayList<>();
    for(int i = 0; i < 7; i++) {
        calendar.add(new ArrayList<>());
    }
    calendar.get(0).add("Math class at 10:00am");
    calendar.get(0).add("Gym at 2:00pm");

    calendar.get(2).add("Dance class at 9:00am");
    calendar.get(2).add("CS class at 11:00am");

    calendar.get(3).add("Club meeting at 5:00pm");

    calendar.get(6).add("Go to the beach at 10:00am");

    System.out.println(calendar.get(0).get(0));
    moveEventTime(calendar,"11:00am","Math class");
    System.out.println(calendar.get(0).get(0));
    moveEventTime(calendar,"12:00pm","Math class");
    System.out.println(calendar.get(0).get(0));
    moveEventTime(calendar,"1:00pm","Math class");
    System.out.println(calendar.get(0).get(0));
    moveEventTime(calendar,"2:00pm","Math class");
    System.out.println(calendar.get(0).get(0));
}
