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
int i=0;
        while (i<10000){
//            try {
//                // If the printouts are done each timestep, a pause is needed
//                Thread.sleep(500);
//            }
//            catch (InterruptedException e){
//            }
            tf.step();
            tf.print();
            i+=10;
        }
        tf.printStatistics();
        ///...
    }
}
