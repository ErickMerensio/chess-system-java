package boardgame;

public abstract class Piece {
	
		protected Position position;
		private Board board;
		
		
		public Piece(Board board) {
			this.board = board;
		}
		
	
		protected Board getBoard() {
			return board;
		}
		
		public abstract boolean [][] possiveisMovimentos();
		
		public boolean possivelMovimento(Position position) {
			return possiveisMovimentos()[position.getRow()][position.getColumn()];
		}
		public boolean temAlgumPossivelMovimento() {
			boolean [][] mat = possiveisMovimentos();
			for (int i=0; i<mat.length; i++) {
				for (int j=0; j<mat.length;j++) {
					if (mat[i][j]) {
						return true;
					}
				}
		}
			return false;
		}
}
