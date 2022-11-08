public class Player 
{
    private String name; 
    private Board board; 
    private IOutput output; 
    private IPlayerInput input; 
    private boolean debug; 
    private String playerID; 
    

    public Player(IOutput output, IPlayerInput input, String playerID)
    {
        this.output = output; 
        this.input = input; 
        this.debug = BattleShip.debug; 
        this.board = new Board(this.output); 
        this.playerID = playerID; 
        this.receiveName(); 

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
        if (this.board.isActiveShipAtPosition(coord)) {
            this.output.showMessage("The given coordinate is already occupied, please enter another one");
            this.placingShipTurn(numberOfTargets);
        }
        return coord; 
    }
    
    public void showBoard() {
        this.output.showBoard(this.board);
    }
}
