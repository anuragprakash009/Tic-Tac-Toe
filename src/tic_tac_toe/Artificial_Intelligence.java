/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

/**
 *
 * @author amo
 */
import static java.lang.Integer.max;
import static java.lang.Integer.min;
public class Artificial_Intelligence {
    
    public char board[][] = 
	{ 
		{ '_', '_', '_' }, 
		{ '_', '_', '_' }, 
		{ '_', '_', '_' } 
	};
    char btns[][] = {
        { '1', '2', '3' }, 
		{ '4', '5', '6' }, 
		{ '7', '8', '9' } 
    };
    public void update_board(char b[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)
                board[i][j]=b[i][j];
        }
        
    }
    
    public void changeBoard(char s){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                
                if(btns[i][j]==s){
                    board[i][j]='o';
                }
                //System.out.print(board[i][j]+" ");
               
            }
            // System.out.println();
        }
        
    }
    
// This function returns true if there are moves 
// remaining on the board. It returns false if 
// there are no moves left to play. 
    
  public boolean isMovesLeft(char board[][]){ 
	for (int i = 0; i<3; i++) 
		for (int j = 0; j<3; j++) 
			if (board[i][j] =='_') 
				return true; 
	return false; 
}

public int evaluate(char b[][], char player, char opponent) 
{ 
	// Checking for Rows for X or O victory. 
	for (int row = 0; row<3; row++) 
	{ 
		if (b[row][0]==b[row][1] && 
			b[row][1]==b[row][2]) 
		{ 
			if (b[row][0]==player) 
				return +10; 
			else if (b[row][0]==opponent) 
				return -10; 
		} 
	} 

	// Checking for Columns for X or O victory. 
	for (int col = 0; col<3; col++) 
	{ 
		if (b[0][col]==b[1][col] && 
			b[1][col]==b[2][col]) 
		{ 
			if (b[0][col]==player) 
				return +10; 

			else if (b[0][col]==opponent) 
				return -10; 
		} 
	} 

	// Checking for Diagonals for X or O victory. 
	if (b[0][0]==b[1][1] && b[1][1]==b[2][2]) 
	{ 
		if (b[0][0]==player) 
			return +10; 
		else if (b[0][0]==opponent) 
			return -10; 
	} 

	if (b[0][2]==b[1][1] && b[1][1]==b[2][0]) 
	{ 
		if (b[0][2]==player) 
			return +10; 
		else if (b[0][2]==opponent) 
			return -10; 
	} 

	// Else if none of them have won then return 0 
	return 0; 
}


// This is the minimax function. It considers all 
// the possible ways the game can go and returns 
// the value of the board 
public int minimax(char board[][], int depth, boolean isMax, char player,char opponent) 
{ 
	int score = evaluate(board,player,opponent); 

	// If Maximizer has won the game return his/her 
	// evaluated score 
	if (score == 10) 
		return score; 

	// If Minimizer has won the game return his/her 
	// evaluated score 
	if (score == -10) 
		return score; 

	// If there are no more moves and no winner then 
	// it is a tie 
	if (isMovesLeft(board)==false) 
		return 0; 

	// If this maximizer's move 
	if (isMax) 
	{ 
		int best = -1000; 

		// Traverse all cells 
		for (int i = 0; i<3; i++) 
		{ 
			for (int j = 0; j<3; j++) 
			{ 
				// Check if cell is empty 
				if (board[i][j]=='_') 
				{ 
					// Make the move 
					board[i][j] = player; 

					// Call minimax recursively and choose 
					// the maximum value 
					best = max( best, minimax(board, depth+1, !isMax,player,opponent)); 

					// Undo the move 
					board[i][j] = '_'; 
				} 
			} 
		} 
		return best; 
	} 

	// If this minimizer's move 
	else
	{ 
		int best = 1000; 

		// Traverse all cells 
		for (int i = 0; i<3; i++) 
		{ 
			for (int j = 0; j<3; j++) 
			{ 
				// Check if cell is empty 
				if (board[i][j]=='_') 
				{ 
					// Make the move 
					board[i][j] = opponent; 

					// Call minimax recursively and choose 
					// the minimum value 
					best = min(best, minimax(board, depth+1, !isMax, player, opponent)); 

					// Undo the move 
					board[i][j] = '_'; 
				} 
			} 
		} 
		return best; 
	} 
}

public Move findBestMove(char player, char opponent) 
{   
	int bestVal = -1000; 
	Move ob=new Move(); 
	ob.row = -1; 
	ob.col = -1;
       
        

	// Traverse all cells, evaluate minimax function for 
	// all empty cells. And return the cell with optimal 
	// value. 
	for (int i = 0; i<3; i++) 
	{ 
		for (int j = 0; j<3; j++) 
		{ 
			// Check if cell is empty 
			if (board[i][j]=='_') 
			{ 
				// Make the move 
				board[i][j] = player; 

				// compute evaluation function for this 
				// move. 
				int moveVal = minimax(board, 0, false, player,opponent); 

				// Undo the move 
				board[i][j] = '_'; 

				// If the value of the current move is 
				// more than the best value, then update 
				// best/ 
				if (moveVal > bestVal) 
				{ 
					ob.row = i; 
					ob.col = j; 
					bestVal = moveVal; 
				} 
			} 
		} 
	} 
        
        
        board[ob.row][ob.col]=player;
	//System.out.println("The value of the best Move is : "+bestVal); 

	return ob; 
} 


  
  
    
    
}
