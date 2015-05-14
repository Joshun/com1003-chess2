package uk.ac.sheffield.aca14jmo;

/**
 * uk.ac.sheffield.aca14jmo.GameChoice.java
 *
 * Enum used to keep track of which players have been selected
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
