import java.util.Random;
import java.util.Arrays;
public class Quick{
    
    public static String name(){
	return "7,Doan,Stephan";
    }

    public static int partition(int[] data, int left, int right){
	Random RNG = new Random();
	int spot = RNG.nextInt(right-left)+left;
	int value = data[spot];
	data[spot]=data[data.length-1];
	data[data.length-1]=value;
	int x = 0;
	int y = data.length-2;
	int hold = 0;
	while(x!=y){
	    if(data[x]>value){
		hold = data[x];
		data[x] = data[y];
		data[y] = hold;
		y--;
	    }else{
		x++;
	    }
	}
	if(data[y]>value){
	    data[data.length-1]=data[y];
	    data[y]=value;
	    return y;
	}else{
	    data[data.length-1]=data[y+1];
	    data[y+1]=value;
	    System.out.println(Arrays.toString(data));
	    return y+1;
	}
    }

    public static int quickselect(int[]data, int k){
	return quickselect(data, k, 0, data.length-1);
    }

    public static int quickselect(int[]data, int k, int left, int right){
	int index = partition(data, left, right);
	if (k==index){
	    return data[k];
	}
	if (k<index){
	    return quickselect(data, k, left, index);
	}
	return quickselect(data, k, index, right);
    }

    public static void main(String[]args){
	int[] a = new int[]{9, 39, 10, -3, 13, 56, 98, 70};
	int[] b = new int[]{3, 5, 89, 76, 20, -18, 4, 205};
	System.out.println(partition(a, 0, 7));
	System.out.println(Arrays.toString(a));
	System.out.println("----------------------------------------");
	System.out.println(quickselect(b, 2));
    }
}