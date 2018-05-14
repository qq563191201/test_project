package number.Floats;

public class compareFloat {
	public static void main(String args[]){
		float f1 = 90.0f;
		float f2 = 90.0f;
		boolean value = (Float.floatToIntBits(f1) == Float.floatToIntBits(f2));
		System.out.println(value); 
	}
}
