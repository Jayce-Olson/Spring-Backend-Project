package edu.wgu.d387_sample_code.Services;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class TimeService {
    ZoneId zoneId=ZoneId.systemDefault();
    LocalDateTime localDateTime=LocalDateTime.of(2024, 12, 12, 17, 0); // The time for the meeting

    public String getEastern(){
        ZonedDateTime zonedDateTime=localDateTime.atZone(zoneId);
        ZoneId zEastern=ZoneId.of("America/New_York");
        return "Eastern: " + zonedDateTime.withZoneSameInstant(zEastern).toString();
    }

    public String getMountain(){
        ZonedDateTime zonedDateTime=localDateTime.atZone(zoneId);
        ZoneId zMountain=ZoneId.of("America/Denver");
        return "Mountain: " + zonedDateTime.withZoneSameInstant(zMountain).toString();
    }

    public String getUTC(){
        ZonedDateTime zonedDateTime=localDateTime.atZone(zoneId);
        ZoneId zUTC=ZoneId.of("UTC");
        return "UTC: " + zonedDateTime.withZoneSameInstant(zUTC).toString();
    }
}
