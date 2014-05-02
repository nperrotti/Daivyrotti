//Big O for revised get method: O(1)
//Big O for revised put method: inbounds - O(1), resizing needed - O(n^2) (n is nums of columns/rows)

import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.util.*; 
 
public class UnboundedGrid2<E> extends AbstractGrid<E>{ 
    private Object[][] occupantArray; 
    private int dim; //current dimension of the occupantArray 
 
    public UnboundedGrid2(){ 
	    dim = 16; 
	    occupantArray = new Object[dim][dim];; 
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
	    ArrayList<Location> theLocations = new ArrayList<Location>(); 
 
	    // Look at all grid locations. 
	    for (int r = 0; r < dim; r++){ 
		    for (int c = 0; c < dim; c++){ 
			     // If there's an object at this location, put it in the array. 
			     Location loc = new Location(r, c); 
			     if (get(loc) != null) 
			        theLocations.add(loc); 
		    } 
	    } 
      return theLocations; 
    } 
 
    public E get(Location loc){ 
	    if (!isValid(loc)) 
	      throw new IllegalArgumentException("Location " + loc + " is not valid"); 
	      //return null if a location is valid, but not in the array 
	if(loc.getRow() >= dim || loc.getCol() >= dim) 
	    return null; 
	return (E) occupantArray[loc.getRow()][loc.getCol()]; 
	// unavoidable warning 
    } 
 
    public E put(Location loc, E obj){ 
	if (loc == null) 
	    throw new NullPointerException("loc == null"); 
	if (obj == null) 
	    throw new NullPointerException("obj == null"); 
 
	//if new location is out of the array, resize the array 
	if (loc.getRow() >= dim || loc.getCol() >= dim) 
	    resize(loc); 
 
	// Add the object to the grid. 
	E oldOccupant = get(loc); 
	occupantArray[loc.getRow()][loc.getCol()] = obj; 
	return oldOccupant; 
    }
    
    public E remove(Location loc) { 
	if (!isValid(loc)) 
	    throw new IllegalArgumentException("Location " + loc 
					       + " is not valid"); 
 
	// if location is valid and not in array, return null 
	if(loc.getRow() >= dim || loc.getCol() >= dim) 
	    return null; 
 
	// Remove the object from the grid. 
	E r = get(loc); 
	occupantArray[loc.getRow()][loc.getCol()] = null; 
	return r; 
    } 
 
    private void resize(Location loc){ 
	//double the size until it is greater than needed 
	int size = dim; 
	while (loc.getRow() >= size || loc.getCol() >= size) 
	    size *= 2; 
 
	//create a new array 
	Object[][] temp = new Object[size][size]; 
 
	//copy over the old contents 
	for(int r = 0; r < dim; r++) 
	    for(int c = 0; c < dim; c++) 
		temp[r][c] = occupantArray[r][c]; 
 
	//assign occupantArray the new array and update dim 
	occupantArray = temp; 
	dim = size; 
    } 
} 
