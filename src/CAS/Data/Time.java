package CAS.Data;

/**
 * 
 * @author Sarah Ben-Kiki
 */
public class Time{
	private int hour;
	private int min;

        /**
         * The Time class takes in the hour and minutes
         * and confirms that the hour is equal to 24 and 
         * the minutes are equal to 59 or else it throws 
         * an exception. Then it sets them.
         * @param hour
         * @param min 
         */
	public Time(int hour, int min) {
		if(hour < 0 || hour > 23 || min < 0 || min > 59) {
			if (hour == 24 && min == 0) {
				this.hour = hour;
				this.min = min;
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		this.hour = hour;
		this.min = min;
	}

	/**
         * Returns the hour of the time
	 * @return hour
	 */
	 public int getHour() {
		return hour;
	}

	/**
         * Returns the minutes of the time
	 * @return minutes
	 */
	 public int getMin() {
		 return min;
	 }

	 /**
	  * Converts the String into universal-time format
	  * @return String time format (HH:MM)
	  */
	 public String toUniversalString(){
		 return String.format( "%02d:%02d", hour, min);
	 }

	 /**
	  * Converts the String in standard-time format (H:MM AM PM)
	  * @return String time format (H:MM AM PM)
	  */
	 @Override
	 public String toString(){
		 if (hour == 24 && min == 0) {
			 return " ";
		 }
		 return String.format( "%d:%02d",(( hour == 0 || hour == 12) ? 12 : hour % 12),min,(hour< 12?"AM":"PM"));
	 }

	 /**
          * Determines whether the hour and the minutes
          * match the hour and minutes of the course. 
          * @param object
	  * @return either true or false
	  */
	 @Override
	 public boolean equals(Object object) {
		 if (object == null) {                return false;            }
		 if (object == this) {                return true;            }
		 if (object instanceof Time) {
			 Time time = (Time)object;
			 return (this.hour == time.getHour() && this.min == time.getMin());
		 }
		 else {
			 return false;
		 }
	 }
}