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

static String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

// Main function that assembles calendar and runs the test cases
void main() {
    /*
    ArrayList< ArrayList<String> > calendar = new ArrayList<>();
    for(int i = 0; i < 7; i++) {
        calendar.add(new ArrayList<>());
    }
    calendar.get(0).add("Math class at 10:00am");
    calendar.get(0).add("Gym at 2:00pm");
    calendar.get(2).add("Dance class at 9:00am");
    calendar.get(2).add("CS class at 11:00am");
    calendar.get(3).add("Club meeting at 5:00pm");
    calendar.get(6).add("Go to the beach at 10:00am");*/

    //Creating a Calendar
    ArrayList<ArrayList<String>> weeklyCalendar = new ArrayList<>();
    //Creating a Scanner to take in input values
    Scanner scanner = new Scanner(System.in);
    String input;
    boolean running = true;
    boolean printStatement = false;
    ArrayList<String> Sunday = new ArrayList<>();
    ArrayList<String> Monday = new ArrayList<>();
    ArrayList<String> Tuesday = new ArrayList<>();
    ArrayList<String> Wednesday = new ArrayList<>();
    ArrayList<String> Thursday = new ArrayList<>();
    ArrayList<String> Friday = new ArrayList<>();
    ArrayList<String> Saturday = new ArrayList<>();
    weeklyCalendar.add(Sunday);
    weeklyCalendar.add(Monday);
    weeklyCalendar.add(Tuesday);
    weeklyCalendar.add(Wednesday);
    weeklyCalendar.add(Thursday);
    weeklyCalendar.add(Friday);
    weeklyCalendar.add(Saturday);
    System.out.println("Welcome to the weekly calendar. Pick an option from below by typing its number or type “exit” to terminate the program.\n" +
            "1.\tAdd an event to the calendar\n" +
            "2.\tRemove an event to the calendar\n" +
            "3.\tMove an event from one day to another\n" +
            "4.\tMove an event to a different time\n" +
            "5.\tPrint a day\n" +
            "6.\tPrint the calendar\n");
    while (running) {
        if (printStatement) {
            System.out.println("Would you like to continue with something else? Pick an option from below by typing its number or type exit to terminate the program.\n" +
                    "1.\tAdd an event to the calendar\n" +
                    "2.\tRemove an event to the calendar\n" +
                    "3.\tMove an event from one day to another\n" +
                    "4.\tMove an event to a different time\n" +
                    "5.\tPrint a day\n" +
                    "6.\tPrint the calendar\n");
        }
        printStatement = true;
        input = scanner.nextLine();
        //option validation
        if (input.equalsIgnoreCase("exit")) {
            running = false;
            System.out.println("It was nice to see you! Have a great day.");
//---------------------------------------------------------------------------------------------------------------------
        } else if (input.equals("1")) {
            System.out.println("Great, you want to add an event. What is the day for the event?\n" +
                    "You can type Monday, Tuesday, etc.");
            input = scanner.nextLine();
            //day validation
            int index = stringParser(input);
            while (index < 0) {
                System.out.println("I’m afraid I don’t have a day " + input + ". Try typing again the day.");
                input = scanner.nextLine();
                index = stringParser(input);
            }
            System.out.println("What is the event you want to add on " + dayNames[index] + "?");
            String eventName = scanner.nextLine();
            System.out.println("What time would the event start? You can type 10:00am, 01:00pm, etc.");
            String eventTime = scanner.nextLine();
            //time validation
            addMovedEvent(weeklyCalendar, index, eventName, eventTime);
            System.out.println("Done. " + eventName + " at " + eventTime +" on "+dayNames[index] + " is added on your calendar.");
//---------------------------------------------------------------------------------------------------------------------
        } else if (input.equals("2")) {
//                   weeklyCalendar.removeEvent();
            System.out.println("Calling deleteEvent");
//---------------------------------------------------------------------------------------------------------------------
        } else if (input.equals("3")) {
//                   weeklyCalendar.moveEventDay();
            System.out.println("Calling moveEventDay");
//---------------------------------------------------------------------------------------------------------------------
        } else if (input.equals("4")) {
//                   weeklyCalendar.moveEventTime();
            System.out.println("Calling moveEventTime");
//---------------------------------------------------------------------------------------------------------------------
        } else if (input.equals("5")) {
            System.out.println("Great! Which day do you want to print? You can type Monday, Tuesday, etc.");
            input = scanner.nextLine();
            int index = stringParser(input);
            while (index < 0) {
                System.out.println("I’m afraid I don’t have a day " + input + ". Try typing again the day.");
                input = scanner.nextLine();
                index = stringParser(input);
            }
            printDay(weeklyCalendar, dayNames[index]);
//---------------------------------------------------------------------------------------------------------------------
        } else if (input.equals("6")) {
            printCalendar(weeklyCalendar);
//---------------------------------------------------------------------------------------------------------------------
        } else {
            System.out.println("I’m afraid I don’t have this option. Try typing your option again.");
            printStatement = false;
        }
    }
}

