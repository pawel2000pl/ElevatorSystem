import java.util.Scanner;

public class ConsoleElevatorSystem extends ElevatorSystem
{    

    public boolean tryStep(String[] commandParameters)
    {
        if (commandParameters.length == 1 && commandParameters[0].equals("step"))
        {
            step();
            return true;
        }
        return false;
    }    
    
    public boolean tryCall(String[] commandParameters)
    {
        try
        {
            if (commandParameters.length == 4 && commandParameters[0].equals("call"))
            {
                call(Integer.valueOf(commandParameters[1]), Integer.valueOf(commandParameters[2]), Integer.valueOf(commandParameters[3]));
                return true;
            }
            return false;
        }
        catch (Exception e)    
        {
            return false;
        }
    }
        
    public boolean tryPickup(String[] commandParameters)
    {
        try
        {
            if (commandParameters.length == 3 && commandParameters[0].equals("pickup"))
            {
                pickup(Integer.valueOf(commandParameters[1]), Integer.valueOf(commandParameters[2]));
                return true;
            }
            return false;
        }
        catch (Exception e)    
        {
            return false;
        }
    }
        
    public boolean tryUpdate(String[] commandParameters)
    {
        try
        {
            if (commandParameters.length == 4 && commandParameters[0].equals("update"))
            {
                update(Integer.valueOf(commandParameters[1]), Integer.valueOf(commandParameters[2]), Integer.valueOf(commandParameters[3]));
                return true;
            }
            return false;
        }
        catch (Exception e)        
        {
            return false;
        }
    }
        
    public boolean tryAddDestination(String[] commandParameters)
    {
        try
        {
            if (commandParameters.length == 4 && commandParameters[0].equals("add") && commandParameters[1].equals("destination"))
            {
                addDestination(Integer.valueOf(commandParameters[2]), Integer.valueOf(commandParameters[3]));
                return true;
            }
            return false;
        }
        catch (Exception e)        
        {
            return false;
        }
    }
            
    public boolean tryStatus(String[] commandParameters)
    {
        try
        {
            if (commandParameters.length == 1 && commandParameters[0].equals("status"))
            {
                System.out.println(getElevatorCount());
                for (ElevatorStatus status : status())
                    System.out.println(status);
                return true;
            }
            return false;
        }
        catch (Exception e)        
        {
            return false;
        }
    }        
    
    public ConsoleElevatorSystem(int count)
    {
        super(count);
    }

    public static void main(String[] args)
    { 
        Scanner sc= new Scanner(System.in);
        
        int count = Integer.valueOf(sc.nextLine());
        if (count > 16 || count <= 0)
        {
            System.out.println("Count of elevators must be between 1 and 16");
            return;
        }
        
        ConsoleElevatorSystem elevatorSystem = new ConsoleElevatorSystem(count);
        
        String command = "";
        
        while (!command.equals("exit"))
        {
            command = sc.nextLine();
            String[] commandParameters = command.toLowerCase().split(" ");
            if (!(elevatorSystem.tryStep(commandParameters) || elevatorSystem.tryCall(commandParameters) || elevatorSystem.tryUpdate(commandParameters) || elevatorSystem.tryAddDestination(commandParameters) || elevatorSystem.tryStatus(commandParameters) || elevatorSystem.tryPickup(commandParameters) || command.equals("exit")))                
                System.out.println("Invalid command or command's parametres");
        }            
    }
}
