/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS;

import CAS.Data.Course;
import CAS.Data.Instructor;
import CAS.DataIO.CourseReader;
import CAS.DataIO.IncorrectFormatException;
import CAS.DataIO.SeniorityListReader;
import CAS.DataIO.TAFReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 *
 * @author Sid
 */
public class CourseAssignment {
    
    public static int round = 1;
    private HashMap<String, Instructor> instructors;
   /**
    * @return Returns the instructor map..
    */
    public HashMap<String, Instructor> getInstructors()
    { return instructors; }
    
    private HashMap<String, Course> courses;
   /**
    * @return Returns the course map.
    */
    public HashMap<String, Course> getCourses()
    { return courses; }
    
    public CourseAssignment()
    {
        instructors = new HashMap<String, Instructor>();
        courses = new HashMap<String, Course>();
    }
    
    
   /**  Populates the course and instructor lists from 
      *  data files.
      *  @return Returns whether or not loading succeeded.
      */    
    /*
    public boolean loadData() throws FileNotFoundException
    {
        loadCourses(file);
        loadInstructors(file);
        loadSeniorityList(file);
        //loadWorkAreas();
        return true;
    }
    * */
    
    public void loadCourses(File file, File workAreaFile) throws FileNotFoundException, IncorrectFormatException
    { courses = CourseReader.loadCourses(file, workAreaFile); }
    
    public void loadInstructors(File file) throws FileNotFoundException, IncorrectFormatException
    { instructors = TAFReader.loadInstructors(file); }
    
    public void loadSeniorityList(File file) throws FileNotFoundException, IncorrectFormatException
    { SeniorityListReader.loadSeniorityList(file, instructors); }
    
    public void loadWorkAreas()
    { /** load work areas */ }
    
  /** Iterates through each instructor and attempts to assign
      * their top available preferred course,  each instructor will have 
      * either 1 or 0 courses by the end.
      */    
    public void assignCourses()
    {
        ArrayDeque<Instructor> theQueue = new ArrayDeque<Instructor>(instructors.values());
       while(!theQueue.isEmpty())
        {
            Instructor instructor = theQueue.poll();
            boolean assigned = false;
            while(!assigned)
            {
                String theKey = instructor.getTAF().poll();
                if(theKey == null)
                    break;
                Course preferredCourse = courses.get(theKey);
                if(preferredCourse.getInstructor() == null)
                {
                    preferredCourse.setInstructor(instructor);
                    instructor.getCourses().add(preferredCourse); 
                    assigned = true;
                }
                else  if(preferredCourse.getInstructor().compareSeniorities(instructor, preferredCourse) < 0)
                {
                    Instructor instructor2 = preferredCourse.getInstructor();
                    
                    
                    //instructor2.getCourses().remove(preferredCourse);
                    preferredCourse.setInstructor(instructor);
                    instructor.getCourses().add(preferredCourse);                  //instructor.getCourses().add(preferredCourse);
                    instructor2.getCourses().remove(preferredCourse);
                    
                    theQueue.offer(instructor2);
                    assigned = true;
                }
                else
                {
            
                }
            }
        }
       round++;
    }
  
   /**  Writes reports based on the current state of the
    *    instructor and course lists.
      *  @return Returns whether or not writing succeeded.
      */       
    public boolean writeReports()
    {
            return true;
    }
}
