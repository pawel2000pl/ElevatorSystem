import java.util.concurrent.ConcurrentSkipListSet;
// import java.util.Arrays;
// import java.util.ArrayList;

public class SmarterElevator implements Elevator
{

    private int fCurrentLevel;
    private int fDirection = 1;    
    private ConcurrentSkipListSet<Integer> fWhereToStop = new ConcurrentSkipListSet<>();
    private ConcurrentSkipListSet<Integer> fWhereToStopWhenGoingUp = new ConcurrentSkipListSet<>();
    private ConcurrentSkipListSet<Integer> fWhereToStopWhenGoingDown = new ConcurrentSkipListSet<>();
            
    private static int sign(int i)
    {
        if (i>0)
            return 1;
        if (i<0)
            return -1;
        return 0;
    }                
    
    private static int min(int a, int b)
    {
        if (a>b)
            return b;
        return a;
    }           
    
    private static int max(int a, int b)
    {
        if (a<b)
            return b;
        return a;
    }       
        
    private static int abs(int i)
    {
        if (i>=0)
            return i;
        return -i;
    }
    
    private static int minDefault(Integer a, Integer b, int def)
    {
        if (a==null && b==null)
            return def;
        if (a==null && b!=null)
            return b;
        if (a!=null && b==null)
            return a;
        return min(a, b); 
    }
    
    private static int maxDefault(Integer a, Integer b, int def)
    {
        if (a==null && b==null)
            return def;
        if (a==null && b!=null)
            return b;
        if (a!=null && b==null)
            return a;
        return max(a, b); 
    }
            
    public void step()
    {
        int velocity = getVelocity();
        fCurrentLevel += velocity;
        
        if (velocity == 0)
        {
            fWhereToStop.remove(fCurrentLevel);   
            if (fDirection > 0)
                fWhereToStopWhenGoingUp.remove(fCurrentLevel);
            else
                fWhereToStopWhenGoingDown.remove(fCurrentLevel);
        }
        
        if (velocity==0)
        {        
            if (fDirection == -1)
            {
                if (fWhereToStopWhenGoingUp.headSet(fCurrentLevel, false).size() > 0)
                    fWhereToStop.add(fWhereToStopWhenGoingUp.first());
                else
                    fDirection = 1;
            }
            else if (fDirection == 1)
            {
                if (fWhereToStopWhenGoingDown.tailSet(fCurrentLevel, false).size() > 0)
                    fWhereToStop.add(fWhereToStopWhenGoingDown.last());
                else
                    fDirection = -1;
            }
        }
        
        //System.out.println("" + fWhereToStop.size() + " " + fWhereToStopWhenGoingUp.size() + " " + fWhereToStopWhenGoingDown.size() + "   " + fDirection + " " + fCurrentLevel + " " + getDestinationLevel() + " " + velocity);
//         System.out.println(Arrays.toString((new ArrayList(fWhereToStop)).toArray()) + " " + Arrays.toString((new ArrayList(fWhereToStopWhenGoingUp)).toArray()) + " " + Arrays.toString((new ArrayList(fWhereToStopWhenGoingDown)).toArray()) + " " + fCurrentLevel + " " + fDirection + " " + getVelocity());
    }
    
    public int getCurrentLevel()
    {
        return fCurrentLevel;
    }
    
    public int getDestinationLevel()
    {        
        if (fDirection > 0)
            return minDefault(fWhereToStop.ceiling(fCurrentLevel), fWhereToStopWhenGoingUp.ceiling(fCurrentLevel), fCurrentLevel);
        if (fDirection < 0)
            return maxDefault(fWhereToStop.floor(fCurrentLevel), fWhereToStopWhenGoingDown.floor(fCurrentLevel), fCurrentLevel);    
        return fCurrentLevel;
    }
    
    public int getVelocity()
    {
        return sign(getDestinationLevel() - getCurrentLevel());
    }
    
    public int getQueueLength()
    {
        return fWhereToStop.size() + fWhereToStopWhenGoingUp.size() + fWhereToStopWhenGoingDown.size();
    }
    
    public void call(int level, int direction)
    {            
        if (direction>0)
            fWhereToStopWhenGoingUp.add(level);
        else
            fWhereToStopWhenGoingDown.add(level);
    }
    
    public void addDestination(int level)
    {
        fWhereToStop.add(level);
    }  
    
    public void update(int currentLevel, int currentDirection)
    {
        fCurrentLevel = currentLevel;
        fDirection = currentDirection;
    }
    
    public static Integer safeFirst(ConcurrentSkipListSet<Integer> collection)
    {
        try
        {
            return collection.first();
        }
        catch (java.util.NoSuchElementException e)
        {
            return null;
        }
    }
        
    public static Integer safeLast(ConcurrentSkipListSet<Integer> collection)
    {
        try
        {
            return collection.last();
        }
        catch (java.util.NoSuchElementException e)
        {
            return null;
        }
    }
    
    public int expectedTime(int level, int direction)
    {   
        if (fDirection == 0)
            return abs(fCurrentLevel - level);
        if (fDirection == direction)
        {
            if (direction > 0 && fCurrentLevel <= level || direction < 0 && fCurrentLevel >= level )
                return abs(fCurrentLevel - level);
            return abs(fCurrentLevel - level) + maxDefault(safeLast(fWhereToStop), safeLast(fWhereToStopWhenGoingUp), 0) + minDefault(safeFirst(fWhereToStop), safeFirst(fWhereToStopWhenGoingDown), 0);
        }
        else
        {
            int h, a;
            if (fDirection > 0)
                h = maxDefault(safeLast(fWhereToStop), safeLast(fWhereToStopWhenGoingUp), 0);
            else
                h = minDefault(safeFirst(fWhereToStop), safeFirst(fWhereToStopWhenGoingDown), 0);
            return abs(fCurrentLevel - h) + abs(level - h);
        }
    }
        
    public SmarterElevator(int intialLevel)
    {
        fCurrentLevel = intialLevel;
    }
    
    public SmarterElevator()
    {
        this(0);
    }
}
