import java.util.ArrayList;

public class ElevatorSystem
{

    //litera f na początku zmiennej - od słowa "field" - stosuję do oznaczania pól prywatnych

    ArrayList<Elevator> fElevators;
    
    public int getElevatorVelocity(int ID)
    {
        return fElevators.get(ID).getVelocity();
    }
    
    public int getElevatorLevel(int ID)
    {
        return fElevators.get(ID).getCurrentLevel();
    }
    
    public void step()
    {
        for (Elevator elevator : fElevators)
            elevator.step();
    }
    
    public void call(int ID, int level, int direction)
    {
        fElevators.get(ID).call(level, direction);
    }
    
    public int pickup(int level, int direction)
    {
        int minTime = Integer.MAX_VALUE;
        int minID = 0;
        int size = fElevators.size();
        for (int ID=0;ID<size;ID++)
        {
            int testTime = fElevators.get(ID).expectedTime(level, direction);
            if (testTime < minTime)
            {
                minTime = testTime;
                minID = ID;
            }
        }
        fElevators.get(minID).call(level, direction);
        return minID;
    }
    
    public void update(int ID, int currentLevel, int currentDirection)
    {
        fElevators.get(ID).update(currentLevel, currentDirection);
    }
    
    public boolean shouldTake(int ID, int direction)
    {
        return fElevators.get(ID).shouldTake(direction);
    }
    
    public ElevatorStatus[] status()
    {
        int count = fElevators.size();
        ElevatorStatus[] result = new ElevatorStatus[count];        
        for (int i=0;i<count;i++)
        {   
            Elevator currentElevator = fElevators.get(i);
            result[i] = new ElevatorStatus(i, currentElevator.getCurrentLevel(), currentElevator.getDestinationLevel());
        }
        return result;
    }
    
    public void addDestination(int ID, int level)
    {
        fElevators.get(ID).addDestination(level);
    }
    
    public int getElevatorCount()
    {
        return fElevators.size();
    }
    
    protected Elevator createElevator()
    {
        return new SmarterElevator();
    }
    
    public ElevatorSystem(int count)
    {
        if (count < 1)
            count = 1;
        fElevators = new ArrayList<>(count);
        for (int i=0;i<count;i++)
            fElevators.add(createElevator());
    }
    
}
