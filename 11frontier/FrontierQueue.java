import java.util.*;
public class FrontierQueue<T> implements Frontier<T>{
    public ArrayDeque<T> data = new ArrayDeque<T>();

    public void add(T element){
        data.add(element);
    }
    
    public T next(){
	return data.poll();
    }
    
    public boolean hasNext(){
	return !isEmpty();
    }

}