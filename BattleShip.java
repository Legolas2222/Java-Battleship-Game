public class BattleShip 
{
    // public Board setBoard; 
    // public Board testBoard; 
    private Player player1; 
    private Player player2; 
    
    private boolean won; 
    private IOutput output; 
    private IPlayerInput input; 
    private  int PlayerCount; 

    private int targetsHit; 
    // implement unique ID Class and remember to set last turn Player in Turn Function !!!
    // * DONE:  print the current board after each action
    //TODO make sure on ship placement that you can't place your ships all at the same coordiantes 
    //TODO make sure you can't attack the same coordinates twice
    //TODO make sure logic is where it belongs, 
    //TODO make turn base game where you place your fleet first, the attack in turns

    //? maybe multiplayer networking / async in the future 
    private String lastPlayerTurn; 

    public static final int numberOfTargets = 3; 
    public static final boolean debug = false; 
    public static final int xSize = 4; 
    public static final int ySize = 4; 
  
    


  private String CreatePlayerID()
    {
    String ID = "Player" + Integer.toString(this.PlayerCount); 
    this.PlayerCount++ ; 
    return ID; 
  }

  public BattleShip(boolean debug, IOutput output, IPlayerInput input)
    {
      this.PlayerCount = 0; 
      this.input = input; 
      this.output = output; 
      this.player1 = new Player(output, input, "Player1"); 
      this.player2 = new Player(output, input, CreatePlayerID()); 
  
  }

  private void OnTargetHit(Coordinate coords)
    {
    this.targetsHit++ ; 
    this.output.onHitMessage(coords); 
    if (this.targetsHit == BattleShip.numberOfTargets)
        {
      this.OnWon(this.lastPlayerTurn); 
    }
    
  }
//TODO this should be in the board class...
  private void OnWon(String playerName)
    {
    this.output.onWonMessage(playerName); 
  }
  public static void main(String[] args)
    {
      BattleShip sv = new BattleShip(false, new ConsoleOutput(), new ConsoleInput()); 
      sv.Play(); 
    //logging ???
    //Game g = new Game(x,y, false);
    //g.play;
  }

  public void Play()
    {
      PlacingShips(this.player1); 
      PlacingShips(this.player2); 

  }

 private void PlacingShips(Player defender)
    {
  this.output.showMessage(defender.playerID() + ", " + defender.name() + " You have to position your fleet: "); 
  for (int i = 0; i < BattleShip.numberOfTargets; i++ )
        {
    defender.placingShipTurn(i+1); 
    defender.showBoard();
  }
  this.output.showMessage("You have succesfully positioned your fleet as you see below, \nproceed to attack your enemy's fleet"); 
 }


  
  }
