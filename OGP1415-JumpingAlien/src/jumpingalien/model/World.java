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
/**
 * @author Melanie Nijs
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
	 * 			The pixel of the left side of the rectangular region. 
	 * @param pixelBottom
	 * 			The pixel of the bottom of the rectangular region.
	 * @param pixelRight
	 * 			The pixel of the right side of the rectangular region. 
	 * @param pixelTop
	 * 			The pixel of the top of the rectangular region.
	 * @return
	 * 		An integer array of coordinates of the tiles in the rectangular region.
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
	 * Gives a feature to the given tile with as bottomleft coordinate (pixelX, pixelY).
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
	

	/**
	 * Returns a collection of all the slimes in this world.
	 * @return
	 * 		A collection of slimes in the world.
	 */
	public Collection<Slime> getSlimesInWorld(){
		this.slimesInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Slime){
				this.slimesInWorld.add(creature);
			}
		}
		return (Collection)slimesInWorld;
	}
	
	/**
	 * Returns a collection of all the plants in this world.
	 * @return
	 * 		A collection of plants in the world.
	 */
	public Collection<Plant> getPlantsInWorld(){
		this.plantsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Plant){
				this.plantsInWorld.add(creature);
			}
		}
		return (Collection)plantsInWorld;
	}
	
	/**
	 * Returns a collection of all the sharks in this world.
	 * @return
	 * 		A collection of sharks in the world.
	 */
	public Collection<Shark> getSharksInWorld(){
		this.sharksInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Shark ){
				this.sharksInWorld.add(creature);
			}
		}
		return (Collection)sharksInWorld;
	}
	
	/**
	 * Returns a collection of all the aliens in this world.
	 * @return
	 * 		A collection of mazubs in the world.
	 */
	public Collection<Mazub> getMazubsInWorld(){
		this.mazubsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Mazub ){
				this.mazubsInWorld.add(creature);
			}
		}
		return (Collection)mazubsInWorld;
	}
	
	/**
	 * Sets the vissible window with the creature as center.
	 * @param creature
	 * 			The alien the player controls.
	 */
	private void setVisibleWindow(Creature creature){
		this.leftWindow = Math.max(xMin, Math.min((int)(creature.getPositionX()-(visibleWindowWidth/2)), xMax-visibleWindowWidth));
		this.bottomWindow = Math.max(yMin,Math.min((int)(creature.getPositionY()-(visibleWindowHeight/2)), yMax-visibleWindowHeight) );
		this.rightWindow = leftWindow + visibleWindowWidth;
		this.topWindow = bottomWindow + visibleWindowHeight;
	}
	
	/**
	 * Returns the visible window of the game.
	 * @return
	 * 		An array of four coordinates of all sides of the window.
	 */
	public int[] getVisibleWindow(){
		int[] array = {leftWindow, bottomWindow, rightWindow, topWindow};
		return array;
	}

	/**
	 * Gets the minimal horizontal pixel value of the world.
	 * @return
	 * 		The minimal horizontal pixel value.
	 */
	public static int getXMin(){
		return xMin;
	}
	
	/**
	 * Gets the minimal vertical pixel value of the world.
	 * @return
	 * 		The minimal vertical pixel value.
	 */
	public static int getYMin(){
		return yMin;
	}
	
	/**
	 * Gets the maximal horizontal pixel value of the world.
	 * @return
	 * 		The maximal horizontal pixel value.
	 */
	public static int getXMax(){
		return xMax;
	}
	
	/**
	 * Gets the maximal vertical pixel value of the world.
	 * @return
	 * 		The maximal vertical pixel value.
	 */
	public static int getYMax(){
		return yMax;
	}
	
	/**
	 * Returns the length of one tile side.
	 * @return
	 * 		the length of the side of the tile.
	 */
	public int getTileLength(){
		return tileLength;
	}
	
	/**
	 * Sets a creature in the world by adding it to the list addCreatures.
	 * @param creature
	 * 			The creature that is created and should be in the world.
	 */
	public void setCreatureInWorld(Creature creature){
		 this.addCreatures.add(creature);
	}
	
	/**
	 * Returns the x coordinate of the tile that belongs to a given pixelX.
	 * @param pixelX
	 * 			An X position in the world.
	 * @return
	 * 		An integer that represents the x coordinate of the tile that belongs to pixel X.
	 */
	private int getTileNbX(int pixelX){
		return (pixelX/this.tileLength);
	}

	/**
	 * Returns the y coordinate of the tile that belongs to a given pixelY.
	 * @param pixelY
	 * 			An Y position in the world.
	 * @return
	 * 		An integer that represents the y coordinate of the tile that belongs to pixelY.
	 */
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

	/**
	 * The maximum x value of the field of the game.
	 */
	private static int xMax;
	
	/**
	 * The maximum y value of the field of the game. 
	 */
	private static int yMax;
	
	/**
	 * The real maximum x value.
	 */
	private static int X;
	
	/**
	 * The real maximum y value.
	 */
	private static int Y;
	
	/**
	 * An array of coordinates of tiles that are in the world. 
	 */
	public int[][] inWorldTiles;
	
	/**
	 * The maximum x value of the field of the game expressed in tiles instead of pixels.
	 */
	private int xTMax;
	
	/**
	 * The maximum x value of the field of the game expressed in tiles instead of pixels.
	 */
	private int yTMax;
	
	/**
	 * The width of the visible window.
	 */
	private int visibleWindowWidth;
	
	/**
	 * The height of the visible window.
	 */
	private int visibleWindowHeight;
	
	/**
	 * An arraylist of the creatures in the world.
	 */
	public List<Creature> inWorldCreatures = new ArrayList<Creature>();
	
	/**
	 * An arraylist of the slimes in the world.
	 */
	private List<Creature> slimesInWorld = new ArrayList<Creature>(); 
	
	/**
	 * An arraylist of the plants in the world.
	 */
	private List<Creature> plantsInWorld = new ArrayList<Creature>(); 
	
	/**
	 * An arraylist of the sharks in the world.
	 */
	private List<Creature> sharksInWorld = new ArrayList<Creature>(); 
	
	/**
	 * An arraylist of the Mazubs in the world.
	 */
	private List<Creature> mazubsInWorld = new ArrayList <Creature> ();
	
	/**
	 * An arraylist of creatures that should be added to the world.
	 */
	private List<Creature> addCreatures = new ArrayList <Creature> ();
	
	/**
	 * An arraylist of creatures that should be removed from the world.
	 */
	public List<Creature> removeCreatures = new ArrayList <Creature> ();
	
	/**
	 * The left side of the visible window.
	 */
	private int leftWindow;
	
	/**
	 * The bottom of the visible window.
	 */
	private int bottomWindow ;
	
	/**
	 * The right side of the visible window.
	 */
	private int rightWindow ;
	
	/**
	 * The top of the visible window.
	 */
	private int topWindow ;
	
	/**
	 * The length of the side of a tile.
	 */
	private int tileLength;
	
	/**
	 * The x coordinate of the target tile. 
	 */
	private int targetTileX;
	
	/**
	 * The y coordinate of the target tile.
	 */
	private int targetTileY;
	
	/**
	 * A boolean that shows whether the player is alive or not.
	 */
	private boolean alienAlive = true;
	
	/**
	 * A boolean that shows whether the player is on the target tile.
	 */
	private boolean alienOnTargetTile;

}
