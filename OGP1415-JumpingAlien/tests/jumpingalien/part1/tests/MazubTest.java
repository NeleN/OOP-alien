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
	public final void getImageAtIndex(){
		assertEquals(sprites[1],alien.getImageAtIndex(1));
	}
	
	@Test
	public final void advanceTime_PositionX(){
		alien.setPositionX(5);
		alien.setSpeedX(100);
		alien.setAccelerationX(800);
		alien.advanceTime(0.1);
		final double DELTA = 1e-15;
		assertEquals(19,alien.getPositionX(),DELTA);
	}
	
	@Test
	public final void advanceTime_PositionY(){
		alien.setPositionY(100);
		alien.setSpeedY(80);
		alien.setAccelerationY(100);
		alien.advanceTime(0.2);
		final double DELTA = 1e-15;
		assertEquals(118,alien.getPositionY(),DELTA);
	}
	
	@Test
	public final void advanceTime_SpeedX(){
		alien.setSpeedX(50);
		alien.setAccelerationX(100);
		alien.advanceTime(0.2);
		final double DELTA = 1e-15;
		assertEquals(70,alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void advanceTime_SpeedY(){
		alien.setPositionY(22);
		alien.setSpeedY(500);
		alien.setAccelerationY(-200);
		alien.advanceTime(0.05);
		final double DELTA = 1e-15;
		assertEquals(490,alien.getSpeedY(),DELTA);
	}
	
	@Test
	public final void advanceTime_SpeedYZero(){
		alien.advanceTime(0.1);
		final double DELTA = 1e-15;
		assertEquals(0,alien.getSpeedY(),DELTA);
	}
	
	@Test
	public final void advanceTime_timeLastMoved(){
		alien.timeLastMovedX = 0.5;
		alien.advanceTime(0.15);
		final double DELTA = 1e-15;
		assertEquals(0.65,alien.timeLastMovedX,DELTA);
	}
	
	@Test
	public final void advanceTime_changedIndex(){
		alien.changedIndex = 0.03;
		alien.advanceTime(0.02);
		final double DELTA = 1e-15;
		assertEquals(0.05, alien.changedIndex,DELTA);
	}
	
	@Test
	public final void advanceTime_updateIndex(){
		alien.alternatingIndex = 5;
		alien.changedIndex = 0.1;
		alien.advanceTime(0.2);
		assertEquals(6,alien.alternatingIndex);
	}
	
	@Test
	public final void advanceTime_Width(){
		alien.advanceTime(0);
		assertEquals(alien.widthMazub, alien.getCurrentSprite().getWidth());
	}
	
	@Test
	public final void advanceTime_Height(){
		alien.advanceTime(0);
		assertEquals(alien.heightMazub, alien.getCurrentSprite().getHeight());
	}
	
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
		final double DELTA = 1e-15;
		assertEquals(0,alien.timeLastMovedX,DELTA);
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
		assertEquals(800, alien.getSpeedY(),DELTA);
	}
	
	@Test
	public final void startJump_isJumping(){
		alien.startJump();
		assertTrue(alien.isJumping());
	}
	
	@Test
	public final void endJump_PositiveSpeed(){
		alien.setSpeedY(500);
		alien.endJump();
		final double DELTA = 1e-15;
		assertEquals(0,alien.getSpeedY(),DELTA);
	}
	
	@Test
	public final void endJump_NegativeSpeed(){
		alien.setSpeedY(-500);
		alien.endJump();
		final double DELTA = 1e-15;
		assertEquals(-500,alien.getSpeedY(),DELTA);
	}
	
	@Test
	public final void endJump_Acceleration(){
		alien.endJump();
		final double DELTA = 1e-15;
		assertEquals(-1000,alien.getAccelerationY(),DELTA);
	}
	
	@Test
	public final void endJump_isJumping(){
		alien.endJump();
		assertFalse(alien.isJumping());
	}
	
	@Test
	public final void startDuck_MaxSpeed(){
		alien.startDuck();
		final double DELTA = 1e-15;
		assertEquals(100,alien.maxSpeedX,DELTA);
	}
	
	@Test
	public final void startDuck_isDucking(){
		alien.startDuck();
		assertTrue(alien.isDucking());
	}
	
	@Test
	public final void endDuck_MaxSpeed(){
		alien.endDuck();
		final double DELTA = 1e-15;
		assertEquals(300,alien.maxSpeedX,DELTA);
	}
	
	@Test
	public final void endDuck_isDucking(){
		alien.endDuck();
		assertFalse(alien.isDucking());
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
	
	@Test
	public final void setPositionX_ValidCase(){
		alien.setPositionX(1000);
		assertEquals(1000, alien.getPositionX());
	}
	
	@Test
	public final void setPositionX_FalseCase_Negative(){
		alien.setPositionX(-500);
		assertEquals(0, alien.getPositionX());		
	}
	
	@Test
	public final void setPositionX_FalseCase_OutOfRange(){
		alien.setPositionX(1500);
		assertEquals(0, alien.getPositionX());		
	}
	
	@Test
	public final void setPositionY_ValidCase(){
		alien.setPositionY(400);
		assertEquals(400, alien.getPositionY());
	}
	
	@Test
	public final void setPositionY_ValidCase_Negative(){
		alien.setPositionY(-500);
		assertEquals(0, alien.getPositionY());		
	}
	
	@Test
	public final void setPositionY_FalseCase_OutOfRange(){
		alien.setPositionY(800);
		assertEquals(0, alien.getPositionY());		
	}
	
	@Test
	public final void setHeight(){
		alien.setHeightMazub(20);
		assertEquals(20,alien.getHeightMazub());
	}
	
	@Test
	public final void setWidth(){
		alien.setWidthMazub(10);
		assertEquals(10,alien.getWidthMazub());
	}
	
	@Test
	public final void setSpeedX_ValidCase(){
		alien.setSpeedX(55);
		final double DELTA = 1e-15;
		assertEquals(55,alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void setSpeedX_FalseCase(){
		alien.setSpeedX(500);
		final double DELTA = 1e-15;
		assertEquals(0,alien.getSpeedX(),DELTA);
	}
	
	@Test
	public final void setSpeedY(){
		alien.setSpeedY(400);
		final double DELTA = 1e-15;
		assertEquals(400,alien.getSpeedY(),DELTA);
	}
	
	@Test
	public final void setAccelerationX(){
		alien.setAccelerationX(-200);
		final double DELTA = 1e-15;
		assertEquals(-200,alien.getAccelerationX(),DELTA);
	}
	
	@Test
	public final void setAccelerationY(){
		alien.setAccelerationY(100);
		final double DELTA = 1e-15;
		assertEquals(100,alien.getAccelerationY(),DELTA);
	}
	
	@Test
	public final void getCurrentSprite_Case0(){
		assertEquals(alien.getImageAtIndex(0),alien.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Case1(){
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
