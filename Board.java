public class Board 
{

    private int xSize; 
    private int ySize; 
    private static final char deadShipMarker = 'd'; // to hit marker
private static final char activeShipMarker = 's'; // transforms to unknown
private static final char emptyFieldMarker = ' '; // transforms to unknown
private static final char missedDefenderMarker = '0'; 

//TODO add a target handler function that manages the different hit types 

    
    private static final char hitMarker = 'x'; // to deadShip marker
private static final char missedAttackerMarker = 'o'; //transforms to missed marker
private static final char unknownMarker = '?'; // transforms to nothing

    
    private char [] [] board; 
    private boolean debug; 
    private IOutput output; 


    public Board(IOutput output)
    {
        this.xSize = BattleShip.xSize; 
        this.ySize = BattleShip.ySize; 
        this.board = new char [xSize] [ySize]; 
        this.debug = BattleShip.debug; 
        this.output = output; 
    }

    public char [] [] getBoard()
    {
        return this.board; 
    }

    public int xSize()
    {
        return this.xSize; 
    }

    public int ySize()
    {
        return  this.ySize; 
    }

    public void fillBoard(char filler)
    {
        for (int i = 0; i < this.xSize; i++ )
        {
          for (int j = 0; j < this.ySize; i++ )
            {
            board [i] [j] = filler; 
            }
         }
        }

    public void showBoard()
    {
        this.output.showBoard(this); 
    }
       
    //! this function is unused !!! ?   
    public boolean indexChecker(Coordinate cord)
    {
        int xIndexRange = this.xSize - 1; 
        int yIndexRange = this.ySize - 1; 
        boolean result = cord.x <= xIndexRange && cord.y <= yIndexRange; 
        if (this.debug)
        {
          System.out.print("index checker" + xIndexRange + " " + yIndexRange + " result is " + result); 
        }
         return result; 
        }

    public void writeEntry(Coordinate cord, char entry)
    {
        try 
        {
            this.board [cord.x] [cord.y] = entry; 
            //System.out.println(entry);
        }catch (Exception e)
        {
            throw e; 
        }
    }

    public char getEntry(Coordinate cord)
    {
        try 
        {
          char value = this.board [cord.x] [cord.y]; 
          System.out.println(value); 
          return value; 
        }catch (Exception e)
        {
          throw e; 
        }
      }
      
    private static char [] [] transformSelfToEnemy(char [] [] board)
    {
        char [] [] returnBoard = new char [BattleShip.xSize] [BattleShip.ySize]; 
        for (int i = 0; i < board.length; i++ )
        {
            for (int j = 0; j < board[0].length; j++ )
            {
                char local = board[i] [j]; 
                switch (local)
                {
                    case deadShipMarker:
                        returnBoard[i] [j] = hitMarker; 
                        break; 
                
                    case activeShipMarker:
                        returnBoard[i] [j] = unknownMarker; 
                        break; 

                    case emptyFieldMarker:
                        returnBoard[i] [j] = unknownMarker; 
                        break; 
                        
                    case missedDefenderMarker:
                        returnBoard[i] [j] = missedAttackerMarker; 
                        break; 

                
                    default:
                        throw(new Error("Board Value could not be resolved")); 
                }
                
            }
        }



        return returnBoard; 
    }

}

 