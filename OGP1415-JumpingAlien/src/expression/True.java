package expression;

public class True extends Boolean {
	
	

	@Override
	public E evaluate() {
		return true;
	}

}
