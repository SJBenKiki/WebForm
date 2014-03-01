package CAS.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;


public class TAF {
	private Date dateOfSubmission;
	private ArrayList<Day> preferredDays;
	private ArrayList<TimeSchedule> preferredTimes;
	private ArrayDeque<String> preferredCourses;
        private ArrayList<String> processedCourses;
        private ArrayDeque<String> preferredCourseKeys;
        
        public TAF(ArrayList<Day> preferredDays,ArrayList<TimeSchedule> preferredTimes,ArrayDeque<String> preferredCourses){
            try {
                dateOfSubmission = new Date(1, 1, 2013);
            }
            catch (DateException e) {
            }
            this.preferredDays = preferredDays;
            this.preferredTimes = preferredTimes;
            this.preferredCourses = preferredCourses;
            preferredCourseKeys = preferredCourses;
            processedCourses = new ArrayList<String>();
        }
	
	public Date getDateOfSubmission(){
		return dateOfSubmission;
	}
	public ArrayList<Day> getPreferredDays(){
		return preferredDays;
	}
	public ArrayList<TimeSchedule> getPreferredTimes(){
		return preferredTimes;
	}
	public ArrayDeque<String> getPreferredCourses(){
		return preferredCourses;
	}
        
        public ArrayDeque<String> GetPreferredCourseKeys() {
            return preferredCourseKeys;
        }
        
        public ArrayList<String> getProcessedCourses() {
            return processedCourses;
        }
        
        public String toString() {
            return "" + dateOfSubmission + "\n" + preferredDays + "\n" +
                    preferredTimes + "\n" + preferredCourses;
        }
        
        public String poll() {
            String course = preferredCourses.poll();
            if (course != null) {
                processedCourses.add(course);
                return course;
            }
            else {
                return null;
            }
        }
}
