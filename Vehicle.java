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
	}

	/**
	 *  Constructs a  Vehicle-object with b bornTime and d destination
	 */
	public int getBornTime(){
		return this.bornTime;
	}

	/**
	 * @return A String-representation of the vehicle and its bornTime and destination
	 */
	public String toString(){

		return Character.toString(destination);
	}
}
