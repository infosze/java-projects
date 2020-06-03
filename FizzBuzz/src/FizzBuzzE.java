
public class FizzBuzzE {
	
	static int NUMBERS = 100;
	static int FIRST_DEALER = 3;
	static int SECOND_DEALER = 5;
	static String FIRST_WORD = " Fizz";
	static String SECOND_WORD = " Buzz";

	public static void main(String[] args) {
		
		printTwoWord();

	}
	
	static void printTwoWord() {
		int i;
		for(i = 1; i <= NUMBERS; i++) {
			System.out.print(i);
			if(i % FIRST_DEALER == 0) {
				System.out.print(FIRST_WORD);
			}
			if(i % SECOND_DEALER == 0) {
				System.out.print(SECOND_WORD);
			}
			System.out.println();
		}
	}
	
}
