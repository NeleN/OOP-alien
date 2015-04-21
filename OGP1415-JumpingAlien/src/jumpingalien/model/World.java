package jumpingalien.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.Raw;



/**
 * A class of worlds with a given size and various creatures and geological features.
 * TODO
 * @invar 	INVULLEN
 * 
 * @author	Nele Nauwelaers, Melanie Nijs
 * 
 */
public class World {
	
	
	/**
	 * Constructor of the class "World". Creates a world and gives a tile size, number of tiles,
	 * the visible window and the target tile.
	 * @param tileSize
	 * 			The length of the side of the square tiles. 
	 * @param nbTilesX
	 * 			Number of tiles on the x axis.
	 * @param nbTilesY
	 * 			Number of tiles on the y axis.
	 * @param visibleWindowWidth
	 * 			The width of the visible window.
	 * @param visibleWindowHeight
	 * 			The Height of the visible window.
	 * @param targetTileX
	 * 			The x coordinate of the target tile.
	 * @param targetTileY
	 * 			The y coordinate of the target tile.
	 */
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
			if (creature instanceof Mazub){
				setVisibleWindow(creature);
				alienAlive = creature.isAlive;
				alienOnTargetTile = isOnTargetTile(creature);
				creature.lastCollisionEnemy += dt;
			}
		}
			
		this.inWorldCreatures.remove(removeCreatures);
		this.addCreatures.removeAll(addCreatures);
     	this.removeCreatures.removeAll(removeCreatures);
	}


/****************************************************************************************************
 * 																									*
 * 									     BOOLEANS													*
 * 																									*										
 ****************************************************************************************************/


	/**
	 * Checks whether it's GameOver or not. 
	 * @return	true if and only if the player is dead or if he has reached the target tile.
	 * 			| (! alienAlive) || (alienOnTargetTile)
	 */
	public boolean isGameOver(){
		return ((! alienAlive) || (alienOnTargetTile));
	}
	
	/**
	 * 
	 * @return true if and only if the player is on the target tile.
	 * 			alienOnTargetTile = true.
	 */
	public boolean playerOnTargetTile(){
		return alienOnTargetTile;
	}
	
	/**
	 * Checks whether the player is on the target tile.
	 * @param creature
	 * 			The creature ( in advanceTime you can see it's always Mazub) whose positions are being checked against the target tile.
	 * @return	true is and only if the creature is on the target tile.
	 * 			|((int)creature.getPositionX() > targetTileX) && ( creature.getPositionY() > targetTileY) && (((int) creature.getPositionX()
	 * 			| < targetTileX + tileLength) && ((int) creature.getPositionY() < targetTileY  + tileLength)
	 * 	
	 */
	private boolean isOnTargetTile(Creature creature){
		return (((int)creature.getPositionX() > targetTileX) && (creature.getPositionY() > targetTileY)
				&& ((int) creature.getPositionX() < targetTileX + tileLength) && ((int) creature.getPositionY() < targetTileY  + tileLength));
	}

	
/****************************************************************************************************
 * 																									*
 * 									     GETTERS AND SETTERS										*
 * 																									*										
 ****************************************************************************************************/
	
	
	
	/**
	 * Returns an integer array of coordinates of the bottomleft of the tiles of the rectangular region between the given pixels.
	 * @param pixelLeft
	 * @param pixelBottom
	 * @param pixelRight
	 * @param pixelTop
	 * @return
	 */
	public int [][] getTilePositions(int pixelLeft, int pixelBottom, int pixelRight, int pixelTop){
		pixelBottom = pixelBottom - tileLength +1; //TODO ask! 
		pixelLeft = pixelLeft - tileLength +1;
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
		
	/**
	 * Returns the geological feature of a tile with its bottomleft coordinate (pixelX, pixelY).
	 * @param pixelX
	 * 			The x pixel coordinate of the bottomleft of the tile.
	 * @param pixelY
	 * 			The y pixel coordinate of the bottomleft of the tile.
	 * @return
	 * 			Returns an integer at the given position in the matrix inWorldTiles.
	 * 			This integer corresponds to a geological feature: 0 = air;   1 = solid ground;
	 * 															  2 = water; 3 = magma. 
	 * TODO inworldtiles printen
	 */
	public int getGeologicalFeature(int pixelX, int pixelY){
		int tileX = this.getTileNbX(pixelX);
		int tileY = this.getTileNbY(pixelY);
		return inWorldTiles[tileX][tileY];
	}
	
	/**
	 * Gives a feature at the given tile with as bottomleft coordinate (pixelX, pixelY).
	 * @param pixelX
	 * 			The x pixel coordinate of the bottomleft of the tile.
	 * @param pixelY
	 * 			The y pixel coordinate of the bottomleft of the tile.
	 * @param feature
	 * 			An integer that represents a geological feature: 0 = air;   1 = solid ground;
	 * 															 2 = water; 3 = magma.
	 */
	public void setGeologicalFeature(int pixelX, int pixelY, int feature){
		int tileX = this.getTileNbX(pixelX);
		int tileY = this.getTileNbY(pixelY);
		inWorldTiles[tileX][tileY] = feature;
	}
	

	public Collection<Slime> getSlimesInWorld(){
		this.slimesInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Slime){
				this.slimesInWorld.add(creature);
			}
		}
		return (Collection)slimesInWorld;
	}
	
	public Collection<Plant> getPlantsInWorld(){
		this.plantsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Plant){
				this.plantsInWorld.add(creature);
			}
		}
		return (Collection)plantsInWorld;
	}
	
	public Collection<Shark> getSharksInWorld(){
		this.sharksInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Shark ){
				this.sharksInWorld.add(creature);
			}
		}
		return (Collection)sharksInWorld;
	}
	
	public Collection<Mazub> getMazubsInWorld(){
		this.mazubsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Mazub ){
				this.mazubsInWorld.add(creature);
			}
		}
		return (Collection)mazubsInWorld;
	}
	
	public int[] getVisibleWindow(){
		int[] array = {leftWindow, bottomWindow, rightWindow, topWindow};
		return array;
	}
	private void setVisibleWindow(Creature creature){
		this.leftWindow = Math.max(xMin, (int)(creature.getPositionX()-(visibleWindowWidth/2)));
		this.bottomWindow = Math.max(yMin, (int)(creature.getPositionY()-(visibleWindowHeight/2)));
		this.rightWindow = Math.min(xMax, (int)(creature.getPositionX()+(visibleWindowWidth/2)));
		this.topWindow = Math.min(yMax, (int)(creature.getPositionY()+(visibleWindowHeight/2)));
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
	
	
/****************************************************************************************************
 * 																									*
 * 									     	VARIABLES												*
 * 																									*										
 ****************************************************************************************************/
	
	
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
	private static int xMax = 1024;
	
	/**
	 * The maximum y value of the field of the game. 
	 */
	private static int yMax = 768;

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
