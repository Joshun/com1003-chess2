package uk.ac.sheffield.aca14jmo;

/**
 * Created by joshua on 24/04/15.
 */
public class HumanPlayerState {
    enum CLICK_STATE { START, END };

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private CLICK_STATE clickState;

    public HumanPlayerState() {
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
        clickState = CLICK_STATE.START;
    }

    public void click(int x, int y) {
        if (clickState == CLICK_STATE.START) {
            startX = x;
            startY = y;
            clickState = CLICK_STATE.END;
        }
        else if (clickState == CLICK_STATE.END) {
            endX = x;
            endY = y;
            clickState = CLICK_STATE.START;
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
}
