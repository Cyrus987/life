import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y) {
        // Construct a 2D array with the given x and y size.
        board = new int[10][10];
    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    
    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    // Step the simulation forward one turn.
    public void step() {
        int[][] newBoard = new int[board.length][board[0].length];
    
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                int neighbors = countNeighbors(x, y);
                if (board[x][y] == 1) { 
                    if (neighbors < 2 || neighbors > 3) {
                        newBoard[x][y] = 0; 
                    } else {
                        newBoard[x][y] = 1; 
                    }
                } else { 
                    if (neighbors == 3) {
                        newBoard[x][y] = 1; 
                    }
                }
            }
        }
    
        
        print(); 
    }


    public int countNeighbors(int x, int y) {
        int count = 0;
        for (int x2 = -1; x2 <= 1; x2++) {
            for (int y2 = -1; y2 <= 1; y2++) {
                if (x2 == 0 && y2 == 0) continue; 
                count += get(x + x2, y + y2); 
            }
        }
        return count;
    }

    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit= board[0].length;
        return board[(x+xLimit)%xLimit][(y+yLimit)%yLimit];
    }

    public int[][] get()
    {
        return board;
    }

    // Test helper to print the current state
    public void print(){
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y%10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x%10);
            for (int y=0; y<board[x].length; y++)
            {
                if (board[x][y] == 1)
                {
                    System.out.print("⬛");
                }
                else
                {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
