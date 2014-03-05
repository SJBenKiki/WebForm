package CAS.Data;

import CAS.CourseAssignment;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Instructor implements Comparable<Instructor> {

    private String name;
    private String phoneNumber;
    private HashMap<String, Integer> seniorities;
    private ArrayList<Course> courses;
    private TAF taf;

    public Instructor(TAF taf, String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.taf = taf;
        seniorities = new HashMap<String, Integer>();
        courses = new ArrayList<Course>();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public HashMap<String, Integer> getSeniorities() {
        return seniorities;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Date getDateOfSubmission() {
        return taf.getDateOfSubmission();
    }

    public ArrayList<Day> getPreferredDays() {
        return taf.getPreferredDays();
    }

    public ArrayList<TimeSchedule> getPreferredTimes() {
        return taf.getPreferredTimes();
    }

    public ArrayList<Course> getUnfulfilledCourseRequests(HashMap<String, Course> cs) {


        ArrayList<Course> unfulfilledRequests = new ArrayList<Course>();
        for (String courseKey : taf.getProcessedCourses()) {
            boolean unfulfilled = true;
            Course course = cs.get(courseKey);
            for (Course assigned : courses) {
                if (course.equals(assigned)) {
                    unfulfilled = false;
                }
            }
            if (unfulfilled) {
                unfulfilledRequests.add(course);
            }
        }
        return unfulfilledRequests;

        /*
         ArrayDeque<String> prefCourseNames = taf.GetPreferredCourseKeys();
         ArrayList<Course> prefCourses = new ArrayList();            
            
         // add equivalent course objects to preferredList
         while(!prefCourseNames.isEmpty() && prefCourseNames.peek() != null) {
         prefCourses.add (cs.get(prefCourseNames.poll()));
         }
        
         // if the course already exists in the instructors courses (assigned)
         // list, we remove it from our ArrayLists
         for (Course c1 : prefCourses) {
         for (Course c2 : courses) {
         if (c1.equals(c2)) {
         prefCourses.remove(c2);
         }
         }
         }
        
         return prefCourses;
         * */
    }

    public TAF getTAF() {
        return taf;
    }

    public int compareSeniorities(Instructor instructor, Course course) {
        //Never give up our first assigned course
        if(courses.size() == 1 && CourseAssignment.round != 1) 
            return 1;
        
        //If we're getting a third before they get a second
        if( (courses.size() + 1 - instructor.getCourses().size()) >= 2 )
        {
            return -1;
        }
        
        //If they're getting a third before we're get a second
        if(instructor.getCourses().size() + 1 - courses.size() >= 2)
        {
            return 1;
        }
        
        String workArea = course.getWorkArea();
        int thisSeniority = (seniorities.get(workArea) == null) ? 0 : seniorities.get(workArea);
        int thatSeniority = (instructor.getSeniorities().get(workArea) == null) ? 0 : instructor.getSeniorities().get(workArea);
        int seniorityComparison = thatSeniority - thisSeniority;
        if (seniorityComparison != 0) {
            return seniorityComparison;
        } else {
            int dateComparison = instructor.getTAF().getDateOfSubmission().compareTo(taf.getDateOfSubmission());
            return dateComparison;
        }
    }

    @Override
    public int compareTo(Instructor instructor) {
        return name.compareTo(instructor.getName());
    }

    @Override
    public int hashCode() {
        String hash = name;
        return hash.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof Instructor) {
            Instructor other = (Instructor) object;
            return name.equals(other.getName()) && phoneNumber.equals(other.getPhoneNumber());
        }
        return false;
    }

    @Override
    public String toString() {
        String temp = "name: " + name + "\nphone number: " + phoneNumber;
        for (Course course : courses) {
            temp += "\n" + course.getClassCode() + "," + course.getSection();
        }
        return temp;
    }
}