public class Job {
	private char id;
	private int release;
	private int deadline;
	private int pushed;
	private int position;
  
	public Job(char i, int r, int d) {
		id = i;
		release = r;
		deadline = d;
		pushed = 0;
		position = 0;
	}
  
	public int getRelease() {
		return this.release;
	}
  
	public int getDeadline() {
		return this.deadline;
	}
  
	public int getPushed() {
		return this.pushed;
	}
  
	public int getPosition() {
		return this.position;
	}
  
//
  
	public void setRelease(int x) {
		this.release = x;
	}
  
	public void setDeadline(int x) {
		this.deadline = x;
	}
  
	public void setPushed(int x) {
		this.pushed = x;
	}
  
	public void setPosition(int x) {
		this.position = x;
	}
  
	public String toString() {
		return this.release + " " + this.deadline + " " + this.id;
	}

	public char getID() {
		return id;
	}

	public void setID(char i) {
		this.id = i;
	}  
}