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
		this.inWorldTiles = new int [nbTilesX][nbTilesY];
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
	
	
	public int getGeologicalFeature(int pixelX, int pixelY){
		int tileX = this.getTileNbX(pixelX);
		int tileY = this.getTileNbY(pixelY);
		return inWorldTiles[tileX+1][tileY+1];
	}
	
	public void setGeologicalFeature(int pixelX, int pixelY, int feature){
		int tileX = this.getTileNbX(pixelX);
		int tileY = this.getTileNbY(pixelY);
		inWorldTiles[tileX][tileY] = feature;
	}
	
	public Collection<Slime> getSlimesInWorld(){
		this.slimesInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Slime)
				this.slimesInWorld.add(creature);
		}
		return (Collection)slimesInWorld;
	}
	
	public Collection<Plant> getPlantsInWorld(){
		this.plantsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Plant )
				this.plantsInWorld.add(creature);
		}
		return (Collection)plantsInWorld;
	}
	
	public Collection<Shark> getSharksInWorld(){
		this.sharksInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Shark )
				this.sharksInWorld.add(creature);
		}
		return (Collection)sharksInWorld;
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
		int matrixLength = ((pixelTop - pixelBottom)/tileLength) * ((pixelRight - pixelLeft)/tileLength);
		int [][] tilePositions = new int [matrixLength][2];
		for (int i=pixelLeft; i <= (pixelRight-tileLength); i+=tileLength){
			for (int j=pixelBottom; j <= (pixelTop - tileLength); j+=tileLength){
				for (int k=1; k<= matrixLength ; k++){
				tilePositions [k][1] = i;
			    tilePositions [k][2] = j;
				}
			}
		}	
		
		return tilePositions;
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
	
	private int getTileNbX(int pixelX){
		return (pixelX/this.tileLength);
	}
	
	private int getTileNbY(int pixelY){
		return (pixelY/this.tileLength);
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

	public int[][] inWorldTiles;
	
	private int xTMax;
	
	private int yTMax;
	
	private int visibleWindowWidth;
	
	private int visibleWindowHeight;
	
	public List<Creature> inWorldCreatures = new ArrayList<Creature>();
	
	private List<Creature> slimesInWorld = new ArrayList<Creature>(); 
	
	private List<Creature> plantsInWorld = new ArrayList<Creature>(); 
	
	private List<Creature> sharksInWorld = new ArrayList<Creature>(); 
	
	private int leftWindow = 0;
	
	private int bottomWindow = 0;
	
	private int rightWindow = visibleWindowWidth;
	
	private int topWindow = visibleWindowHeight;
	
	private int tileLength;
	
	private int targetTileX;
	
	private int targetTileY;
	
	private boolean alienAlive;
	
	private boolean alienOnTargetTile;

}
