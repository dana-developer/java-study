package prob05;

public class Sol05 {
	public static void main(String[] arg) {

		int array[] = {5, 9, 3, 8, 60, 20, 1};
		int count = array.length;

		System.out.println("Before Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}

		for(int i=0; i<count-1; i++) {
			for(int j=0; j<(count-1)-i; j++) {
				if(array[j] < array[j+1]) {
					int tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}

		System.out.println("\nAfter Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}
	}
}