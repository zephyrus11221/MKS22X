import java.util.*;
import java.io.*;

public class BetterMaze{
    private class Node{
	private int r, c;
	private Node next, prev;
	
	public Node(int r1, int c1){
	    r = r1;
	    c = c1;
	}
	public int getR(){
	    return r;
	}
	public int getC(){
	    return c;
	}
	public void setNext(Node n){
	    next = n;
	}
	public void setPrev(Node n){
	    prev = n;
	}
	public void setValue(int r1, int c1){
	    r = r1;
	    c = c1;
	}
	public Node getNext(){
	    return next;
	}
	public Node getPrev(){
	    return prev;
	}
    }

    private char[][] maze;
    private int[]    solution;
    private int      startRow,startCol;
    private Frontier<Node> placesToGo;
    private boolean  animate;//default to false

   /**return a COPY of solution.
     *This should be : [x1,y1,x2,y2,x3,y3...]
     *the coordinates of the solution from start to end.
     *Precondition : one of the solveXXX methods has already been 
     * called (solveBFS OR solveDFS OR solveAStar)
     *(otherwise an empty array is returned)
     *Postcondition:  the correct solution is in the returned array
    **/
    public int[] solutionCoordinates(Node n){
        ArrayList<Integer> data = new ArrayList<Integer>();
	Node current = n;
	while (current!=null){
	    data.add(current.getR());
	    data.add(current.getC());
	    current=current.getPrev();
	}
	solution = new int[data.size()];
	for (int x = 0; x<data.size(); x++){
	    solution[x]=data.get(x);
	}
	return solution;
    }    


    /**initialize the frontier as a queue and call solve
    **/
    public boolean solveBFS(){  
        placesToGo = new FrontierQueue<Node>(new Node(startRow, startCol));
	//	placesToGo.add(new Node(startRow,startCol));
	System.out.println(placesToGo.hasNext());
	return solve();
    }   

   /**initialize the frontier as a stack and call solve
    */ 
    public boolean solveDFS(){  
	placesToGo = new FrontierStack<Node>();
	placesToGo.add(new Node(startRow,startCol));
	
	return solve();
    }    

   /**Search for the end of the maze using the frontier. 
      Keep going until you find a solution or run out of elements on the frontier.
    **/
    private boolean solve(){  
	while (placesToGo.hasNext()){
	    wait(100);
	    System.out.println(this);
	    Node hold = placesToGo.next();
	    int r = hold.getR();
	    int c = hold.getC();
	    if (maze[r][c]=='E'){
		solutionCoordinates(hold);
		System.out.println(printSolution());
		String otpt = Arrays.toString(solution);
		System.out.println(otpt);
		return true;
	    }
	    checkNeighbors(hold);
	}
	return false;
    }    
     
    public void checkNeighbors(Node n){
	int r = n.getR();
	int c = n.getC();
	Node _n;
	maze[r][c]='.';
	if (maze[r+1][c]==' '||maze[r+1][c]=='E'){
	    _n = new Node(r+1,c);
	    _n.setPrev(n);
	    //	    maze[r+1][c]='X';
	    placesToGo.add(_n);
	}
	if (maze[r-1][c]==' '||maze[r-1][c]=='E'){
	    _n = new Node(r-1,c);
	    _n.setPrev(n);
	    //	    maze[r-1][c]='X';
	    placesToGo.add(_n);
	}
	if (maze[r][c+1]==' '||maze[r][c+1]=='E'){
	    _n = new Node(r,c+1);
	    _n.setPrev(n);
	    //	    maze[r][c+1]='X';
	    placesToGo.add(_n);	
	}
	if (maze[r][c-1]==' '||maze[r][c-1]=='E'){
	    _n = new Node(r,c-1);
	    _n.setPrev(n);
	    //	    maze[r][c-1]='X';
	    placesToGo.add(_n);
	}
    }


   /**mutator for the animate variable  **/
    public void setAnimate(boolean b){
	animate=b;
    }    


    public BetterMaze(String filename){
	animate = false;
	int maxc = 0;
	int maxr = 0;
	startRow = -1;
	startCol = -1;
	//read the whole maze into a single string first
	String ans = "";
	try{
	    Scanner in = new Scanner(new File(filename));

	    //keep reading next line
	    while(in.hasNext()){
		String line = in.nextLine();
		if(maxr == 0){
		    //calculate width of the maze
		    maxc = line.length();
		}
		//every new line add 1 to the height of the maze
		maxr++;
		ans += line;
	    }
	}
	catch(Exception e){
	    System.out.println("File: " + filename + " could not be opened.");
	    e.printStackTrace();
	    System.exit(0);
	}
	System.out.println(maxr+" "+maxc);
	maze = new char[maxr][maxc];
	for(int i = 0; i < ans.length(); i++){
	    char c = ans.charAt(i);
	    maze[i / maxc][i % maxc] = c;
	    if(c == 'S'){
		startCol = i % maxc;
		startRow = i / maxc;
	    }
	}
    }







    private static final String CLEAR_SCREEN =  "\033[2J";
    private static final String HIDE_CURSOR =  "\033[?25l";
    private static final String SHOW_CURSOR =  "\033[?25h";
    private String go(int x,int y){
	return ("\033[" + x + ";" + y + "H");
    }
    private String color(int foreground,int background){
	return ("\033[0;" + foreground + ";" + background + "m");
    }

    public void clearTerminal(){
	System.out.println(CLEAR_SCREEN);
    }

    public void wait(int millis){
	try {
	    Thread.sleep(millis);
	}
	catch (InterruptedException e) {
	}
    }

    public String printSolution(){
	int maxr = maze.length;
	int maxc = maze[0].length;
	String ans = "";
	if(animate){
	    ans = "Solving a maze that is " + maxr + " by " + maxc + "\n";
	}
	for (int x = 0; x<solution.length; x+=2){
	    maze[solution[x]][solution[x+1]]='X';
	}
	for(int i = 0; i < maxc * maxr; i++){
	    if(i % maxc == 0 && i != 0){
		ans += color(37,40) + "\n";
	    }
	    char c =  maze[i / maxc][i % maxc];
	    if(c == '#'){
		ans += color(38,47)+c;
	    }
	    else{
		ans += color(33,40)+c;
	    }
	}
	//nice animation string
	if(animate){
	    return HIDE_CURSOR + go(0,0) + ans + color(37,40) +"\n"+ SHOW_CURSOR + color(37,40);
	}else{
	    return ans + color(37,40) + "\n";
	}
    }	

    public String toString(){
	int maxr = maze.length;
	int maxc = maze[0].length;
	String ans = "";
	if(animate){
	    ans = "Solving a maze that is " + maxr + " by " + maxc + "\n";
	}
	for(int i = 0; i < maxc * maxr; i++){
	    if(i % maxc == 0 && i != 0){
		ans += color(37,40) + "\n";
	    }
	    char c =  maze[i / maxc][i % maxc];
	    if(c == '#'){
		ans += color(38,47)+c;
	    }
	    else{
		ans += color(33,40)+c;
	    }
	}
	//nice animation string
	if(animate){
	    return HIDE_CURSOR + go(0,0) + ans + color(37,40) +"\n"+ SHOW_CURSOR + color(37,40);
	}else{
	    return ans + color(37,40) + "\n";
	}
    } 
    


       
    
    

}
