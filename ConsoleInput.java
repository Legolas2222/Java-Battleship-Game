public class ConsoleInput implements IPlayerInput 
{
    public String getName(String playerID) {
        String value;
        try {
            System.out.println("Please enter your name and hit enter: ");
            value = Kon.readString();
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
            System.out.println("Please enter the second coordinate (Should be a number) and hit enter ");
            second = Kon.readChar();
            
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
            System.out.println("Please enter the second coordinate (Should be a number) and hit enter ");
            second = Kon.readInt();  
        } catch (Exception e) {
            throw(e);
        }
        return new Coordinate(first, second);
    }
}
