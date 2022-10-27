import java.lang.reflect.Array;

public class ConsoleOutput implements IOutput {


    public void showBoard(Board board)
    {
        char [] [] grid = board.getBoard();
        int xSize = BattleShip.xSize;
        int ySize = BattleShip.ySize;
        // System.out.println(xSize + "  " +    ySize);
        System.out.println(Array.getLength(grid));

        System.out.print("* -"); 
        for (int k = 0; k <= xSize; k++ )
        {
            System.out.print(k + "-"); 
        }
        System.out.print("\n"); 
        for (int i = 0; i < xSize; i++ )
        {
            // char u = (char)(i + 65); 
            System.out.print("-" + i + "-"); 
            for (int j = 0; j < ySize; j++ )
            {
                   
                if ((int)grid[i] [j] == 0)
                {
                System.out.print(grid[i] [j] + "|"); 
                }
                else 
                {
                System.out.print(grid[i] [j] + "|"); 
                }
        
            }
            System.out.print("\n"); 
            }
        }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void onHitMessage(Coordinate coords) {
        System.out.println("You hit your enemies target at " + coords.x + " and " + coords.y );
    }
    public void onMissedMessage() {
        System.out.println("You missed your shot at the enemies`s fleet");
    }
    public void onWonMessage(String playerName) {
        System.out.println("Player " + playerName + " you have successfully won the Game!");
    }

}  


