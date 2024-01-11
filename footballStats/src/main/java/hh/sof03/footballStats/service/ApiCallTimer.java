package hh.sof03.footballStats.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCallTimer {
	
	@Autowired
	private PlayerApiCall playerApiCall;
	@Autowired
	private TeamApiCall teamApiCall;
	
	private static final String LAST_EXECUTION_DATE_KEY = "lastExecutionDate";
	
	public void main() {
		
		Preferences preferences = Preferences.userNodeForPackage(ApiCallTimer.class);
		
	    LocalDate lastExecutionDate = LocalDate.parse(preferences.get(LAST_EXECUTION_DATE_KEY, "1970-01-01"));
		
	    // Get the current date
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isEqual(lastExecutionDate)) {
            System.out.println("API calls already made today.");
        } else {
            LocalTime currentTime = LocalTime.now();
            LocalTime targetTime = LocalTime.of(1, 0);

            // Check if the current time is 1:00 AM or later
            if (currentTime.isAfter(targetTime) || currentTime.equals(targetTime)) {
                teamApiCall.main();
                playerApiCall.main();

                // Set the last execution date to today
                preferences.put(LAST_EXECUTION_DATE_KEY, currentDate.toString());
            } else {
                System.out.println("It's not 1:00 AM yet.");
            }
        }
    }

}