// finds the index of the day of the week inputted Monday being 0, Sunday being 6
static int findIndex (String day) {
    String[] calendarDays = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    int idx;
    for (idx = 0; idx < calendarDays.length; idx++) {
        if (day.equals(calendarDays[idx])){
            break;
        }
    }
    System.out.println(idx);
    return idx;
}

static int stringParser(String input) {//returns an index 0-6 of the day from the input string
    for (int i = 0; i < dayNames.length; i++) {
        if (input.equalsIgnoreCase(dayNames[i])) {
            return i;
        }
    }
    return -1;
}

/**
 * deletes an event from the weekly calendar based on matching the first word of the event string.
 *
 * this method searches through all days in the calendar and compares the first word
 * (before the first space) of each event against the userInputEvent parameter.
 * When a match is found, the entire event is removed from that day.
 *
 * intended implementation:
 * - iterates through each day of the week
 * - for each event in the day, extracts the event by splitting at the " at "
 * - Compares that evnet with the userInputEvent parameter
 * - If a match is found, removes the event and sets removedFlag to true
 * - After searching all days, if any event was removed, prints the updated calendar
 *
 * example usage:
 *   deleteEvent(myCalendar, "Math")  // this wiill delete "Math class at 10:00am" event
 * oh and time complexity is O(n^n))
 */
static void deleteEvent(ArrayList<ArrayList<String>> weeklyCalendar, String userInputEvent){
    if (weeklyCalendar == null){
        System.err.println("no calendar, please check your calendar.");

    } else {
        boolean removedFlag = false;

        for (ArrayList<String> dayOfWeek : weeklyCalendar ) {
            if (dayOfWeek == null){
                System.err.println("dayOfweek does not exist, please check your calender.");

            } else {
                int weekSize = dayOfWeek.size();

                for (int i = 0; i < weekSize; i++){
                    String extractedEventString = dayOfWeek.get(0);
                    //can be "" or null
                    if (extractedEventString.equals("") || extractedEventString == null){
                        //skips over the rest of loop and continues iteration
                        continue;

                    }

                    ArrayList<String> newEventsInDay = new ArrayList <>();

                    for (String event: dayOfWeek){
                        /* docs: https://docs.oracle.com/javase/tutorial/java/data/manipstrings.html
                         *Returns the index of the first (for atIndex) and last (for atLastIndex) occurrence of the specified substring
                         */
                        int splitAt = event.lastIndexOf(" at ");
                        String eventName = event.substring(0, splitAt);
                        String eventRest = event.substring(splitAt);

                        if (eventName.toLowerCase().equals(userInputEvent.toLowerCase())){
                            removedFlag = true;

                        } else {
                            newEventsInDay.add(eventName + eventRest);

                        }
                    }
                }
            }
        }

        if (removedFlag){
            System.out.println("Event Deleted, here is your new calendar!");
            printCalendar(weeklyCalendar);
        } else {
            System.err.println("No events found! please check your input and try again.");
        }
    }
}

/**
 Based on input on day of week, it returns the index integer where its corresponding list of events are located
 Input: String Weekday
 Result: Returns integer which can be used to locate index of array list corresponding to weekday
 Returns: integer
 */
static int checksEventIndex(String dayOfWeek) {
    // Gets given day of the week's associated index
    return DayOfWeek.valueOf(dayOfWeek.toUpperCase()).ordinal();
}


/**
 Method checks if there is already a duplicate event by iterating through specified day
 of the week and comparing the full string. If false, then it is later used in method addEvent
 to add the event
 */
