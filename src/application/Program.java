package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.chessException;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
		try {
			UI.limpaTela();
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.println("Origem: ");
			ChessPosition origem = UI.lerAPosiçãoDaPeça(sc);
			
			boolean [][] possiveisMovimento = chessMatch.possiveisMovimentos(origem);
			UI.limpaTela();
			UI.printBoard(chessMatch.getPieces(), possiveisMovimento);
			
			System.out.println();
			System.out.println("Destino: ");
			ChessPosition destino = UI.lerAPosiçãoDaPeça(sc);
			
			ChessPiece peçaCapturada = chessMatch.performanceDeMovimentoDoXadrez(origem, destino);
		} catch(chessException e) {
			System.out.println(e.getMessage());
			sc.nextLine();
		}
		  catch(InputMismatchException i) {
			System.out.println(i.getMessage());
			sc.nextLine();
			}
		
	}

}
}