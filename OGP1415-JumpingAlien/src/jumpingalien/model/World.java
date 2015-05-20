package jumpingalien.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;



/**
 * A class of worlds with a given size and various creatures and geological features.
 * 
 * @author	Nele Nauwelaers, Melanie Nijs
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
	 * @post ...
	 * 		|new.tileLength == tileSize;
	 * @post ...
	 * 		|new.visibleWindowWidth == visibleWindowWidth;
	 * @post ...
	 * 		|new.visibleWindowHeight == visibleWindowHeight;
	 * @post ...
	 * 		|new.X == tileSize*(nbTilesX);
	 * @post ...
	 * 		|new.Y == tileSize*(nbTilesY);
	 * @post ...
	 * 		|new.xMax == this.X - 1;
	 * @post ...
	 * 		|new.yMax == this.Y - 1;
	 * @post ...
	 * 		|new.inWorldTiles == int [nbTilesX][nbTilesY];
	 */
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY){
		this.tileLength = tileSize;
		this.visibleWindowWidth = visibleWindowWidth;
		this.visibleWindowHeight = visibleWindowHeight;
		this.X = tileSize*(nbTilesX);
		this.Y = tileSize*(nbTilesY);	
		this.xMax = X - 1;
		this.yMax = Y - 1;
		this.targetTileX = targetTileX*tileSize;
		this.targetTileY = targetTileY*tileSize;
		this.inWorldTiles = new int [nbTilesX][nbTilesY];
	}
	
	/**
	 * Counts how many times a given int appears in the matrix inWorldTiles.
	 * @param d
	 * 		An integer number that represent a feature. (0, 1, 2 or 3).
	 * @return
	 * 		Returns the number of times d appears in the matrix inWorldTiles.
	 */
	@Basic  
	private int countOccurrences(int d){
		int count = 0;
		for ( int col = 0 ; col < inWorldTiles[1].length ; col++){
	       for (int i = 0; i < inWorldTiles.length; i++){
	            if (inWorldTiles[i][col] == d){
	              count++;
	            }
	          }
		}
	    return count;
	    }
	
	/**
	 * GEEN DOCUMENTATIE NODIG
	 * @throws IllegalDeltaTimeException 
	 */
	@Raw
	public void advanceTime(double dt) throws IllegalDeltaTimeException{
		for (Creature creature: new ArrayList<Creature>(inWorldCreatures)){
			double smallDt=  0.01/((Math.sqrt(Math.pow(Math.abs(creature.getSpeedX()), 2)+ Math.pow(Math.abs(creature.getSpeedY()), 2)) + 
					(Math.sqrt(Math.pow(Math.abs(creature.getAccelerationX()),2) + Math.pow(Math.abs(creature.getAccelerationY()), 2)))*dt));
			creature.advanceTime(dt);
			if (creature.isAlive == false){
				inWorldCreatures.remove(creature);
			}
			if (creature instanceof Mazub){
				setVisibleWindow(creature);
				alienAlive = creature.isAlive;
				alienOnTargetTile = isOnTargetTile(creature);
				creature.lastCollisionEnemy += dt;
				
			}
		}
	}

	/**
	 * At the beginning of the game are all creatures added to the arraylist inWorldCreatures.
	 * @post ...
	 * 		|new.inWorldCreatures == this.addCreatures; 
	 */
	@Basic
	public void startGame(){
		this.inWorldCreatures.addAll(addCreatures);
			
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
	@Basic
	public boolean isGameOver(){
		return ((! alienAlive) || (alienOnTargetTile));
	}
	
	/**
	 * 
	 * @return true if and only if the player is on the target tile.
	 * 			alienOnTargetTile = true.
	 */
	@Basic
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
	@Raw 
	private boolean isOnTargetTile(Creature creature){
		return (((int)creature.getPositionX() == targetTileX && (int)creature.getPositionY() + 1 == targetTileY)
				|| (creature.getPositionX()+creature.getWidthSprite() == targetTileX && creature.getPositionY() +1 == targetTileY));
		}

	
/****************************************************************************************************
 * 																									*
 * 									     GETTERS AND SETTERS										*
 * 																									*										
 ****************************************************************************************************/
		
	/**
	 * Returns a two dimensional matrix (or an array of arrays) that contains the coordinates of pixels of tiles that
	 * have the given feature.
	 * @param feature
	 * 		An integer that represents a feature of a tile.
	 * @return
	 * 		An integer array of coordinates of the tiles that have the given feature.
	 */
	int[][] getFeatureTiles(int feature){
		int matrixLength = countOccurrences(feature);
		int[][] featureTiles = new int [matrixLength][2];
		int k = 0;
		for (int i= 0; i < (inWorldTiles.length); i+=1){
			for (int j = 0; j < (inWorldTiles[1].length); j+=1){
				if (inWorldTiles[i][j] == feature){
					featureTiles[k][0] = i*getTileLength();
					featureTiles[k][1] = j*getTileLength();	
					k +=1;
				}
			}
		}
		return featureTiles;	
	}
	
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
	@Raw
	public int [][] getTilePositions(int pixelLeft, int pixelBottom, int pixelRight, int pixelTop){
		int matrixLength = ((pixelTop - pixelBottom)/tileLength) * ((pixelRight - pixelLeft)/tileLength);
		int [][] tilePositions = new int [matrixLength][2];
		int k = 0;
		for (int j=pixelBottom; j <= (pixelTop - tileLength); j+=tileLength){
			for (int i=pixelLeft; i <= (pixelRight-tileLength); i+=tileLength){
				tilePositions [k][0] = getTileNbX(i);
			    tilePositions [k][1] = getTileNbY(j);
				k +=1;
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
	 */
	@Raw
	public int getGeologicalFeature(int pixelX, int pixelY){
		int tileX = this.getTileNbX(pixelX);
		int tileY = this.getTileNbY(pixelY);
		return inWorldTiles[tileX][tileY];
	}
		
	/**
	 * Gives a feature to the given tile with as bottomleft tile coordinate (pixelX, pixelY).
	 * @param pixelX
	 * 			The x tile coordinate of the bottomleft of the tile.
	 * @param pixelY
	 * 			The y tile coordinate of the bottomleft of the tile.
	 * @param feature
	 * 			An integer that represents a geological feature: 0 = air;   1 = solid ground;
	 * 															 2 = water; 3 = magma.
	 * @post ...
	 * 		| new.inWorldTiles[pixelX][pixelY] = feature;
	 */
	@Basic @Raw
	public void setGeologicalFeature(int pixelX, int pixelY, int feature){
		inWorldTiles[pixelX][pixelY] = feature;
	}
	
	/**
	 * Returns a collection of all the slimes in this world.
	 * @return
	 * 		A collection of slimes in the world.
	 */
	@Basic @Immutable
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
	@Basic @Immutable
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
	@Basic @Immutable
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
	 * Returns a collection of all the Mazubs in this world.
	 * @return
	 * 		A collection of mazubs in the world.
	 */
	@Basic @Immutable
	public Collection<Mazub> getMazubsInWorld(){
		this.mazubsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Mazub ){
				this.mazubsInWorld.add(creature);
			}
		}
		if (mazubsInWorld.removeAll(getBuzamsInWorld())){
			return (Collection)mazubsInWorld;
		}
		else{
			return (Collection)mazubsInWorld ;
		}
	}
	
	/**
	 * Returns a collection of all the buzams in this world.
	 * @return
	 * 		A collection of buzams in the world.
	 */
	@Basic @Immutable
	public Collection<Buzam> getBuzamsInWorld(){
		this.buzamsInWorld = new ArrayList<Creature>();
		for (Creature creature: inWorldCreatures){
			if (creature instanceof Buzam ){
				this.buzamsInWorld.add(creature);
			}
		}
		return (Collection)buzamsInWorld;
	}
	
	/**
	 * Sets the vissible window with the creature as center.
	 * @param creature
	 * 			The alien the player controls.
	 * @post ...
	 * 		|new.leftWindow = Math.max(xMin, Math.min((int)(creature.getPositionX()-(visibleWindowWidth/2)), xMax-visibleWindowWidth));
	 * @post ...
	 * 		|new.bottomWindow = Math.max(yMin,Math.min((int)(creature.getPositionY()-(visibleWindowHeight/2)), yMax-visibleWindowHeight));
	 * @post ...
	 * 		|new.rightWindow = leftWindow + visibleWindowWidth + 2*getTileLength();
	 * @post ...
	 * 		|new.topWindow = bottomWindow + visibleWindowHeight + 2*getTileLength();
	 */
	@Raw
	private void setVisibleWindow(Creature creature){
		this.leftWindow = Math.max(xMin, Math.min((int)(creature.getPositionX()-(visibleWindowWidth/2)), xMax-visibleWindowWidth));
		this.bottomWindow = Math.max(yMin,Math.min((int)(creature.getPositionY()-(visibleWindowHeight/2)), yMax-visibleWindowHeight) );
		this.rightWindow = leftWindow + visibleWindowWidth + 2*getTileLength();
		this.topWindow = bottomWindow + visibleWindowHeight + 2*getTileLength();
	}
	
	/**
	 * Returns the visible window of the game.
	 * @return
	 * 		An array of four coordinates of all sides of the window.
	 */
	@Basic @Immutable
	public int[] getVisibleWindow(){
		int[] array = {leftWindow, bottomWindow, rightWindow, topWindow};
		return array;
	}

	/**
	 * Gets the minimal horizontal pixel value of the world.
	 * @return
	 * 		The minimal horizontal pixel value.
	 */
	@Basic @Immutable
	public static int getXMin(){
		return xMin;
	}
	
	/**
	 * Gets the minimal vertical pixel value of the world.
	 * @return
	 * 		The minimal vertical pixel value.
	 */
	@Basic @Immutable
	public static int getYMin(){
		return yMin;
	}
	
	/**
	 * Gets the maximal horizontal pixel value of the world.
	 * @return
	 * 		The maximal horizontal pixel value.
	 */
	@Basic @Immutable
	public static int getXMax(){
		return xMax;
	}
	
	/**
	 * Gets the maximal vertical pixel value of the world.
	 * @return
	 * 		The maximal vertical pixel value.
	 */
	@Basic @Immutable
	public static int getYMax(){
		return yMax;
	}
	
	/**
	 * Returns the length of one tile side.
	 * @return
	 * 		the length of the side of the tile.
	 */
	@Basic @Immutable
	public int getTileLength(){
		return tileLength;
	}
	
	/**
	 * Sets a creature in the world by adding it to the list addCreatures.
	 * @param creature
	 * 			The creature that is created and should be in the world.
	 * @post ...
	 * 		|new.addCreatures == this.addCreatures.add(creature);
	 */
	@Basic 
	public void setCreatureInWorld(Creature creature){
		 this.addCreatures.add(creature);
	}
	
	/**
	 * Returns the x coordinate of the tile that has pixelX as bottomleft x coordinate.
	 * @param pixelX
	 * 			An X position in the world.
	 * @return
	 * 		An integer that represents the x coordinate of the tile that belongs to pixel X.
	 */
	@Basic @Raw
	public int getTileNbX(int pixelX){
		return (pixelX/this.tileLength);
	}

	/**
	 * Returns the y coordinate of the tile that has pixelY as bottomleft y coordinate.
	 * @param pixelY
	 * 			An Y position in the world.
	 * @return
	 * 		An integer that represents the y coordinate of the tile that belongs to pixelY.
	 */
	@Basic @Raw
	public int getTileNbY(int pixelY){
		return (pixelY/this.tileLength);
	}
	
	/**
	 * Returns the list of all the creatures in world.
	 * @return
	 * 		A list inWorldCreatures of creatures.
	 */
	@Basic 
	public List <Creature> getInWorldCreatures(){
		return this.inWorldCreatures;
	}
	
	/**
	 * Returns the list of all the creatures to be added in the world.
	 * @return
	 * 		A list addCreatures of creatures.
	 */
	@Basic 
	public List <Creature> getAddCreatures(){
		return this.addCreatures;
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
	private final int X;
	
	/**
	 * The real maximum y value.
	 */
	private final int Y;
	
	/**
	 * An array of coordinates of tiles that are in the world. 
	 */
	int[][] inWorldTiles;

	/**
	 * The width of the visible window.
	 */
	private final int visibleWindowWidth;
	
	/**
	 * The height of the visible window.
	 */
	private final int visibleWindowHeight;
	
	/**
	 * An arraylist of the creatures in the world.
	 */
	List<Creature> inWorldCreatures = new ArrayList<Creature>();
	
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
	 * An arraylist of the Buzams in the world
	 */
	private List<Creature> buzamsInWorld= new ArrayList <Creature> ();
	
	/**
	 * An arraylist of creatures that should be added to the world.
	 */
	private List<Creature> addCreatures = new ArrayList <Creature> ();
	
	/**
	 * An arraylist of creatures that should be removed from the world.
	 */
	List<Creature> removeCreatures = new ArrayList <Creature> ();
	
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
	private final int tileLength;
	
	/**
	 * The x coordinate of the target tile. 
	 */
	private final int targetTileX;
	
	/**
	 * The y coordinate of the target tile.
	 */
	private final int targetTileY;
	
	/**
	 * A boolean that shows whether the player is alive or not.
	 */
	private boolean alienAlive = true;
	
	/**
	 * A boolean that shows whether the player is on the target tile.
	 */
	private boolean alienOnTargetTile;

}
