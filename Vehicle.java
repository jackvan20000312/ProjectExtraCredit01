package edu.mtc.egr283.ProjectExtraCredit01;

/**  Vehicle
 * Represents a vehicle
 */
public class Vehicle {

	private int bornTime;
	private Character destination;

	/**
	 *  Constructs a  Vehicle-object with b bornTime and d destination
	 */
	public Vehicle(int b, char d){
		this.bornTime=b;
		this.destination=d;
	}//Ending Bracket of Method

	/**
	 *  Constructs a  Vehicle-object with b bornTime and d destination
	 */
	public int getBornTime(){
		return this.bornTime;
	}//Ending Bracket of Method
	
	/**
	 *  Constructs a  Vehicle-object with b bornTime and d destination
	 */
	public char getDestination(){
		return this.destination;
	}//Ending Bracket of Method

	/**
	 * @return A String-representation of the vehicle and its bornTime and destination
	 */
	public String toString(){
		return Character.toString(destination);
	}//Ending Bracket of Method
}//Ending Bracket of Class
