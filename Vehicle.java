package edu.mtc.egr283.ProjectExtraCredit01;

/**  Vehicle
 * Represents a vehicle
 */
public class Vehicle {

    private int bornTime;
    private char destination;

    /**
     *  Constructs a  Vehicle-object with b bornTime and d destination
     */
    public Vehicle(int b, char d){
    	this.bornTime=b;
    	this.destination=d;
    }

    /**
     * @return A String-representation of the vechile and its bornTime and destination
     */
    public String toString(){
    	
        return (bornTime+", "+destination);
    }
}
