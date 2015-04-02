/**
 * 
 */
package jumpingalien.model;

import java.util.ArrayList;

/**
 * @author Melanie Nijs
 *
 */
public class Tile{
	
	public Tile(int xT, int yT, int feature){
		this.xT = xT;
		this.yT = yT;
		this.feature = feature;
	}
	
	void setGeologicalFeature (int feature){
		this.feature = feature; 
	}
	
	int getGeologicalFeature(){
		return this.feature;
	}
	
	int[] getPostionTile(){
		int [] array = {xT, yT};
		return array;
	}

	private int xT;
	private int yT;
	private int feature = 0;
}
