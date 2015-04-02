/**
 * 
 */
package jumpingalien.model;

import java.util.*;



/**
 * @author Nele
 *
 */
public class World {
	
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY){
		this.tileLength = tileSize;
		this.visibleWindowWidth = visibleWindowWidth;
		this.visibleWindowHeight = visibleWindowHeight;
		xTMax = nbTilesX;
		yTMax = nbTilesY;
		this.X = tileSize*(nbTilesX+1);
		this.Y = tileSize*(nbTilesY+1);	
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
	}
	
	/**
	 * GEEN DOCUMENTATIE NODIG
	 * @throws IllegalDeltaTimeException 
	 */
	public void advanceTime(double dt) throws IllegalDeltaTimeException{
		for (Creature creature: inWorldCreatures){
			creature.advanceTime(dt);
			if (creature instanceof Mazub)
				setVisibleWindow(creature);
				alienAlive = creature.isAlive;
				alienOnTargetTile = isOnTargetTile(creature);
				creature.lastCollisionEnemy += dt;
		}
	}
	
	public int[] getVisibleWindow(){
		int[] array = {leftWindow, bottomWindow, rightWindow, topWindow};
		return array;
	}
	
	public boolean isGameOver(){
		return ((! alienAlive) || (alienOnTargetTile));
	}
	
	public boolean playerOnTargetTile(){
		return alienOnTargetTile;
	}
	
	
	public int [][] getTilePositions(int pixelLeft, int pixelBottom, int pixelRight, int pixelTop){
		for (int i=pixelLeft; i <= (pixelRight-tileLength); i+=tileLength){
			for (int j=pixelBottom; j <= (pixelTop - tileLength); j+=tileLength){
				int [] array = {i,j};
				tilePositions.add(array);
			}
		}	
		
	}
	
	private boolean isPassable(String TileType){
		if ((TileType == "air") || (TileType == "water") || (TileType == "magma"))
			return true;
		else
			return false;
	}
	
	private boolean isOnTargetTile(Creature creature){
		return ((int)creature.getPositionX() > targetTileX && creature.getPositionY() > targetTileY
				&& (int) creature.getPositionX() < targetTileX + tileLength && (int) creature.getPositionY() < targetTileY  + tileLength);
	}
	
	
	private void setVisibleWindow(Creature creature){
		leftWindow = Math.max(xMin, (int)creature.getPositionX()-(visibleWindowWidth/2));
		bottomWindow = Math.max(yMin, (int)creature.getPositionY()-(visibleWindowHeight/2));
		rightWindow = Math.min(xMax, (int)creature.getPositionX()+(visibleWindowWidth/2));
		topWindow = Math.min(yMax, (int)creature.getPositionY()+(visibleWindowHeight/2));
	}
		
	
	public static int getXMin(){
		return xMin;
	}
	
	public static int getYMin(){
		return yMin;
	}
	
	public static int getXMax(){
		return xMax;
	}
	
	public static int getYMax(){
		return yMax;
	}
	
	public int getTileLength(){
		return tileLength;
	}
	
	public void setCreatureInWorld(Creature creature){
		this.inWorldCreatures.add(creature);
	}
	
	
	/**
	 * The minimum x value of the field of the game.
	 */
	private static int xMin = 0;
	
	/**
	 * The minimum y value of the field of the game 
	 */
	private static int yMin = 0;
	private static int X;
	private static int Y;
	
	/**
	 * The maximum x value of the field of the game.
	 */
	private static int xMax = X - 1;
	
	/**
	 * The maximum y value of the field of the game. 
	 */
	private static int yMax = Y - 1;
	
	private int xTMax;
	
	private int yTMax;
	
	private String TileType = "air";
	
	private int visibleWindowWidth;
	
	private int visibleWindowHeight;
	
	public List<Creature> inWorldCreatures = new ArrayList<Creature>();
	
	private int leftWindow = 0;
	
	private int bottomWindow = 0;
	
	private int rightWindow = visibleWindowWidth;
	
	private int topWindow = visibleWindowHeight;
	
	private int tileLength;
	
	private int targetTileX;
	
	private int targetTileY;
	
	private boolean alienAlive;
	
	private boolean alienOnTargetTile;
	
	private List tilePositions = new ArrayList();
	
	private Collection plantsInWorld;

}
