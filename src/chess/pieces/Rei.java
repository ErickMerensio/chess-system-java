package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece{

	public Rei(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K" ;
	}
	
	private boolean podeMover(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
				return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
	
	Position p = new Position(0,0);
	
				// Rei movendo para cima
				p.setValues(position.getRow() - 1, position.getColumn());
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
						
			//Rei movendo para baixo
				p.setValues(position.getRow() + 1, position.getColumn());
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			
			//Rei movendo para a esquerda
				p.setValues(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			
			//Rei movendo para a direita
				p.setValues(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}

			
			//Rei movendo para o noroeste
				p.setValues(position.getRow() - 1, position.getColumn() - 1);
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			
			//Rei movendo para o nordeste
				p.setValues(position.getRow() - 1, position.getColumn() + 1);
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			
			
			//Rei movendo para o sudoeste
				p.setValues(position.getRow() + 1, position.getColumn() - 1);
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			
			//Rei movendo para o sudeste
				p.setValues(position.getRow() + 1, position.getColumn() + 1);
				if (getBoard().positionExists(p) && podeMover(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			
			return mat;
		}
}