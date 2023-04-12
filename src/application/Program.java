package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
		UI.printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("Origem: ");
		ChessPosition origem = UI.lerAPosiçãoDaPeça(sc);
		
		System.out.println();
		System.out.println("Destino: ");
		ChessPosition destino = UI.lerAPosiçãoDaPeça(sc);
		
		ChessPiece peçaCapturada = chessMatch.performanceDeMovimentoDoXadrez(origem, destino);
		
		}
		
	}

}
