package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rainha extends ChessPiece{

	public Rainha(Board board, Color color) {
		super(board, color);
	}
	
	
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		// Rainha movendo para cima
		p.setValues(position.getRow() -1 , position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Rainha movendo para baixo
		p.setValues(position.getRow() +1 , position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Rainha movendo para os esquerda
		p.setValues(position.getRow(), position.getColumn() -1 );
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Rainha movendo para a direita
		p.setValues(position.getRow(), position.getColumn() +1 );
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Rainha movendo para noroeste
				p.setValues(position.getRow() -1 , position.getColumn() -1);
				while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() -1 , p.getColumn() -1);
				}
				if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				//Rainha movendo para nordeste
				p.setValues(position.getRow() -1 , position.getColumn() +1);
				while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() -1, p.getColumn() +1);
				}
				if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				//Rainha movendo para sudoeste
				p.setValues(position.getRow() +1 , position.getColumn() -1 );
				while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() +1, p.getColumn() -1);
				}
				if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				//Rainha movendo para sudeste
				p.setValues(position.getRow() +1, position.getColumn() +1 );
				while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() +1, p.getColumn() +1);
				}
				if(getBoard().positionExists(p) && temAlgumaPeçaDoOponente(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
		
		
		return mat;
	}
}
	