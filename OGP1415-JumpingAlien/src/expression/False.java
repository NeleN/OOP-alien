package expression;

public class False extends Boolean {

	@Override
	public E evaluate() {
		return false;
	}

}
