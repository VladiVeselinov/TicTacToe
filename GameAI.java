import java.awt.*;

/**
 * Game AI for Tic Tac Toe
 * 
 * @author Vladi Veselinov
 */
public class GameAI
{
    public static Point bestMove(Board board)
    {
	Point errorPoint = new Point(-1, -1);
	if (board.getComplete())
	    return errorPoint;
	/*
	 * if(board.getMovesLeft() == 9) return new Point(0, 0); // Corner for a
	 * starting move. if(board.getMovesLeft() == 8) {
	 * if(board.getBoard()[1][1] == ' ') return new Point(1, 1); // Get
	 * center if you can. else return new Point(0, 0); // Else get a corner.
	 * }
	 */
	Board workBoard;
	Point best = errorPoint;
	int score, bestScore = -2;
	for (int i = 0; i < 3; i++)
	    for (int j = 0; j < 3; j++)
		if (board.isEmpty(i, j))
		{
		    workBoard = new Board(board);
		    workBoard.makeMove(i, j);
		    score = ponder(workBoard, false);
		    if (score > bestScore) // Maximizing the score
		    {
			bestScore = score;
			best = new Point(i, j);
			if (bestScore == 1)
			    return best; // Winning strategy
		    }
		}
	return best;
    }

    public static int ponder(Board board, boolean maximizing)
    {
	if (board.getComplete())
	{
	    if (board.getWinner() == 'T')
		return 0;
	    else if (maximizing)
		return -1;
	    else
		return 1;
	}
	Board workBoard;
	int maxi = -2;
	int mini = 2;
	int score;
	for (int i = 0; i < 3; i++)
	    for (int j = 0; j < 3; j++)
		if (board.isEmpty(i, j))
		{
		    workBoard = new Board(board);
		    workBoard.makeMove(i, j);
		    score = ponder(workBoard, !maximizing);
		    if (maximizing && score > maxi)
		    {
			maxi = score;
			if (maxi == 1)
			    return 1;
		    } else if (!maximizing && score < mini)
		    {
			mini = score;
			if (mini == -1)
			    return -1;
		    }
		}
	if (maximizing)
	    return maxi;
	else
	    return mini;
    }
}
