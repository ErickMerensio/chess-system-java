package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
		private int turno;
		private Color jogadorAtual;
		private Board board;
		private boolean check;
		
		private List<Piece> peçasNoTabuleiro = new ArrayList<>();
		private List<Piece> peçascapturadas = new ArrayList<>();
		
		
		public ChessMatch() {
			board = new Board(8,8);
			turno = 1;
			jogadorAtual = Color.White;
			InitialSetup();
		}
		
		public int getTurno() {
			return turno;
		}
		
		public Color getJogadorAtual() {
			return jogadorAtual;
		}
		
		public boolean getCheck () {
			return check;
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
			peçasNoTabuleiro.add(piece);
		}
		
		public ChessPiece performanceDeMovimentoDoXadrez(ChessPosition posiçãoDeOrigem,ChessPosition posiçãoDeDestino) {
			
			Position origem = posiçãoDeOrigem.toPosition();
			Position destino = posiçãoDeDestino.toPosition();
			validarPosiçãoDeOrigem(origem);
			validarPosiçãoDeDestino(origem,destino);
			Piece peçaCapturada = movimento(origem,destino);
			if(testeCheck(jogadorAtual)) {
				desfazerMovimento(origem, destino, peçaCapturada);
				throw new chessException("Você não pode se colocar em Check");
			}
			check = (testeCheck(oponente(jogadorAtual))) ? true : false ;
			proximoTurno();
			return (ChessPiece) peçaCapturada;
		}
		
		private void validarPosiçãoDeDestino (Position origem,Position destino) {
			
			if (!board.piece(origem).possivelMovimento(destino)) {
				throw new chessException("A peça não pode ser movida para o destino");
			}
			
		}
		
		private void validarPosiçãoDeOrigem(Position position) {
			if (!board.thereIsAPiece(position)) {
				throw new chessException("Não tem peça na posição de origem");
			}
			if (jogadorAtual != ((ChessPiece)board.piece(position)).getColor()) {
				throw new chessException("A peça escolhida não é sua");
			}
			if (!board.piece(position).temAlgumPossivelMovimento()) {
				throw new chessException("Não tem movimento possivel para peça");
				
			}
		}
		
		private Piece movimento(Position origem,Position destino) {
			Piece p = board.removePiece(origem);
			Piece peçaCapturada = board.removePiece(destino);
			board.placePiece(p, destino);
			if (peçaCapturada != null) {peçasNoTabuleiro.remove(peçaCapturada);
			peçascapturadas.add(peçaCapturada);
			}
			
			return peçaCapturada;
		}
		
		private void desfazerMovimento(Position origem,Position destino,Piece peçaCapturada) {
			Piece p = board.removePiece(destino);
			board.placePiece(p, origem);
			if(peçaCapturada != null) {
				board.placePiece(peçaCapturada, destino);
				peçascapturadas.remove(peçaCapturada);
				peçasNoTabuleiro.add(peçaCapturada);
			}
		}
		
		public boolean [][] possiveisMovimentos (ChessPosition posiçãoDeOrigem) {
			Position position = posiçãoDeOrigem.toPosition() ;
			validarPosiçãoDeOrigem(position);
			return board.piece(position).possiveisMovimentos();
			
		}
		
		private void proximoTurno () {
			turno++;
			jogadorAtual = (jogadorAtual == Color.White) ? Color.Black : Color.White;
		}
		
		private Color oponente(Color cor) {
			return (cor == Color.White) ? Color.Black :Color.White;
		}
		
		private ChessPiece rei(Color cor) {
			List<Piece> list = peçasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getColor() == cor).collect(Collectors.toList());
			for (Piece p : list) {
				if(p instanceof King) {
					return (ChessPiece)p;
				}
			}
			throw new IllegalStateException("Não existe" + cor + "rei no tabuleiro");
		}
		
		private boolean testeCheck(Color cor) {
			Position posiçãoDoRei = rei(cor).getPosiçãoDoXadrez().toPosition();
			List<Piece> peçasDoOponente = peçasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getColor() == oponente(cor)).collect(Collectors.toList());
			for (Piece p : peçasDoOponente) {
				boolean[][] mat = p.possiveisMovimentos();
				if(mat[posiçãoDoRei.getRow()][posiçãoDoRei.getColumn()]) {
					return true;
				}
			}
			return false;
		}
}
