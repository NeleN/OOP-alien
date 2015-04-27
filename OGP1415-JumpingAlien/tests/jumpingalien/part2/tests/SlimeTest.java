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

public class SlimeTest {
	
	World world;
	School school;
	Slime slime;

	@Before
	public void setUp() throws Exception {
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		school = new School();
		slime = new Slime(50, 70, sprites,school);
		world.setCreatureInWorld(slime);
		slime.world = world;
		world.startGame();
	}

	@Test
	public final void testContstructor(){
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		Slime slime = new Slime(0, 0, sprites, school);
		final double DELTA = 1e-15;
		assertEquals(0, slime.getPositionX(),DELTA);
		assertEquals(0, slime.getPositionY(),DELTA);
		assertEquals(250, slime.maxSpeedX,DELTA);
		assertEquals(100, slime.getHitpoints());
		assertEquals(-1000, slime.getAccelerationY(),DELTA);
		assertEquals(school, slime.getSchool());
		school.getSlimesInSchool().contains(slime);
		assert slime.isMovingX();
	}
	
	@Test
	public final void testGetCurrentSprite_RightMovement(){
		slime.lastDirection = 1;
		assertEquals(slime.getImageAtIndex(1),slime.getCurrentSprite());
	}
	
	@Test
	public final void testGetCurrentSprite_LeftMovement(){
		slime.lastDirection = -1;
		assertEquals(slime.getImageAtIndex(0),slime.getCurrentSprite());
	}
}