package Type;

public enum T {
	DOUBLE {
		public T getType(){
			return DOUBLE;
		}
	},
	
	BOOLEAN{
		public T getType(){
			return BOOLEAN;
		}
	},
	
	CREATURE {
		public T getType(){
			return CREATURE;
		}
	},
	
	DIRECTION {
		public T getType(){
			return DIRECTION;
		}
	};
	
	public abstract T getType();
}
