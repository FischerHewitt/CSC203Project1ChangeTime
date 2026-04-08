//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//[
//[ “Math class at 10:00am”, “Gym at 2:00pm” ],
//[ ],
//[ “Dance class at 9:00am”, “CS class at 11:00am” ],
//[ “Club meeting at 5:00pm” ],
//[ ],
//[ ],
//[ “Go to the beach at 10:00am” ]
//]
import java.util.ArrayList;


public static void main(String[] args) {
    ArrayList< ArrayList<String> > calendar = new ArrayList<>();
    for(int i = 0; i < 7; i++) {
        calendar.add(new ArrayList<>());
    }
    calendar.get(0).add("Math class at 10:00am");
    calendar.get(0).add("Gym at 2:00pm");
    calendar.get(2).add("Dance class at 9:00am");
    calendar.get(2).add("CS class at 11:00am");
    calendar.get(3).add("Club meeting at 5:00pm");
    calendar.get(6).add("Go to the beach at 10:00am");
    System.out.println(calendar);
    testCases(calendar);
}

public static void moveEventTime(ArrayList< ArrayList<String> > calendar,String newTime, String event ){
    Object[] found_dayIdx_eventIdx_eventName_oldTime = getEventTime(calendar, event);
    boolean found = (boolean) found_dayIdx_eventIdx_eventName_oldTime [0];
    int dayIdx = (int) found_dayIdx_eventIdx_eventName_oldTime [1];
    int eventIdx = (int) found_dayIdx_eventIdx_eventName_oldTime [2];
    String eventName = (String) found_dayIdx_eventIdx_eventName_oldTime [3];
    String oldTime = (String) found_dayIdx_eventIdx_eventName_oldTime [4];
    String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday", "Sunday"};

    if (!found){
        System.out.println("Event not found");
    } else {
        boolean isAConflict = checkForConflict(calendar.get(dayIdx), newTime);
        if (isAConflict) {
            boolean running = true;
            do {
                System.out.println("There is a Conflict. Do you still want to add this event? " +
                        "(yes/no)");
                Scanner myScanner = new Scanner(System.in);
                String userInput = myScanner.next();
                if (userInput.toLowerCase().equals("yes")){
                    isAConflict = false;
                    running = false;
                } else if ((userInput.toLowerCase().equals("no"))) {
                    running = false;
                } else {
                    System.out.println("Invalid Argument.");
                }

            } while (running);
        }

        if (!isAConflict) {
            //deleteEvent(calendar, event_name);
            //addEvent(calendar, days_of_the_week[day_idx], event_name, newTime);
            System.out.println("Event Has Been Changed");
        } else {
            System.out.println("No events have been edited");
        }
    }
}




public static Object[] getEventTime(ArrayList<ArrayList<String>> calendar, String event){
    int dayIdx = 0;
    while (dayIdx < 7){
        ArrayList<String> dayItems = calendar.get(dayIdx);
        int eventIdx = 0;
        while (eventIdx < dayItems.size()){

            String[] eventArray = dayItems.get(eventIdx).split(" at ");

            if (eventArray[0].equals(event)) {
                return new Object[]{true, dayIdx, eventIdx, eventArray[0], eventArray[1]};
            }

            eventIdx++;
        }

        dayIdx++;
    }
    return new Object[]{false, -1, -1, "N", "N"};
}

public static boolean checkForConflict(ArrayList<String> dayEvents, String newTime){
    for (int idx = 0; idx < dayEvents.size(); idx++){
        String[] event_array = dayEvents.get(idx).split(" at ");
        if (event_array[1].equals(newTime)){
            return true;
        }
    }
    return false;
}

public static void testCases(ArrayList<ArrayList<String>> calendar){
    Object[] test01 = getEventTime(calendar, "Math class");
    System.out.print(Arrays.toString(test01));
    System.out.println(", [true, 0, 0, Math class, 10:00am]");

    Object[] test02 = getEventTime(calendar, "CS class");
    System.out.print(Arrays.toString(test02));
    System.out.println(", [true, 2, 1, CS class, 11:00am]");

    Object[] test03 = getEventTime(calendar, "CS Class");
    System.out.print(Arrays.toString(test03));
    System.out.println(", [false, -1, -1, N, N]");

    boolean test04 = checkForConflict(calendar.get(2), "9:00am");
    String test04ans = String.valueOf(test04);
    System.out.print(test04ans);
    System.out.println(", true");

    boolean test05 = checkForConflict(calendar.get(0), "9:00am");
    String test05ans = String.valueOf(test05);
    System.out.print(test05ans);
    System.out.println(", false");

    System.out.println("Test: 06 (9:00am, Dance class)");
    moveEventTime(calendar, "9:00am", "Dance class");
    System.out.println(calendar);

    System.out.println("Test: 07 (9:00am, Dance class)");
    moveEventTime(calendar, "9:00am", "Dance class");

    System.out.println("Test: 08 (9:00am, not an event)");
    moveEventTime(calendar, "9:00am", "not an event");
}