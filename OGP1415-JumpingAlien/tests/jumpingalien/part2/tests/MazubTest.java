package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Direction;
import jumpingalien.model.IllegalDeltaTimeException;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MazubTest {
	
	World world;
	Mazub alien;


	@Before
	public void setUp() throws Exception {
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		alien = new Mazub(50, 70, sprites, 300, 100);
		world.setCreatureInWorld(alien);
		alien.world = world;
		world.startGame();
	}

	@Test
	public final void testContstructor(){
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		Mazub alien = new Mazub(0, 0, sprites, 300, 100);
		final double DELTA = 1e-15;
		assertEquals(0, alien.getPositionX(),DELTA);
		assertEquals(0, alien.getPositionY(),DELTA);
		assertEquals(300, alien.maxSpeedX,DELTA);
		assertEquals(100, alien.getHitpoints());
		assertEquals(-1000, alien.getAccelerationY(),DELTA);
	}
	
	@Test
	public final void testStartMove_isMoving(){
		alien.startMove(Direction.RIGHT);
		assertTrue(alien.isMovingX());
	}
	
	@Test
	public final void testStartMove_indexChanged(){
		alien.startMove(Direction.RIGHT);
		final double DELTA = 1e-15;
		assertEquals(0,alien.changedIndex,DELTA);
	}
	
	@Test
	public final void testStartMove_alternatingIndex(){
		alien.startMove(Direction.RIGHT);
		assertEquals(0,alien.alternatingIndex);
	}
	
	@Test
	public final void testStartMove_Speed_Left(){
		alien.startMove(Direction.LEFT);
		final double DELTA = 1e-15;
		assertEquals(-100,alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void testStartMove_Speed_Right(){
		alien.startMove(Direction.RIGHT);
		final double DELTA = 1e-15;
		assertEquals(100,alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void testStartMove_Acceleration_Left(){
		alien.startMove(Direction.LEFT);
		final double DELTA = 1e-15;
		assertEquals(-90,alien.getAccelerationX(),DELTA);
	}
	
	@Test
	public final void testStartMove_Acceleration_Right(){
		alien.startMove(Direction.RIGHT);
		final double DELTA = 1e-15;
		assertEquals(90,alien.getAccelerationX(),DELTA);
	}
	
	@Test
	public final void testStartMove_LastDirection_Left(){
		alien.startMove(Direction.LEFT);
		assertEquals(-1,alien.lastDirection);
	}
	
	@Test
	public final void testStartMove_LastDirection_Right(){
		alien.startMove(Direction.RIGHT);
		assertEquals(1,alien.lastDirection);
	}
	
	
	@Test
	public final void testStartJump_Speed(){
		alien.startJump();
		final double DELTA = 1e-15;
		assertEquals(800, alien.getSpeedY(),DELTA);
	}
	
	@Test
	public final void testStartJump_isJumping(){
		alien.startJump();
		assertTrue(alien.isJumping());
	}
	
	@Test
	public final void testStartDuck_MaxSpeed(){
		alien.startDuck();
		final double DELTA = 1e-15;
		assertEquals(100,alien.maxSpeedX,DELTA);
	}
	
	@Test
	public final void testStartDuck_isDucking(){
		alien.startDuck();
		assertTrue(alien.isDucking());
	}
	
	@Test
	public final void testEndDuck_MaxSpeed(){
		alien.endDuck();
		final double DELTA = 1e-15;
		assertEquals(300,alien.maxSpeedX,DELTA);
	}
	
	@Test
	public final void testEndDuck_isDucking(){
		alien.endDuck();
		assertFalse(alien.isDucking());
	}
	
	@Test
	public final void testIsImmune_TrueCase()throws IllegalDeltaTimeException{
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		alien = new Mazub(50, 70, sprites, 300, 100);
		Shark shark = new Shark(50, 70, sprites);
		world.setCreatureInWorld(alien);
		world.setCreatureInWorld(shark);
		alien.world = world;
		world.startGame();
		world.advanceTime(0.2);
		world.advanceTime(0.2);
		assert (alien.isImmune());		
	}
	
	@Test
	public final void testIsImmune_FlaseCase()throws IllegalDeltaTimeException{
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		alien = new Mazub(50, 70, sprites, 300, 100);
		Shark shark = new Shark(50, 70, sprites);
		world.setCreatureInWorld(alien);
		world.setCreatureInWorld(shark);
		alien.world = world;
		world.startGame();
		world.advanceTime(0.2);
		world.advanceTime(0.2);
		world.advanceTime(0.2);
		world.advanceTime(0.2);
		assert (! alien.isImmune());		
	}
	
	@Test
	public final void getCurrentSprite_Case0(){
		assertEquals(alien.getImageAtIndex(0),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case1(){
		alien.timeLastMovedX = 5;
		alien.startDuck();
		assertEquals(alien.getImageAtIndex(1),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case2(){
		alien.timeLastMovedX = 0.5;
		alien.lastDirection = 1;
		assertEquals(alien.getImageAtIndex(2),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case3(){
		alien.timeLastMovedX = 0.5;
		alien.lastDirection = -1;
		assertEquals(alien.getImageAtIndex(3),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case4(){
		alien.startMove(Direction.RIGHT);
		alien.startJump();
		assertEquals(alien.getImageAtIndex(4),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case5(){
		alien.startMove(Direction.LEFT);
		alien.startJump();
		assertEquals(alien.getImageAtIndex(5),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case6A(){
		alien.startMove(Direction.RIGHT);
		alien.startDuck();
		assertEquals(alien.getImageAtIndex(6),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case6B(){
		alien.timeLastMovedX = 0.3;
		alien.lastDirection = 1;
		alien.startDuck();
		assertEquals(alien.getImageAtIndex(6),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case7A(){
		alien.startMove(Direction.LEFT);
		alien.startDuck();
		assertEquals(alien.getImageAtIndex(7),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case7B(){
		alien.timeLastMovedX = 0.7;
		alien.lastDirection = -1;
		alien.startDuck();
		assertEquals(alien.getImageAtIndex(7),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case8(){
		alien.startMove(Direction.RIGHT);
		alien.alternatingIndex = 4;
		assertEquals(alien.getImageAtIndex(12),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case9(){
		alien.startMove(Direction.LEFT);
		alien.alternatingIndex = 9;
		assertEquals(alien.getImageAtIndex(28),alien.getCurrentSprite());
	}

}
