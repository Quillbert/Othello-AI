import java.util.ArrayList;

public class Move {
	public static final int MAX_DEPTH = 6;
	private int depth;
	private int score;
	public int x, y;
	public static int team = -1;
	
	private Move selectedMove;
	
	private ArrayList<Move> moves = new ArrayList<Move>();
	
	public Board state;
	
	public Move(int d, int x, int y, Board b) {
		state = new Board(b);
		depth = d;
		this.x = x;
		this.y = y;
		state.makeTurn(x, y);
		state.turn = state.turn == 1 ? -1:1;
		if(depth < MAX_DEPTH) {
			generateMoves();
		}
		moves.trimToSize();
		if(moves.size() <= 0) {
			score = calculateFitness(state);
			selectedMove = this;
		} else {
			if(state.turn == team) {
				score = moves.get(0).score;
				selectedMove = moves.get(0);
				for(int i = 1; i < moves.size(); i++) {
					if(moves.get(i).score > score) {
						score = moves.get(i).score;
						selectedMove = moves.get(i);
					}
				}
			} else {
				score = moves.get(0).score;
				selectedMove = moves.get(0);
				for(int i = 1; i < moves.size(); i++) {
					if(moves.get(i).score < score) {
						score = moves.get(i).score;
						selectedMove = moves.get(i);
					}
				}
			}
		}
		moves.clear();
	}
	
	public Move(Board b) {
		state = new Board(b);
		depth = 0;
		generateMoves();
		if(moves.size() <= 0) {
			score = calculateFitness(state);
			selectedMove = this;
		} else {
			if(state.turn == team) {
				score = moves.get(0).score;
				selectedMove = moves.get(0);
				for(int i = 1; i < moves.size(); i++) {
					if(moves.get(i).score > score) {
						score = moves.get(i).score;
						selectedMove = moves.get(i);
					}
				}
			} else {
				score = moves.get(0).score;
				selectedMove = moves.get(0);
				for(int i = 1; i < moves.size(); i++) {
					if(moves.get(i).score < score) {
						score = moves.get(i).score;
						selectedMove = moves.get(i);
					}
				}
			}
		}
		x = selectedMove.x;
		y = selectedMove.y;		
	}
	
	private void generateMoves() {
		moves.clear();
		if(!state.canPlay()) {
			state.turn = state.turn == 1 ? -1:1;
		}
		for(int i = 0; i < state.board.length; i++) {
			for(int j = 0; j < state.board[i].length; j++) {
				if(state.canPlay(i, j)) {
					Move newMove = new Move(depth+1, i, j, state);
					moves.add(newMove);
				}
			}
		}
	}
	
	private static int calculateFitness(Board b) {
		int count = 0;
		for(int i = 0; i < b.board.length; i++) {
			for(int j = 0; j < b.board[0].length; j++) {
				if(b.board[i][j] == team) {
					count++;
				}
			}
		}
		return count;
	}
}
