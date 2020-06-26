import java.util.ArrayDeque;
import java.util.Queue;

public class RadixSortWithQueues {

	public static int[] radixSort(int[] array) { 
		
		int max = array[0]; 
		for(int i=1; i < array.length; i++) { 
			if (array[i] > max){ 
				max = array[i]; 
			} 
		} 

		int time = 0; 
		while(max > 0) { 
			max /= 10; 
			time++;
		}

		Queue<Integer>[] queue = new ArrayDeque[10]; 
		for(int i=0; i < 10; i++) { 
			queue[i]=new ArrayDeque<Integer>();
		}

		for(int i = 0; i < time; i++) {
			for(int j = 0; j < array.length; j++) { 
				queue[array[j] % (int)Math.pow(10, i+1) / (int)Math.pow(10, i)].offer(array[j]); 
			} 
			int count=0;
			for (int k = 0; k < 10; k++) { 
				while (queue[k].size() > 0){ 
					array[count]=(Integer) queue[k].poll(); 
					count++; 
				} 
			} 
		} 
		return array;
	} 

	public static void main(String[] args) {

		int[] array = {1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713};

		int[] newArray = radixSort(array);
		for (int i = 0; i < newArray.length; i++) {
			System.out.print(newArray[i] + " ");
		}
	}

}
