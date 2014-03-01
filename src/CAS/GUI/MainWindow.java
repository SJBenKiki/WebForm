package CAS.GUI;

/* Richard Hayes */

import CAS.CourseAssignment;
import CAS.Data.Course;
import CAS.Data.Instructor;
import CAS.DataIO.DataIO;
import CAS.DataIO.IncorrectFormatException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * This class is the frame that contains the selectable lists of
 * courses and instructors as well as access to the reports and
 * course assignment functions through the press of buttons.
 * This serves as the Main Window for the program.
 */
public class MainWindow extends JFrame {
    
    //An instance of CourseAssignment for access to data on the courses and instructors
    private CourseAssignment courseAssignment;
    //An int to keep track of the current round for course assignment
    private int round;
  
    //The panel that displays a list of selectable courses
    private CourseReportPanel courseReportPanel;
    //The panel that displays a list of selectable instructors
    private InstructorReportPanel instructorReportPanel;
    //The panel to display the information of a selected course
    private CoursePanel coursePanel;
    //The panel to display the information of a selected instructor
    private InstructorPanel instructorPanel;
  
    //The panel that holds either CourseReportPanel or InstructorReportPanel based on what is toggled
    private JPanel reportPanel;
    //The panel that holds either CoursePanel or InstructorPanel based on what is selected
    private JPanel detailsPanel;
  
    //The top panel for the Frame organization
    private JPanel topPanel;
    //The middle panel for the Frame organization
    private JPanel middlePanel;
    //The panel in the left side of the middle panel that holds the reportPanel
    private JPanel middleLeftPanel;
    //The panel in the right side of the middle panel that holds the detailsPanel
    private JPanel middleRightPanel;
    //The bottom panel for the Frame organization
    private JPanel bottomPanel;
    
    //A panel of buttons that is held in the bottomPanel
    private JPanel buttonPanel;
  
    //The button to run the next round of course assignment, if possible
    private JButton assignButton;
    //The button to toggle between instructor and course view
    private JButton toggleButton;
    //The button to show reports
    private JButton reportButton;
    //The mouseListener to listen for double-clicking
    private MouseListener mouseListener;
    
    //A final int for the window's width
    private final int WIDTH = 800;
    //A final int for the window's height
    private final int HEIGHT = 600;
    
    //The constraints for GridBagLayout
    GridBagConstraints constraints;
 
    /*
     * The constructor for the MainWindow that accepts a CourseAssignment object
     * as a parameter, initializes all of the variables and calls the buildPanels
     * method.
     */
  public MainWindow(CourseAssignment courseAssignment)
  {
    super();
    setSize(WIDTH,HEIGHT);
    setMaximumSize(new Dimension(WIDTH, HEIGHT));
    setMinimumSize(new Dimension(WIDTH, HEIGHT));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    
    this.courseAssignment = courseAssignment;
    mouseListener = new MouseClickListener();
    round = 1;
    buildPanels();

    setVisible(true);
  }
  
