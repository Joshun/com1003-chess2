package uk.ac.sheffield.aca14jmo;

/**
 * Created by joshua on 09/05/15.
 */
public class GameChoice {
    private Player player1;
    private Player player2;

    public GameChoice(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        p1.setOpponent(p2);
        p2.setOpponent(p1);
    }

    public String getPlayer1Type() {
        return player1.getClass().getSimpleName();
    }

    public String getPlayer2Type() {
        return player2.getClass().getSimpleName();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    

    public static GameChoice[] generateChoices() {
        Board player1Board = new Board();
        Pieces player1Pieces = new Pieces(player1Board, PieceCode.WHITE);
        String player1Name = "Player 1";
        
        Board player2Board = new Board();
        Pieces player2Pieces = new Pieces(player2Board, PieceCode.BLACK);
        String player2Name = "Player 2";
        
        Player[] player1Choices = {
                new HumanPlayer(player1Name, player1Pieces, player1Board, null, null),
                new AggressivePlayer(player1Name, player1Pieces, player1Board, null),
                new RandomPlayer(player1Name, player1Pieces, player1Board, null)
        };

        Player[] player2Choices = {
                new HumanPlayer(player2Name, player2Pieces, player2Board, null, null),
                new AggressivePlayer(player2Name, player2Pieces, player2Board, null),
                new RandomPlayer(player2Name, player2Pieces, player2Board, null)
        };
        
        GameChoice[] gameChoices = new GameChoice[9];
        for (int i=0; i<player1Choices.length; i++) {
            for (int j=0; i<player2Choices.length; i++) {
                gameChoices[i+j] = new GameChoice(player1Choices[i], player2Choices[j]);
            }
        }
        
        return gameChoices;
    }
}
