import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class workAreasTest {
  
  public static void main(String[] args) {
    File file;
    Scanner scan = null;
    try {
      file = new File("workAreas.wrk");
      scan = new Scanner(file);
      HashMap<String, String> workAreaList = new HashMap<String, String>();
      String area = null;
      
      while(scan.hasNext()) {
        String line = scan.nextLine();
        if(line.equals("")) {
          area = null;
        }
        else {
          if(area == null) {
            area = line;
          }
          else {
            String[] split = line.replaceAll("[(),]", "").split(" ");
            for(int i = 1; i < split.length; i++) {
              workAreaList.put("" + split[0] + split[i], area);
            }
          }
        }
      }
      System.out.print(workAreaList);
    }
    catch (IOException e) {
      
    }
    finally {
      if(scan != null) {
        scan.close();
      }
    }
  }
}


 /* public HashMap<String, String> loadWorkAreas(String filename) {
    File file;
    Scanner scan = null;
    try {
      file = new File(filename);
      scan = new Scanner(file);
      HashMap<String, String> workAreaList = new HashMap<String, String>();
      String area = null;
      
      while(scan.hasNext()) {
        String line = scan.nextLine();
        if(line.equals("")) {
          area = null;
        }
        else {
          if(area == null) {
            area = line;
          }
          else {
            String[] split = line.replaceAll("[(),]", "").split(" ");
            for(int i = 1; i < split.length; i++) {
              workAreaList.put("" + split[0] + split[i], area);
            }
          }
        }
      }
      return workAreaList;
    }
    catch (IOException e) {
      
    }
    finally {
      if(scan != null) {
        scan.close();
      }
    }
    return null;
  } */