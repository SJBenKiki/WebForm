/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS.GUI;

import CAS.CourseAssignment;
import CAS.DataIO.DataIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Richard Hayes
 */

/*
 * This class offers the user a choice between a number of reports they would
 * like to view, and opens a PrintWindow with the appropriate data. This acts
 * as a connection between the MainWindow and the PrintWindow.
 */
public class ReportSelectionWindow extends JFrame{
    
    //A button to click for the Unfulfilled Request Report
    JButton unfulfilledRequestButton;
    //A button to click for the Assigned Course Report
    JButton assignedCourseButton;
    //A button to click for the Unassigned Course Report
    JButton unassignedCourseButton;
    //An instance of courseAssignment to allow access to the data from courses and instructors
    CourseAssignment courseAssignment;
    
    //A final int for the window's width
    private final int WIDTH = 400;
    //A final int for the window's height
    private final int HEIGHT = 120;
    
    /*
     * The constructor for the ReportSelectionWindow that accepts a courseAssignment
     * object as a parameter, initializes the variables and calls the buildPanels
     * method.
     */
    public ReportSelectionWindow(CourseAssignment courseAssignment) {
        super();
        setSize(WIDTH,HEIGHT);
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setLayout(new GridLayout(2,1));
        this.courseAssignment = courseAssignment;
        buildPanels();
        pack();
        setVisible(true);
    }
    
    /*
     * This method builds the panels by initializing them, setting layouts,
     * adding content and placing them in the proper locations within the Frame.
     */
    public void buildPanels() {
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
        topPanel.setLayout(new GridLayout(3, 1));
        GridLayout bottomPanelLayout = new GridLayout(1, 0);
        bottomPanelLayout.setHgap(20);
        bottomPanelLayout.setVgap(50);
        bottomPanel.setLayout(bottomPanelLayout);
        
        JLabel selectLabel = new JLabel("Please select the report you would like to view:");
        selectLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(new JPanel());
        topPanel.add(selectLabel);
        topPanel.add(new JPanel());
        
        unfulfilledRequestButton = new JButton("Unfulfilled Request Report");
        unfulfilledRequestButton.addActionListener(new ButtonListener());
        assignedCourseButton = new JButton("Assigned Course Report");
        assignedCourseButton.addActionListener(new ButtonListener());
        unassignedCourseButton = new JButton("Unassigned Course Report");
        unassignedCourseButton.addActionListener(new ButtonListener());
        
        bottomPanel.add(unfulfilledRequestButton);
        bottomPanel.add(assignedCourseButton);
        bottomPanel.add(unassignedCourseButton);
        
        add(topPanel);
        add(bottomPanel);
    }
    
    /*
     * This class is the button listener for the various report buttons. It opens
     * a PrintWindow with the appropriate data for the user's selection.
     */
    private class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == unfulfilledRequestButton)
      {
          String unfulfilledData = DataIO.GetUnfulfilledRequestReport(courseAssignment.getCourses(), courseAssignment.getInstructors());
          PrintWindow printWindow = new PrintWindow(unfulfilledData);
          printWindow.setVisible(true);
          ReportSelectionWindow.this.dispose();
      }
      else if (e.getSource() == assignedCourseButton)
      {
          String courseData = DataIO.GetCourseReport(courseAssignment.getCourses(), true);
          PrintWindow printWindow = new PrintWindow(courseData);
          printWindow.setVisible(true);
          ReportSelectionWindow.this.dispose();
      }
      else if (e.getSource() == unassignedCourseButton)
      {
          String courseData = DataIO.GetCourseReport(courseAssignment.getCourses(), false);
          PrintWindow printWindow = new PrintWindow(courseData);
          printWindow.setVisible(true);
          ReportSelectionWindow.this.dispose();
      }
    }
    }
}