static boolean notHasDuplicate(ArrayList<ArrayList<String>> weeklyCalendar, String dayOfWeek, String eventNew) {
    int indexDay = checksEventIndex(dayOfWeek); // index based on day of week
    ArrayList<String> indexDaySchedule = weeklyCalendar.get(indexDay); //opens arraylist of the day of the week needed to iterate through

    for (String eventDay : indexDaySchedule) {
        if (eventDay.equals(eventNew)) {
            System.out.println("Event already exists in calendar");
            return false;
        }
    }
    return true;
}


/**
 Input: A string of time "10:54pm"
 Result: Converts standard time to military time as an integer to later compare which event time is greater
 Returns: Integer from 0 -> 2400
 */
static int conversionTime(String timeString) {
    String ampm = timeString.substring(timeString.length() - 2);
    String timeWithoutAMPM = timeString.substring(0, timeString.length() - 2);
    int colonIndex = timeWithoutAMPM.indexOf(":");
    int hourTime = Integer.parseInt(timeWithoutAMPM.substring(0, colonIndex)); // 10
    int minuteTime = Integer.parseInt(timeWithoutAMPM.substring(colonIndex + 1)); //54
    if (ampm.contains("am")) {
        if (hourTime == 12) {
            hourTime = 0; // Midnight Time ex 12:06am -> 12 -> 0
        }
    } else {
        if (hourTime != 12) { // deals with the 12:06pm
            hourTime += 12; // ex 5:06pm -> 17:06
        }
    }
    return hourTime * 100 + minuteTime; // 10 * 100 + 54 = 1056 (10:56am)
}


/**
 Compares time so comparison can be used to place new event in the correct order
 Input: New event Time, Event Time Already in List
 Result: Compare if new event should come before or after an event
 Returns: Boolean
 */
static boolean compareTimeWhichGreater(int timeInt1, int timeInt2) {
    return timeInt1 > timeInt2;
}


/**
 Adds event to a specific day of the week and time in ascending order
 Input: day, time, event
 Result: The event is added to the calendar on the day and time specified
 Returns: None, just alters the WeeklyCalendar
 */
static void addEvent(ArrayList<ArrayList<String>> weeklyCalendar, String dayOfWeek, String eventName, String timeOfEvent) {
    String newEvent = eventName + " at " + timeOfEvent; // "Math class at 10:00am"
    // If there is no duplicate event in weekday, then continue to add event
    if (notHasDuplicate(weeklyCalendar, dayOfWeek, newEvent)) {
        int dayToInsertNewEvent = checksEventIndex(dayOfWeek); // gets the index of wanted day of week to later open
        ArrayList<String> eventListOfWeekday = weeklyCalendar.get(dayToInsertNewEvent); // Opens list of desired day of week
        int newTimeOfEventToInteger = conversionTime(timeOfEvent); //10:00 am -> 1000


        // Loops through events in ArrayList on specific Weekday
        for (int i = 0; i < eventListOfWeekday.size(); i += 1) {
            String eventInList = eventListOfWeekday.get(i);


            int indexToGetToTime = eventInList.indexOf(" at ");
            // Gets the time whether it is 4:00pm or 10:00am
            String eventDayTime = eventInList.substring(indexToGetToTime + 4);

            int convertCurrentEventTimeToInt = conversionTime(eventDayTime);
            // if new event is earlier than the current event, then insert the event there
            if (!compareTimeWhichGreater(newTimeOfEventToInteger, convertCurrentEventTimeToInt)) {
                eventListOfWeekday.add(i, newEvent);
                return; // breaks because event added, no need to continue looping through      (return?)
            }
        }
        eventListOfWeekday.add(newEvent);
    }
}

//moves the inputted event if found to a new day
static void moveEventDay (ArrayList<ArrayList<String>> calendar, String sourceDay, String documentationDay, String event) {
    int sDidx = findIndex(sourceDay.toLowerCase());
    int eventLength = event.length();
    for (int i = 0; i < calendar.get(sDidx).size(); i++) { //iterates through the inputted source day
        if (calendar.get(sDidx).get(i).substring(0, eventLength).equals(event)) { //makes the string in the calendar the length of even then checks if it matches
            String time = calendar.get(sDidx).get(i).substring((eventLength + 4)); // finds the time that will be used in the addEvent()
            // vvvvvvv SUPER IMPORTANT NOTE: for regular addEvent, use documentationDay INSTEAD of findIndex(documentationDay) !!!!!!!
            addMovedEvent(calendar, findIndex(documentationDay), event, time);
            // ^^^^^^^ SUPER IMPORTANT NOTE: for regular addEvent, use documentationDay INSTEAD of findIndex(documentationDay) !!!!!!!
            calendar.get(sDidx).remove(i); //removes the event on the original day
            break; // ends the for loop
        }
    }
}