  /*
   * This method builds all the panels by initializing them, assigning layouts,
   * adding content and borders, and placing them in the proper locations in the
   * Frame.
   */
  public void buildPanels() {
    constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    
    courseReportPanel = new CourseReportPanel(courseAssignment, mouseListener);
    
    instructorReportPanel = new InstructorReportPanel(courseAssignment, mouseListener);
                              
    coursePanel = new CoursePanel();
                    
    instructorPanel = new InstructorPanel();
    
    reportPanel = courseReportPanel;
    detailsPanel = coursePanel;
    
    topPanel = new JPanel();
    topPanel.setLayout(new GridLayout(1,0));
    topPanel.add(new JLabel(" "));
    
    middleRightPanel = new JPanel();
    BorderLayout middleRightLayout = new BorderLayout();
    middleRightLayout.setHgap(30);
    middleRightLayout.setVgap(50);
    middleRightPanel.setLayout(middleRightLayout);
    detailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    middleRightPanel.add(detailsPanel, BorderLayout.CENTER);
    middleRightPanel.add(new JLabel(" "), BorderLayout.SOUTH);
    middleRightPanel.add(new JLabel(" "), BorderLayout.WEST);
    middleRightPanel.add(new JLabel(" "), BorderLayout.EAST);
    
    middleLeftPanel = new JPanel();
    middleLeftPanel.setLayout(new BorderLayout());
    middleLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    middleLeftPanel.add(reportPanel);
    
    middlePanel = new JPanel();
    middlePanel.setLayout(new GridLayout(1,2));
    middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
    middlePanel.add(middleLeftPanel);
    middlePanel.add(middleRightPanel);
                 
    buttonPanel = new JPanel();
    bottomPanel = new JPanel();
    buttonPanel.setLayout(new GridBagLayout());
    assignButton = new JButton("Round 1");
    toggleButton = new JButton("Instructors");
    reportButton = new JButton("Report");
    assignButton.addActionListener(new ButtonListener());
    toggleButton.addActionListener(new ButtonListener());
    reportButton.addActionListener(new ButtonListener());
    constraints.weightx = 0.1;
    constraints.weighty = 0.2;
    constraints.gridx = 0;
    constraints.gridy = 0;
    buttonPanel.add(new JPanel(), constraints);
    constraints.weightx = 0.1;
    constraints.weighty = 0.6;
    constraints.gridx = 0;
    constraints.gridy = 1;
    buttonPanel.add(new JPanel(), constraints);
    constraints.weightx = 0.2;
    constraints.weighty = 0.6;
    constraints.gridx = 1;
    constraints.gridy = 1;
    buttonPanel.add(assignButton, constraints);
    constraints.weightx = 0.1;
    constraints.weighty = 0.6;
    constraints.gridx = 2;
    constraints.gridy = 1;
    buttonPanel.add(new JPanel(), constraints);
    constraints.weightx = 0.2;
    constraints.weighty = 0.6;
    constraints.gridx = 3;
    constraints.gridy = 1;
    buttonPanel.add(toggleButton, constraints);
    constraints.weightx = 0.1;
    constraints.weighty = 0.6;
    constraints.gridx = 4;
    constraints.gridy = 1;
    buttonPanel.add(new JPanel(), constraints);
    constraints.weightx = 0.2;
    constraints.weighty = 0.6;
    constraints.gridx = 5;
    constraints.gridy = 1;
    buttonPanel.add(reportButton, constraints);
    constraints.weightx = 0.1;
    constraints.weighty = 0.6;
    constraints.gridx = 6;
    constraints.gridy = 1;
    buttonPanel.add(new JPanel(), constraints);
    constraints.weightx = 0.1;
    constraints.weighty = 0.2;
    constraints.gridx = 0;
    constraints.gridy = 2;
    buttonPanel.add(new JPanel(), constraints);
    bottomPanel.add(buttonPanel);
    
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = 0.1;
    
    add(topPanel, BorderLayout.NORTH);
    
    constraints.gridy = 1;
    constraints.weighty = 0.7;
    
    add(middlePanel, BorderLayout.CENTER);
    
    constraints.gridy = 2;
    constraints.weighty = 0.2;
    
    add(bottomPanel, BorderLayout.SOUTH);
  }
  
