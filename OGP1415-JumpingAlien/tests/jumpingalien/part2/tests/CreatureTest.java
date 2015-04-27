package jumpingalien.part2.tests;

import static org.junit.Assert.*;

import org.junit.*;

import jumpingalien.model.Creature;
import jumpingalien.model.Direction;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.*;

public class CreatureTest {
	
	World world;
	Mazub alien;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() {
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		world = new World(70, 20, 12, 1024, 769, 18, 9);
		alien = new Mazub(50, 70, sprites, 300, 100);
		world.setCreatureInWorld(alien);
		world.setCreatureInWorld(alien);
		alien.world = world;
		world.startGame();
	}	

	@After
	public void tearDown() throws Exception {
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
	
	public static boolean fuzzyLessThanOrEqualTo(double x, double y) {
		return fuzzyLessThanOrEqualTo(x, y, DEFAULT_EPSILON);
	}

	public static boolean fuzzyLessThanOrEqualTo(double x, double y, double eps) {
		if (fuzzyEquals(x, y, eps)) {
			return true;
		} else {
			return Double.compare(x, y) < 0;
		}
	}
	
	
	@Test
	public final void testContstructor(){
		Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
		Creature creature = new Mazub(0, 0, sprites, 300, 100);
		final double DELTA = 1e-15;
		assertEquals(0, creature.getPositionX(),DELTA);
		assertEquals(0, creature.getPositionY(),DELTA);
//		assertEquals(sprites, creature.images);
		assertEquals((sprites.length-10)/2, creature.m);
		assertEquals(300, creature.maxSpeedX,DELTA);
		assertEquals(100, creature.getHitpoints());
	}

	@Test
	public final void testGetHitpoints() {
		assertEquals(100, alien.getHitpoints());
	}
	
	@Test
	public final void endMove_Speed(){
		alien.endMove();
		final double DELTA = 1e-15;
		assertEquals(0,alien.getSpeedX(),DELTA);
	}

	@Test
	public final void endMove_Acceleration(){
		alien.endMove();
		final double DELTA = 1e-15;
		assertEquals(0,alien.getAccelerationX(),DELTA);
	}
	
	@Test
	public final void endMove_timeLastMoved(){
		alien.endMove();
		final double DELTA = 1e-15;
		assertEquals(0,alien.timeLastMovedX,DELTA);
	}
	
	@Test
	public final void endMove_isMoving(){
		alien.endMove();
		assertFalse(alien.isMovingX());
	}
	
	@Test
	public final void testEndJump(){
		alien.startJump();
		alien.endJump();
		assert fuzzyLessThanOrEqualTo(0, alien.getSpeedY());
		assert fuzzyEquals(-1000, alien.getAccelerationY());
		assertEquals(false, alien.isJumping());
	}
	
	@Test
	public final void testGetPositionX(){
		world.setCreatureInWorld(alien);
		world.startGame();
		assert fuzzyEquals(50, alien.getPositionX());
	}
	
	@Test
	public final void testGetPositionY(){
		world.setCreatureInWorld(alien);
		world.startGame();
		assert fuzzyEquals(70, alien.getPositionY());
	}
	
	

}
