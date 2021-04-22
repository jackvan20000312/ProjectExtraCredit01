package edu.mtc.egr283.ProjectExtraCredit01;

/** Lane
 *  Represents a lane of vehicles using an array
 */
public class Lane {

	private Vehicle[] theLane;

	/**
	 *  Constructs a  Lane-object with place for n vehicles
	 */
	public Lane(int n){
		this.theLane = new Vehicle[n];
	}

	/**
	 * Step all vehicles (except the one in place 0) one step
	 * (if possible). (The vehicle at position 0 is removed before calling
	 * this method using the method below).
	 */
	public void step(){
		for(int i=1; i<this.theLane.length; i++) {
			this.theLane[i-1]=this.theLane[i];
			this.theLane[i]=null;
		}
	}

	/**
	 * Removes the first vehicle
	 * @return The Vehicle at the first place or null if it is empty
	 */
	public Vehicle removeFirst(){
		Vehicle temp= theLane[0];
		this.theLane[0]= null;
		return temp;
	}

	/**
	 * Returns the first vehicle without removing it
	 * @return A reference to the vehicle in the first place or null if it is
	 * empty
	 */
	public Vehicle getFirst(){
		return this.theLane[0];
	}

	/**
	 * @return true if the last place if empty, else false
	 */
	public boolean lastFree(){
		if(this.theLane[this.theLane.length-1]==null) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Stores a vehicle at the end of the lane
	 * @param v The vehicle that is to be stored
	 */
	public void putLast(Vehicle v){
		if(this.lastFree()) {
			this.theLane[this.theLane.length-1]=v;
		}
	}

	/**
	 * @return A string representation of a lane and its vehicles
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i=0; i<this.theLane.length; i++) {
			if(this.theLane[i]==null) {
				sb.append(" ");
			}else {
				sb.append(this.theLane[i].toString());
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
