package CAS.DataIO;

import CAS.Data.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author Peter Collins - Final Project CS208 DataIO is responsible for reading
 * our data into classes / data structures, and writing formatted reports to a
 * file for printing.
 */
public class DataIO {

    /**
     * Determines Operating System at runtime.
     *
     * @return whether or not the current OS is windows, false if OSX (assumed)
     */
    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win");
    }

    ;
    
    /** The OS specific directory that will hold reports
     *  @return returns an OS specific file path for writing and 
     *  retrieving reports for printing
     */
    public static String BaseReportPath() {
        if (isWindows()) {
            return System.getenv("LOCALAPPDATA");
        } else {
            return System.getenv("HOME") + "/Library/Caches/";
        }
    }

    ;
    
     /** Writes formatted data to a file, if the file
     *  already exists we overwrite it.
     *  @param formattedData - The data to write.
     *  @param fullReportPathName - The fully qualified path name.
     *  @return Returns whether or not writing succeeded.
     */
    public static boolean WriteReportToFile(String formattedData, String fullReportPathName) {
        try {
            FileWriter fw = new FileWriter(fullReportPathName + ".log", false);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(formattedData);
            writer.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }

    /**
     * Gets a sorted and formatted course report. This method should be used to
     * fetch both the assigned, and unassigned course reports.
     *
     * @param courses - The map of courses.
     * @param isAssigned - True if the report should be of assigned courses,
     * false if the report should be of unassigned courses.
     * @return Returns a formatted course report.
     */
    public static String GetCourseReport(HashMap<String, Course> courses, boolean isAssigned) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Course> coursePQ = GetSortedCourses(courses, isAssigned);

        // the following can be modified to retreieve whatever course fields
        // are desireable for the report
        sb.append(AddBuffer("Work Area", 65));
        sb.append(AddBuffer("ID", 20));
        sb.append(AddBuffer("Section", 25));
        sb.append(AddBuffer("Course Name", 85));
        sb.append("Instructor\n");
        sb.append("--------------------------------------------------------------------------------------"
                + "------------------------------------------------------------------------------------\n");
        while (!coursePQ.isEmpty() && coursePQ.peek() != null) {
            Course c = coursePQ.poll();
            String profName = "";

            if (c.getInstructor() != null) {
                profName = c.getInstructor().getName();
            }
            sb.append(AddBuffer(c.getWorkArea(), 65));
            sb.append(AddBuffer(c.getClassCode(), 20));
            sb.append(AddBuffer(c.getSection(), 15));
            sb.append(AddBuffer(c.getTitle(), 85));
            sb.append(profName);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Gets a sorted priority queue of courses This method can be used to fetch
     * both the assigned, and unassigned courses.
     *
     * @param courses - The map of courses.
     * @param isAssigned - True if the report should be of assigned courses,
     * false if the report should be of unassigned courses.
     * @return Returns a sorted queue of courses
     */
    public static PriorityQueue<Course> GetSortedCourses(HashMap<String, Course> courses, boolean isAssigned) {
        PriorityQueue<Course> coursePQ = new PriorityQueue(100, new CourseComparator());

        // sort courses by desired reporting qualities
        for (Course c : courses.values()) {
            // only add assigned or unassigned courses
            if (isAssigned == (c.getInstructor() != null)) {
                coursePQ.offer(c);
            }
        }
        return coursePQ;
    }

    /**
     * Gets a sorted and formatted unfulfilled request report
     *
     * @param courses - The map of courses.
     * @param instructors - The map of instructors
     * @return Returns a formatted unfulfilled assignment report.
     */
    public static String GetUnfulfilledRequestReport(HashMap<String, Course> courses, HashMap<String, Instructor> instructors) {
        StringBuilder sb = new StringBuilder();
        sb.append(AddBuffer("Instructor", 50));
        sb.append(AddBuffer("Code", 20));
        sb.append(AddBuffer("Section", 15));
        sb.append("Course Name\n");
        sb.append("--------------------------------------------------------------------"
                + "--------------------------------------------------------------------\n");
        ArrayList<Instructor> sortedInstructors = new ArrayList(instructors.values());
        Collections.sort(sortedInstructors);

        for (Instructor instructor : sortedInstructors) {
            ArrayList<Course> unfulfilled = instructor.getUnfulfilledCourseRequests(courses);
            if (!unfulfilled.isEmpty()) {
                sb.append(AddBuffer(instructor.getName(), 50));
                for (Course course : unfulfilled) {
                    sb.append(AddBuffer(course.getClassCode(), 20));
                    sb.append(AddBuffer(course.getSection(), 15));
                    sb.append(course.getTitle());
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    private static String AddBuffer(String s, int buffSize) {
        String temp = s;
        for (int i = s.length(); i <= (buffSize - s.length()); i++) {
            temp += " ";
        }

        return temp;
    }

    static class CourseComparator implements Comparator<Course> {

        @Override
        public int compare(Course c1, Course c2) {
            // Sort courses alphabetically by work area first,
            // and then by course ID
            return (c1.getWorkArea().equals(c2.getWorkArea())
                    ? c1.getId() - c2.getId()
                    : c1.getWorkArea().compareTo(c2.getWorkArea()));
        }
    }
}
