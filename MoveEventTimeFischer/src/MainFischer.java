//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;


public static void main(String[] args) {
    ArrayList<ArrayList<String>> calendar = new ArrayList<>()
    calendar.add()

}

public static void moveEventTime(ArrayList<ArrayList<String>> calendar,String newTime, String event ){
    Object[] found_dayIdx_eventIdx = getEventTime(calendar, event);

}

public static Object[] getEventTime(ArrayList<ArrayList<String>> calendar, String event ){
    int day_idx = 0;
    while (day_idx < 7){
        ArrayList<String> dayItems = calendar.get(day_idx);
        int event_idx = 0;
        while (event_idx < dayItems.toArray().length){

            String[] eventArray = dayItems.get(event_idx).split(" at ");

            if (eventArray[0].equals(event)) {
                return new Object[]{true, day_idx, event_idx};
            }

            event_idx++;
        }

        day_idx++;
    }
    return new Object[]{false, -1, -1};
}