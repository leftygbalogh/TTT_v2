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
    Scanner keyboard = new Scanner(System.in);

//  BOARD Methods
    public static String[] _InitBoard() {
        String board[] = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = (" ");
        }
        return board;
    }

    public static void _PrintBoard2(String[] board, String[][] players) {

        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";
        String[] tmp;

        tmp = players[player1][name].split("");
        String player1_first_initial = tmp[0];

        tmp = players[player2][name].split("");
        String player2_first_initial = tmp[0];
        String player1_figure = players[player1][figure];
        String player2_figure = players[player2][figure];

        String top_banner_border = "      ╔═══════════╦═══════════╗";
        String who_plays_row = "      ║ " + player1_first_initial + " plays " + player1_figure + " ║ " + player2_first_initial + " plays " + player2_figure + " ║";
        String top_banner_bottom = "      ╚═════╦═════╩═════╦═════╝";
        String header_row = "	    ║ A   B   C ║";
        String top_border = "	    ╠═╧═╤═╧═╤═╧═╣";
        String first_row = "	   1╢ " + board[0] + " │ " + board[1] + " │ " + board[2] + " ║";
        String row_separator = "	    ╟───┼───┼───╢";
        String second_row = "	   2╢ " + board[3] + " │ " + board[4] + " │ " + board[5] + " ║";
        String third_row = "	   3╢ " + board[6] + " │ " + board[7] + " │ " + board[8] + " ║";
        String bottom_border = "	    ╚═══╧═══╧═══╝";

        System.out.println(top_banner_border);
        System.out.println(who_plays_row);
        System.out.println(top_banner_bottom);
        System.out.println(header_row);
        System.out.println(top_border);
        System.out.println(first_row);
        System.out.println(row_separator);
        System.out.println(second_row);
        System.out.println(row_separator);
        System.out.println(third_row);
        System.out.println(bottom_border);

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

        System.out.print("\nCan I have the opposing player's name: ");
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
        }
        tmp = _GetFigure(keyboard);

        return tmp;
    }

