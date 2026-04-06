//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
public class Main {

    void moveEventTime(ArrayList<ArrayList<String>>calendar, String newTime, String event){
        boolean running = true;
        int day = 0;
        int event_idx = 0;
        while (day <= calendar.toArray().length){
            if (running == false){
                break
            }
            int day_length = calendar.get(day).toArray().length;
            while(event_idx <= day_length){
                if (calendar.get(day).get(event_idx).contains(event));
                {
                    String new_event = event + " at " + newTime;
                    calendar.get(day).set(event_idx, new_event);
                    running = false;
                    break
                }else{
                    event_idx++;
                }
            day++;
            }
        }
    }
}
