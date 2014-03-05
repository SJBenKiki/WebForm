/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS.DataIO;

/**
 *
 * @author Justin
 */
import java.io.*;
import java.util.HashMap;
import CAS.Data.Course;
import CAS.Data.Day;
import CAS.Data.Time;
import CAS.Data.TimeSchedule;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The CourseReader class provides a static method to generate a HashMap of
 * Course objects
 */
public class CourseReader {

    /**
     * Wrapper method for loadCourses method to generate a HashMap of Course
     * objects
     *
     * @param filename The name of the course schedule file
     * @param workAreaFilename The name of the work area file
     * @return courseList The HashMap of Course objects
     * @throws FileNotFoundException
     * @throws IncorrectFormatException
     */
    public static HashMap<String, Course> loadCourses(String filename, String workAreaFilename) throws FileNotFoundException, IncorrectFormatException {
        return loadCourses(new File(filename), new File(workAreaFilename));
    }

    /**
     * The loadCourses method generates a HashMap of Course objects from files
     *
     * @param file The course schedule file
     * @param workAreaFile the work area file
     * @return courseList The HashMap of Course objects
     * @throws FileNotFoundException
     * @throws IncorrectFormatException
     */
    public static HashMap<String, Course> loadCourses(File file, File workAreaFile) throws FileNotFoundException, IncorrectFormatException {
        Scanner scan = new Scanner(file);
        HashMap<String, Course> courseList = new HashMap<String, Course>();

        HashMap<String, String> workAreas = loadWorkAreas(workAreaFile);

        scan.nextLine();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitline = line.split("\\t");
            for (int i = 0; i < splitline[0].length(); i++) {
                if (!Character.isDigit(splitline[0].charAt(i))) {
                    throw new IncorrectFormatException("Incorrect Class Nbr format");
                }
            }
            int id = Integer.parseInt(splitline[0]);
            String workArea = null;

            ArrayList<Day> days;
            Time start;
            Time end;

            if (splitline.length > 5) {
                days = new ArrayList<Day>();
                if (splitline[5].contains("M")) {
                    days.add(Day.MONDAY);
                }
                if (splitline[5].contains("T")) {
                    days.add(Day.TUESDAY);
                }
                if (splitline[5].contains("W")) {
                    days.add(Day.WEDNESDAY);
                }
                if (splitline[5].contains("R")) {
                    days.add(Day.THURSDAY);
                }
                if (splitline[5].contains("F")) {
                    days.add(Day.FRIDAY);
                }
                if (splitline[5].contains("Sa")) {
                    days.add(Day.SATURDAY);
                }
                if (splitline[5].contains("Su")) {
                    days.add(Day.SUNDAY);
                }
                char[] string = splitline[5].toCharArray();
                for (int i = 0; i < string.length; i++) {
                    if (!(string[i] == 'M' || string[i] == 'T' || string[i] == 'W' ||
                        string[i] == 'R' || string[i] == 'F' || string[i] == 'S')) {
                        if (string[i] == 'a' || string[i] == 'u') {
                            if (i - 1 >= 0) {
                                if (string[i - 1] != 'S') {
                                    throw new IncorrectFormatException("Incorrect Day format");
                                }
                            }
                        }
                        else {
                            throw new IncorrectFormatException("Incorrect Day format");
                        }
                    }
                }

                if (!splitline[6].contains(":")) {
                    throw new IncorrectFormatException("Incorrect Start Time format");
                }
                String[] startTime = splitline[6].split(":");
                for (int i = 0; i < startTime[0].trim().length(); i++) {
                    if (!Character.isDigit(startTime[0].trim().charAt(i))) {
                        throw new IncorrectFormatException("Incorrect Start Time format");
                    }
                }
                
                int hour = Integer.parseInt(startTime[0].trim());
                char[] startMin = startTime[1].toCharArray();
                if (!Character.isDigit(startMin[0]) || !Character.isDigit(startMin[1])
                        || !(startMin[2] == 'p' || startMin[2] == 'a')) {
                    throw new IncorrectFormatException("Incorrect Start Time format");
                }
                String minString = String.valueOf(startMin[0]) + String.valueOf(startMin[1]);
                int min = Integer.parseInt(minString);
                if (startMin[2] == 'p') {
                    hour += 12;
                    if (hour == 24) {
                        hour -= 12;
                    }
                }
                start = new Time(hour, min);
                if (!splitline[7].contains(":")) {
                    throw new IncorrectFormatException("Incorrect End Time Format");
                }
                String[] endTime = splitline[7].split(":");
                for (int i = 0; i < endTime[0].trim().length(); i++) {
                    if (!Character.isDigit(endTime[0].trim().charAt(i))) {
                        throw new IncorrectFormatException("Incorrect End Time format");
                    }
                }
                hour = Integer.parseInt(endTime[0].trim());
                char[] endMin = endTime[1].toCharArray();
                if (!Character.isDigit(endMin[0]) || !Character.isDigit(endMin[1])
                        || !(endMin[2] == 'p' || endMin[2] == 'a')) {
                    throw new IncorrectFormatException("Incorrect End Time format");
                }
                minString = String.valueOf(endMin[0]) + String.valueOf(endMin[1]);
                min = Integer.parseInt(minString);
                if (endMin[2] == 'p') {
                    hour += 12;
                    if (hour == 24) {
                        hour -= 12;
                    }
                }
                end = new Time(hour, min);
            } else {
                days = new ArrayList<Day>();
                days.add(Day.ANY);
                start = new Time(24, 0);
                end = new Time(24, 0);
            }

            if (splitline[1].contains("\\s")) {
                throw new IncorrectFormatException("Incorrect Subject format");
            }
            String[] subjectNumber = splitline[1].split("\\s");

            String subject = subjectNumber[0];
            String number = subjectNumber[1];
            String section = splitline[2];
            
            int session = 0;
            String title = splitline[3];
            int credits = 0;
            String campus = splitline[4];
            String room = null;
            
            workArea = workAreas.get(subjectNumber[0] + subjectNumber[1]);
            
            int sectionLength;
            if (section.contains("L")) {
                sectionLength = 4;
            }
            else {
                sectionLength = 3;
            }
            while(section.length() < sectionLength)
            {
                section = "0" + section;
            }
            
            Course course = new Course(id,subject + number, section, title, campus, days, new TimeSchedule(start, end), workArea);
            String key = course.getClassCode() + "," + course.getSection();
            if (section.contains("L")) {
                courseList.get(key.substring(0, key.length() - 1)).setLab(course);
            }
            else {
                courseList.put(key, course);
            }
        }
        return courseList;
    }

    /**
     * The loadWorkAreas method generates a HashMap of work areas from a file
     *
     * @param file The work area file
     * @return workAreaList the HashMap of work areas
     */
    public static HashMap<String, String> loadWorkAreas(File file) {
        Scanner scan = null;
        try {
            scan = new Scanner(file);
            HashMap<String, String> workAreaList = new HashMap<String, String>();
            String area = null;

            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equals("")) {
                    area = null;
                } else {
                    if (area == null) {
                        area = line;
                    } else {
                        String[] split = line.replaceAll("[(),]", "").split(" ");
                        for (int i = 1; i < split.length; i++) {
                            workAreaList.put("" + split[0] + split[i], area);
                        }
                    }
                }
            }
            return workAreaList;
        } catch (IOException e) {
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
        return null;
    }
}
