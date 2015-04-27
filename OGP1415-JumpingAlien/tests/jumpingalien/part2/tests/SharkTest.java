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

public class SharkTest {
	
	World world;
	Shark shark;

	@Before
	public void setUp() throws Exception {
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		shark = new Shark(50, 70, sprites);
		world.setCreatureInWorld(shark);
		shark.world = world;
		world.startGame();
	}

	@Test
	public final void testContstructor(){
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		Shark shark = new Shark(0, 0, sprites);
		final double DELTA = 1e-15;
		assertEquals(0, shark.getPositionX(),DELTA);
		assertEquals(0, shark.getPositionY(),DELTA);
		assertEquals(400, shark.maxSpeedX,DELTA);
		assertEquals(100, shark.getHitpoints());
		assert shark.isMovingX();
	}
	
	@Test
	public final void testGetCurrentSprite_RightMovement(){
		shark.lastDirection = 1;
		assertEquals(shark.getImageAtIndex(1),shark.getCurrentSprite());
	}
	
	@Test
	public final void testGetCurrentSprite_LeftMovement(){
		shark.lastDirection = -1;
		assertEquals(shark.getImageAtIndex(0),shark.getCurrentSprite());
	}
}
