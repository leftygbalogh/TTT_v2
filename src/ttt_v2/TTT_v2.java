/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt_v2;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lefty
 */
public class TTT_v2 {

    /**
     * @param args the command line arguments
     */
//  BOARD Methods
    public static String[] _InitBoard() {
        String board[] = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = (" ");
        }
        return board;
    }

    public static String[] _RandomInitBoard() {
        Random rand;
        rand = new Random();
        String board[] = new String[9];
        for (int i = 0; i < 8; i++) {
            board[i] = ("x");
            board[++i] = (" ");
            i = i + rand.nextInt(2);
            if (i < 9) {
                board[i] = ("o");
            }
        }

        board[8] = " ";

        return board;
    }

    public static void _PrintBoard(String[] board) {

        String header_row = "	      A   B   C";
        String top_border = "	    ╔═╧═╤═╧═╤═╧═╗";
        String first_row = "	   1╢ " + board[0] + " │ " + board[1] + " │ " + board[2] + " ║";
        String row_separator = "	    ╟───┼───┼───╢";
        String second_row = "	   2╢ " + board[3] + " │ " + board[4] + " │ " + board[5] + " ║";
        String third_row = "	   3╢ " + board[6] + " │ " + board[7] + " │ " + board[8] + " ║";
        String bottom_border = "	    ╚═══╧═══╧═══╝";

        System.out.println(header_row);
        System.out.println(top_border);
        System.out.println(first_row);
        System.out.println(row_separator);
        System.out.println(second_row);
        System.out.println(row_separator);
        System.out.println(third_row);
        System.out.println(bottom_border);
        System.out.println();
        System.out.println();

    }

    public static String _CreateNameRow(String name, String figure) {

        if (name.length() > 16) {
            name = figure;
        }

        String name_line = "	║";
        for (int i = 0; i < (int) (16 - name.length()) / 2; i++) {
            name_line = name_line + " ";
        }
        name_line = name_line + name;

        name_line = name_line + " Won";

        for (int i = name_line.length(); i < 22; i++) {
            name_line = name_line + " ";
        }
        name_line = name_line + "║";

        return name_line;
    }

    public static void _PrintWinningBoard(String name, String figure) {
        String header_row = "	      A   B   C";
        String top_border = "	    ╔═╧═╤═╧═╤═╧═╗";
        String first_row = "	   1╢ O │ X │ O ║";
        String banner_top = "	╔════════════════════╗";
        String congrats = "	║  CONGRATULATIONS!  ║";
        String name_row = "	║       Alexander Won║";
        String banner_bottom = "	╚════════════════════╝";
        String third_row = "	   3╢ O │ X │ O ║";
        String bottom_border = "	    ╚═══╧═══╧═══╝";

        name_row = _CreateNameRow(name, figure);

        System.out.println(header_row);
        System.out.println(top_border);
        System.out.println(first_row);
        System.out.println(banner_top);
        System.out.println(congrats);
        System.out.println(name_row);
        System.out.println(banner_bottom);
        System.out.println(third_row);
        System.out.println(bottom_border);
    }

//PLAYER Methods
    public static String[][] _SetPlayers() {

        String players[][] = new String[2][5];
        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Can I have the first player's name, please: ");
        players[player1][name] = keyboard.nextLine();

        players[player1][figure] = _GetFigure(keyboard);

        System.out.print("Can I have the opposing player's name: ");
        players[player2][name] = keyboard.nextLine();
        players[player2][figure] = (players[player1][figure].equals("o")) ? "x" : "o";
        return players;
    }

    public static String _GetFigure(Scanner keyboard) {
        System.out.print("Choose your figure. Enter x or o here: ");
        String tmp = keyboard.nextLine();
        if (tmp.equals("x")) {
            return tmp;
        }
        if (tmp.equals("o")) {
            return tmp;
        } else {
            _GetFigure(keyboard);
        }
        return tmp;
    }

//GAME Methods
    
    public static boolean _IsThatStepValid(String [] board, String step){
        int step_index = _ConvertStepIntoArrayIndex(step);
        if (board[step_index].equals(" ")){
            return true;
        }
        return false;
    }
    
    public static int _ConvertStepIntoArrayIndex(String step){
        String a="a1";
        String [] aa = a.split("");
        System.out.println(aa[0]);
        
        return -1;
    }
    
    public static void _PutStepInBoard(String[] board ,String step){
        //TODOOOO
    }
    
    public static boolean _IsThereAWinner(String last_step){
        //TODOOOOOO
        return false;
    }
    
    
    
    String game[] = new String[5];
    int game_status = 0; //init, ongoing, won, draw, replay
    int active_player = 1;

    public static void main(String[] args) {

//        String[] board = _InitBoard();
//        _PrintBoard(board);
//
//        String[] board2 = _RandomInitBoard();
//        _PrintBoard(board2);
//
//        _PrintWinningBoard("Jack", "o");
//        _PrintWinningBoard("Jackie", "o");
//        _PrintWinningBoard("Jack London", "o");
//        _PrintWinningBoard("Jack Gedeon Davis", "o");
//
//        _SetPlayers();
        
        _ConvertStepIntoArrayIndex("a2");
    }

}
