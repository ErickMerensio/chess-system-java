package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece{

	private ChessMatch partidaDeXadrez;
	
	public Rei(Board board, Color color,ChessMatch partidaDeXadrez) {
		super(board, color);
		this.partidaDeXadrez = partidaDeXadrez;
	}
	
	@Override
	public String toString() {
		return "K" ;
	}
	
	private boolean podeMover(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
				return p == null || p.getColor() != getColor();
	}
	
	private boolean testeRoquedeTorre(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getContadorDeMovimento() == 0;
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
				
				// movimento especial - Roque de Torre 
				if(getContadorDeMovimento() == 0 && !partidaDeXadrez.getCheck()) {
					//movimento do roque menor
					Position roqueMenor = new Position(position.getRow(),position.getColumn() +3);
					if(testeRoquedeTorre(roqueMenor)) {
						Position p1 = new Position(position.getRow(),position.getColumn() +1);
						Position p2 = new Position(position.getRow(),position.getColumn() +2);
						if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
							mat[position.getRow()][position.getColumn() + 2] = true;
						}
					}
					//movimento do roque maior
					Position roqueMaior = new Position(position.getRow(),position.getColumn() -4);
					if(testeRoquedeTorre(roqueMaior)) {
						Position p1 = new Position(position.getRow(),position.getColumn() -1);
						Position p2 = new Position(position.getRow(),position.getColumn() -2);
						Position p3 = new Position(position.getRow(),position.getColumn() -3);
						if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
							mat[position.getRow()][position.getColumn() -2] = true;
						}
					}
				}
			
			return mat;
		}
}