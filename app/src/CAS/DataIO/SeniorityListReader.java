/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAS.DataIO;
import java.util.*;
import java.io.*;
import CAS.Data.*;
/**
 * Reads in teachers seniority from a file and parses it into usable data
 * @author Eric Sullivan
 */

public class SeniorityListReader
{
    private Scanner scan;
    private String workArea;
    private int senior;
    private String name;
    private String input;
    
    /*
    public SeniorityListReader() throws IOException {
        super("FakeSeniority.txt");
        scan = new Scanner(super.getFile());
        workArea = "";
        senior =0;
        name ="";
        input = "";
    }
    * */
    
    public static HashMap<String,Instructor> loadSeniorityList(String filename, HashMap<String,Instructor> theMap)
            throws FileNotFoundException, IncorrectFormatException
    {
        return loadSeniorityList(new File(filename), theMap);
    }
    //intializes data from data file found by the fileSelector window.
    public static HashMap<String,Instructor> loadSeniorityList(File file, HashMap<String,Instructor> theMap)
           throws FileNotFoundException, IncorrectFormatException
    {
        
        Scanner scan = new Scanner(file);
        String workArea;
        int senior;
        String name;
        String input;
        
        
        workArea = "";
        senior =0;
        name ="";
        input = "";
        
        
        StringBuilder snip;
        StringTokenizer st;
        while(scan.hasNextLine()){  //loop that continue untill the end of the file
            input = scan.nextLine();
            //System.out.println("input: " + input);
            if(!input.equals("")){  // checks if line is empty if true the next line in a new work area field
            if(input.charAt(0)=='-'&&input.charAt(1)=='-'){ // all work areas has -- in the beginning
                snip = new StringBuilder(input);
                if(input.charAt(input.length()-1)==' '){
                    throw new IncorrectFormatException("Space at the end of : "+input);
                }
                workArea = snip.substring(2); //cuts the -- of and adds it to the workarea string.
                //System.out.println("work area: " + workArea);
                
            }
            else{
                st = new StringTokenizer(input,"\r\n\t\f"); //tokenizes string for parsing
                name = st.nextToken(); //gets the next token and add it to the name field
                if(name.charAt(name.length()-1)==' '){ // Check if there is a space at the end of the name if there is throws exception
                    throw new IncorrectFormatException("Space at the end of "+input);
                }
                //System.out.println("name: " + name);
                senior =Integer.parseInt(st.nextToken()); //gets the next token and addit to the senior field if there is a space throws a parse exception
                //System.out.println("senior: " + senior);
                try {
                theMap.get(name).getSeniorities().put(workArea,senior);
                }
                catch (NoSuchElementException e){}
                
            }
            }
        }
        return theMap;
    }
}