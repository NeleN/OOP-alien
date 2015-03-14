package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling an period of time dt that exceeds its given interval.
 * 
 * @version 1.0 
 * 			12/03/2015
 * @author 	Melanie Nijs and Nele Nauwelaers
 * 
 */
public class IllegalDeltaTimeException extends Exception {
	
	public IllegalDeltaTimeException(double dt ){
		this.dt = dt;
		
	}
	
	@Basic @Immutable
	public double getTimeSpan(){
		return this.dt;
	}
	
	public final double dt;
	
	/**
	 * An error suggested this, but in our exercises we're told not to worry about this.
	 */
	private static final long serialVersionUID = 1L;

	

}
