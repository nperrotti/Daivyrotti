import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;

import java.util.ArrayList;

public class BlusterCritter extends Critter {

    private int courage;
    private static final double DARKENING_FACTOR = 0.05; 

    public BlusterCritter(int c){
        super();
        courage = c;
    }

    public ArrayList<Actor> getActors(){
        ArrayList<Actor> adj = getGrid().getNeighbors(getLocation());
        ArrayList<Actor> actors = new ArrayList<Actor>();
        for( Actor a : adj ){
            ArrayList<Actor> n = getGrid().getNeighbors(a.getLocation());
            for(Actor b : n){
                if(!b.equals(this) && !actors.contains(b) && !adj.contains(b)){
                    actors.add(b);
                }
            }
            actors.add(a);
        }
        return actors;
    }

    public void processActors(ArrayList<Actor> actors){
        int counter = 0;
        for(Actor a : actors){
            if(a instanceof Critter){
                counter++;
            }
        }
        if(courage <= counter)
            darken();
        else
            brighten();
    }

    public void darken(){
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }

    public void brighten(){
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        
        if(red < 255) 
            red++;
        if(green < 255)
            green++;
        if(blue < 255)
            blue++;
        setColor(new Color(red, green, blue));
    }





}
