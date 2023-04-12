package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	
		private Board board;
		
		public ChessMatch() {
			board = new Board(8,8);
			InitialSetup();
		}
		
		public ChessPiece[][] getPieces() {
			ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
			for(int i=0 ; i<board.getRows() ; i++) {
				for(int j=0 ; j<board.getColumns() ; j++) {
					mat[i][j] = (ChessPiece) board.piece(i,j);
				}
			}
			return mat;
		}

		private void InitialSetup() {
			placeNewPiece('b',1,new Rook(board,Color.White));
			placeNewPiece('g',1,new Rook(board,Color.White));
			placeNewPiece('b',8,new Rook(board,Color.Black));
			placeNewPiece('g',8,new Rook(board,Color.Black));
			placeNewPiece('e',8,new King(board,Color.Black));
			placeNewPiece('e',1,new King(board,Color.White));
			
		}
		
		private void placeNewPiece(char column, int row, ChessPiece piece) {
			board.placePiece(piece, new ChessPosition(column,row).toPosition());
		}
		
		public ChessPiece performanceDeMovimentoDoXadrez(ChessPosition posiçãoDeOrigem,ChessPosition posiçãoDeDestino) {
			
			Position origem = posiçãoDeOrigem.toPosition();
			Position destino = posiçãoDeDestino.toPosition();
			validarPosiçãoDeOrigem(origem);
			Piece peçaCapturada = movimento(origem,destino);
			return (ChessPiece) peçaCapturada;
		}
		
		private void validarPosiçãoDeOrigem(Position position) {
			if (!board.thereIsAPiece(position)) {
				throw new chessException("Não tem peça na posição de origem");
			}
		}
		
		private Piece movimento(Position origem,Position destino) {
			Piece p = board.removePiece(origem);
			Piece peçaCapturada = board.removePiece(destino);
			board.placePiece(p, destino);
			return peçaCapturada;
		}
}
