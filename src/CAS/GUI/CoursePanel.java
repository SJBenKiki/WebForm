/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS.GUI;

/* @author Daniel Sherman
Class to handle a GUI panel for course details.
*/

import javax.swing.*;
import java.awt.*;

import CAS.Data.Course;
import CAS.Data.Day;

 /*
 Default Constructor. not intended for use.
 */
public class CoursePanel extends JPanel
{
 private JPanel panel,labPanel;
 private JLabel id, workArea, instructor, day, start, end, number, section, session, title, credits, campus, room; 
 private JLabel idLab, dayLab, startLab, endLab, sectionLab;
 private String dayList, dayListLab;
 private Course thisCourse, lab;
 
 /*
 Default Constructor. not intended for use.
 */
  public CoursePanel()
  {
   id = new JLabel("ID: ");
   workArea = new JLabel("Work Area: ");
   instructor = new JLabel("Instructor: ");
   day = new JLabel("Days: ");
   start =  new JLabel("Start: ");
   end =  new JLabel("End: ");
   number =  new JLabel("Number: ");
   section =  new JLabel("Section: " );
   title =  new JLabel("Title: ");
   campus =  new JLabel("Campus: ");
  }
  
    /*
  Main Constructor. Takes a Course object and creates the Panel
  @param Course c
  */
  public CoursePanel(Course c)
  { 
    thisCourse = c;
    setFields();
    buildPanel();
    
  }
  /*
  public class to change the Course object. Not intended for use.
  */
  public void setCourse(Course c)
  {
   thisCourse = c;
  }
  /*
  Private method to initialize all of the variables.
  */
  private void setFields()
  {
      
   
   if(thisCourse.getInstructor() != null)
       instructor =  new JLabel("Instructor: " + thisCourse.getInstructor());      
   else 
       instructor = new JLabel("Instructor: N/A");
   
   dayList = "";
//   if(thisCourse.getDays() != null) {
   for (Day d:thisCourse.getDays())
   {
     dayList += d.toString() + " ";
   }
//   }
   
   id = new JLabel("ID: " + thisCourse.getId());
   workArea =  new JLabel("Workarea: " + thisCourse.getWorkArea());
  
   day =  new JLabel("Day: " + dayList);
   start =  new JLabel("Start: " + thisCourse.getStart());
   end =  new JLabel("End: " + thisCourse.getEnd());
   number =  new JLabel("Class Code: " + thisCourse.getClassCode().toString());
   section =  new JLabel("Section: " + thisCourse.getSection());
   title =  new JLabel("Title: " + thisCourse.getTitle());
   campus =  new JLabel("Campus: "+ thisCourse.getCampus());
   
   if(thisCourse.getLab() != null)
   {
       
   labPanel = new JPanel();
   lab = thisCourse.getLab();
   idLab = new JLabel("Id: " + lab.getId());
   startLab = new JLabel("Start: " + lab.getStart());
   endLab =  new JLabel("End: " + lab.getEnd());
   sectionLab = new JLabel("Secton: " + lab.getSection());
   
   
   dayListLab = "";
   if(lab.getDays() != null) {
   for (Day d:lab.getDays())
   {
     dayListLab += d.toString() + " ";
   }
   }
   else 
       lab = null;
   }
   dayLab = new JLabel("Days: " + dayListLab);
  }
  /*
  Private inner class to build the GUI panel.
  */
  private void buildPanel()
  {
    panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder("Course Info"));
    setLayout(new GridLayout(2,1));
    panel.setLayout(new GridLayout(0,1));
    
    setBackground(Color.WHITE);
    panel.setBackground(Color.WHITE);
    
    title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    number.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    workArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    section.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    campus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    day.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    end.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    
    panel.add(title);
    panel.add(number);
    panel.add(workArea);
    panel.add(section);
    panel.add(campus);
    panel.add(day);
    panel.add(start);
    panel.add(end);
    
    add(panel);
    
    if(lab != null)
    {
    labPanel = new JPanel();
    labPanel.setLayout(new GridLayout(0,1));
    labPanel.setBackground(Color.WHITE);
    labPanel.setBorder(BorderFactory.createTitledBorder("Lab Info"));

        
    dayLab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    startLab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    endLab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    sectionLab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    
    
    labPanel.add(dayLab);  
    labPanel.add(startLab);
    labPanel.add(endLab);
    labPanel.add(sectionLab);
    
    add(labPanel);
    }
    
  }
}
