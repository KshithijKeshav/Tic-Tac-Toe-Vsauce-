import java.util.Random;
import java.util.Scanner;
class Grid{
    public char[] matrix=new char[9];
    public int ptr=-1;
    public Grid(){
        for(int i=0;i<9;i++){
            matrix[i]=' ';
            ptr=0;
        }
    }
    public int grid_place(){
        Scanner sc=new Scanner(System.in);
        int flag=0;
        while(flag==0){
            System.out.println("Enter Box to place in ");
            this.ptr=sc.nextInt();
            if(matrix[ptr-1]!=' '){
                System.out.println("Occupied");
                continue;
            }
            flag=1;
        }
        matrix[ptr-1]='X';
        return ptr;
    }
    public void grid_place(int index,char C){
        matrix[index]=C;
        return;
    }
    public int comp_place(){
        Random rand=new Random();
        while (true) {
            int rn=rand.nextInt(9);
            if(matrix[rn]==' '){
                matrix[rn]='O';
                System.out.println("Computer played in box no : "+(rn+1));
                return rn+1;
            }
        }
    }
    public boolean grid_full(){
        for(int i=0;i<9;i++){
            if(matrix[i]==' '){
                return false;
            }
        }
        return true;
    }
    public boolean win_check(){
        for(int i=0;i<7;i+=3){
            if((matrix[i]==matrix[i+1])&&(matrix[i+1]==matrix[i+2])&& matrix[i]!=' '&& matrix[i]!='F'){
                ptr=i;
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(matrix[i]==matrix[i+3]&&matrix[i+3]==matrix[i+6]&&matrix[i]!=' '&& matrix[i]!='F'){
                ptr=i;
                return true;
            }
        }
        if(matrix[0]==matrix[4]&&matrix[4]==matrix[8]&& matrix[0]!=' '&& matrix[0]!=' '){
            ptr=0;
            return true;
        }
        if(matrix[2]==matrix[4]&&matrix[4]==matrix[6]&&matrix[4]!=' '&&matrix[4]!='F'){
            ptr=2;
            return true;
        }
        return false;
    }    
}

class Tic_Tac_Toe{
    
    static void print_grid(Grid[] grid){
        int c;
        System.out.println("\n---------------------------------------------------");
        for(int i=0;i<9;i=i+3){
            c=i;
            System.out.print("|-|");
            while (c<i+3) {
                for(int j=0;j<3;j++){
                    if(j==2)
                    System.out.print(grid[c].matrix[j]);
                    else
                    System.out.print(grid[c].matrix[j]+"  |  ");
                }
                System.out.print("|-|");
                c+=1;
            }
            System.out.print("\n|-|");
            c=i;
            while (c<i+3) {
                for(int j=3;j<6;j++){
                    if(j==5)
                    System.out.print(grid[c].matrix[j]);
                    else
                    System.out.print(grid[c].matrix[j]+"  |  ");
                }
                System.out.print("|-|");
                c++;
            }
            System.out.print("\n|-|");
            c=i;
            while (c<i+3) {
                for(int j=6;j<9;j++){
                    if(j==8)
                    System.out.print(grid[c].matrix[j]);
                    else
                    System.out.print(grid[c].matrix[j]+"  |  ");
                }
                System.out.print("|-|");
                c++;
            }
            System.out.println("\n---------------------------------------------------");
        }
    }
    static char status(Grid grid){
        if(grid.win_check()){
            if(grid.matrix[grid.ptr]=='X'){
                return 'X';
            }else{
                return 'O';
            }
        }else if(grid.grid_full()){
            return 'F';
        }
        else{
            return ' ';
        }
    }
    public static void main(String[] args) {
        Grid[] grid=new Grid[9];
        for(int i=0;i<9;i++){
            grid[i]=new Grid();
        }
        Grid Result=new Grid();
        Scanner sc=new Scanner(System.in);
        Random rnd=new Random();
        int flag=1;
        int index=1,ind=0;
        while (true) {
            print_grid(grid);
            if(Result.win_check()){
                System.out.println(Result.matrix[Result.ptr]+" WINS");
                break;
            }
            if(Result.grid_full()){
                System.out.println("ITS A TIE");
                break;
            }
            if(flag==1){
                System.out.println("Enter which board u want to play in");
                index=sc.nextInt();
                if(Result.matrix[index-1]!=' ')
                continue;
                ind=grid[index-1].grid_place();
                Result.grid_place(index-1,status(grid[index-1]));
                if(Result.matrix[index-1]=='X'){
                    System.out.println("\n\nU WON THIS BOX\n\n");
                }
                index=ind;
                Result.grid_place(index-1,status(grid[index-1]));
                if(Result.matrix[index-1]!=' '){
                    flag=2;
                }else{
                    flag=0;
                }
                continue;
            }
            if(flag==2){
                System.out.print("Computer choose BOARD :");
                while (true) {
                    index=rnd.nextInt(9)+1;
                    if(Result.matrix[index-1]==' ')break;
                }
                System.out.println(index);
                ind=grid[index-1].comp_place();
                Result.grid_place(index-1,status(grid[index-1]));
                if(Result.matrix[index-1]=='O'){
                    System.out.println("\n\n COMPUTER WON THIS BOX\n\n");
                }
                index=ind;
                Result.grid_place(index-1,status(grid[index-1]));
                if(Result.matrix[index-1]!=' ')flag=1;
                else flag=-1;
                continue;
            }
            if(flag<=0){
                System.out.println("Playing in Board : "+index);
                if(flag==-1){
                    ind=grid[index-1].grid_place();
                    Result.grid_place(index-1,status(grid[index-1]));
                    if(Result.matrix[index-1]=='X')System.out.println("\n\nU WON THIS BOX\n\n");
                    index=ind;
                    Result.grid_place(index-1,status(grid[index-1]));
                    if(Result.matrix[index-1]!=' '){
                        flag=2;
                    }else{
                        flag=0;
                    }
                continue;
                }
                if(flag==0){
                    ind=grid[index-1].comp_place();
                    Result.grid_place(index-1,status(grid[index-1]));
                    if(Result.matrix[index-1]=='O')System.out.println("\n\nCOMPUTER WON THIS BOX\n\n");
                    index=ind;
                    Result.grid_place(index-1,status(grid[index-1]));
                    if(Result.matrix[index-1]!=' ')flag=1;
                    else flag=-1;
                    continue;
                }
            }
        }
    }
}