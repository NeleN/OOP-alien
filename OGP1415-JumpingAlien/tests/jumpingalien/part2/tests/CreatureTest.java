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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
	World world = new World(70, 20, 12, 1024, 769, 18, 9);
	
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
		Mazub alien = new Mazub(0, 0, sprites, 300, 100);
		assertEquals(100, alien.getHitpoints());
	}
	
	@Test
	public final void testEndMoveCaseOneMovement(){
		Mazub alien = new Mazub(0, 0, sprites, 300, 100);
		alien.startMove(Direction.RIGHT);
		alien.endMove();
		assert fuzzyEquals(0, alien.getSpeedX());
	}
	
	@Test
	public final void testEndJump(){
		Mazub alien = new Mazub(0, 0, sprites, 300, 100);
		alien.startJump();
		alien.endJump();
		assert fuzzyLessThanOrEqualTo(0, alien.getSpeedY());
		assert fuzzyEquals(-1000, alien.getAccelerationY());
		assertEquals(false, alien.isJumping());
	}
	
	@Test
	public final void testGetPositionX(){
		Mazub alien = new Mazub(50, 70, sprites, 300, 100);
		world.setCreatureInWorld(alien);
		world.startGame();
		assert fuzzyEquals(50, alien.getPositionX());
	}
	
	@Test
	public final void testGetPositionY(){
		Mazub alien = new Mazub(50, 70, sprites, 300, 100);
		world.setCreatureInWorld(alien);
		world.startGame();
		assert fuzzyEquals(70, alien.getPositionY());
	}
	
	@Test
	public final void testGetHeightOfSprite(){
		// TODO
	}
	
	@Test
	public final void testGetWidthOfSprite(){
		// TODO
	}
	
	
	

}
