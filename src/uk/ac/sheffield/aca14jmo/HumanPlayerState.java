package uk.ac.sheffield.aca14jmo;

/**
 * Created by joshua on 24/04/15.
 */
public class HumanPlayerState {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private ClickState clickState;
    private boolean successful;

    public HumanPlayerState() {
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
        clickState = ClickState.INITIAL;
        successful = false;
    }

    public void click(int x, int y) {
        if (clickState == ClickState.INITIAL) {
            startX = x;
            startY = y;
            clickState = ClickState.CLICK_START;
        }
        else if (clickState == ClickState.CLICK_START) {
            endX = x;
            endY = y;
            clickState = ClickState.CLICK_END;
        }
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public ClickState getClickState() {
        return clickState;
    }

    public boolean getSuccessful() {
        return successful;
    }

    public void reset() {
        clickState = ClickState.INITIAL;
        successful = false;
    }

    public void setSuccessful() {
        successful = true;
    }

    public String toString() {
        return "[" + startX + "," + startY + "] ->" + " [" + endX + "," + endY + "]" + "successful: " + successful + " clickstate: " + clickState;
    }
}
