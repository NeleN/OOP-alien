/**
 * 
 */
package jumpingalien.part2.facade;

import java.util.*;

import jumpingalien.util.*;
import jumpingalien.model.*;


/**
 * @author Nele
 *
 */
public class Facade implements IFacadePart2 {
	
		public int getNbHitPoints(Mazub alien){
			return alien.getHitpoints();
		}

		public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
				int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
				int targetTileY){
			return new World(tileSize, nbTilesX, nbTilesY, visibleWindowWidth, visibleWindowHeight, targetTileX, targetTileY);
		}
			
		public int[] getWorldSizeInPixels(World world){
			int[] array = {World.getXMax()-World.getXMin(),World.getYMax()-World.getYMin()};
			return array;
		}

		public int getTileLength(World world){
			return world.getTileLength();
		}

		public void startGame(World world){
		}

		public boolean isGameOver(World world){
			return world.isGameOver();
		}

		public boolean didPlayerWin(World world){
			return (world.isGameOver() && world.playerOnTargetTile());
		}

		public void advanceTime(World world, double dt) {
			try {
				world.advanceTime(dt);
			} catch (IllegalDeltaTimeException exc) {
				throw new jumpingalien.util.ModelException("Illegal time interval", exc);
				
			}
		}


		public int[] getVisibleWindow(World world){
			return world.getVisibleWindow();
		}

		public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY){
			int [] array = {tileX * world.getTileLength(),tileY * world.getTileLength()};
			return array;
		}

	
		public int[][] getTilePositionsIn(World world, int pixelLeft, int pixelBottom,
				int pixelRight, int pixelTop){
			return world.getTilePositions(pixelLeft, pixelBottom, pixelRight, pixelTop);
		}

		public int getGeologicalFeature(World world, int pixelX, int pixelY)
				throws ModelException{
			return world.getGeologicalFeature(pixelX, pixelY);
		}

		public void setGeologicalFeature(World world, int tileX, int tileY, int tileType){
			world.setGeologicalFeature(tileX, tileX, tileType);
		}

		public void setMazub(World world, Mazub alien){
			world.setCreatureInWorld(alien);
			alien.world = world;
		}

		public boolean isImmune(Mazub alien){
			return alien.isImmune();
		}

		public Plant createPlant(int x, int y, Sprite[] sprites){
			return new Plant (x, y, sprites);
		}

		public void addPlant(World world, Plant plant){
			world.setCreatureInWorld(plant);
			plant.world = world;
		}

		public Collection<Plant> getPlants(World world){
			return world.getPlantsInWorld();
		}

		public int[] getLocation(Plant plant){
			return plant.getLocation();
		}

		public Sprite getCurrentSprite(Plant plant){
			return plant.getCurrentSprite();
		}

		public Shark createShark(int x, int y, Sprite[] sprites){
			return new Shark (x, y, sprites);
		}

		public void addShark(World world, Shark shark){
			world.setCreatureInWorld(shark);
			shark.world = world;
		}

		public Collection<Shark> getSharks(World world){
			return world.getSharksInWorld();
		}

		public int[] getLocation(Shark shark){
			return shark.getLocation();
		}

		public Sprite getCurrentSprite(Shark shark){
			return shark.getCurrentSprite();
		}

		public School createSchool(){
			return new School();
		}

		public Slime createSlime(int x, int y, Sprite[] sprites, School school){
			return new Slime(x, y, sprites, school);
		}

		public void addSlime(World world, Slime slime){
			world.setCreatureInWorld(slime);
			slime.world = world;
		}

		public Collection<Slime> getSlimes(World world){
			return world.getSlimesInWorld();	
		}

		public int[] getLocation(Slime slime){
			return slime.getLocation();
		}

		public Sprite getCurrentSprite(Slime slime){
			return slime.getCurrentSprite();
		}

		public School getSchool(Slime slime){
			return slime.getSchool();
		}
		
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//																												//
//											FACADE Part 1														//						
//																												//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
			return new Mazub(pixelLeftX,pixelBottomY,sprites, 300, 100);
		}
		
		public int[] getLocation(Mazub alien){
			int[] array = {(int)alien.getPositionX(),(int)alien.getPositionY()};
			return array;
		}
		
		public double[] getVelocity(Mazub alien){
			double[] array = {alien.getSpeedX(),alien.getSpeedY()};
			return array;
		}
		
		public double[] getAcceleration(Mazub alien){
			double[] array = {alien.getAccelerationX(),alien.getAccelerationY()};
			return array;
		}
		
		public int[] getSize(Mazub alien){
			int[] array = {alien.getWidthSprite(),alien.getHeightSprite()};
			return array;
		}
		
		public Sprite getCurrentSprite(Mazub alien){
			return alien.getCurrentSprite();
		}
		
		public void startJump(Mazub alien){
			alien.startJump();
		}
		
		public void endJump(Mazub alien){
			alien.endJump();
		}
		
		public void startMoveLeft(Mazub alien){
			alien.startMove(Direction.LEFT);
		}
		
		public void endMoveLeft(Mazub alien){
			alien.endMove();
		}
		
		public void startMoveRight(Mazub alien){
			alien.startMove(Direction.RIGHT);
		}
		
		public void endMoveRight(Mazub alien){
			alien.endMove();
		}
		
		public void startDuck(Mazub alien){
			alien.startDuck();
		}
		
		public void endDuck(Mazub alien){
			alien.endDuck();
		}
		
}



