package com.foxminded.university.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeslotPatternConstraintValidator implements ConstraintValidator<TimeslotPattern, String> {
    @Override
    public boolean isValid(String timeslot, ConstraintValidatorContext constraintValidatorContext) {
        if (timeslot.length() != 13) { return false; }
        
        String[] substrings = timeslot.split(" ");
        
        boolean allNeededDigits = substrings[0].matches("\\d{2}:\\d{2}") && substrings[2].matches("\\d{2}:\\d{2}");
        if (!allNeededDigits) { return false; }
        
        int startHour = Integer.parseInt(substrings[0].substring(0,2));
        int starMunites = Integer.parseInt(substrings[0].substring(3));
        int endHour = Integer.parseInt(substrings[2].substring(0,2));
        int endMinutes = Integer.parseInt(substrings[2].substring(3));

        boolean correctTime = (startHour <= 23) &&
                              (starMunites <= 59) &&
                              (endHour <= 23) &&
                              (endMinutes <= 59) &&
                              (startHour * 60 + starMunites < endHour * 60 + endMinutes);
        
        return substrings.length == 3 &&
               substrings[1].equals("-") && 
               allNeededDigits &&
               correctTime;
    }
}
