import java.util.LinkedList;

public class FifoElevator implements Elevator
{
    private int fCurrentLevel;
    private LinkedList<Integer> fLevelList = new LinkedList<>();
    
    private static int sign(int i)
    {
        if (i>0)
            return 1;
        if (i<0)
            return -1;
        return 0;
    }
        
    private static int abs(int i)
    {
        if (i>=0)
            return i;
        return -i;
    }
    
    public void step()
    {
        int velocity = getVelocity();
        fCurrentLevel += velocity;
        if (fLevelList.size()>0 && getDestinationLevel()==fCurrentLevel && velocity==0)
            fLevelList.remove();
    }
    
    public int getCurrentLevel()
    {
        return fCurrentLevel;
    }
    
    public int getDestinationLevel()
    {
        if (fLevelList.size()>0)
            return fLevelList.getFirst();
        return fCurrentLevel;
    }
    
    public int getVelocity()
    {
        if (fLevelList.size()>0)
            return sign(getDestinationLevel()-fCurrentLevel);
        return 0;
    }
    
    public int getQueueLength()
    {
        return fLevelList.size();
    }
    
    public void call(int level, int direction)
    {
        fLevelList.add(level); //najprostszy przypadek nie uwzględnia kierunku jazdy
    }
    
    public void addDestination(int level)
    {
        if (!fLevelList.contains(level))
            fLevelList.add(level);
    }
    
    public int expectedTime(int level, int direction)
    {   
        int currentLevel = fCurrentLevel;
        int result = 0;
        for (Integer newLevel : fLevelList)
        {
            if (newLevel == level)
                return result;
            result += abs(currentLevel - newLevel)+1;
            currentLevel = newLevel;
        }
        result += abs(currentLevel - level);
        return result;
    }
    
    public void update(int currentLevel, int currentDirection)
    {
        fCurrentLevel = currentLevel;
    }
    
    public FifoElevator(int intialLevel)
    {
        fCurrentLevel = intialLevel;
    }
    
    public FifoElevator()
    {
        this(0);
    }
    
}
