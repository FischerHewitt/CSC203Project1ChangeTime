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

// Main function that assembles calendar and runs the test cases
void main() {
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

// Moves an event from one time to another within a day
public static void moveEventTime(ArrayList< ArrayList<String> > calendar, String newTime, String event ){
    String[] found_dayIdx_eventIdx_eventName_oldTime = getEventTime(calendar, event);
    boolean found = Boolean.parseBoolean(found_dayIdx_eventIdx_eventName_oldTime[0]);
    int dayIdx = Integer.parseInt(found_dayIdx_eventIdx_eventName_oldTime[1]);
    String eventName = found_dayIdx_eventIdx_eventName_oldTime[3];

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
            delEv(calendar, eventName);
            addMovedEvent(calendar, dayIdx, eventName, newTime);
            System.out.println("Event Has Been Changed");
        } else {
            System.out.println("No events have been edited");
        }
    }
}

// Gets information about an event including its current time and index in the 2D calendar ArrayList
public static String[] getEventTime(ArrayList<ArrayList<String>> calendar, String event){
    int dayIdx = 0;
    while (dayIdx < 7){
        ArrayList<String> dayItems = calendar.get(dayIdx);
        int eventIdx = 0;
        while (eventIdx < dayItems.size()){

            String[] eventArray = dayItems.get(eventIdx).split(" at ");

            if (eventArray[0].equals(event)) {
                return new String[]{"true", String.valueOf(dayIdx), String.valueOf(eventIdx), eventArray[0], eventArray[1]};
            }


            eventIdx++;
        }

        dayIdx++;
    }
    return new String[]{"false", "-1", "-1", "N", "N"};
}

// Removes an event from the calendar
public static void delEv(ArrayList<ArrayList<String>> calendar, String event) {
    String[] results = getEventTime(calendar, event);
    boolean found = Boolean.parseBoolean(results[0]);
    int dayIdx = Integer.parseInt(results[1]);
    int dateIdx = Integer.parseInt(results[2]);
    if(found) {
        calendar.get(dayIdx).remove(dateIdx);
        // dont need System.out.println("Event '" + event + "' removed");
    } /* else {
        System.out.println("Event not found. Couldn't delete.");
    } we should not need these either*/
}

// Adds an event to the calendar respecting the time of day of other events
public static void addMovedEvent(ArrayList< ArrayList <String> > calendar, int dayIdx, String eventName, String newTime){
    ArrayList <String> dayEvents = calendar.get(dayIdx); //Gets the days of the events
    int eventIdx = 0; // each invent in the day idx
    int newTimeMinutes = getTimeInMinutes(newTime); // Time the event is going to be changed
    boolean to_big = false; // becomes true when our newTime becomes greater than the time of the event we are currently looking at
    while (eventIdx < dayEvents.size() & !to_big){
        String[] eventArray = dayEvents.get(eventIdx).split(" at "); //current event
        int currentEventMintues = getTimeInMinutes(eventArray[1]);
        if (currentEventMintues > newTimeMinutes){
            to_big = true;
        } else {
            eventIdx++;
        }
    }
    calendar.get(dayIdx).add(eventIdx, eventName + " at " + newTime);
}

// Gets the number of minutes at a specific time. Ex: 1:49pm = (13*60) + 49 = 829 minutes
public static int getTimeInMinutes(String time){
    String[] parts = time.split(":");
    String AMorPM = parts[1].substring(2);
    int hours = Integer.parseInt(parts[0]);
    int minutes = Integer.parseInt(parts[1].substring(0,2));
    if (AMorPM.toLowerCase().equals("am")){ // 12:00am - 11:59am
        if (hours < 12){     //1:00am - 11:59am
            return hours * 60 + minutes;
        } else {    //12:00am - 12:59 am
            return minutes;
        }
    } else { // 12:00pm - 11:59pm
        if (hours < 12){ //1:00 pm - 11:59pm
            return (hours + 12) * 60 + minutes;
        } else { //12:00pm - 12:59pm
            return hours * 60 + minutes;
        }
    }
}

// Checks and returns true/false if there is another event happening at a given time
public static boolean checkForConflict(ArrayList<String> dayEvents, String newTime){
    for (int idx = 0; idx < dayEvents.size(); idx++){
        String[] event_array = dayEvents.get(idx).split(" at ");
        if (event_array[1].equals(newTime)){
            return true;
        }
    }
    return false;
}

// Test cases to ensure proper functioning of all the functions.
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
    System.out.println();

    System.out.println(calendar);
    System.out.println("Test: 06 (10:00am, Dance class)");
    moveEventTime(calendar, "10:00am", "Dance class");
    System.out.println(calendar);

    System.out.println("Test: 07 (2:00pm, Math class)");
    moveEventTime(calendar, "2:00pm", "Math class");
    System.out.println(calendar);

    System.out.println("Test: 08 (9:00am, not an event)");
    moveEventTime(calendar, "9:00am", "not an event");
    System.out.println(calendar);

    int test09 = getTimeInMinutes("12:00pm");
    String test09ans = String.valueOf(test09);
    System.out.print(test09ans);
    System.out.println(", 720");

    int test10 = getTimeInMinutes("12:01am");
    String test10ans = String.valueOf(test10);
    System.out.print(test10ans);
    System.out.println(", 1");

    int test11 = getTimeInMinutes("3:05am");
    String test11ans = String.valueOf(test11);
    System.out.print(test11ans);
    System.out.println(", 185");

    int test12 = getTimeInMinutes("11:20pm");
    String test12ans = String.valueOf(test12);
    System.out.print(test12ans);
    System.out.println(", 1400");

    int test13 = getTimeInMinutes("1:49pm");
    String test13ans = String.valueOf(test13);
    System.out.print(test13ans);
    System.out.println(", 829");
}