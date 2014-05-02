/* This is a more time-efficient implementation than BoundedGrid because
 * in BoundedGrid, getOccupiedLocations requires you to iterate through 
 * c elements per row (c = total # of cols in the grid), while in 
 * SparseBoundedGrid, you only have to iterate through the number of 
 * occupants in each row, which is faster.
 *
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {

    private ArrayList<LinkedList> occupantArray;
    private int rows;
    private int cols;

    public SparseBoundedGrid<E>(int rows, int cols){
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        this.rows = rows;
        this.cols = cols;
        occupantArray = new ArrayList<LinkedList>();
        for(int i = 0; i < rows; i++){
            occupantArray.add(new LinkedList<OccupantInCol>());
        } 
    }

    public int getNumRows(){
        return rows;
    }

    public int getNumCols(){
        return cols;
    }

    public boolean isValid(){
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public E put(Location loc, E obj){
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant; 
    }

    public E remove(Location loc){
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
        if(row != null){
            Iterator<OccupantInCol> it = row.iterator();
            while(it.hasNext()){
                if(it.next().getCol() == loc.getCol()){
                    it.remove();
                    break;
                }
            }
        }
        return r;
    }

    public E get(Location loc){
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
        int locCol = loc.getCol();
        if(row != null){
            for( OccupantInCol o : row ){
                if(o.getCol() == locCol){
                    return (E) o.getOccupant();
                }
            } 
        }
        return null;
    }

    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> locations = new ArrayList<Location>();
        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++){
            LinkedList<OccupantInCol> row = occupantArray.get(r);
            if(row != null){
                for( OccupantInCol o : cols ){
                    locations.add( new Location( r, o.getCol() ));
                }
            }
        }
        return locations;
    } 

}
