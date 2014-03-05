/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS;

import CAS.Data.Course;
import CAS.Data.Instructor;
import java.io.File;

/**
 *
 * @author Sid
 */
public class MyTester {
    
    public static void main(String[] args)
    {
        final String DEFAULT_DIRECTORY = "user.dir";
        CourseAssignment ca = new CourseAssignment();
        String directory = System.getProperty(DEFAULT_DIRECTORY);
        try
        {
            ca.loadCourses(new File(directory + "/input/CourseSchedule.crs"), new File(directory + "/input/workAreas.wrk"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Courses loaded. Count = " + ca.getCourses().size());
        
        try
        {
            ca.loadInstructors(new File(directory + "/input/TAF.taf"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Instructors loaded. Count = " + ca.getInstructors().size());
        
        try
        {
            ca.loadSeniorityList(new File(directory + "/input/Seniority.snr"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Seniority List loaded.");
        
        for(Instructor instructor : ca.getInstructors().values())
        {
            System.out.println(instructor);
        }
        System.out.println("***************");
        System.out.println("****ROUND1*****");
        System.out.println("***************");
        
        ca.assignCourses();  
         for(Instructor instructor : ca.getInstructors().values())
        {
            System.out.println(instructor);
        }
        System.out.println("***************");
        System.out.println("****ROUND2*****");
        System.out.println("***************");
                  ca.assignCourses();  
         for(Instructor instructor : ca.getInstructors().values())
        {
            System.out.println(instructor);
        }    
    }
    
}
