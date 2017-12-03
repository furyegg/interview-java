package interview.binary;

public class Operations {
	public static void main(String[] args) {
		int add = add(100, 54);
		int sub = sub(124, 127);
		System.out.println(add);
		System.out.println(sub);
	}
	
	private static int add(int num1, int num2) {
		int m = num1;
		int n = num2;
		do {
			int x = m ^ n;
			int y = (m & n) << 1;
			m = x;
			n = y;
		} while (n != 0);
		return m;
	}
	
	private static int sub(int num1, int num2) {
		int temp = add(~num2, 1);
		int x = add(num1, temp);
		return x;
	}
}