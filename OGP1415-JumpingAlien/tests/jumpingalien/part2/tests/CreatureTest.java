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
	public final void testGetHitpoints() {
		assertEquals(100, alien.getHitpoints());
	}
	
	@Test
	public final void testEndMoveCaseOneMovement(){
		alien.startMove(Direction.RIGHT);
		alien.endMove();
		assert fuzzyEquals(0, alien.getSpeedX());
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
