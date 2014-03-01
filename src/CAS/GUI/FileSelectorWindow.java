package CAS.GUI;

import CAS.CourseAssignment;
import CAS.DataIO.IncorrectFormatException;
import CAS.GUI.Login.LoginFrame;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class tries to locate the files necessary for this program to run. If it
 * can't find them automatically, it'll pop open a file chooser.
 *
 * @author Julian Kuk
 */
public class FileSelectorWindow {

    private final String WORK_DIALOG_TITLE = "Select WORK AREA file...";
    private final String COURSE_DIALOG_TITLE = "Select COURSE file...";
    private final String TAF_DIALOG_TITLE = "Select TAF file...";
    private final String SENIORITY_DIALOG_TITLE = "Select SENIORITY file...";
    private final String WORK_EXTENSION = "wrk";
    private final String COURSE_EXTENSION = "crs";
    private final String TAF_EXTENSION = "taf";
    private final String SENIORITY_EXTENSION = "snr";
    private final String DEFAULT_DIRECTORY = "user.dir";
    private final String INPUT_FOLDER = "\\input\\";
    private final String WORK_LIST = "WorkAreas." + WORK_EXTENSION;
    private final String COURSE_LIST = "CourseSchedule." + COURSE_EXTENSION;
    private final String TAF_LIST = "TAF." + TAF_EXTENSION;
    private final String SENIORITY_LIST = "Seniority." + SENIORITY_EXTENSION;
    private final int CANCEL = -1;
    private final int WORK_CHOOSER = 0;
    private final int COURSE_CHOOSER = 1;
    private final int TAF_CHOOSER = 2;
    private final int SENIORITY_CHOOSER = 3;
    private final int MAIN_WINDOW = 4;
    
    private CourseAssignment courseAssignment;
    private String directory;

    /**
     * The constructor creates the main CourseAssignment object. If it can
     * successfully find all the necessary files, it will open the main GUI
     * window.
     */
    public FileSelectorWindow() {
        courseAssignment = new CourseAssignment(); // create the main course assignment object
        boolean ready = false; // variable for tracking whether the program is ready
        int entryPoint = WORK_CHOOSER; // the initial entry point for the file selection

        directory = System.getProperty(DEFAULT_DIRECTORY); // get the current directory
        File workAreasFile = new File(directory + INPUT_FOLDER + WORK_LIST); // try to open the work areas file
        while (!ready) {
            switch (entryPoint++) {
                case WORK_CHOOSER:
                    if (!workAreasFile.exists()) { // if it failed to open the file
                        workAreasFile = chooseFile(WORK_DIALOG_TITLE, WORK_EXTENSION); // open the file chooser
                        if (workAreasFile == null) { // if the file chooser doesn't return anything
                            entryPoint = CANCEL; // return to the login window
                        }
                    }
                    break;

                case COURSE_CHOOSER:
                    try { // try to load the courses
                        courseAssignment.loadCourses(new File(directory + INPUT_FOLDER + COURSE_LIST), workAreasFile);
                    } catch (FileNotFoundException e) { // if it can't, open the file chooser
                        entryPoint = chooseCourseFile(entryPoint, workAreasFile);
                    } catch (IncorrectFormatException e) { // if the file is not in the correct format
                        entryPoint = CANCEL; // return to the log in window
                        e.printStackTrace(); // and print the stack trace
                    }
                    break;

                case TAF_CHOOSER:
                    try { // try to load the instructors
                        courseAssignment.loadInstructors(new File(directory + INPUT_FOLDER + TAF_LIST));
                    } catch (FileNotFoundException e) { // if it can't, open the file chooser
                        entryPoint = chooseTAFFile(entryPoint);
                    } catch (IncorrectFormatException e) { // if the file is not in the correct format
                        entryPoint = CANCEL; // return to the log in window
                        e.printStackTrace(); // and print the stack trace
                    }
                    break;

                case SENIORITY_CHOOSER:
                    try { // try to load the seniorities
                        courseAssignment.loadSeniorityList(new File(directory + INPUT_FOLDER + SENIORITY_LIST));
                    } catch (FileNotFoundException e) { // if it can't, open the file chooser
                        entryPoint = chooseSeniorityFile(entryPoint);
                    } catch (IncorrectFormatException e) { // if the file is not in the correct format
                        entryPoint = CANCEL; // return to the log in window
                        e.printStackTrace(); // and print the stack trace
                    }
                    break;
                    
                case MAIN_WINDOW:
                    ready = true; // break out of the loop
                    new MainWindow(courseAssignment); // create the main GUI window
                    break;
                    
                default:
                    ready = true; // break out of the loop
                    new LoginFrame(); // return to the log in window
                    break;
            }
        }
    }

