package edu.mtc.egr283.ProjectExtraCredit01;

/**
 * Traffic Simulator
 * @author Jacob Vaught
 * @professor William Sims
 * @DueDate 04/28/2021
 * @version 1.00 04.22.2021
 * Copyright(c) 2021 Jacob C. Vaught. All rights reserved.
 */
public class Simulation {

	/**
	 * Create a TrafficSystem, steps it and calls the print methods
	 */
	public static void main(String [] args){
		TrafficSystem tf = new TrafficSystem();
		int i=0;
		while (i<100){
			try {
				// If the printouts are done each timestep, a pause is needed
				Thread.sleep(50);
			}catch (InterruptedException e){
			}
			tf.step();
			System.out.println(tf.print());
			i++;
		}//end of while loop
		System.out.println(tf.printStatistics());

	}//end of main method
}//end of class
