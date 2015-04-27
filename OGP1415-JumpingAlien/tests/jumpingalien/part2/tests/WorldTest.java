package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.*;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {
	
	World world;
	Mazub alien;
	Plant plant;
	Shark shark;
	School school;
	Slime slime;


	@Before
	public void setUp()  {
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		alien = new Mazub(50, 50, sprites, 300, 100);
		plant = new Plant(0, 0, sprites);
		shark = new Shark (1,1,sprites);
		school = new School();
		slime = new Slime(3, 3, sprites, school);
		world.startGame();
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////


	
	public static final double DEFAULT_EPSILON = 1e-4;
	
	public static boolean fuzzyEquals(double x, double y) {
	return fuzzyEquals(x, y, DEFAULT_EPSILON);
	}
	
	public static boolean fuzzyEquals(double x, double y, double eps) {
	if (Double.isNaN(x) || Double.isNaN(y))
	return false;
	return Math.abs(x - y) <= eps
	|| Double.valueOf(x).equals(Double.valueOf(y));
	}
	
	@Test
	public final void testConstructor(){
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		assertEquals(70, world.getTileLength());
		assertEquals(1399, world.getXMax());
		assertEquals(839,world.getYMax());
	}
	
	@Test
	public final void testAdvanceTime(){
		
	}
	
	
	@Test
	public final void testGameStarted(){
		assert world.getInWorldCreatures().contains(alien);
		assert world.getInWorldCreatures().contains(plant);
		assert world.getInWorldCreatures().contains(slime);
		assert world.getInWorldCreatures().contains(shark);
	}
	
	@Test
	public final void testIsGameOverFalseCase(){
		assert (! world.playerOnTargetTile());
		//assert (alienAlive); TODO
	}
	
	@Test
	public final void testIsGameOverTrueCase1(){
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		alien = new Mazub(0, 0, sprites, 18, 9);
		assert (world.playerOnTargetTile());
		// assert (alienAlive); TODO
	}
	
	@Test
	public final void testIsGameOverTrueCase2(){
		// assert (! alienAlive);
	}
	
	@Test
	public final void testPlayerOnTargetTileFalseCase(){
		assert(! world.playerOnTargetTile());
	}
	
	@Test
	public final void testPlayerOnTargetTileTrueCase(){
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		alien = new Mazub(0, 0, sprites, 18, 9);
		assert (world.playerOnTargetTile());
	}
	
	
	@Test
	public final void testSetGeologicalFeatureFirstCase(){
		world.setGeologicalFeature(0, 0, 1);
		assertEquals(1,world.getGeologicalFeature(0*world.getTileLength(), 0*world.getTileLength()));
	}
	
	@Test
	public final void testSetGeologicalFeatureMiddleCase(){
		world.setGeologicalFeature(7, 3, 1);
		assertEquals(1,world.getGeologicalFeature(7*world.getTileLength(), 3*world.getTileLength()));
	}
	
	
	@Test
	public final void testSetCreatureInWorld(){
		world.setCreatureInWorld(plant);
		assert world.getAddCreatures().contains(alien);
	}
	
	
}
