//*******************************************************************
// Dear CompileJava users,
//
// CompileJava has been operating since 2013 completely free. If you
// find this site useful, or would otherwise like to contribute, then
// please consider a donation (link in 'More Info' tab) to support
// development of the new CompileJava website (stay tuned!).
//
// Most sincerely, Z.
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class

// one class needs to have a main() method
public class HelloWorld
{
  // arguments are passed using the text field below this editor
  public static void NOTmain(String[] args)
  {
    OtherClass myObject = new OtherClass("Hello World!");
    System.out.print(myObject);
  }
}

// you can add other public classes to this editor in any order
public class OtherClass
{
  private String message;
  private boolean answer = false;
  public OtherClass(String input)
  {
    message = "Why, " + input + " Isn't this something?";
  }
  public String toString()
  {
    return message;
  }
}

public class Job
{
  public int release;
  public int deadline;
  public int pushed;
  public int position;
  public Job(int d){
    position = 0;
  }
}

public class Mainbit
{
 //Alright, so the first thing to do is generate the jobs. We'll ask the user for how many jobs they want, and an upper range of possible deadline times.
  Job[] mahJobs;
  public static void main(String[] args){
    if(args.length != 2){
      System.out.println("We've got a problem.");
      return;
    }
    System.out.println("Eyyyyy");
    mahJobs = new Job[args[0]];
    return;
  }
  public void generateJobs(int end, int numJobs){
    
  }
}