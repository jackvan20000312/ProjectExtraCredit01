package edu.mtc.egr283.ProjectExtraCredit01;

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
*/ public class TrafficSystem
{
    // Attributes that describe the elements of the system
    private Lane r0;
    private Lane r1;
    private Lane r2;
    private Light s1;
    private Light s2;

    // Various attributes for simulation parameters (intensity of arrivals
    // destinations...)
    // Various attributes for collection of statistics
    private int time = 0;

    public TrafficSystem()
    {
    }

    /**
     * Defines how vehicles should mod in the system.
     * Steps the system one time step using the step methods in the
     * components
     * Creates vehicles, add and remove into the different lanes.
    */
    public void step()
    {
    }

    /**
     * Print the collected statistics sofar
    */
    public void printStatistics()
    {
    }

    /**
     * Prints a graphical representation of the current traffic situation
     * using the toString-methods in the components.
     */
    public void print()
    {
    }
}