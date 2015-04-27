package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {

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

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////

	Sprite[] sprites = TestUtils.spriteArrayForSize(2, 2);
	World world = new World(70, 20, 12, 1024, 769, 18, 9);
	Mazub alien = new Mazub(0, 0, sprites, 300, 100);
	
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
	
	@Test
	public final void testAdvanceTime(){
		
	}
	
	@Test
	public final void testIsGameOverFalseCase(){
		
	}
	
	@Test
	public final void testIsGameOverTrueCase1(){
		
	}
	
	@Test
	public final void testIsGameOverTrueCase2(){
		
	}
	
	@Test
	public final void testPlayerOnTargetTileFalseCase(){
		
	}
	
	@Test
	public final void testPlayerOnTargetTileTrueCase(){
		
	}
	
	@Test
	public final void testGetTilePositions(){
		
	}
	
	@Test
	public final void testGetGeologicalFeature(){
		
	}
	
	@Test
	public final void testSetGeologicalFeature(){
		
	}
	
	@Test
	public final void testGetSlimesInWorld(){
		
	}
	
	@Test
	public final void testGetPlantsInWorld(){
		
	}

	@Test
	public final void testGetSharksInWorld(){
		
	}
	
	@Test
	public final void testGetMazubsInWorld(){
		
	}
	
	@Test
	public final void testGetVisibleWindow(){
		
	}
	
	@Test
	public final void testGetTileLength(){
		
	}
	
	@Test
	public final void testSetCreatureInWorld(){
		
	}
	
	
}
