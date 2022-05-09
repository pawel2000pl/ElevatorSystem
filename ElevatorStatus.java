public record ElevatorStatus(int ID, int currentLevel, int destinationLevel) 
{
    
    public String toString()
    {
        return ("" + ID) + (" " + currentLevel) + (" " + destinationLevel);
    }

}
