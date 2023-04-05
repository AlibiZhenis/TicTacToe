//package tictactoe;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        char[][] grid = new char[3][3];
        String input;

        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){;
                grid[i][j] = ' ';
            }
        }
        printGrid(grid);

        Scanner in = new Scanner(System.in);

        while(getState(grid) == 'N'){
            System.out.println("Make your move");
            input = in.nextLine();
            while(!makeMove(input, grid, 'X')){
                input = in.nextLine();
            }
            printGrid(grid);
            if(getState(grid) != 'N'){break;}

            System.out.println("Make your move");
            input = in.nextLine();
            while(!makeMove(input, grid, 'O')){
                input = in.nextLine();
            }
            printGrid(grid);
        }

        switch (getState(grid)){
            case 'X':{
                System.out.println("X wins");
                break;
            }
            case 'O':{
                System.out.println("O wins");
                break;
            }
            case 'D':{
                System.out.println("Draw");
                break;
            }
        }
    }

    public static void printGrid(char[][] grid){
        System.out.println("---------");
        for(int i=0; i<3;i++){
            System.out.print("|");
            for(int j=0; j<3;j++){
                System.out.print(' ');
                System.out.print(grid[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }
    public static boolean makeMove(String input, char[][] grid, char player){
        if(input.length()!=3||!Character.isDigit(input.charAt(0))||!Character.isDigit(input.charAt(2))){
            System.out.println("You should enter numbers!");
            return false;
        }
        int i = Character.getNumericValue(input.charAt(0));
        int j = Character.getNumericValue(input.charAt(2));
        if(i>3||j>3||i<1||j<1){
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (grid[i-1][j-1]!=' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        grid[i-1][j-1] = player;
        return true;
    }
    public static char getState(char[][] grid){
        ArrayList<Character> winners = getWinners(grid);
        int[] cnt = getCnt(grid);
        if (!winners.isEmpty()) {
            return winners.get(0);
        } else if (cnt[2]==0) {
            return 'D';
        } else {
            return 'N';
        }

    }

    public static ArrayList<Character> getWinners(char[][] grid){
        ArrayList<Character> winners = new ArrayList<>();
        for(int i=0; i<3;i++){
            if(grid[i][0]==grid[i][1]&&grid[i][1]==grid[i][2]&&grid[i][0]!=' '){
                winners.add(grid[i][0]);
            } else if (grid[0][i]==grid[1][i]&&grid[1][i]==grid[2][i]&&grid[0][i]!=' ') {
                winners.add(grid[0][i]);
            }
        }
        if(grid[0][0]==grid[1][1]&&grid[1][1]==grid[2][2]&&grid[0][0]!=' '){
            winners.add(grid[0][0]);
        }
        if(grid[2][0]==grid[1][1]&&grid[1][1]==grid[0][2]&&grid[0][2]!=' '){
            winners.add(grid[0][2]);
        }
        return winners;
    }

    public static int[] getCnt(char[][] grid){
        int[] cnt = new int[] {0,0,0};
        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){
                if(grid[i][j]=='X'){
                    cnt[0]++;
                } else if (grid[i][j]=='O') {
                    cnt[1]++;
                } else {
                    cnt[2]++;
                }
            }
        }
        return cnt;
    }
}
