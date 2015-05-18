package expression;

public class IsAir extends Boolean<Boolean> {
	
	Public IsAir(Expression tile){
		this.tileX = (int)tile[0];
		this.tileY = (int)tile[1];
	}
	
	private int tileX;
	private int tileY;

}