static boolean isValidOption(String option) {

    //checks if the day is valid
    ArrayList<String> valid_option = new ArrayList<>(
            Arrays.asList("1", "2", "3", "4", "5", "6", "7")
    );


    return valid_option.contains(option);
}


static boolean isValidDay(String day) {

    ArrayList<String> valid_days = new ArrayList<>(
            Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    );
    return valid_days.contains(day);
}

//Isolates minutes, hours, and days in order to make sure the input is syntax is a proper time
static void isValidTime(String time) {
    if (!time.contains(":")) {
        System.out.print("invalid syntax");
    }
    String[] parts = time.split(":");

    int hours = Integer.parseInt(parts[0]);
    int minutes = Integer.parseInt(parts[1]);

    String hoursString = String.valueOf(hours);
    String minutesString = String.valueOf(minutes);

    if (hoursString.length() > 2) {
        System.out.print("invalid hours");
    }

    if (minutesString.length() > 2) {
        System.out.print("invalid minutes");
    }

    if (hours > 12 | minutes > 59) {
        System.out.print("number out of range");
    }
}

/*
    Prints events with day and time for each day of the week
    Input: Calendar, Day, Time
    Result: a weekly calendar
    Returns: Printed weekly calendar
*/
static void printCalendar (ArrayList<ArrayList<String>> calendar) {
    System.out.println("Great! Here is your current calendar for this week.\n");
    for (int i = 0; i < calendar.size(); i++) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayList<String> day = calendar.get(i);
        System.out.println(days[i] + ":");
        if (day.isEmpty()) {
            System.out.println("\tNo events");
        }
        for (String event : day) {
            System.out.println("\t" + event); // Prints event
        }
    }
}

static void printDay(ArrayList<ArrayList<String>>calendar, String day) {
    // Initialize a list of Days
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    int dayIndex = -1;
    // Checks list of Days to retrieve the index for a given day
    for (int i = 0; i < days.length; i++) {
        if (days[i].equals(day)) {
            dayIndex = i;
            break;
        }
    }
    // If an index isn't found then an error message is printed
    if (dayIndex == -1) {
        System.out.println("I'm afraid I don't have that day. Try again.");
        return;
    }
    // Retrieves all events within the given day
    ArrayList<String> events = calendar.get(dayIndex);
    // If there are no events, then a message is printed
    if (events.isEmpty()) {
        System.out.println("You don't have any events on" + day + ".");
        return;
    }
    // Else, print out the day and its events
    System.out.println(day + ":");
    for (String event : events) {
        System.out.println("\t" + event);
    }
}

// Moves an event from one time to another within a day
static void moveEventTime(ArrayList< ArrayList<String> > calendar, String newTime, String event ){
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
static String[] getEventTime(ArrayList<ArrayList<String>> calendar, String event){
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
static void delEv(ArrayList<ArrayList<String>> calendar, String event) {
    String[] results = getEventTime(calendar, event);
    boolean found = Boolean.parseBoolean(results[0]);
    int dayIdx = Integer.parseInt(results[1]);
    int dateIdx = Integer.parseInt(results[2]);
    if(found) {
        calendar.get(dayIdx).remove(dateIdx);
    }

}

// Adds an event to the calendar respecting the time of day of other events
static void addMovedEvent(ArrayList< ArrayList <String> > calendar, int dayIdx, String eventName, String newTime){
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
static int getTimeInMinutes(String time){
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
static boolean checkForConflict(ArrayList<String> dayEvents, String newTime){
    for (int idx = 0; idx < dayEvents.size(); idx++){
        String[] event_array = dayEvents.get(idx).split(" at ");
        if (event_array[1].equals(newTime)){
            return true;
        }
    }
    return false;
}

// Test cases to ensure proper functioning of all the functions.
static void testCases(ArrayList<ArrayList<String>> calendar){
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