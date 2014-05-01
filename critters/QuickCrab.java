import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter{

    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> l = new ArrayList<Location>();
        Grid g = getGrid();
        checkTwoAdjacentLocations(l, getDirection() + Location.LEFT);
        checkTwoAdjacentLocations(l, getDirection() + Location.RIGHT);
        if (l.size() == 0)
            return super.getMoveLocations();
        return l;
    }

    private void checkTwoAdjacentLocations(ArrayList<Location> l, int direction){
        Grid g = getGrid();
        Location l = getLocation();
        Location adj = l.getAdjacentLocation(direction);
        if(g.isValid(adj) && g.get(adj) == null){
            Location adjadj = adj.getAdjacentLocation(direction);
            if(g.isValid(adjadj) && g.get(adjadj)== null)
                l.add(adjadj);
        }
    }

}
