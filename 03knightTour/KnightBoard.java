public class KnightBoard{
    public int[][] board;
    public int length, width, steps = 0;
    public int steps2 = 1; //for use in move2
    public int[]k = {0, 0};

    public String name(){
	return "Doan,Stephan";
    }

    public KnightBoard (int l, int w){
	board = new int[l+4][w+4];
	length = l;
	width = w;
	int offset=0;
	for (int x = 0; x<width+4; x++){
	    board[0][x]=-1;
	    board[1][x]=-1;
	    board[length+3][x]=-1;
	    board[length+2][x]=-1;
	}
	for (int x = 0; x<length+4; x++){
	    board[x][0]=-1;
	    board[x][1]=-1;
	    board[x][width+3]=-1;
	    board[x][width+2]=-1;
	}
    }
    public boolean move(int x, int y){
	if (board[x][y]!=0){
	    return false;
	}else{
	    steps++;
	    board[x][y]=steps;
	}
	if (steps==length*width){
	    return true;
	}
	if (move(x+2, y+1) || move(x-2, y+1) || move(x+2, y-1) || move(x-2, y-1) ||
	    move(x+1, y+2) || move(x-1, y+2) || move(x+1, y-2) || move(x-1, y-2)){
	    return true;
	}
	board[x][y]=0;
	steps--;
	return false;
    }

    public boolean move2(int x, int y, int step){
	if (steps2<length*width){
	    board[2][2]=1;
	    if (board[x+2][y+1]==0&&step<1){
		k[0]=x+2;
		k[1]=y+1;
		steps2++;
		board[x+2][y+1]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x+2][y+1]=0;
		    move2(k[0], k[1], 1);
		}
	    }else if (board[x-2][y+1]==0&&step<2){
		k[0]=x-2;
		k[1]=y+1;
		steps2++;
		board[x-2][y+1]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x-2][y+1]=0;
		    move2(k[0], k[1], 2);
		}
	    }else if (board[x+2][y-1]==0&&step<3){
		k[0]=x+2;
		k[1]=y-1;
		steps2++;
		board[x+2][y-1]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x+2][y-1]=0;
		    move2(k[0], k[1], 3);
		}
	    }else if (board[x-2][y-1]==0&&step<4){
		k[0]=x-2;
		k[1]=y-1;
		steps2++;
		board[x-2][y-1]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x-2][y-1]=0;
		    move2(k[0], k[1], 4);
		}
	    }else if (board[x+1][y+2]==0&&step<5){
		k[0]=x+1;
		k[1]=y+2;
		steps2++;
		board[x+1][y+2]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x+1][y+2]=0;
		    move2(k[0], k[1], 5);
		}
	    }else if (board[x-1][y+2]==0&&step<6){
		k[0]=x-1;
		k[1]=y+2;
		steps2++;
		board[x-1][y+2]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x-1][y+2]=0;
		    move2(k[0], k[1], 6);
		}
	    }else if (board[x+1][y-2]==0&&step<7){
		k[0]=x+1;
		k[1]=y-2;
		steps2++;
		board[x+1][y-2]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x+1][y-2]=0;
		    move2(k[0], k[1], 7);
		}
	    }else if (board[x-1][y-2]==0&&step<8){
		k[0]=x-1;
		k[1]=y-2;
		steps2++;
		board[x-1][y-2]=steps2;
		if (!move2(k[0], k[1], 0)){
		    k[0]=x;
		    k[1]=y;
		    steps2--;
		    board[x-1][y-2]=0;
		    move2(k[0], k[1], 8);
		}
	    }   
	}
	if (steps2==length*width){
	    board[2][2]=1;
	    return true;
	}
	board[2][2]=0;
	return false;
    }

    public boolean solve(){
	return move2(2,2,0);
    }

    public String toString(){
	String otpt = "";
	for (int x = 0; x<length+4; x++){
	    for (int y = 0; y<width+4; y++){
		if (board[x][y]>9||board[x][y]<0){
		    otpt+= " ";
		}else{
		    otpt+= "  ";
		}
		otpt += board[x][y];
	    }
	    otpt+="\n";
	}
	return otpt;
    }
    public String printSolution(){
	solve();
	String otpt = "";
	for (int x = 2; x<length+2; x++){
	    for (int y = 2; y<width+2; y++){
		if (board[x][y]>9||board[x][y]<1){
		    otpt+= " ";
		}else{
		    otpt+= "  ";
		}
		otpt += board[x][y];
	    }
	    otpt+="\n";
	}
	return otpt;
    }
}