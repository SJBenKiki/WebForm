package CAS.Data.Build;

import static CAS.DataIO.CourseReader.loadWorkAreas;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TAFBuilder {

    private int size;
    private Random rand;
    private final int AMOUNT_OF_DIGITS = 10;
    private final String NAMES = "names.txt";
    private final String TAF = "TAF.taf";
    private final String SENIORITY = "Seniority.snr";
    private final String WORK = "WorkAreas.wrk";
    private final String COURSE_SCHEDULE = "CourseSchedule.crs";
    private final String DEFAULT_DIRECTORY = "user.dir";
    private final String INPUT_FOLDER = "\\input\\";
    private String directory;

    public TAFBuilder(int size) {
        rand = new Random(System.currentTimeMillis());
        this.size = size;

        directory = System.getProperty(DEFAULT_DIRECTORY);
        directory += INPUT_FOLDER;
    }

    public ArrayList<String> buildNameList() throws FileNotFoundException {
        ArrayList<String> names = new ArrayList<String>();
        File file = new File(directory + NAMES);
        if (!file.exists()) {
            return null;
        }
        Scanner scan = new Scanner(file);
        //int lines = 0;
        String temp = null;
        while (scan.hasNextLine() /* && lines < size */) {
            temp = scan.nextLine();
            if (scan.hasNextLine()) {
                names.add(temp + ", " + scan.nextLine());
            }
            //++lines;
        }
        scan.close();
        scan = null;
        file = null;
        return names;
    }

    public void buildTAFFile(ArrayList<String> names,
            ArrayList<String> numbers,
            ArrayList<String> courses) throws IOException {
        File workAreaFile = new File(directory + WORK);
        HashMap<String, String> workAreas = loadWorkAreas(workAreaFile);

        HashMap<String, ArrayList<String>> seniorities = new HashMap<String, ArrayList<String>>();

        File fTaf = new File(directory + TAF);
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fTaf.getAbsoluteFile()));
        String[] split = null;
        String line = null;
        String courseLine = null;
        String[] timeZone = {"10-2", "2-6", "6-10"};
        int courseLoad = 0;
        for (int i = 0; i < size; ++i) {
            do {
                split = courses.get(rand.nextInt(courses.size() - 1)).split("\t");
            } while (!(split.length > 5));
            String instructor = names.get(rand.nextInt(names.size() - 1));
            line = instructor
                    + "\t"
                    + numbers.get(rand.nextInt(numbers.size() - 1));
            bw.write(line);
            bw.newLine();
            line = split[5];
            String days = "";
            for (int j = 0; j < line.length(); ++j) {
                Character character = line.charAt(j);
                character = Character.toLowerCase(character);
                //System.out.println(character);
                if (character.equals('r')) {
                    days += 't';
                    character = 'h';
                }
                if (character.equals('s')) {
                    days += 's';
                    character = line.charAt(++j);
                }
                days += character + ",";
            }
            /*
             char[] clean = days.toCharArray();
             clean[clean.length-1] = '\b';
             * */
            String clean = days.substring(0, days.length() - 1);
            clean += "\t";
            //String formatted = new String(clean);
            courseLoad = rand.nextInt(3) + 1;
            clean += timeZone[courseLoad - 1];
            bw.write(clean);
            bw.newLine();
            for (int j = 0; j < courseLoad; ++j) {
                split = courses.get(rand.nextInt(courses.size() - 1)).split("\t");
                String[] course = split[1].split(" ");
                if (split[2].contains("L")) {
                    split[2] = split[2].substring(0, split[2].length() - 1);
                }
                while (split[2].length() < 3) {
                    split[2] = "0" + split[2];
                }
                line = course[0] + course[1] + "," + split[2];
                bw.write(line);
                bw.newLine();

                String key = workAreas.get(course[0] + course[1]);
                ArrayList<String> instructors = seniorities.get(key);
                if (instructors == null) {
                    instructors = new ArrayList<String>();
                }
                instructors.add(instructor);
                seniorities.put(key, instructors);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();

        buildSeniorityFile(seniorities);
    }

    public void buildSeniorityFile(HashMap<String, ArrayList<String>> seniorities) throws IOException {
        File fTaf = new File(directory + SENIORITY);
        BufferedWriter bw = new BufferedWriter(new FileWriter(fTaf.getAbsoluteFile()));

        List<String> keys = new ArrayList(seniorities.keySet());
        Collections.sort(keys);

        Random rng = new Random();

        for (String key : keys) {
            //System.out.println("--" + key);
            bw.write("--" + key);
            bw.newLine();
            ArrayList<String> instructors = seniorities.get(key);
            for (String instructor : instructors) {
                int seniority = rng.nextInt(30);
                if (seniority > 3) {
                    //System.out.println(instructor + "\t" + seniority);
                    bw.write(instructor + "\t" + seniority);
                    bw.newLine();
                }
            }
        }

        bw.flush();
        bw.close();
    }

    public ArrayList<String> buildCourseList() throws IOException {
        ArrayList<String> courses = new ArrayList<String>();
        File file = new File(directory + COURSE_SCHEDULE);
        if (!file.exists()) {
            return null;
        }
        Scanner scan = new Scanner(file);
        if (scan.hasNextLine()) {
            scan.nextLine();
        }
        //int lines = 0;
        while (scan.hasNextLine() /* && lines < size */) {
            courses.add(scan.nextLine());
            //++lines;
        }
        scan.close();
        scan = null;
        file = null;
        return courses;
    }

    public ArrayList<String> buildNumberList() {
        ArrayList<String> numbers = new ArrayList<String>();
        for (int i = 0; i < size; ++i) {
            numbers.add(buildNumber("###-###-####"));
        }
        return numbers;
    }

    public String buildNumber(String pattern) {
        int index = 0;
        int literalEnd = -1;
        String output = "";
        while (index < pattern.length()) {
            if (pattern.charAt(index) == '#') {
                output += rand.nextInt(AMOUNT_OF_DIGITS);
            } else {
                output += pattern.charAt(index);
            }
            index++;
        }
        return output;
    }
}