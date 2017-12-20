import java.util.*;
import java.io.*;

public class RabinKarp
{

   public final static int d = 256;
   
   
   static void search(String pat, String txt, int q)
    {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; 
        int t = 0; 
        int h = 1;
     
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;
     
        for (i = 0; i < M; i++)
        {
            p = (d*p + pat.charAt(i))%q;
            t = (d*t + txt.charAt(i))%q;
        }
     
        for (i = 0; i <= N - M; i++)
        {
            if ( p == t )
            {
                for (j = 0; j < M; j++)
                {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }
            if (j == M)
                    System.out.println("Pattern found at index " + i);
            }
     
            if ( i < N-M )
            {
                t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
                if (t < 0)
                t = (t + q);
            }
        }
    }
     public static void main (String[] args)
   {
      String testFile;
      String pattern;
      String test= "";
      Scanner scan= new Scanner(System.in);
      
      System.out.println("Enter location of test text file:");
      
      testFile = scan.nextLine();
      
      System.out.println("You entered: \"" + testFile + "\"");
      System.out.println("Enter search pattern:");
      
      pattern = scan.nextLine();
      
      System.out.println("You entered: \"" + pattern + "\"");
      
     try
     {
      FileReader fr = new FileReader(testFile);
      int j;
      while ((j=fr.read())!=-1)
      {
         test+=(Character.toString((char) j));
      }   
     }
      
      catch (IOException e)
      {
         System.out.println("File not found");
      }
      int q = 101;
      search(pattern, test, q);
   }
}