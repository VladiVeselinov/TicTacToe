import java.awt.*;

/**
 * Tests the GameAI by playing with itself
 * 
 * @author Vladi Veselinov
 */
public class GameTest
{
    public static void main(String[] args)
    {
	Board game = new Board();
	Point bestMove;
	int x, y;
	while (!game.getComplete())
	{
	    bestMove = GameAI.bestMove(game);
	    x = bestMove.x;
	    y = bestMove.y;
	    System.out.println(x + ", " + y);
	    game.makeMove(x, y);
	}
	System.out.println(game.status());
    }
}
