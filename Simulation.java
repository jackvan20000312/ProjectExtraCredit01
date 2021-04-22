package edu.mtc.egr283.ProjectExtraCredit01;

/** Simulation
 * Run a  simulation
 */
public class Simulation {

    /**
     * Create a TrafficSystem, steps it and calls the print methods
     */
    public static void main(String [] args)
    {
        TrafficSystem tf = new TrafficSystem();

        //...

        while (true){
            try {
                // If the printouts are done each timestep, a pause is needed
                Thread.sleep(100);
            }
            catch (InterruptedException e){
            }
            tf.step();
            tf.print();
        }

        ///...
    }
}