  /*
   * This class is the ButtonListener for the MainWindow. This is used for the
   * toggle button, report button and assign button.
   */
   private class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == toggleButton)
      {
        if(reportPanel.equals(courseReportPanel)) {
          middleLeftPanel.remove(reportPanel);
          middleRightPanel.remove(detailsPanel);
          reportPanel = instructorReportPanel;
          middleLeftPanel.add(reportPanel);
          constraints.weightx = 0.4;
          constraints.weighty = 0.6;
          constraints.gridx = 1;
          constraints.gridy = 0;
          detailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
          middleRightPanel.add(detailsPanel, BorderLayout.CENTER);
          middleLeftPanel.revalidate();
          middleRightPanel.revalidate();
          toggleButton.setText("Courses");
          repaint();
        }
        else {
          middleLeftPanel.remove(reportPanel);
          middleRightPanel.remove(detailsPanel);
          reportPanel = courseReportPanel;
          middleLeftPanel.add(reportPanel);
          constraints.weightx = 0.4;
          constraints.weighty = 0.6;
          constraints.gridx = 1;
          constraints.gridy = 0;
          detailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
          middleRightPanel.add(detailsPanel, BorderLayout.CENTER);
          middleLeftPanel.revalidate();
          middleRightPanel.revalidate();
          toggleButton.setText("Instructors");
          repaint();
        }
      }
      if (e.getSource() == reportButton) {
          ReportSelectionWindow r = new ReportSelectionWindow(courseAssignment);
      }
      if (e.getSource() == assignButton) {
          if(round == 1) {
              courseAssignment.assignCourses();
              instructorReportPanel.updateList();
              courseReportPanel.updateList();
              assignButton.setText("Round 2");
              round = 2;
          }
          else if(round == 2) {
              courseAssignment.assignCourses();
              instructorReportPanel.updateList();
              courseReportPanel.updateList();
              assignButton.setText("Round 3");
              round = 3;
          }
          else if(round == 3) {
              courseAssignment.assignCourses();
              instructorReportPanel.updateList();
              courseReportPanel.updateList();
              assignButton.setText("Done");
              assignButton.setEnabled(false);
              round = 4;
          }
      }
    }
  }
   
   /*
    * This class is the MouseListener for the MainWindow, listening for a user
    * double-clicking on a course or an instructor so the details can be sent to
    * the appropriate panel for displaying.
    */
    private class MouseClickListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                if (reportPanel == instructorReportPanel) {
                    Object selected = instructorReportPanel.getSelected();
                    if (selected != null) {
                        if (selected instanceof Instructor) {
                            Instructor i = (Instructor) selected;
                            middleLeftPanel.remove(reportPanel);
                            middleRightPanel.remove(detailsPanel);
                            instructorPanel = new InstructorPanel(i);
                            detailsPanel = instructorPanel;
                            middleLeftPanel.add(reportPanel);
                            constraints.weightx = 0.4;
                            constraints.weighty = 0.6;
                            constraints.gridx = 1;
                            constraints.gridy = 0;
                            detailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                            middleRightPanel.add(detailsPanel, BorderLayout.CENTER);
                            middleLeftPanel.revalidate();
                            middleRightPanel.revalidate();
                            repaint();
                        } else if (selected instanceof Course) {
                            Course c = (Course) selected;
                            middleLeftPanel.remove(reportPanel);
                            middleRightPanel.remove(detailsPanel);
                            coursePanel = new CoursePanel(c);
                            detailsPanel = coursePanel;
                            middleLeftPanel.add(reportPanel);
                            constraints.weightx = 0.4;
                            constraints.weighty = 0.6;
                            constraints.gridx = 1;
                            constraints.gridy = 0;
                            detailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                            middleRightPanel.add(detailsPanel, BorderLayout.CENTER);
                            middleLeftPanel.revalidate();
                            middleRightPanel.revalidate();
                            repaint();
                        }
                    }
                } else if (reportPanel == courseReportPanel) {
                    Course selected = courseReportPanel.getCourse();
                    if (selected != null) {
                        middleLeftPanel.remove(reportPanel);
                        middleRightPanel.remove(detailsPanel);
                        coursePanel = new CoursePanel(selected);
                        detailsPanel = coursePanel;
                        middleLeftPanel.add(reportPanel);
                        constraints.weightx = 0.4;
                        constraints.weighty = 0.6;
                        constraints.gridx = 1;
                        constraints.gridy = 0;
                        detailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                        middleRightPanel.add(detailsPanel, BorderLayout.CENTER);
                        middleLeftPanel.revalidate();
                        middleRightPanel.revalidate();
                        repaint();
                    }
                }
            }
        }
    }
}