package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int contadorDeMovimento;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public ChessPosition getPosiçãoDoXadrez() {
		return ChessPosition.fromPosition(position);
	}

	public Color getColor() {
		return color;
	}
	
	public int getContadorDeMovimento() {
		return contadorDeMovimento;
	}
	
	public void aumentarContadorDeMovimento () {
		contadorDeMovimento++;
	}
	
	public void diminuirContadorDeMovimento () {
		contadorDeMovimento--;
	}

	protected boolean temAlgumaPeçaDoOponente(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p  != null && p.getColor() != color;
	}
}
