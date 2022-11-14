public class Player 
{
    private String name; 
    private Board board; 
    private IOutput output; 
    private IPlayerInput input; 
    private boolean debug; 
    private String playerID; 
    

    public Player(IOutput output, IPlayerInput input, String playerID, String playerName)
    {
        this.output = output; 
        this.input = input; 
        this.debug = BattleShip.debug; 
        this.board = new Board(this.output); 
        this.playerID = playerID; 
        if (playerName.length() == 0) {
            this.receiveName();
        }  
        this.name = playerName;

    }

    public String playerID() {
        return this.playerID;
    }

    public String name()
    {
        return this.name; 
    }

    public Board board()
    {
        return this.board; 
    }

    private void receiveName()
    {
        this.name = this.input.getName(this.playerID); 
    }
    public Coordinate attackingTurnCoordinate()
    {
        Coordinate coord = this.input.getTargetCoordinate(this.playerID, this.name);
        return coord; 
    }

    
    public Coordinate placingShipTurn(int numberOfTargets) {
        Coordinate coord = this.input.getShipCoordinate(this.playerID, this.name, numberOfTargets);
        //Checking for the size of the index, if out of range!, THEREFORE if the indexChecker result is FALSE!!!
        if (!this.board.CoordChecker(coord)) {
            this.output.showMessage("The given coordinates are either to big or too small, please retry! ");
            this.placingShipTurn(numberOfTargets);
        //Checking if the given coordinates already exist!
        }
        if (this.board.isShipAlreadyPlacedAtPosition(coord)) {
            this.output.showMessage("The given coordinate is already occupied, please enter another one");
            this.placingShipTurn(numberOfTargets);
        }
        this.board.PlaceShip(coord);
        return coord; 
    }
    
    public void showBoard() {
        this.output.showBoard(this.board);
    }
}
