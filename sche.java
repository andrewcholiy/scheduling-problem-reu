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
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class sche {
  //Alright, so the first thing to do is generate the jobs. We'll ask the user for how many jobs they want, and an upper range of possible deadline times.
  static Job[] mahJobs; // holds unique jobs
  static Job[] time; // where scheduling is done
  static int calib = 3;
  static int isC = 0;
  
  Scanner console = new Scanner(System.in);
  Random rand = new Random();
  
  static Comparator<Job> comparator = new EDFComparator();
  static PriorityQueue<Job> pq = new PriorityQueue<Job>(15, comparator);
  
  public static void main(String[] args) {
	  Scanner console = new Scanner(System.in);
	  Random rand = new Random();

	  int numJ = 0;
	  do {
		  System.out.println("Enter # of jobs larger than 2: ");
		  numJ = console.nextInt();
	  } while (numJ < 3);
	  	  
	  mahJobs = new Job[numJ];
	  time = new Job[6]; // numJ*0.75
	  	  
	  generateJobs(rand);
	  schedule();
  }
  
  public static void generateJobs(Random rand) {
	  int r;
	  int d;
	  char a = 'a';
	  
	  /*
	  for (int i = 0; i < mahJobs.length; i++) {
		  r = rand.nextInt((time.length - 0) + 1) + 0;
		  d = rand.nextInt((time.length - r) + 1) + r;
		  mahJobs[i] = new Job(a, r, d);
		  System.out.println(mahJobs[i].toString());
		  a++;
	  }
	  */
	  
	  
	  mahJobs[0] = new Job('a',1,1);
	  mahJobs[1] = new Job('b',2,5);
	  mahJobs[2] = new Job('c',3,4);
	  mahJobs[3] = new Job('d',4,4);
	  

	  System.out.println("*****");

	  
  }
  
  // for each time step
  //	add released job to priority queue
  //	if m is calibrated
  //		remove earliest deadline from Q and schedule (pushBack)
  //		if new top of Q has deadline at this time
  //			pushback
  public static void schedule() {
	  Arrays.fill(time, new Job('z',0,0)); // set all job slots to be empty jobs, id = z, meaningless
	  
	  for (int i = 0; i < time.length; i++) {
		  for (Job j : mahJobs) {
			  if (j.getRelease() == i) {
				  pq.add(j);
				  System.out.println(j + " " + i);
			  }
		  }

		  if (isC % 3 < calib) { // T = 3, isC %3
			  if (isCalibrated(i)) {
				  if (!pq.isEmpty()) {
					  Job j = pq.remove();
					  int c = 0;
					  
					  do { // this loop is necessary before pushBack .. but after? idts ..
						  j = pq.remove();
						  c++;
						  System.out.println(j.toString() + " here");
						  System.out.println(j.getDeadline() + " " + i);
					  } while (j.getDeadline() > i && pq.size() > 0);
					  
					  
					  while (j.getDeadline() < i && pq.size() > 0) {
						  j = pq.remove();
						  c++;
						  System.out.println(j.toString() + " here");
						  System.out.println(j.getDeadline() + " " + i);
					  }
					  
//					  if (j.getDeadline() < i) { // necessary before pushBack .. but after? idk
//						  System.out.println("fuggedaboutit " + j.toString());
//					  } else {
						  time[i] = j;
						  j.setPosition(i);
						  System.out.println("Scheduled: " + j.toString() + " @ " + j.getPosition());
						  
						  if (pq.peek() != null) { // two jobs, 1 slot
							  if (pq.peek().getDeadline()==j.getDeadline()) {
								  System.out.println("!!!!!!!!!!!");

								  Job newJ = pq.remove();
								  pushBack(j, newJ, i);
							  }
						  }
//						  System.out.println(!pq.isEmpty());
//						  System.out.println(j.getDeadline() == pq.peek().getDeadline());
						  if (!pq.isEmpty() && j.getDeadline() == pq.peek().getDeadline()) {
							  System.out.println("!!!!!!!!!!!!");
							  Job newJ = pq.remove();
							  pushBack(j, newJ, i);
						  }
//					  }
				  }
			  } else { // must calibrate, check pushBack
				  System.out.println("Calibrate: \t @ time: " + i);
				  time[i] = new Job('*',0,0);
				  // check for job
			  }
		  }
		  isC++;
	  }
	  for (int r = 0; r < time.length; r++) {
		  System.out.println(time[r].toString());
	  }
  }
  
  public static void pushBack(Job j1, Job j2, int i) {
	  if (j1.getID() != '*' && j2.getID() != '*') {
		  int r1 = j1.getRelease();
		  int r2 = j2.getRelease();
		  Job lookB = null;
		  int newI = i;
		  if (r1 < r2) {
			  time[j1.getPosition()] = j2;
			  do {
				  if (time[i-1] != null) {
					  lookB = time[i-1];
					  newI++;
				  }
			  } while (!(lookB.getDeadline() > i));
			  time[lookB.getPosition()] = j1;
			  time[i+1] = lookB;
		  }
	  }
  }
  
  public static boolean isCalibrated(int i) {
	  if (i == 0) {
		  return false;
	  } else if (i == 1) {
		  if (time[i-1].getID()=='*') {
			  return true;
		  }
	  } else if (i == 2) {
		  if (time[i-1].getID()=='*' || time[i-2].getID()=='*') {
			  return true;
		  }
	  } else if (i >= 3) {
		  if ((time[i-1].getID()=='*') || (time[i-2].getID()=='*') || (time[i-3].getID()=='*')) {
			  return true;
		  }
	  }
	  return false; // fix this logic
  }
  
  public static int compareTo(Job x, Job y) {
      // Assume neither string is null. Real code should
      // probably be more robust
      // x.length() - y.length(),
      if (x.getDeadline() < y.getDeadline()) {
          return -1;
      }
      if (x.getDeadline() > y.getDeadline()) {
          return 1;
      }
      return 0;
  }
  
  
}