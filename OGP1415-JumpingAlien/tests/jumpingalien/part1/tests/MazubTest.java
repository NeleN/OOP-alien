/**
 * 
 */
package jumpingalien.part1.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Direction;
import jumpingalien.model.Mazub;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.*;

import org.junit.*;

/**
 * @author 	Nele Nauwelaers and Melanie Nijs
 * @version	11/03
 *
 */
public class MazubTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	
////////////////////////////////////////////////////////////////////////////////
	
	Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
	Mazub alien = new Mazub(0, 0, sprites);
	
	
	@Test
	public final void constructor_SingleCase() {
		Mazub alien = new Mazub(0, 0, sprites);
		assertEquals(0, alien.getPositionX());
		assertEquals(0, alien.getPositionY());
	}
	
	@Test
	public final void isValidPosition_TrueCase(){
		assertTrue (alien.isValidPosition(27,489));
	}
	
	@Test
	public final void isValidPosition_FalsePosX_OutOfRange(){
		assertFalse (alien.isValidPosition(2000, 5));
	}
	
	@Test
	public final void isValidPosition_FalsePosX_Negative(){
		assertFalse (alien.isValidPosition(-5, 5));
	}
	
	@Test
	public final void isValidPosition_FalsePosY_OutOfRange(){
		assertFalse (alien.isValidPosition(2, 1000));
	}
	
	@Test
	public final void isValidPosition_FalsePosY_Negative(){
		assertFalse (alien.isValidPosition(5,-200));
	}
	
	@Test
	public final void isValidPosition_FalseBoth(){
		assertFalse (alien.isValidPosition(-9,9000));
	}
	
	@Test
	public final void isValidSpeedX_TrueCase(){
		assertTrue (alien.isValidSpeedX(20));
	}
	
	@Test
	public final void isValidSpeedX_FalseCase(){
		if (alien.isDucking())
			assertFalse (alien.isValidSpeedX(200));
		else
			assertFalse (alien.isValidSpeedX(-400));
	}
	
	// Test advanceTime
	
	@Test
	public final void updateIndex_NormalCase(){
		alien.alternatingIndex = 5;
		alien.updateIndex();
		assertEquals (6, alien.alternatingIndex);
	}
	
	@Test
	public final void updateIndex_SpecialCase1(){
		alien.alternatingIndex = 9;
		alien.updateIndex();
		assertEquals(10, alien.alternatingIndex);
	}
	
	@Test
	public final void updateIndex_SpecialCase2(){
		alien.alternatingIndex = 10;
		alien.updateIndex();
		assertEquals(0, alien.alternatingIndex);
	}

	
	@Test
	public final void formulePositionX_NormalCase(){
		alien.setSpeedX(200);
		alien.setAccelerationX(90);
		alien.formulePositionX(0.1);
		assertEquals((int)(20.45),alien.travelledDistanceX);
	}
	
	@Test
	public final void formulePositionY_NormalCase(){
		alien.setSpeedY(0);
		alien.setAccelerationY(-1000);
		alien.formulePositionY(0.1);
		assertEquals((int)(-5),alien.travelledDistanceY);
	}
	
	@Test
	public final void startMove_isMoving(){
		alien.startMove(Direction.RIGHT);
		assertTrue(alien.isMovingX());
	}
	
	@Test
	public final void startMove_indexChanged(){
		alien.startMove(Direction.RIGHT);
		final double DELTA = 1e-15;
		assertEquals(0,alien.changedIndex,DELTA);
	}
	
	@Test
	public final void startMove_alternatingIndex(){
		alien.startMove(Direction.RIGHT);
		assertEquals(0,alien.alternatingIndex);
	}
	
	@Test
	public final void startMove_Speed_Left(){
		alien.startMove(Direction.LEFT);
		final double DELTA = 1e-15;
		assertEquals(-100,alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void startMove_Speed_Right(){
		alien.startMove(Direction.RIGHT);
		final double DELTA = 1e-15;
		assertEquals(100,alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void startMove_Acceleration_Left(){
		alien.startMove(Direction.LEFT);
		final double DELTA = 1e-15;
		assertEquals(-90,alien.getAccelerationX(),DELTA);
	}
	
	@Test
	public final void startMove_Acceleration_Right(){
		alien.startMove(Direction.RIGHT);
		final double DELTA = 1e-15;
		assertEquals(90,alien.getAccelerationX(),DELTA);
	}
	
	@Test
	public final void startMove_LastDirection_Left(){
		alien.startMove(Direction.LEFT);
		assertEquals(-1,alien.lastDirection);
	}
	
	@Test
	public final void startMove_LastDirection_Right(){
		alien.startMove(Direction.RIGHT);
		assertEquals(1,alien.lastDirection);
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
		assertEquals(0,alien.timeLastMovedX);
	}
	
	@Test
	public final void endMove_isMoving(){
		alien.endMove();
		assertFalse(alien.isMovingX());
	}
	
	@Test
	public final void startJump_Speed(){
		alien.startJump();
		final double DELTA = 1e-15;
		assertEquals(800, alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void startJump_isJumping(){
		alien.startJump();
		assertTrue(alien.isJumping());
	}
	
	
}
