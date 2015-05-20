/**
 * 
 */
package jumpingalien.model;

import program.Program;
import jumpingalien.util.Sprite;

/**
 * @author Nele
 *
 */
public class Buzam extends Mazub {

	public Buzam(int positionX, int positionY, Sprite[] sprites,
			double maxSpeedX, int hitpoints, Program program) {
		super(positionX, positionY, sprites, maxSpeedX, 500, program);
	}

}