//GAME Methods
    public static String _GetNextStep(String player_name, String[] board) {
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        String step = "x9";
        System.out.println(player_name+"... ");
        System.out.print("What's our vector Victor? ");
        boolean flag = false;

        do {
            if (flag) {
                System.out.print("Surely you can't be serious?");
            }
            step = keyboard.nextLine();
            flag = true;
        } while (_IsThatStepValid(board, step) == false);

        return step;
    }

    public static boolean _IsThatStepValid(String[] board, String step) {
        if (step.length()!=2){
            return false;
        }
        ///
        int step_index = _ConvertStepIntoArrayIndex(step);
        if (step_index == -1) {
            return false;
        }
        if (board[step_index].equals(" ")) {
            return true;
        }
        return false;
    }

    public static int _ConvertStepIntoArrayIndex(String step) {
        String column, row;
        String[] aa = step.split("");
        column = aa[0];
        row = aa[1];

        int index;

        switch (column) {
            case "a":
                index = 0;
                break;
            case "b":
                index = 1;
                break;
            case "c":
                index = 2;
                break;
            default:
                index = -1;
                break;
        }

        switch (row) {
            case "1":
                index = index + 0;
                break;
            case "2":
                index = index + 3;
                break;
            case "3":
                index = index + 6;
                break;
            default:
                index = -1;
                break;
        }

        return index;
    }

    public static String[] _PutStepInBoard(String[] board, String step, String player_figure) {
        int next_step = _ConvertStepIntoArrayIndex(step);
        board[next_step] = player_figure;
        return board;
    }

    public static void _PrintStartingPlayer(String[][] players) {
        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";

        int active_player = _GetActivePlayer(players);

        System.out.println("\nI just want to tell you both good luck. \nWe're all counting on you.\n");
        System.out.println(players[active_player][name] + ", you start.");
        System.out.println();

    }

    public static int _GetActivePlayer(String[][] players) {
        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";

        if (players[player1][status].equals("active")) {
            return player1;
        } else {
            return player2;
        }

    }

    public static String[][] _SetActivePlayer(String[][] players, int active_player) {
        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";

        if (active_player == player1) {
            players[player1][status] = "active";
            players[player2][status] = "waiting";
        } else {
            players[player1][status] = "waiting";
            players[player2][status] = "active";
        }

        return players;

    }

    public static String[][] _RandomSetActivePlayer(String[][] players) {
        Random rand = new Random();
        int active = rand.nextInt(2);
        players = _SetActivePlayer(players, active);
        return players;

    }

    public static boolean _IsThereAWinner(String last_step, String player_figure, String[] board) {

        if (_HorizontalWin(last_step, player_figure, board)) {
            return true;
        }

        if (_VerticalWin(last_step, player_figure, board)) {
            return true;
        }

        if (_NW_SEWin(last_step, player_figure, board)) {
            return true;
        }

        if (_SW_NEWin(last_step, player_figure, board)) {
            return true;
        }

        return false;
    }

    public static boolean _HorizontalWin(String last_step, String player_figure, String[] board) {

        int index = _ConvertStepIntoArrayIndex(last_step);
        int hits = 0;
        boolean won = false;
        switch (index) {
            case 0:
            case 1:
            case 2: {
                if (board[0].equals(player_figure)) {
                    hits++;
                }
                if (board[1].equals(player_figure)) {
                    hits++;
                }
                if (board[2].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
                break;

            }

            case 3:
            case 4:
            case 5: {
                if (board[3].equals(player_figure)) {
                    hits++;
                }
                if (board[4].equals(player_figure)) {
                    hits++;
                }
                if (board[5].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
                break;

            }

            case 6:
            case 7:
            case 8: {
                if (board[6].equals(player_figure)) {
                    hits++;
                }
                if (board[7].equals(player_figure)) {
                    hits++;
                }
                if (board[8].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
                break;

            }
        }

        return won;
    }

    public static boolean _VerticalWin(String last_step, String player_figure, String[] board) {
        int index = _ConvertStepIntoArrayIndex(last_step);
        int hits = 0;
        boolean won = false;

        switch (index) {
            case 0:
            case 3:
            case 6: {
                if (board[0].equals(player_figure)) {
                    hits++;
                }
                if (board[3].equals(player_figure)) {
                    hits++;
                }
                if (board[6].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
                break;

            }

            case 1:
            case 4:
            case 7: {
                if (board[1].equals(player_figure)) {
                    hits++;
                }
                if (board[4].equals(player_figure)) {
                    hits++;
                }
                if (board[7].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
                break;

            }

            case 2:
            case 5:
            case 8: {
                if (board[2].equals(player_figure)) {
                    hits++;
                }
                if (board[5].equals(player_figure)) {
                    hits++;
                }
                if (board[8].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
                break;

            }
        }

        return won;
    }

    public static boolean _NW_SEWin(String last_step, String player_figure, String[] board) {
        int index = _ConvertStepIntoArrayIndex(last_step);
        int hits = 0;
        boolean won = false;

        switch (index) {
            case 0:
            case 4:
            case 8: {
                if (board[0].equals(player_figure)) {
                    hits++;
                }
                if (board[4].equals(player_figure)) {
                    hits++;
                }
                if (board[8].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
            }
        }

        return won;
    }

    public static boolean _SW_NEWin(String last_step, String player_figure, String[] board) {
        int index = _ConvertStepIntoArrayIndex(last_step);
        int hits = 0;
        boolean won = false;

        switch (index) {
            case 2:
            case 4:
            case 6: {
                if (board[2].equals(player_figure)) {
                    hits++;
                }
                if (board[4].equals(player_figure)) {
                    hits++;
                }
                if (board[6].equals(player_figure)) {
                    hits++;
                }

                if (hits == 3) {
                    won = true;
                    break;
                }
            }
        }

        return won;
    }

    
    public static void _PrintDraw2(String[] board, String[][] players) {

        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";
        String[] tmp;

        tmp = players[player1][name].split("");
        String player1_first_initial = tmp[0];

        tmp = players[player2][name].split("");
        String player2_first_initial = tmp[0];
        String player1_figure = players[player1][figure];
        String player2_figure = players[player2][figure];

        String top_banner_border = "      ╔═══════════╦═══════════╗";
        String who_plays_row = "      ║ " + player1_first_initial + " plays " + player1_figure + " ║ " + player2_first_initial + " plays " + player2_figure + " ║";
        String top_banner_bottom = "      ╚═════╦═════╩═════╦═════╝";
        String header_row = "	    ║ A   B   C ║";
        String top_border = "	    ╠═╧═╤═╧═╤═╧═╣";
        String first_row = "	   1╢ " + board[0] + " │ " + board[1] + " │ " + board[2] + " ║";
        String row_separator = "	    ╟───┼───┼───╢";
        String second_row = "	   2╢ " + board[3] + " │ " + board[4] + " │ " + board[5] + " ║";
        String third_row = "	   3╢ " + board[6] + " │ " + board[7] + " │ " + board[8] + " ║";
        String bottom_border = "	    ╚═══╧═══╧═══╝";

        String banner_top = "   ╔═══════════════════════════════╗";
        String titans_row = "   ║ Clash of the Titans! It is a  ║";
        String row1 = "   ║   ___   ___    __    _        ║";
        String row2 = "   ║  | | \\ | |_)  / /\\  \\ \\    /  ║";
        String row3 = "   ║  |_|_/ |_| \\ /_/--\\  \\_\\/\\/   ║";
        String banner_bottom = "   ╚═══════════════════════════════╝";
        
        
        System.out.println(top_banner_border);
        System.out.println(who_plays_row);

        System.out.println(banner_top);
        System.out.println(titans_row);
        System.out.println(row1);
        System.out.println(row2);
        System.out.println(row3);
        System.out.println(banner_bottom);
        System.out.println(second_row);
        System.out.println(row_separator);
        System.out.println(third_row);
        System.out.println(bottom_border);
        System.out.println();


    }
    
    public static String[][] _SwitchActivePlayer(String[][] players) {
        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";

        if (players[player1][status].equals("active")) {
            players[player1][status] = "waiting";
            players[player2][status] = "active";
        } else {
            players[player1][status] = "active";
            players[player2][status] = "waiting";
        }

        return players;

    }

    public static void RunGame(String[] game, String board[], String[][] players) {
        int player1 = 0;
        int player2 = 1;
        int name = 0;
        int figure = 1;
        int status = 2;
        int won = 3;
        int score = 4;
        String figure1 = "o";
        String figure2 = "x";
        int step_counter = 0;
        Scanner keyboard = new Scanner(System.in);

        int game_status = 0; //init, ongoing, won, draw, replay
        int active_player = 1;

        if (game[game_status].equals("init")) {

            players = _RandomSetActivePlayer(players);

            String player_name = players[active_player][name];
            String player_figure = players[active_player][figure];
            _PrintStartingPlayer(players);
            _PrintBoard2(board, players);
        }

        if (game[game_status].equals("replay")) {
            board = _InitBoard();
            players = _RandomSetActivePlayer(players);

            String player_name = players[active_player][name];
            String player_figure = players[active_player][figure];
            _PrintStartingPlayer(players);
            _PrintBoard2(board, players);
        }

        do {
            active_player = _GetActivePlayer(players);

            String step = _GetNextStep(players[active_player][name], board);

            int step_index = _ConvertStepIntoArrayIndex(step);
            board = _PutStepInBoard(board, step, players[active_player][figure]);
            _PrintBoard2(board, players);
            if (_IsThereAWinner(step, players[active_player][figure], board)) {
                step_counter = 10;
                _PrintWinningBoard(players[active_player][name], players[active_player][figure]);
                break;
            }
            _SwitchActivePlayer(players);
            //switch active player

            step_counter++;
            if (step_counter == 9) {
                _PrintDraw2(board, players);
            }

        } while (step_counter < 9);

        if (step_counter == 10) {
            System.out.print("Do you wan to replay? y/n ");
            if (keyboard.nextLine().contains("y")) {
                game[game_status] = "replay";
                RunGame(game, board, players);
            } else {
                System.out.println(" Oh... Looks like I picked the wrong week to quit games.\n");

            }

        }

    }

    public static void main(String[] args) {

        String game[] = new String[5];
        int game_status = 0;
        game[game_status] = "init";
        String[] board;
        String players[][];
        board = _InitBoard();
        players = _SetPlayers();

        RunGame(game, board, players);

    }

}
