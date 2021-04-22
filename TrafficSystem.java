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

	// Various attributes for simulation parameters (intensity of arrivals, destinations...)
	private final int INTENSITY_OF_SOUTH=50;//percentage out of a hundred so 50% =50
	private final int INTENSITY_OF_ARRIVALS=80;//percentage out of a hundred so 50% =50
	private final char SOUTH='S';
	private final char WEST='W';

	
	// Various attributes for collection of statistics
	private final int S1_GREEN_LENGTH=3;//time steps
	private final int S2_GREEN_LENGTH=2;//time steps
	private final int PERIOD_LENGTH=7;//time steps


	private final int shoulderLength=10;
	private final int roadLength=30;
	private int time;
	private int stuck1=0;
	private LinkedList<Integer> s1AverageTimes;
	private LinkedList<Integer> s2AverageTimes;
	public TrafficSystem(){
		this.r0=new Lane(this.roadLength);
		this.r1=new Lane(this.shoulderLength);
		this.r2=new Lane(this.shoulderLength);
		this.s1=new Light(this.PERIOD_LENGTH,this.S1_GREEN_LENGTH);
		this.s2=new Light(this.PERIOD_LENGTH,this.S2_GREEN_LENGTH);
		this.time=0;
		this.s1AverageTimes=new LinkedList<Integer>();
		this.s2AverageTimes=new LinkedList<Integer>();

	}

	/**
	 * Defines how vehicles should move in the system.
	 * 		*if light is green, remove first vehicle from shoulder(do for both shoulders)
	 * 		*step r1&r2
	 * 		*if vehicle in r0 is W or S, send to correct Shoulder(r1, r2)
	 * 		*step r0(main road)
	 * 		*
	 * Steps the system one time step using the step methods in the components
	 * Creates vehicles, add and remove into the different lanes.
	 */
	public void step(){
		this.time++;
		this.s1.step();//lights
		this.s2.step();
		if(this.s1.isGreen()) {//moves vehicles in shoulder lanes
			if(this.r1.getFirst()!=null) {
				this.s1AverageTimes.add(this.time-this.r1.removeFirst().getBornTime());
			}else {
				this.r1.removeFirst();
			}
		} if(this.s2.isGreen()) {
			if(this.r2.getFirst()!=null) {
				this.s2AverageTimes.add(this.time-this.r2.removeFirst().getBornTime());
			}else {
				this.r2.removeFirst();
			}
		}

		this.r2.step();//steps shoulder lanes
		this.r1.step();
		if(this.r0.getFirst()!=null) {
			if(this.r0.getFirst().getDestination()==this.WEST) {
				if(this.r1.lastFree()) {
					this.r1.putLast(this.r0.removeFirst());
				}else {
					this.stuck1++;
				}
			}else if(this.r0.getFirst().getDestination()==this.SOUTH) {
				if(this.r2.lastFree()) {
					this.r2.putLast(this.r0.removeFirst());
				}else {
					this.stuck1++;
				}
			}
		}
		this.r0.step();
		this.r0.putLast(this.generateVehicle());



	}

	public Vehicle generateVehicle(){
		Random random = new Random();
		int temp=random.nextInt(102);
		if (temp<this.INTENSITY_OF_ARRIVALS) {
			temp=random.nextInt(102);
			if(temp<this.INTENSITY_OF_SOUTH) {
				return new Vehicle(this.time, this.SOUTH);
			}else if(temp>=this.INTENSITY_OF_SOUTH) {
				return new Vehicle(this.time, this.WEST);
			}
		}
		return null;
	}

	/**
	 * Print the collected statistics so far
	 */
	public void printStatistics(){
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
		sb.append(this.stuck1);

		this.stuck1=0;
		this.s1AverageTimes=new LinkedList<Integer>();
		this.s2AverageTimes=new LinkedList<Integer>();
		System.out.println(sb.toString());
	}

	/**
	 * Prints a graphical representation of the current traffic situation
	 * using the toString-methods in the components.
	 */
	public void print(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.s1.toString());
		sb.append(this.r1.toString());
		sb.append(this.r0.toString());
		sb.append("\n");
		sb.append(this.s2.toString());
		sb.append(this.r2.toString());
		System.out.println(sb.toString());

	}
}