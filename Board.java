public class Board 
{

    private int xSize; 
    private int ySize; 

//for own use, different symbols for the own ship 
private static final char deadShipMarker = 'H'; // to hit marker
private static final char activeShipMarker = 'S'; // transforms to unknown
private static final char emptyFieldMarker = ' '; // transforms to unknown
private static final char missedDefenderMarker = 'O'; //when the attacker has hit the field, but there was no ship 


    
private static final char hitMarker = 'x'; // to deadShip marker
private static final char missedAttackerMarker = 'o'; //transforms to missed marker
private static final char unknownMarker = '?'; // transforms to nothing

    
    private char [] [] board; 
    private boolean debug; 
    private IOutput output; 

// Constructor for the board class 
    public Board(IOutput output)
    {
        this.xSize = BattleShip.xSize; 
        this.ySize = BattleShip.ySize; 
        this.board = new char [xSize] [ySize]; 
        this.fillBoard(emptyFieldMarker); 
        this.debug = BattleShip.debug; 
this.output = output; 
    }

// ------------------------------------------------------PUBLIC WRAPPERS FOR INTERN VARIABLES---------------------------------------------------
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

    
    public void showBoard()
    {
        this.output.showBoard(this); 
    }
    
    
    //---------------------------------------------------------UTIL FUNCTIONS------------------------------------------------------------------- 

    private void fillBoard(char filler)
    {
        for (int i = 0; i < this.xSize;  ++ i)
        {
          for (int j = 0; j < this.ySize;  ++ j)
            {
                this.board [i] [j] = filler; 

            }
         }
    }


    //writes the given entry to the field 
    private void writeEntry(Coordinate coord, char entry)
    {
        this.indexChecker(coord);
        try 
        {
            this.board [coord.x] [coord.y] = entry; 
            //System.out.println(entry);
        }catch (Exception e)
        {
            throw e; 
        }
    }

    //returns the value at the given coordinates of the field, throws an error if out of range  
    private char getEntry(Coordinate coord)
    {
        this.indexChecker(coord);
        try 
        {
            char value = this.board [coord.x] [coord.y]; 
            //System.out.println(value); 
            return value; 
        }catch (Exception e)
        {
            throw e; 
        }
    }
    //transforms the own board into new char [] [] board for the enemy where only hit fields are visible, everything else is unknown 
    private static char [] [] transformSelfToEnemy(char [] [] board)
    {
        char [] [] returnBoard = new char [BattleShip.xSize] [BattleShip.ySize]; 
        for (int i = 0; i < board.length; i ++ )
        {
            for (int j = 0; j < board[0].length; j ++ )
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


// ----------------------------------------------------------PUBLIC ACTION FUNCTIONS FOR PLAYING----------------------------------------------

public boolean AttackField(Coordinate coord) {
    indexChecker(coord);
    if (isFieldAlreadyHit(coord)){
        return false;
    }
    char fieldValue = this.getEntry(coord);
    if (fieldValue == activeShipMarker) {
        this.writeEntry(coord, Board.deadShipMarker);
        return true;
    }
    if (fieldValue == emptyFieldMarker) {
        this.writeEntry(coord, Board.missedDefenderMarker);
        return true;
    }
    else {
        throw(new Error("FieldValue in AttackField function could not be resolved, maybe wrong phase of the game"));
    }
}

public boolean PlaceShip(Coordinate coord) {
    indexChecker(coord);
    if (isShipAlreadyPlacedAtPosition(coord)) {
        return false;
    }
    this.writeEntry(coord, activeShipMarker);
    return true;    
}


//------------------------------------------------------VALIDATOR FUNCTIONS--------------------------------------------------------------------

// returns true if there is an active ship at the position, if the field is empty, then false, ONLY FOR PLACING THE SHIPS 
    public boolean isShipAlreadyPlacedAtPosition(Coordinate coord)
    {
        indexChecker(coord); 
        char value = this.getEntry(coord); 
        switch (value)
        {
            case Board.activeShipMarker:
                return true; 
            case Board.emptyFieldMarker:
                return false; 
            default:
                throw(new Error("isActiveShipAtPosition received unknown marker, maybe called from wrong phase of the game")); 
        }
    }

    //checks if the given field is already hit (attacked) by the attacking player, true if yes, no if it has not been attacked yet
    private boolean isFieldAlreadyHit(Coordinate coord)
    {
        this.indexChecker(coord); 
        switch (this.getEntry(coord))
        {
            case Board.deadShipMarker:
                return true; 
            case Board.missedDefenderMarker:
                return true; 
            case Board.activeShipMarker:
                return false; 
            case Board.emptyFieldMarker:
                return false; 
            default:
                throw(new Error("isFieldAlreadyHit function received unknown marker, maybe in wrong phase of the game")); 
        }
    }

    // checks if the given coordinates are in range of the grid, throws error if not in range   
    public boolean CoordChecker(Coordinate coord)
    {
        int xIndexMaxRange = this.xSize - 1; 
        int yIndexMaxRange = this.ySize - 1; 
        boolean result = coord.x <= xIndexMaxRange && coord.y <= yIndexMaxRange; 
        if (this.debug)
        {
          System.out.print("index checker" + xIndexMaxRange + " " + yIndexMaxRange + " result is " + result); 
        }
        return result; 
        }
    
    private boolean indexChecker(Coordinate coord) {
        boolean result = this.CoordChecker(coord);
        // if (!result) {
        //     throw(new Error("Index Checker out of range, either to big or too small index"));
        // }
        return result;
    }
        

}
