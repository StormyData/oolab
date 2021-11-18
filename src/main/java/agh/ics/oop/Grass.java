package agh.ics.oop;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Grass extends AbstractWorldMapElement implements IPositionChangedObservable{
    static private final Random random =new Random();
    private List<IPositionChangeObserver> positionChangeObservers = new LinkedList<>();
    private int grassGenBound;

    Grass(IWorldMap map, int grassGenBound)
    {
        super(null,map);
        this.grassGenBound = grassGenBound;
        regenerate();
    }
    Grass(Vector2d position, IWorldMap map, int grassGenBound)
    {
        super(position,map);
        this.grassGenBound = grassGenBound;
    }

    @Override
    public String toString() {
        return "*";
    }
    public void eat()
    {
        Vector2d oldPos=position;
        regenerate();
        if(Config.DEBUG)
            System.out.printf("grass at %s got eaten and regenerated at %s\n",oldPos,position);
    }
    private void regenerate()
    {
        int tries=1;
        Vector2d nextPosition=new Vector2d(random.nextInt(grassGenBound), random.nextInt(grassGenBound));
        while(map.isOccupied(nextPosition))
        {
            nextPosition = new Vector2d(random.nextInt(grassGenBound), random.nextInt(grassGenBound));
            tries++;
            if(tries>grassGenBound*grassGenBound*grassGenBound)
                return;//TODO: Throw cannot regenrate
        }
        if(!nextPosition.equals(position))
            onPositionChange(position,nextPosition);
        position=nextPosition;
    }
    private void onPositionChange(Vector2d oldPos, Vector2d newPos)
    {
        for(IPositionChangeObserver positionChangeObserver : positionChangeObservers)
            positionChangeObserver.positionChange(oldPos,newPos);
    }
    @Override
    public void addObserver(IPositionChangeObserver observer) {
        positionChangeObservers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        positionChangeObservers.remove(observer);
    }
}
