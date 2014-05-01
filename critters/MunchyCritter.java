/*****************************************************************
 * IVY WONG NICK PERROTTI DAVID BANG --- DAIVYROTTI
 * APCS pd 8
 * HW35 -- GridWorld, Part 4
 * 2014-04-30
 *
 * class MunchyCritter
 *
 * BEACUASE:
 * At first we wanted to do VoldemortCritter because Harry Potter,
 * but it seemed too easy to code, so we looked for something similar
 * (because destroying other actors around you sounds cool) and decided
 * on MunchyCritter. The dying when eating a Rock is an interesting twist
 * as well.
 *
 * SPECIFICATIONS:
 * MunchyCritter will extend Critter. A MunchyCritter looks at all of the 
 * neighbors within one step of its current location. 
 * It will then proceed to eat (remove) all actors within one step of its current location. 
 * However, if the actor that is removed is a Rock, then the MunchyCritter dies. 
 * If there are no actors within a one step radius of its current location, 
 * the MunchyCritter will move in a random direction for one step.
 *
 * TEST CASES:
 * First Test Case: A MunchyCritter is placed in a grid where there are only actors 
 * except for Rocks. 
 * The MunchyCritter would be able to eat anything in its radius and keep moving 
 * if there is nothing to eat.
 * Second Test Case: A MunchyCritter is placed in a grid where 
 * there are only actors that are Rocks.
 * The MunchyCritter move around and then if it encounters a rock, it will eat 
 * and then proceed to "die" or remove itself.
 * Third Test Case: A MunchyCritter is placed at the edge of the grid.
 * It should be able to discern which direction is a valid location to step in 
 * and proceed to step in the correct path.
 * Fourth Test Case: When the MunchyCritter is surrounded by Actors other than Rocks.
 * All Actors should be consumed.
 * Fifth Test Case: When the MunchyCritter is surrounded by Rocks
 * All rocks should be consumed and the MunchyCritter will remove itself.  
 *
 * ERRATA:
 * Nope. 
 *****************************************************************/
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
public class MunchyCritter extends Critter {

    public MunchyCritter(){
        setColor(Color.ORANGE);
    }

    public void act(){
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        if( getGrid() != null ){
            ArrayList<Location> moveLocs = getMoveLocations();
            Location loc = selectMoveLocation(moveLocs);
            makeMove(loc);
        }
    } 

    public void processActors(ArrayList<Actor> actors){
        boolean isDead = false;
        for( Actor a : actors ){
            if( a instanceof Rock )
                isDead = true;
            a.removeSelfFromGrid();
            //System.out.println("GOT KILLED");
        }
        if(isDead){
            removeSelfFromGrid();
            //System.out.println("IM DED");
        }
    }

}
