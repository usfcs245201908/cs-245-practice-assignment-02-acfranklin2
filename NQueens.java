import java.util.ArrayList;

public class NQueens
{
	private String[][] board;
	private int boardSize;
	private ArrayList<ArrayList<Integer>> QueenPlacements = new ArrayList<ArrayList<Integer>>();

	public NQueens(int n)
	{
		board = new String[n][n];
		boardSize = n;
		ArrayList<ArrayList<Integer>> QueenPlacements = new ArrayList<ArrayList<Integer>>();
	}

	public NQueens()
	{
		board = new String[1][1];
		boardSize = 1;
		ArrayList<ArrayList<Integer>> QueenPlacements = new ArrayList<ArrayList<Integer>>(1);
	}

	public ArrayList<ArrayList<Integer>> showQueenPlacements()
	{
		return(QueenPlacements);
	}

	public boolean isListEmpty()
	{
		return(QueenPlacements.isEmpty());
	}

	public boolean isQueenInPath(int queenX, int queenY)
	{
		//First checks if there's even any queens - If not, it auto-returns "false"
		if(QueenPlacements.isEmpty())
			return(false);
		//If not, it checks all paths in horizontal directions from the placement
		for(int h = 0; h < boardSize; h++)
		{
			if(h != queenX)
				for(ArrayList<Integer> queen: QueenPlacements)
					if((h == queen.get(0)) && (queenY == queen.get(1)))
						return(true);
		}
		//Then checks all paths vertically from the current place
		for(int v = 0; v < boardSize; v++)
		{
			if(v != queenY)
			{
				for(ArrayList<Integer> queen: QueenPlacements)
					if((queenX == queen.get(0)) && (v == queen.get(1)))
						return(true);
			}
		}
		//Then, it checks diagonal paths through this method:
		int diagonal1 = queenX - queenY;
		int diagonal2 = queenX + queenY;
		//Then, it goes through the board and sees if "queenX -/+ queenY" will equal d1 or d2, respectively
		for(int bRow = 0; bRow < boardSize; bRow++)
			for(int bCol = 0; bCol < boardSize; bCol++)
				if((bCol != queenY && (bRow != queenX)))
					if(((bRow + bCol == diagonal2) || (bRow - bCol == diagonal1))) 
						for(ArrayList<Integer> queen: QueenPlacements)
							if((bRow == queen.get(0)) && (bCol == queen.get(1)))
								return(true);
		return(false);
	}

	public boolean placeNQueens() throws Exception
	{
		if(determineQueens(0, boardSize))
			return(true);
		else
			return(false);
		
	}

	public boolean determineQueens(int row, int queens) throws Exception
	{
		if(boardSize <= 0)
			throw new Exception();
		//Obviously, if the size of the board is 1, there's only one place to put a Queen, and there won't be any other spaces for queens to attack from
		else if(boardSize == 1)
			return true;
		else
		{
			/**Starting from the top row, if the code finds a place for the Queen that works, it will go down to the 
			next row and start checking every spot in that row. But if it can't find any, then it goes back up to the last 
			row and checks the other spots on the row to see if they work and may allow the other rows below it to work.
			This will continuously repeat until eventually, every Queen will either have found a spot or the whole thing has
			failed.
			*/
			for(int currentColumn = 0; currentColumn < boardSize; currentColumn++) 
			{
				boolean currentlyValid = true;
				if(isQueenInPath(row, currentColumn))
					currentlyValid = false;
				else
				{
					ArrayList<Integer> newQueen = new ArrayList();
					newQueen.add(row);
					newQueen.add(currentColumn);
					QueenPlacements.add(newQueen);
					//System.out.println(QueenPlacements);
					//System.out.println(currentlyValid);
				}
				if(currentlyValid)
					if(determineQueens(row + 1, queens))
						return(true);

			}
			return(false);
		}
	}

	
	public String printToConsole()
	{
		String boardString = "";
		for(int i = 0; i < boardSize; i++)
		{
			for(int j = 0; j < boardSize; j++)
				boardString += (board[i][j] + " ");
			boardString += "\n";
		}
		return(boardString);
	}
	
}