public class ConsoleInput implements IPlayerInput 
{
    public String getName(String playerID) {
        String value;
        try {
            System.out.println("Please enter your name and hit enter: ");
            value = Kon.readString();
            if (value.length() == 0) {
                System.out.println("Not a valid string please try again");
                getName(playerID);
            }
        } catch (Exception e) {
            throw(e);
        }
        return value;
    } 
    public Coordinate getTargetCoordinate(String playerID, String playerName) {
        char first;
        char second;
        try {
            System.out.println(playerID +"You are asked to enter the Coordinates of your target: ");
            System.out.println("Please enter the first coordinate (Should be a number) and hit enter ");
            first = Kon.readChar();
            if(Character.isDigit(first)) {
                System.out.println("The given coordinate was not a number, please try again");
                getTargetCoordinate(playerID, playerName);
            }
            System.out.println("Please enter the second coordinate (Should be a number) and hit enter ");
            second = Kon.readChar();
            if(Character.isDigit(second)) {
                System.out.println("The given coordinate was not a number, please try again");
                getTargetCoordinate(playerID, playerName);
            }
        } catch (Exception e) {
            throw(e);
        }
        return new Coordinate(first, second);
    }
    public Coordinate getShipCoordinate(String playerID, String playerName, int numberOfTargets) {
        int first;
        int second;
        try {
            System.out.println("You are asked to enter the Coordinates of your ship number: " + numberOfTargets);
            System.out.println("Please enter the first coordinate (Should be a number) and hit enter ");
            first = Kon.readInt();
            if(first > BattleShip.xSize) {
                System.out.println("The given coordinate was too big, please try again");
                getShipCoordinate(playerID, playerName, numberOfTargets);
            }
            System.out.println("Please enter the second coordinate (Should be a number) and hit enter ");
            second = Kon.readInt();
            if(second > BattleShip.ySize) {
                System.out.println("The given coordinate too big, please try again");
                getShipCoordinate(playerID, playerName, numberOfTargets);
            }
        } catch (Exception e) {
            throw(e);
        }
        return new Coordinate(first, second);
    }
}
