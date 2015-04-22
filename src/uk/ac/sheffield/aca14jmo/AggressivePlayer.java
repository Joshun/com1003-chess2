package uk.ac.sheffield.aca14jmo;

/**
 * Created by joshua on 22/04/15.
 */
public class AggressivePlayer extends ComputerPlayer {
    public AggressivePlayer(String n, Pieces p, Board b, Player o) {
        super(n, p, b, o);
    }
    public boolean makeMove() {
        return true;
    }
}
