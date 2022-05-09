public interface Elevator
{
    //wykonuje krok symulacji
    public void step();
    //sprawdza gdzie jest winda
    public int getCurrentLevel();
    //sprawdza aktualny cel windy
    public int getDestinationLevel();
    //zwraca aktualny kierunek jazdy windy (-1, 0, 1)
    public int getVelocity();
    //zwraca długość kolejki windy
    public int getQueueLength();
    //woła windę na podane piętro w celu odebrania pasażera
    public void call(int level, int direction);
    //każe windzie jechać na podane piętro
    public void addDestination(int level);    
    //aktualizuję stan windy (np po odczycie z czujników)
    public void update(int currentLevel, int currentDirection);    
    //zwraca przewidywany czas dojazdu po odbiór. jest jedynie poglądowy, nie musi być rzeczywisty
    public int expectedTime(int level, int direction);    
    //zwraca, czy pasażer powinien wsiąść do windy (a nie powinien, jeżeli winda jedzie w przeciwną stronę)
    public boolean shouldTake(int direction);    
}
