/**
 * 
 */
package jumpingalien.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.Raw;



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
		X = tileSize*(nbTilesX);
		Y = tileSize*(nbTilesY);	
		xMax = X - 1;
		yMax = Y - 1;
		this.targetTileX = targetTileX*tileSize;
		this.targetTileY = targetTileY*tileSize;
		this.inWorldTiles = new int [nbTilesX][nbTilesY];
	}
	
	/**
	 * GEEN DOCUMENTATIE NODIG
	 * @throws IllegalDeltaTimeException 
	 */
	public void advanceTime(double dt) throws IllegalDeltaTimeException{
		this.inWorldCreatures.addAll(addCreatures);
		for (Creature creature: inWorldCreatures){
			creature.advanceTime(dt);
			if (creature instanceof Mazub)
				setVisibleWindow(creature);
				alienAlive = creature.isAlive;
				alienOnTargetTile = isOnTargetTile(creature);
				creature.lastCollisionEnemy += dt;
		}
		
		this.inWorldCreatures.remove(removeCreatures);
		this.addCreatures.removeAll(addCreatures);
		this.removeCreatures.removeAll(removeCreatures);
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
	
	public Collection<Mazub> getMazubsInWorld(){
		this.mazubsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Mazub )
				this.mazubsInWorld.add(creature);
		}
		return (Collection)mazubsInWorld;
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
		for (int j=pixelBottom; j <= (pixelTop - tileLength); j+=tileLength){
			for (int i=pixelLeft; i <= (pixelRight-tileLength); i+=tileLength){
				for (int k=1; k< matrixLength ; k++){
				tilePositions [k][0] = getTileNbX(i);
			    tilePositions [k][1] = getTileNbY(j);
				}
			}
		}	
		
		return tilePositions;
	}
	
	private boolean isPassable(int TileType){
		if ((TileType == 0) || (TileType == 2) || (TileType == 3))
			return true;
		else
			return false;
	}
	
	private boolean isOnTargetTile(Creature creature){
		return ((int)creature.getPositionX() > targetTileX && creature.getPositionY() > targetTileY
				&& (int) creature.getPositionX() < targetTileX + tileLength && (int) creature.getPositionY() < targetTileY  + tileLength);
	}
	
	
	private void setVisibleWindow(Creature creature){
		this.leftWindow = Math.max(xMin, (int)creature.getPositionX()-(visibleWindowWidth/2));
		this.bottomWindow = Math.max(yMin, (int)creature.getPositionY()-(visibleWindowHeight/2));
		this.rightWindow =  Math.min(xMax, (int)creature.getPositionX()+(visibleWindowWidth/2));
		this.topWindow = Math.min(yMax, (int)creature.getPositionY()+(visibleWindowHeight/2));
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
		 this.addCreatures.add(creature);
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
	private static int xMax;
	
	/**
	 * The maximum y value of the field of the game. 
	 */
	private static int yMax;

	public int[][] inWorldTiles;
	
	private int xTMax;
	
	private int yTMax;
	
	private int visibleWindowWidth;
	
	private int visibleWindowHeight;
	
	public List<Creature> inWorldCreatures = new ArrayList<Creature>();
	
	private List<Creature> slimesInWorld = new ArrayList<Creature>(); 
	
	private List<Creature> plantsInWorld = new ArrayList<Creature>(); 
	
	private List<Creature> sharksInWorld = new ArrayList<Creature>(); 
	
	private List<Creature> mazubsInWorld = new ArrayList <Creature> ();
	
	private List<Creature> addCreatures = new ArrayList <Creature> ();
	
	public List<Creature> removeCreatures = new ArrayList <Creature> ();
	
	private int leftWindow;
	
	private int bottomWindow ;
	
	private int rightWindow ;
	
	private int topWindow ;
	
	private int tileLength;
	
	private int targetTileX;
	
	private int targetTileY;
	
	private boolean alienAlive = true;
	
	private boolean alienOnTargetTile;

}
