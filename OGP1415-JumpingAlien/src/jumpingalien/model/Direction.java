package jumpingalien.model;


/**
 * A class of the possible directions Mazub can move in.
 * 
 * @author Melanie Nijs and Nele Nauwelaers
 *
 */
public enum Direction {
	
	RIGHT {
		public int getDirection(){
			return 1;
		}
	},
	
	LEFT {
		public int getDirection(){
			return -1;
		}
	};
	
	public abstract int getDirection();

}
