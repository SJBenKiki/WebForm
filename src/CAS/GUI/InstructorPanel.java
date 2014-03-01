/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS.GUI;

import CAS.Data.TimeSchedule;
import CAS.Data.Instructor;
import CAS.Data.Day;
import CAS.Data.Course;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

/** InstructorPanel.java
 * A class to display information about a specific Instructor object
 * @author Dan
 */
public class InstructorPanel extends JPanel
{
    
 private JPanel northPanel;
 private JLabel name, phoneNumber, dateOfSubmission, preferredDays, preferredCoursesLabel,preferredTimes;
 private JTextArea courses;
 private String dayList,preferredTimesList;
 private ArrayDeque<Course> preferredCoursesArray;
 private ArrayList<TimeSchedule> preferredTimesArray;
 private Instructor thisInstructor;
 
 /*
 Default constructor, not intended for actual use.
 */
  public InstructorPanel()
  {
   name = new JLabel("Name: ");
   phoneNumber = new JLabel("Phone: 555-555-5555");
   dateOfSubmission = new JLabel("Submitted: ");
   preferredDays = new JLabel("Preferred Days: ");
   preferredCoursesLabel = new JLabel("Preferred Courses: ");
  }
   
  /*
  Constructor. Takes an Instructor object and creates the panel.
  @param Instructor in
  */
  public InstructorPanel(Instructor in)
  { 
    thisInstructor = in;
    setFields();
    buildPanel();
    
  }
  /*
  Public method to alter the instructor
  */
  public void setInstructor(Instructor in)
  {
   thisInstructor = in;
   setFields();
  }
  /*
  Private method used to setup the class variables.
  */
  private void setFields()
  {
      
   name = new JLabel("Name: " + thisInstructor.getName());
   phoneNumber = new JLabel("Phone: " + thisInstructor.getPhoneNumber());
   dateOfSubmission = new JLabel("Submitted: " + thisInstructor.getDateOfSubmission());
   preferredDays = new JLabel("Preferred Days: " + thisInstructor.getPreferredDays());
   String coursesString = "";
   for(Course course : thisInstructor.getCourses()) {
       coursesString += "\t" + course.getTitle() + "\n";
   }
   courses = new JTextArea("\nCourses: "+ coursesString);
   courses.setWrapStyleWord(true);
   courses.setEditable(false);
   courses.setFont(new Font(courses.getText(), Font.BOLD, 12));
 
   preferredCoursesLabel = new JLabel("Preferred Courses: ");

   preferredTimesList = "Preferred Times: ";
   preferredTimesArray = new ArrayList<TimeSchedule>();
   for(TimeSchedule t:preferredTimesArray)
   {
   preferredTimesList += "Start: " + t.getStartTime().toString() + " End: " + t.getEndTime().toString()+ "\n";    
   }
   preferredTimes = new JLabel(preferredTimesList);
  }
  /*
  Private class that takes the variables set by setFields, and forms the panel.
  */
  private void buildPanel()
  {
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createTitledBorder("Intructor Information"));
    setLayout(new GridLayout(0,1));
    
    name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    phoneNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    dateOfSubmission.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    preferredDays.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    preferredTimes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    courses.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    
    add(name);
    add(phoneNumber);
    add(dateOfSubmission);
    add(preferredDays);
    add(preferredTimes);
    add(courses);
    
    
    
  }
}
