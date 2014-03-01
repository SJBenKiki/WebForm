package CAS.Data.Build;

import java.io.IOException;
import java.util.ArrayList;
public class TAFBuilderMain
{
    public static void main(String[] src)
    {
        TAFBuilder tBuild = new TAFBuilder(60);
        try
        {
            ArrayList<String> names = tBuild.buildNameList();
            ArrayList<String> numbers = tBuild.buildNumberList();
            ArrayList<String> courses = tBuild.buildCourseList();
            if(names != null && courses != null) {
                tBuild.buildTAFFile(names,numbers,courses);
                System.out.println("Success");
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}