    /**
     * Wrapper function for the chooseFile method. Sets it up for selecting the
     * course schedule file specifically.
     *
     * @param entryPoint where the program should return to if successful
     * @param workAreasFile the work areas file
     * @return where the program will actually return to
     */
    private int chooseCourseFile(int entryPoint, File workAreasFile) {
        File file = chooseFile(COURSE_DIALOG_TITLE, COURSE_EXTENSION); // open a file chooser window
        if (file == null) { // if no file was selected
            return CANCEL; // return to the log in window
        } else {
            try { // try to load the file
                courseAssignment.loadCourses(file, workAreasFile);
            } catch (FileNotFoundException e) { // consume the FileNotFoundException
            } catch (IncorrectFormatException e) { // if the file is not in the correct format
                entryPoint = CANCEL; // return to the log in window
                e.printStackTrace(); // and print the stack trace
            }
            return entryPoint; // return the proper entry point
        }
    }

    /**
     * Wrapper function for the chooseFile method. Sets it up for selecting taf
     * file specifically.
     *
     * @param entryPoint where the program should return to if successful
     * @return where the program will actually return to
     */
    private int chooseTAFFile(int entryPoint) {
        File file = chooseFile(TAF_DIALOG_TITLE, TAF_EXTENSION); // open a file chooser window
        if (file == null) { // if no file was selected
            return CANCEL; // return to the log in window
        } else {
            try { // try to load the file
                courseAssignment.loadInstructors(file);
            } catch (FileNotFoundException e) { // consume the FileNotFoundException
            } catch (IncorrectFormatException e) { // if the file is not in the correct format
                entryPoint = CANCEL; // return to the log in window
                e.printStackTrace(); // and print the stack trace
            }
            return entryPoint; // return the proper entry point
        }
    }

    /**
     * Wrapper function for the chooseFile method. Sets it up for selecting the
     * seniority file specifically.
     *
     * @param entryPoint where the program should return to if successful
     * @return where the program will actually return to
     */
    private int chooseSeniorityFile(int entryPoint) {
        File file = chooseFile(SENIORITY_DIALOG_TITLE, SENIORITY_EXTENSION); // open a file chooser window
        if (file == null) { // if no file was selected
            return CANCEL; // return to the log in window
        } else {
            try { // try to load the file
                courseAssignment.loadSeniorityList(file);
            } catch (FileNotFoundException e) { // consume the FileNotFoundException
            } catch (IncorrectFormatException e) { // if the file is not in the correct format
                entryPoint = CANCEL; // return to the log in window
                e.printStackTrace(); // and print the stack trace
            }
            return entryPoint; // return the proper entry point
        }
    }

    /**
     * Sets up a file chooser window.
     *
     * @param dialogTitle the title for the dialog window
     * @param fileExtension the specific extension to filter for by default
     * @return the file that is selected
     */
    private File chooseFile(String dialogTitle, String fileExtension) {
        directory = System.getProperty(DEFAULT_DIRECTORY); // get the current directory
        JFileChooser chooser = new JFileChooser(new File(directory + INPUT_FOLDER)); // open at a default location

        chooser.setDialogTitle(dialogTitle); // set the dialog title
        FileNameExtensionFilter filter = new FileNameExtensionFilter(fileExtension, fileExtension); // create a filter
        chooser.setFileFilter(filter); // set the default filter
        if (chooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) { // if you selected a file
            return chooser.getSelectedFile(); // return the file
        } else {
            return null; // else, return null
        }
    }
}