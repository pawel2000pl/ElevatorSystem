import java.util.Random;

//klasa referenacyjna
class FifoElevatorSystem extends ElevatorSystem
{
        protected Elevator createElevator()
        {
            return new FifoElevator();
        }    
        
        public FifoElevatorSystem(int intialLevel)
        {
            super(intialLevel);
        }
        
        public FifoElevatorSystem()
        {
            super(0);
        }
}
    
class Passenger
{
    public int initLevel, destLevel;
    public int elevatorID;
    public boolean done;
    public boolean inElevator;
    public int delay;
    private ElevatorSystem fSystem;
    public int enterTime;
    public int outTime;
    public int stepCount;
    
    public Passenger(Random rnd, int minLevel, int maxLevel, int maxDelay, ElevatorSystem system)
    {
        initLevel = rnd.nextInt(maxLevel-minLevel)+minLevel;        
        do
        {
            destLevel = rnd.nextInt(maxLevel-minLevel)+minLevel;
        }
        while (destLevel == initLevel);
        delay = rnd.nextInt(maxDelay);
        elevatorID = -1;
        done = false;
        inElevator = false;
        fSystem = system;
        stepCount = 0;
    }
    
    public void step()
    {    
        if (done || delay-- > 0)
            return;
        stepCount++;
        if (elevatorID < 0)
        {        
            if (initLevel < destLevel)
                elevatorID = fSystem.pickup(initLevel, 1);
            else
                elevatorID = fSystem.pickup(initLevel, -1);                
//              System.out.println("Pickup: " + initLevel + " " + destLevel);     
        }
        if (!inElevator)
        {
            if (fSystem.getElevatorLevel(elevatorID) == initLevel && fSystem.getElevatorVelocity(elevatorID) == 0)
            {
                fSystem.addDestination(elevatorID, destLevel);
                inElevator = true; 
                enterTime = stepCount;
//                  System.out.println("In: " + initLevel + " " + destLevel);         
            }
        }
        if (fSystem.getElevatorLevel(elevatorID) == destLevel && fSystem.getElevatorVelocity(elevatorID) == 0)
        {
            done = true;
            outTime = stepCount;
//              System.out.println("Out: " + initLevel + " " + destLevel);       
            inElevator = false;
        }     
    }
}
    
public class ElevatorSystemTest
{
    
    public static double test(ElevatorSystem system, int levelCount, int passengerCount, int maxDelay)
    {    
        int steps = 0;
        int doneCount = 0;
        Random rnd = new Random();         
        Passenger[] passengers = new Passenger[passengerCount];
        for (int i=0;i<passengerCount;i++)
            passengers[i] = new Passenger(rnd, 0, levelCount, maxDelay + 1, system);
            
        while (doneCount < passengerCount)
        {
            system.step();
            steps++;
            for (int i=0;i<passengerCount;i++)
                passengers[i].step();
            doneCount = 0;            
            for (int i=0;i<passengerCount;i++)
                if (passengers[i].done)
                    doneCount++;
//              System.out.println("" + doneCount + " / " + steps);  
        }   
        /*
        for (int i=0;i<passengerCount;i++)
            if (!passengers[i].done)
                System.out.println(" " + passengers[i].initLevel + " " + passengers[i].destLevel + " " + passengers[i].inElevator + " " + passengers[i].elevatorID);*/
                
        int drivingTime = 0;
        int waitingTime = 0;
        for (int i=0;i<passengerCount;i++)
        {
            drivingTime += passengers[i].outTime - passengers[i].enterTime;
            waitingTime += passengers[i].enterTime;
        }
        System.out.println("Average waiting time: " + (double)waitingTime/passengerCount + " Average driving time: " + (double)drivingTime/passengerCount);
        return (double)steps / (double)passengerCount;
    }

    public static void main(String[] args)
    {        
        System.out.println("Fifo");
        int i=0;
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 20, 1000, 20000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 20, 1000, 1000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 20, 1000, 100) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 200, 1000, 20000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 200, 1000, 1000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 200, 1000, 100) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 200, 10000, 20000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 200, 10000, 1000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new FifoElevatorSystem(10), 200, 10000, 100) + " steps per passenger");
        System.out.println("Smarter");
        i=0;
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 20, 1000, 20000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 20, 1000, 1000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 20, 1000, 100) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 200, 1000, 20000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 200, 1000, 1000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 200, 1000, 100) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 200, 10000, 20000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 200, 10000, 1000) + " steps per passenger");
        System.out.println("Test " + ++i);
        System.out.println(test(new ElevatorSystem(10), 200, 10000, 100) + " steps per passenger");
        System.out.println();
    }
}
