package CAS.GUI;

import java.awt.*;
import javax.swing.*;
import CAS.CourseAssignment;
import CAS.Data.Course;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Pat
 */
public class CourseReportPanel extends JPanel {

    private ArrayList<Course> aList;
    private JList<String> list;
    private JLabel label1;
    private DefaultListModel<String> listModel;
    private JScrollPane listScroller;
    private CourseAssignment courseAssignment;
    private Course course;
    private MouseListener mouseListener;

    /**
     * Creates a CourseReportPanel which displays all of the courses by their
     * class code.
     *
     * @param courseAssignment
     * @param mouseListener
     */
    public CourseReportPanel(CourseAssignment courseAssignment, MouseListener mouseListener) {
        this.courseAssignment = courseAssignment;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setBackground(Color.LIGHT_GRAY);

        label1 = new JLabel("Courses");
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.05;
        add(label1, c);

        aList = new ArrayList<>();
        for (Course co : courseAssignment.getCourses().values()) {
            aList.add(co);
        }
        Collections.sort(aList);
        
        listModel = new DefaultListModel<>();
        updateList();
        list = new JList<>(listModel);

        this.mouseListener = mouseListener;
        list.addMouseListener(mouseListener);

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        listScroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;
        add(listScroller, c);
        setVisible(true);
    }

    /**
     * Returns a Course object from the JList.
     *
     * @return The selected Course
     */
    public Course getCourse() {
        if (list.getSelectedValue() != null) {
            String[] selected = ((String) list.getSelectedValue()).split(" :", 0);
            String s = selected[0];
            Course c = courseAssignment.getCourses().get(s);
            return c;
        } else {
            return null;
        }
    }

    /**
     * Clears the DefaultListModel then adds elements to it from the ArrayList.
     * Sorts the assigned courses as well.
     */
    public void updateList() {
        listModel.clear();

        ArrayList<String> a = new ArrayList<>();
        for (Course co : aList) {
            if (co.getInstructor() == null && co.getLab() == null) {
                listModel.addElement(co.getClassCode() + "," + co.getSection() + " : " + "n/a");
            } else if (co.getInstructor() != null && co.getLab() != null) {
                a.add(co.getClassCode() + "," + co.getSection() + " : " + co.getInstructor().getName() + " (Lab)");
            } else if (co.getInstructor() != null && co.getLab() == null) {
                a.add(co.getClassCode() + "," + co.getSection() + " : " + co.getInstructor().getName());
            } else {
                listModel.addElement(co.getClassCode() + "," + co.getSection() + " : " + "n/a" + " (Lab)");
            }
        }
        Collections.sort(a);
        for (int i = 0; i < a.size(); i++) {
            listModel.add(i, a.get(i));
        }
    }
}
