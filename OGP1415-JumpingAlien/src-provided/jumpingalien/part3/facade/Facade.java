package jumpingalien.part3.facade;

import java.util.Collection;

import program.Program;
import jumpingalien.model.Buzam;
import jumpingalien.model.Direction;
import jumpingalien.model.IllegalDeltaTimeException;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.part3.programs.ProgramParser;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart3 {

	public Buzam createBuzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		return new Buzam(pixelLeftX, pixelBottomY, sprites, 300, 500, null);
	}

	public Buzam createBuzamWithProgram(int pixelLeftX, int pixelBottomY,
			Sprite[] sprites, Program program){
		Buzam user = new Buzam(pixelLeftX, pixelBottomY, sprites, 300, 500, program);
		program.setUser(user);
		return user;
	}

	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites,
			Program program){
		Plant user = new Plant(x, y, sprites, program);
		program.setUser(user);
		return user;
	}

	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites,
			Program program){
		Shark user = new Shark(x, y, sprites, program);
		program.setUser(user);
		return user;
	}

	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites,
			School school, Program program){
		Slime user = new Slime(x, y, sprites, school, program);
		program.setUser(user);
		return user;
	}

	public ParseOutcome<?> parse(String text){
		return new parser(new ProgramFactory()).parse(text);
		
	}
	
	//voor break checken 
	public boolean isWellFormed(Program program){
		return true;
	}

	public void addBuzam(World world, Buzam buzam){
		world.setCreatureInWorld(buzam);
		buzam.world = world;
	}

	public int[] getLocation(Buzam alien){
		int[] array = {(int)alien.getPositionX(),(int)alien.getPositionY()};
		return array;
	}

	public double[] getVelocity(Buzam alien){
		double[] array = {alien.getSpeedX(),alien.getSpeedY()};
		return array;
	}

	public double[] getAcceleration(Buzam alien){
		double[] array = {alien.getAccelerationX(),alien.getAccelerationY()};
		return array;
	}

	public int[] getSize(Buzam alien){
		int[] array = {alien.getWidthSprite(),alien.getHeightSprite()};
		return array;
	}

	public Sprite getCurrentSprite(Buzam alien){
		return alien.getCurrentSprite();
	}

	public int getNbHitPoints(Buzam alien){
		return alien.getHitpoints();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//																												//
//										FACADE Part 2															//						
//																												//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public int getNbHitPoints(Mazub alien){
		return alien.getHitpoints();
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
		return world.getTileLength();
	}

	public void startGame(World world){
		world.startGame();
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
		int[][] tmp = world.getTilePositions(pixelLeft, pixelBottom, pixelRight, pixelTop);
		return tmp;
	}

	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException{
		return world.getGeologicalFeature(pixelX, pixelY);
	}

	public void setGeologicalFeature(World world, int tileX, int tileY, int tileType){
		world.setGeologicalFeature(tileX, tileY, tileType);
	}

	public void setMazub(World world, Mazub alien){
		world.setCreatureInWorld(alien);
		alien.world = world;
	}

	public boolean isImmune(Mazub alien){
		return alien.isImmune();
	}

	public Plant createPlant(int x, int y, Sprite[] sprites){
		return new Plant (x, y, sprites, null);
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
		return new Shark (x, y, sprites, null);
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
		return new Slime(x, y, sprites, school, null);
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
//										FACADE Part 1															//						
//																												//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Mazub(pixelLeftX,pixelBottomY,sprites, 300, 100, null);
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
