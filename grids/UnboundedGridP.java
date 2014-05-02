//Big O for revised get method: O(1)
//Big O for revised put method: inbounds - O(1), resizing needed - O(n^2) (n is nums of columns/rows)

import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.*;
//import java.util.ArrayList;
import java.util.*; 

public class UnboundedGridP<E> extends AbstractGrid<E>{ 

    private Object[][] a; 
    private int n;

    public UnboundedGridP(){ 
        n = 16; 
        a = new Object[n][n];; 
    } 

    public int getNumRows(){ 
        return -1; 
    } 

    public int getNumCols(){ 
        return -1; 
    } 

    public boolean isValid(Location loc){ 
        return loc.getRow() >= 0 && loc.getCol() >= 0; 
    } 

    public ArrayList<Location> getOccupiedLocations(){ 
        ArrayList<Location> l = new ArrayList<Location>(); 
        for (int i = 0; i < n; i++){ 
            for (int j = 0; j < n; j++){ 
                Location loc = new Location(i, j); 
                if (get(loc) != null) 
                    l.add(loc); 
            } 
        } 
        return l; 
    } 

    public E get(Location loc){ 
        if (!isValid(loc)) 
            throw new IllegalArgumentException("This location " + loc + " is unvalid"); 
        if(loc.getRow() >= n || loc.getCol() >= n) 
            return null; 
        return (E) a[loc.getRow()][loc.getCol()];  
    } 

    public E put(Location loc, E newObj){ 
        if (loc == null) 
            throw new NullPointerException("loc == null"); 
        if (newObj == null) 
            throw new NullPointerException("newObj == null"); 
        if (loc.getRow() >= n || loc.getCol() >= n) 
            expand(loc); 
        E prevObj = get(loc); 
        a[loc.getRow()][loc.getCol()] = newObj; 
        return prevObj; 
    }

    public E remove(Location loc){ 
        if (!isValid(loc)) 
            throw new IllegalArgumentException("This location " + loc  + " is unvalid"); 
        if(loc.getRow() >= n || loc.getCol() >= n) 
            return null; 
        E obj = get(loc); 
        a[loc.getRow()][loc.getCol()] = null; 
        return obj; 
    } 

    private void expand(Location loc){ 
        int size = n; 
        while (loc.getRow() >= size || loc.getCol() >= size) 
            size = size * 2; 
        Object[][] newGrid = new Object[size][size];  
        for(int i = 0; i < n; i++) 
            for(int j = 0; j < n; j++) 
                newGrid[i][j] = a[i][j]; 
        a = newGrid; 
        n = size; 
    }  

} 
