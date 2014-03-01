package CAS.Data;

import java.util.ArrayList;

/**
 * @author Sarah Ben-Kiki
 *
 */
public class Course implements Comparable<Course> {

	private int id;
	private String classCode;
	private String section;
	private String title;
	private String campus;
	private ArrayList<Day> days;
	private TimeSchedule startEnd;
	//private Time start;
	//private Time end;
	private String workArea;
	private Instructor instructor;
	private Course lab;

        /**
         * The Course class provides the class information such as number, course
         * id, section, title, campus, days, start time, and end time.
         * 
         * @param id
         * @param classCode
         * @param section
         * @param title
         * @param campus
         * @param days
         * @param startEnd
         * @param workArea 
         */
	public Course(int id, String classCode, String section, String title,
			String campus, ArrayList<Day> days, TimeSchedule startEnd, String workArea) {
		this.id = id;
		this.classCode = classCode;
		this.section = section;
		this.title = title;
		this.setCampus(campus);
		this.days = days;
		this.startEnd = startEnd;
		//this.start = start;
		//this.end = end;
		this.workArea = workArea;
		instructor = null;
		lab = null;
	}

	/**
         * Takes the classCode and the section of the course 
         * and assigns it a unique integer. 
	 * @return hashCode of classCode and section
	 */
	@Override
	public int hashCode() {
		String hash = classCode + section;
		return hash.hashCode();
	}

	/**
         * This is the equals method that takes in a Course
         * class code or section and compares it to see whether 
         * it makes the class code or section you're looking for.
         * @param object
	 * @return either true or false
	 */
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object == this) {
			return true;
		}
		Course course = (Course) object;
		if (!classCode.equals(course.getClassCode())) {
			return false;
		}
		if (!section.equals(course.getSection())) {
			return false;
		}
		return true;
	}

	/**
         * Sets the unique course ID for the class
         * such as 8143 for General Biology I
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
         * Returns the unique course ID for the class
	 * @return id of the class
	 */
	public int getId() {
		return id;
	}

	/**
         * Sets the course code for the class
         * such as BI 101 for General Biology I
	 * @param classCode
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	/**
         * Returns the course code for the class
	 * @return classCode of the class
	 */
	public String getClassCode() {
		return classCode;
	}

	/**
         * Sets the section number of the class
         * such as section 1 for General Biology I
	 * @param section
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
         * Returns the section number of the class
	 * @return the section number of the class
	 */
	public String getSection() {
		return section;
	}

	/**
         * Sets the title of the course
         * such as General Biology I
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
         * Returns the title of the course
	 * @return title of the course
	 */
	public String getTitle() {
		return title;
	}

	/**
         * Sets the campus that the course is going 
         * to be taught at. Such as WEL for Wellesley 
         * campus and FRA for Framingham campus.
	 * @param campus
	 */
	public void setCampus(String campus) {
		this.campus = campus;
	}

	/**
         * Returns the campus that the course is 
         * going to be taught at. 
	 * @return campus that course is taught at
	 */
	public String getCampus() {
		return campus;
	}

	/**
         * Sets the days the course is going to be taught
         * such as MWF, TR, T, R, M etc.
	 * @param days
	 */
	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}

	/**
         * Returns the days the course is taught on.
	 * @return days the course is taught
	 */
	public ArrayList<Day> getDays() {
		return days;
	}

	/**
         * Sets the start time of the course
         * such as 8:00am
	 * @param start
	 */
	public void setStart(Time start) {
		startEnd.setStartTime(start);
		//this.start = start;
	}

	/**
         * Returns the start time of the course
	 * @return start time of course
	 */
	public Time getStart() {
		return startEnd.getStartTime();
	}

	/**
         * Sets the end time of the course
         * such as 8:50pm
	 * @param end
	 */
	public void setEnd(Time end) {
		startEnd.setEndTime(end);
		//this.end = end;
	}

	/**
         * Returns the end time of the course
	 * @return end time of course
	 */
	public Time getEnd() {
		return startEnd.getEndTime();
	}

	/**
	 * @param startEnd
	 */
	public void setStartEnd(TimeSchedule startEnd) {
		this.startEnd = startEnd;
	}

	/**
	 * @return
	 */
	public TimeSchedule getStartEnd() {
		return startEnd;
	}

	/**
	 * @param workArea
	 */
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	/**
	 * @return
	 */
	public String getWorkArea() {
		return workArea;
	}

	/**
         * Sets the instructor of the course
	 * @param instructor
	 */
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	/**
         * Returns the instructor of the course
	 * @return instructor
	 */
	public Instructor getInstructor() {
		return instructor;
	}

	/**
         * Sets the lab of the course if there
         * is a lab
	 * @param lab
	 */
	public void setLab(Course lab) {
		this.lab = lab;
	}

	/**
         * Returns the lab of the course if there
         * is a lab.
	 * @return lab of course
	 */
	public Course getLab() {
		return lab;
	}

	/**
         * 
	 * @return 
	 */
	@Override
	public int compareTo(Course c) {
		int comparedWorkArea = workArea.compareTo(c.workArea);
		if (comparedWorkArea != 0) {
			return comparedWorkArea;
		} else {
			String thisCode = "";
			String thatCode = "";
			for (int i = 0; i < classCode.length(); i++) {
				char tempChar = classCode.charAt(i);
				if (Character.isDigit(tempChar)) {
					thisCode += tempChar;
				}
			}
			String thatClassCode = c.getClassCode();
			for (int i = 0; i < thatClassCode.length(); i++) {
				char tempChar = thatClassCode.charAt(i);
				if (Character.isDigit(tempChar)) {
					thatCode += tempChar;
				}
			}

			int thisNumber = Integer.parseInt(thisCode);
			int thatNumber = Integer.parseInt(thatCode);
			int numberDiff = thisNumber - thatNumber;
			if (numberDiff != 0) {
				return numberDiff;
			} else {
				thisCode = "";
				thatCode = "";
				for (int i = 0; i < section.length(); i++) {
					char tempChar = section.charAt(i);
					if (Character.isDigit(tempChar)) {
						thisCode += tempChar;
					}
				}

				thatClassCode = c.getSection();
				for (int i = 0; i < thatClassCode.length(); i++) {
					char tempChar = thatClassCode.charAt(i);
					if (Character.isDigit(tempChar)) {
						thatCode += tempChar;
					}
				}

				thisNumber = Integer.parseInt(thisCode);
				thatNumber = Integer.parseInt(thatCode);
				numberDiff = thisNumber - thatNumber;
				return numberDiff;
			}
		}
	}

	/**
         * 
	 * @return 
	 */
	public String toString() {
		String instructorName = instructor == null ? "n/a " : instructor.getName();
		String temp = getClassCode() + "," + getSection() + " : " + instructorName + "\n";
		/*
         String temp = "nbr: " + getId() + " course: " + getClassCode() + "\n" + 
         "section: " + getSection() + " title: " + getTitle() + "\n" +
         "campus: " + getCampus() + " days: " + getDays() + "\n" +
         "start: " + getStart() + " end: " + getEnd() + "\n" +
         "work area: " + getWorkArea() + " instructor: " + getInstructor().getName() + "\n";*/
		if (lab != null) {
			temp += " HAS A LAB\n";
		}
		return temp;
	}
}