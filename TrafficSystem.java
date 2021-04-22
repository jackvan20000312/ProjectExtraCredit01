package edu.mtc.egr283.ProjectExtraCredit01;

import java.util.Collections;
import java.util.LinkedList;
import java.util.OptionalDouble;
import java.util.Random;

/** TrafficSystem *
 * Defines the lanes and signals that is to be studied. Collects statistics
 *
 * Model for traffic simulation
 * ============================
 *
 *  The following classes are used:
 *
 *     Vehicle Represents a vehicle
 *             Time of arrival and destination are set when create.
 *
 *     Light   Represents the light signals
 *             See below
 *
 *     Lane    Represents a piece of a road
 *             A lane is represented by an array where each element
 *             either is empty or contain a reference to a
 *             vehicle-object.
 *
 *     TrafficSystem
 *             Defines the components, ie the lanes  and signals that
 *             build the system. See below
 *
 *     Simulation
 *            main-method the controls the simulation
 *
 *
 *  The situation that is to be simulated looks schematically like
 *
 *          C           X                               E
 *   W    s1<----r1-----<---------r0---------------------
 *   S    s2<----r2-----<
 *
 *  A lane (a piece of a road) r0 split into two files r1 and r2 at X.
 *  The signal s1 controls the lane r1 and the signal s2 the lane r2.
 *
 *  Vehicles are create at E. The probability that a vehicle arrives to E
 *  at a certain time is called "the intensity of arrival".
 *
 *  At a time step the vehicles move one step forward  (if possible).
 *  At C, the vehicles are removed from the system if the resp signal is green.
 *  At X, vehicles are move from r0 to either r1 or r2 depending of its
 *  destination (if there are space for them).
 *
 *
 */ 
public class TrafficSystem{
	// Attributes that describe the elements of the system
	private Lane r0;
	private Lane r1;
	private Lane r2;
	private Light s1;
	private Light s2;

	// Various attributes for simulation parameters (intensity of arrivals, destinations...) CONSTANTS 
	private final int INTENSITY_OF_SOUTH=50;//percentage out of a hundred so 50% =50
	private final int INTENSITY_OF_ARRIVALS=80;//percentage out of a hundred so 50% =50
	private final char SOUTH='S';
	private final char WEST='W';
	private final int S1_GREEN_LENGTH=3;//time steps
	private final int S2_GREEN_LENGTH=2;//time steps
	private final int PERIOD_LENGTH=7;//time steps
	private final int TWO_ROADS_LENGTH=10;
	private final int ROADLENGTH=30;

	// Various attributes for collection of statistics
	private int time;//internal clock
	private int stuckTime=0;//time stuck
	private LinkedList<Integer> s1AverageTimes;
	private LinkedList<Integer> s2AverageTimes;


	public TrafficSystem(){
		this.r0=new Lane(this.ROADLENGTH);
		this.r1=new Lane(this.TWO_ROADS_LENGTH);
		this.r2=new Lane(this.TWO_ROADS_LENGTH);
		this.s1=new Light(this.PERIOD_LENGTH, this.S1_GREEN_LENGTH);
		this.s2=new Light(this.PERIOD_LENGTH, this.S2_GREEN_LENGTH);
		this.time=0;
		this.s1AverageTimes=new LinkedList<Integer>();
		this.s2AverageTimes=new LinkedList<Integer>();
	}//Ending Bracket of Constructor

	/**
	 * Defines how vehicles should move in the system.
	 * Steps the system one time step using the step methods in the components
	 * Creates vehicles, add and remove into the different lanes.
	 */
	public void step(){
		this.time++;
		this.s1.step();//lights
		this.s2.step();

		if(this.s1.isGreen()) {//moves vehicles in shoulder lanes
			Vehicle temp=this.r1.removeFirst();
			if(temp!=null) {
				this.s1AverageTimes.add(this.time-temp.getBornTime());
			}//Ending bracket of if statement.
		} //Ending bracket of if statement.

		if(this.s2.isGreen()) {
			Vehicle temp=this.r2.removeFirst();
			if(temp!=null) {
				this.s2AverageTimes.add(this.time-temp.getBornTime());
			}//Ending bracket of if statement.
		}//Ending bracket of if statement.

		this.r2.step();//steps shoulder lanes
		this.r1.step();
		
//TODO try cleaning this up.
		Vehicle temp=this.r0.getFirst();
		if(temp!=null) {
			if(temp.getDestination()==this.WEST) {
				if(this.r1.lastFree()) {
					this.r1.putLast(this.r0.removeFirst());
				}else {
					this.stuckTime++;
				}//Ending bracket of if statement.
			}else if(temp.getDestination()==this.SOUTH) {
				if(this.r2.lastFree()) {
					this.r2.putLast(this.r0.removeFirst());
				}else {
					this.stuckTime++;
				}//Ending bracket of if statement.
			}//Ending bracket of if statement.
		}//Ending bracket of if statement.
		this.r0.step();
		this.r0.putLast(this.generateVehicle());
	}//Ending Bracket of Method

	
	
	/**
	 * @return generated Vehicle using probabilities (intensity set at the top of program)
	 */
	public Vehicle generateVehicle(){
		//uses the probabilities set above to generate vehicles going south, west, or null vehicles.
		Random random = new Random();
		int temp=random.nextInt(101);
		if (temp<this.INTENSITY_OF_ARRIVALS) {
			temp=random.nextInt(101);
			if(temp<this.INTENSITY_OF_SOUTH) {
				return new Vehicle(this.time, this.SOUTH);
			}//Ending bracket of if statement.
			return new Vehicle(this.time, this.WEST);
		}//Ending bracket of if statement.
		return null;
	}//Ending Bracket of Method

	/**
	 * @return the collected statistics so far
	 */
	public String printStatistics(){
		StringBuffer sb = new StringBuffer();
		sb.append("\nS1 Maximal Time = ");
		sb.append(Collections.max(this.s1AverageTimes));
		OptionalDouble avg = this.s1AverageTimes.stream().mapToDouble(i -> i).average();
		sb.append("\nS1 Average Time = " + avg.getAsDouble());

		sb.append("\nS2 Maximal Time = ");
		sb.append(Collections.max(this.s2AverageTimes));
		avg = this.s2AverageTimes.stream().mapToDouble(i -> i).average();
		sb.append("\nS2 Average Time = " + avg.getAsDouble());
		sb.append("\nList of step times when queue was blocked\n");
		sb.append(this.stuckTime);

		//resets statistics
		this.stuckTime=0;
		this.s1AverageTimes=new LinkedList<Integer>();
		this.s2AverageTimes=new LinkedList<Integer>();
		return (sb.toString());
	}//Ending Bracket of Method

	/**
	 * @return a graphical representation of the current traffic situation
	 * using the toString-methods in the components.
	 */
	public String print(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.s1.toString());
		sb.append(this.r1.toString());
		sb.append(this.r0.toString());
		sb.append("\n");
		sb.append(this.s2.toString());
		sb.append(this.r2.toString());
		return (sb.toString());

	}//Ending Bracket of Method
}//Ending Bracket of Class TrafficSystem