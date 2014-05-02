import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;


import java.util.ArrayList;




public class ChameleonKid extends ChameleonCritter{


    public ArrayList<Actor> getActors(){
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] d = { Location.AHEAD, Location.HALF_CIRCLE };
        for (Location l : getLocationsInDirections(d)){
            Actor a = getGrid().get(l);
            if (a != null)
                actors.add(a);
        }
      return actors;
      }
      
    public ArrayList<Location> getLocationsInDirections(int[] directions){
        ArrayList<Location> locations = new ArrayList<Location>();
        Grid g = getGrid();
        Location l = getLocation();
        for (int a : directions){
            Location neighbor = l.getAdjacentLocation(getDirection() + a);
            if (g.isValid(neighbor))
                locations.add(neighbor);
        }
        return locations;
    }


}
