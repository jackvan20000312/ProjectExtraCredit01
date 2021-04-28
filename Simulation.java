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
		TrafficSystem trafficSystem = new TrafficSystem();
		
		for(int i=0; i<500; i++){
			
			//if you do not care what the simulation looks like and just want the statistics, comment out line 22-26 and line 28
			try {
				// If the printouts are done each timestep, a pause is needed
				Thread.sleep(10);
			}catch (InterruptedException e){
			}//end of catch
			trafficSystem.step();
			System.out.println(trafficSystem.print());
		}//end of while loop
		
		System.out.println(trafficSystem.printStatistics());

	}//end of main method
}//end of class
