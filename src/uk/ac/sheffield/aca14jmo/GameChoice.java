package uk.ac.sheffield.aca14jmo;

/**
 * Created by joshua on 09/05/15.
 */
public enum GameChoice {
    HUMAN, AGGRESSIVE_COMPUTER, RANDOM_COMPUTER;

    @Override
    public String toString() {
        switch(this) {
            case HUMAN:
                return "Human";
            case AGGRESSIVE_COMPUTER:
                return "Aggressive Computer";
            case RANDOM_COMPUTER:
                return "Random Computer";
            default:
                return "Undefined";
        }
    }
}
