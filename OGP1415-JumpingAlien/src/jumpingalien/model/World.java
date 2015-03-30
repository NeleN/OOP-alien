/**
 * 
 */
package jumpingalien.model;

/**
 * @author Nele
 *
 */
public class World {
	
	
	public int getXMin(){
		return xMin;
	}
	
	public int getYMin(){
		return yMin;
	}
	
	public int getXMax(){
		return xMax;
	}
	
	public int getYMax(){
		return yMax;
	}
	
	/**
	 * The minimum x value of the field of the game.
	 */
	private int xMin = 0;
	
	/**
	 * The minimum y value of the field of the game 
	 */
	private int yMin = 0;
	
	/**
	 * The maximum x value of the field of the game.
	 */
	private int xMax = 1024 - 1;
	
	/**
	 * The maximum y value of the field of the game. 
	 */
	private int yMax = 786 - 1;
	
	private int xTMax;
	
	private int yTMax;
	
	private String TileType = "air";

}
