/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS.Data;

/**
 *
 * @author Julian Kuk
 */
public class TimeSchedule {
    private Time startTime, endTime;
    
    public TimeSchedule(int startHour, int endHour) {
        this(startHour, 0, endHour, 0);
    }
    
    public TimeSchedule(int startHour, int startMinute, int endHour, int endMinute) {
        this(new Time(startHour, startMinute), new Time(endHour, endMinute));        
    }
    
    public TimeSchedule(Time startTime, Time endTime) {
        setStartTime(startTime);
        setEndTime(endTime);
    }
    
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    
    public Time getStartTime() {
        return startTime;
    }
    
    public Time getEndTime() {
        return endTime;
    }
    
    public boolean withinRange(TimeSchedule timeSchedule) {
        return withinRange(timeSchedule.getStartTime().getHour(), timeSchedule.getEndTime().getHour());
    }
    
    public boolean withinRange(int startHour, int endHour) {
        return (startHour >= startTime.getHour() && startHour < endTime.getHour()) &&
                (endHour > startTime.getHour() && endHour <= endTime.getHour());
    }
    
    public String toString() {
        if (startTime.equals(endTime)) {
            return " ";
        }
        else {
            return startTime + " - " + endTime;
        }
    }
}