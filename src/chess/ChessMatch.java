package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bispo;
import chess.pieces.Cavalo;
import chess.pieces.Peao;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	
		private int turno;
		private Color jogadorAtual;
		private Board board;
		private boolean check;
		private boolean checkMate;
		
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
		
		public boolean getCheckMate () {
			return checkMate;
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
	        	placeNewPiece('e', 1, new Rei(board, Color.White));
			  	placeNewPiece('c', 1, new Bispo(board, Color.White));
			  	placeNewPiece('f', 1, new Bispo(board, Color.White));
			  	placeNewPiece('a', 1, new Torre(board, Color.White));
		        placeNewPiece('h', 1, new Torre(board, Color.White));
		        placeNewPiece('b', 1, new Cavalo(board, Color.White));
		        placeNewPiece('g', 1, new Cavalo(board, Color.White));
		        placeNewPiece('a', 2, new Peao(board, Color.White));
		        placeNewPiece('b', 2, new Peao(board, Color.White));
		        placeNewPiece('c', 2, new Peao(board, Color.White));
		        placeNewPiece('d', 2, new Peao(board, Color.White));
		        placeNewPiece('e', 2, new Peao(board, Color.White));
		        placeNewPiece('f', 2, new Peao(board, Color.White));
		        placeNewPiece('g', 2, new Peao(board, Color.White));
		        placeNewPiece('h', 2, new Peao(board, Color.White));
			//-------------------------------------------------------------------
		        placeNewPiece('e', 8, new Rei(board, Color.Black));
		        placeNewPiece('c', 8, new Bispo(board, Color.Black));
		        placeNewPiece('f', 8, new Bispo(board, Color.Black));
		        placeNewPiece('a', 8, new Torre(board, Color.Black));
		        placeNewPiece('h', 8, new Torre(board, Color.Black));
		        placeNewPiece('b', 8, new Cavalo(board, Color.Black));
		        placeNewPiece('g', 8, new Cavalo(board, Color.Black));
		        placeNewPiece('a', 7, new Peao(board, Color.Black));
		        placeNewPiece('b', 7, new Peao(board, Color.Black));
		        placeNewPiece('c', 7, new Peao(board, Color.Black));
		        placeNewPiece('d', 7, new Peao(board, Color.Black));
		        placeNewPiece('e', 7, new Peao(board, Color.Black));
		        placeNewPiece('f', 7, new Peao(board, Color.Black));
		        placeNewPiece('g', 7, new Peao(board, Color.Black));
		        placeNewPiece('h', 7, new Peao(board, Color.Black));
			
			
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
			if (testeCheckMate(oponente(jogadorAtual))) {
				checkMate = true;
			} else {
			proximoTurno();
			}
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
			ChessPiece p = (ChessPiece)board.removePiece(origem);
			p.aumentarContadorDeMovimento();
			Piece peçaCapturada = board.removePiece(destino);
			board.placePiece(p, destino);
			if (peçaCapturada != null) {peçasNoTabuleiro.remove(peçaCapturada);
			peçascapturadas.add(peçaCapturada);
			}
			
			return peçaCapturada;
		}
		
		private void desfazerMovimento(Position origem,Position destino,Piece peçaCapturada) {
			ChessPiece p = (ChessPiece)board.removePiece(destino);
			p.diminuirContadorDeMovimento();
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
				if(p instanceof Rei) {
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
		
		private boolean testeCheckMate(Color cor) {
			if (!testeCheck(cor)) {
				return false;
			}
			List<Piece> list = peçasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getColor() == cor).collect(Collectors.toList());
			for (Piece p : list ) {
				boolean[][] mat = p.possiveisMovimentos();
				for (int i=0; i<board.getRows();i++) {
					for (int j=0;j<board.getColumns();j++) {
						if(mat[i][j]) {
							Position origem = ((ChessPiece)p).getPosiçãoDoXadrez().toPosition();
							Position destino = new Position(i,j);
							Piece peçaCapturada = movimento(origem,destino);
							boolean testeCheck = testeCheck(cor);
							desfazerMovimento(origem, destino, peçaCapturada);
							if(!testeCheck) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}
}
