package interview.string;

public class ReverseString {
	public static void main(String[] args) {
		char[] s = "What are you doing?".toCharArray();
		reverse(s);
		// reverse2(s);
		System.out.println(s);
	}
	
	private static void reverse(char[] s) {
		int start = 0;
		int end = s.length - 1;
		while (start < end) {
			s[start] ^= s[end];
			s[end] ^= s[start];
			s[start] ^= s[end];
			++start;
			--end;
		}
	}
	
	private static void reverse2(char[] s) {
		int start = 0;
		int end = s.length - 1;
		while (start < end) {
			s[start] = (char) (s[start] + s[end]);
			s[end] = (char) (s[start] - s[end]);
			s[start] = (char) (s[start] - s[end]);
			++start;
			--end;
		}
	}
	
}