package jumpingalien.model;

/**
 * A class for signaling an period of time dt that exceeds its given interval.
 * 
 * @version 1.0 
 * 			12/03/2015
 * @author 	Melanie Nijs and Nele Nauwelaers
 * 
 */
public class IllegalDeltaTimeException extends Exception {
	
	
	/**
	 * An error suggested this, but in our exercises we're told not to worry about this.
	 */
	private static final long serialVersionUID = 1L;

	public IllegalDeltaTimeException(String e){
		
	}
	/**
	 * Checks if dt is a valid time span.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return	True if and only if the given dt is positive and smaller than 0.2
	 * 			| (dt>=0) && (dt<0.2)
	 */
//	public void IllegalDeltaTimeException(double dt){
//		return (dt>=0) && (dt<0.2);	
//	}

}
