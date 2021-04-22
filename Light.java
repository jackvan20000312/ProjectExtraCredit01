package edu.mtc.egr283.ProjectExtraCredit01;

/** Light
 *  Represents a traffic signal
 */
public class Light {
	private int period;
	private int green;
	private int time;

	/**
	 *  Constructs a  Light-object for p period and g green
	 */
	public Light(int p, int g){
		this.green=g;
		this.period=p;
		this.time=0;
	}//Ending Bracket of Method

	/**
	 * Steps the clock of the signal
	 */
	public void step(){
		this.time++;
	}//Ending Bracket of Method

	/**
	 * @return true if the signal is green otherwise false
	 */
	public boolean isGreen(){
		if(this.time%period<=green) {
			return true;
		}//Ending bracket of if statement.
		return false;
	}//Ending Bracket of Method

	/**
	 * @return A String-representation of the signal that shows its color
	 */
	public String toString(){
		if(this.isGreen()) {
			return"G:";
		}//Ending bracket of if statement.
		return "R:";
	}//Ending Bracket of Method
}//Ending Bracket of Class
