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

public class PlantTest {
	
	World world;
	Plant plant;

	@Before
	public void setUp() throws Exception {
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		plant = new Plant(50, 70, sprites);
		world.setCreatureInWorld(plant);
		plant.world = world;
		world.startGame();
	}

	@Test
	public final void testContstructor(){
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		Plant plant = new Plant(0, 0, sprites);
		final double DELTA = 1e-15;
		assertEquals(0, plant.getPositionX(),DELTA);
		assertEquals(0, plant.getPositionY(),DELTA);
		assertEquals(50, plant.maxSpeedX,DELTA);
		assertEquals(1, plant.getHitpoints());
		assert plant.isMovingX();
	}
	
	@Test
	public final void testGetCurrentSprite_RightMovement(){
		plant.lastDirection = 1;
		assertEquals(plant.getImageAtIndex(1),plant.getCurrentSprite());
	}
	
	@Test
	public final void testGetCurrentSprite_LeftMovement(){
		plant.lastDirection = -1;
		assertEquals(plant.getImageAtIndex(0),plant.getCurrentSprite());
	}
}