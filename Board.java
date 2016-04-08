/**
 * Board for Tic Tac Toe
 * 
 * @author Vladi Veselinov
 */
public class Board
{
    private char[][] board;
    private int movesLeft;
    private boolean playerTurn; // true for X; false for O
    private boolean complete; // no moves can be made if true
    private char winner; // 'X', 'O', 'T', or 'I'(incomplete)

    public Board()
    {
	board = new char[3][3];
	for (int i = 0; i < 3; i++)
	    for (int j = 0; j < 3; j++)
		board[i][j] = ' ';
	movesLeft = 9;
	playerTurn = true;
	complete = false;
	winner = 'I';
    }

    public Board(Board board)
    {
	this.board = new char[3][3];
	char[][] origBoard = board.getBoard();
	for (int i = 0; i < 3; i++)
	    for (int j = 0; j < 3; j++)
		this.board[i][j] = origBoard[i][j];
	movesLeft = board.getMovesLeft();
	playerTurn = board.getPlayerTurn();
	complete = board.getComplete();
	winner = board.getWinner();
    }

    public char makeMove(int x, int y)
    {
	if (x < 0 || x > 2 || y < 0 || y > 2 || board[x][y] != ' ')
	    return 'E'; // Error (illegal move)
	if (complete)
	    return winner;
	if (playerTurn)
	    board[x][y] = 'X';
	else
	    board[x][y] = 'O';
	playerTurn = !playerTurn;
	movesLeft--;
	check();
	return winner;
    }

    private void check()
    {
	char c;
	// Rows check
	for (int i = 0; i < 3; i++)
	{
	    c = board[i][0];
	    if (c == ' ')
		continue;
	    if (c == board[i][1] && c == board[i][2])
		win(c);
	}
	// Columns check
	for (int i = 0; i < 3; i++)
	{
	    c = board[0][i];
	    if (c == ' ')
		continue;
	    if (c == board[1][i] && c == board[2][i])
		win(c);
	}
	// Diagonals check
	c = board[1][1];
	if (c != ' ')
	{
	    if (c == board[0][0] && c == board[2][2])
		win(c);
	    if (c == board[2][0] && c == board[0][2])
		win(c);
	}
	if (movesLeft == 0)
	    win('T');
    }

    private void win(char c)
    {
	if (complete)
	    return;
	winner = c;
	complete = true;
    }

    public String status()
    {
	switch (winner)
	{
	case 'X':
	    return "X has won the game!";
	case 'O':
	    return "O has won the game!";
	case 'T':
	    return "The game is a tie!";
	case 'I':
	    return "The game is still going!";
	default:
	    return "Error";
	}
    }

    public char[][] getBoard()
    {
	return board;
    }

    public int getMovesLeft()
    {
	return movesLeft;
    }

    public boolean getPlayerTurn()
    {
	return playerTurn;
    }

    public boolean getComplete()
    {
	return complete;
    }

    public char getWinner()
    {
	return winner;
    }

    public boolean isEmpty(int x, int y)
    {
	if (board[x][y] == ' ')
	    return true;
	else
	    return false;
    }
}
