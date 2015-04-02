/**
 * 
 */
package jumpingalien.part2.facade;

import jumpingalien.model.*;
import jumpingalien.util.Sprite;

/**
 * @author Nele
 *
 */
public class FacadePart2 implements IFacadePart2 {
	
		public int getNbHitPoints(Mazub alien){
			alien.getHitpoints();
		}

		public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
				int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
				int targetTileY){
			return new World(tileSize, nbTilesX, nbTilesY, visibleWindowWidth, visibleWindowHeight, targetTileX, targetTileY);
		}
			
		public int[] getWorldSizeInPixels(World world){
			int[] array = {world.getXMax()-world.getXMin(),world.getYMax()-world.getYMin()};
			return array;
		}

		public int getTileLength(World world){
			world.getTileLength();
		}

		public void startGame(World world){
		}

		public boolean isGameOver(World world){
			world.isGameOver();
		}

		public boolean didPlayerWin(World world){
			return (world.isGameOver() && world.playerOnTargetTile());
		}

		public void advanceTime(World world, double dt){
			world.advanceTime(dt);
		}


		public int[] getVisibleWindow(World world){
			world.getVisibleWindow();
		}

		public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY){
			int [] array = {tileX,tileY};
			return array;
		}

	
		int[][] getTilePositionsIn(World world, int pixelLeft, int pixelBottom,
				int pixelRight, int pixelTop);

		int getGeologicalFeature(World world, int pixelX, int pixelY)
				throws ModelException;

		void setGeologicalFeature(World world, int tileX, int tileY, int tileType);

		public void setMazub(World world, Mazub alien){
			world.setCreatureInWorld(alien);
		}

		public boolean isImmune(Mazub alien){
			alien.isImmune();
		}

		public Plant createPlant(int x, int y, Sprite[] sprites){
			return new Plant (x, y, sprites);
		}

		public void addPlant(World world, Plant plant){
			world.setCreatureInWorld(plant);
		}

		public Collection<Plant> getPlants(World world);

		public int[] getLocation(Plant plant){
			plant.getLocation();
		}

		public Sprite getCurrentSprite(Plant plant){
			plant.getCurrentSprite();
		}

		public Shark createShark(int x, int y, Sprite[] sprites){
			return new Shark (x, y, sprites);
		}

		public void addShark(World world, Shark shark){
			world.setCreatureInWorld(shark);
		}

		public Collection<Shark> getSharks(World world);

		public int[] getLocation(Shark shark){
			shark.getLocation();
		}

		public Sprite getCurrentSprite(Shark shark){
			shark.getCurrentSprite();
		}

		public School createSchool(){
			return new School();
		}

		public Slime createSlime(int x, int y, Sprite[] sprites, School school){
			return new Slime(x, y, sprites, school);
		}

		public void addSlime(World world, Slime slime){
			world.setCreatureInWorld(slime);
		}

		public Collection<Slime> getSlimes(World world);

		public int[] getLocation(Slime slime){
			slime.getLocation();
		}

		public Sprite getCurrentSprite(Slime slime){
			slime.getCurrentSprite();
		}

		public School getSchool(Slime slime){
			slime.getSchool();
		}

	}


}
