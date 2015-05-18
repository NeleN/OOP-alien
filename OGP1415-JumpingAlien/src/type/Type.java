package type;

public enum Type {
	DOUBLE {
		public Type getType(){
			return DOUBLE;
		}
	},
	
	BOOLEAN{
		public Type getType(){
			return BOOLEAN;
		}
	},
	
	CREATURE {
		public Type getType(){
			return CREATURE;
		}
	},
	
	DIRECTION {
		public Type getType(){
			return DIRECTION;
		}
	};
	
	public abstract Type getType();
}
