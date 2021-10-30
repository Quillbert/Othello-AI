public class Board {
	public int board[][];
	public int turn = 1;
	public Board() {
		board = new int[8][8];
	}
	
	public Board(Board b) {
		board = new int[b.board.length][b.board[0].length];
		turn = b.turn;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j] = b.board[i][j];
			}
		}
	}
	
	public boolean makeTurn(int x, int y) {
		if(board[x][y] != 0) {
			return false;
		}
		int copy[][] = new int[board.length][board[0].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				copy[i][j] = board[i][j];
			}
		}
		
		for(int i = x+1; i < board.length; i++) {
			if(copy[i][y] == 0) {
				break;
			} else if(copy[i][y] == turn) {
				for(int j = x; j <= i; j++) {
					copy[j][y] = turn;
				}
				break;
			}
		}
		
		for(int i = x-1; i >= 0; i--) {
			if(copy[i][y] == 0) {
				break;
			} else if(copy[i][y] == turn) {
				for(int j = x; j >= i; j--) {
					copy[j][y] = turn;
				}
				break;
			}
		}
		
		for(int i = y+1; i < board[0].length; i++) {
			if(copy[x][i] == 0) {
				break;
			} else if(copy[x][i] == turn) {
				for(int j = y; j <= i; j++) {
					copy[x][j] = turn;
				}
				break;
			}
		}
		
		for(int i = y-1; i >= 0; i--) {
			if(copy[x][i] == 0) {
				break;
			} else if(copy[x][i] == turn) {
				for(int j = y; j >= i; j--) {
					copy[x][j] = turn;
				}
				break;
			}
		}
		
		for(int i = 1; x+i < board.length && y+i < board[0].length; i++) {
			if(board[x+i][y+i] == 0) {
				break;
			} else if(copy[x+i][y+i] == turn) {
				for(int j = 0; j <= i; j++) {
					copy[x+j][y+j] = turn;
				}
				break;
			}
		}
		
		for(int i = 1; x-i >= 0 && y+i < board[0].length; i++) {
			if(board[x-i][y+i] == 0) {
				break;
			} else if(copy[x-i][y+i] == turn) {
				for(int j = 0; j <= i; j++) {
					copy[x-j][y+j] = turn;
				}
				break;
			}
		}
		
		for(int i = 1; x+i < board.length && y-i >= 0; i++) {
			if(board[x+i][y-i] == 0) {
				break;
			} else if(copy[x+i][y-i] == turn) {
				for(int j = 0; j <= i; j++) {
					copy[x+j][y-j] = turn;
				}
				break;
			}
		}
		
		for(int i = 1; x-i >= 0 && y-i >= 0; i++) {
			if(board[x-i][y-i] == 0) {
				break;
			} else if(copy[x-i][y-i] == turn) {
				for(int j = 0; j <= i; j++) {
					copy[x-j][y-j] = turn;
				}
				break;
			}
		}
		
		
		if(opponentCount(copy) == opponentCount(board)) {
			return false;
		}
		
		board = copy;
		return true;
	}
	
	private int opponentCount(int[][] b) {
		int opponent = turn == 1 ? -1: 1;
		int count = 0;
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				if(b[i][j] == opponent) {
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean canPlay() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(canPlay(i, j)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canPlay(int x, int y) {
		int copy[][] = new int[board.length][board[0].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				copy[i][j] = board[i][j];
			}
		}
		if(makeTurn(x, y)) {
			board = copy;
			return true;
		}
		return false;
	}
	
	public String toString() {
		String out = "";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 0) {
					out += "-";
				} else if(board[i][j] == 1) {
					out += "@";
				} else if(board[i][j] == -1) {
					out += "#";
				}
				out += " ";
			}
			out = out.substring(0, out.length()-1);
			out += "\n";
		}
		return out;
	}
}
