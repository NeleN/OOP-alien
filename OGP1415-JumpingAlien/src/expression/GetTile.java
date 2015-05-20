package expression;

public class GetTile extends Expression <int []>{
	
	public GetTile(Expression <Integer> x, Expression <Integer> y){
		pixelX = (Integer) x.evaluate();
		pixelY = (Integer) y.evaluate();
	}
	
	@Override
	public int[] evaluate(){
		tileX = this.getProgram().getUser().getWorld().getTileNbX(pixelX);
		tileY = this.getProgram().getUser().getWorld().getTileNbY(pixelY);
		int[] array = {tileX, tileY};
		return array;
	}
	
	int pixelX;
	int pixelY;
	int tileX;
	int tileY;

}
