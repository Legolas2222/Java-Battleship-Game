public interface IPlayerInput {
    default public String getName(String playerID) {
        return "DEFAULT NAME";
    } 
    default public Coordinate getTargetCoordinate(String playerID, String playerName) {
        return new Coordinate(0, 0);
    }
    default public Coordinate getShipCoordinate(String playerID, String playerName, int numberOfTargets) {
        return new Coordinate(0, 0);
    }
}
