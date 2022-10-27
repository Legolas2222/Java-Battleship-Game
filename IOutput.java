public interface IOutput {
    default public void showBoard(Board board) {
        System.out.println("Board Output");
    }
    public void showMessage(String message);
    default public void onHitMessage(Coordinate coords) {
        System.out.println("Hit Default Output");
    }
    default public void onMissedMessage() {
        System.out.println("Missed Default Output");
    }
    default public void onWonMessage(String playerName) {
        System.out.println("Won Default Output");
    }
}
