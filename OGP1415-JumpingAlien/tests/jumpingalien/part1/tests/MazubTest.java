/**
 * 
 */
package jumpingalien.part1.tests;

import static org.junit.Assert.*;
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
	public final void isValidPosition_FalsePosX_outOfRange(){
		assertFalse (alien.isValidPosition(2000, 5));
	}
	
	@Test
	public final void isValidPosition_FalsePosX_negative(){
		assertFalse (alien.isValidPosition(-5, 5));
	}
	
	@Test
	public final void isValidPosition_FalsePosY_outOfRange(){
		assertFalse (alien.isValidPosition(2, 1000));
	}
	
	@Test
	public final void isValidPosition_FalsePosY_negative(){
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
	public final void isValidSpeedX_falseCase(){
		if (alien.isDucking())
			assertFalse (alien.isValidSpeedX(200));
		else
			assertFalse (alien.isValidSpeedX(-400));
	}
	
	@Test
	public final void update

}
