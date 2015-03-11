/**
 * 
 */
package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.model.Mazub;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

	@Test
	public final void constructor_SingleCase() {
		Mazub alien = new Mazub(0, 0, sprites);
		assertEquals(0, alien.getPositionX());
		assertEquals(0, alien.getPositionY());
	}
	
	@Test
	public final void isValidPosition_TrueCase(){
		assertTrue (Mazub.isValidPosition(27,489));
	}
	
	@Test	//  verschillende gevallen 
	public final void isValidPosition_FalsePosX(){
		assertFalse (Mazub.isValidPosition(2000, 5));
	}
	
	@Test
	public final void isValidPosition_FalsePosY(){
		assertFalse (Mazub.isValidPosition(5,-200));
	}
	
	@Test
	public final void isValidPosition_FalseBoth(){
		assertFalse (Mazub.isValidPosition(-9,9000));
	}
	
	@Test
	public final void isValidSpeedX_TrueCase(){
		assertTrue (Mazub.isValidSpeedX(20));
	}
	
	@Test
	public final void isValidSpeedX_falseCase(){
		if (isDucking)
			assertFalse (Mazub.isValidSpeedX(200);
		else
			assertFalse (Mazub.isValidSpeedX(-400));
	}
	

}
