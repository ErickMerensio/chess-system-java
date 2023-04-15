package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void printBoard(ChessPiece[][] pieces) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	public static void imprimirPartida(ChessMatch partidaDeXadrez,List<ChessPiece> capturas) {
		printBoard(partidaDeXadrez.getPieces());
		System.out.println();
		imprimirPeçaCapturada(capturas);
		System.out.println();
		System.out.println("Turno: " + partidaDeXadrez.getTurno());
		if (!partidaDeXadrez.getCheckMate()) {
		System.out.println("Esperando jogador: " + partidaDeXadrez.getJogadorAtual());
		if (partidaDeXadrez.getCheck()) {
			System.out.println("Você está em CHECK!");
		}
		} else {
			System.out.println("CHECKMATE");
			System.out.println("Ganhador: " + partidaDeXadrez.getJogadorAtual());
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces,boolean [][] possiveisMovimentos) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possiveisMovimentos[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece,boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.White) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	public static ChessPosition lerAPosiçãoDaPeça(Scanner sc) {
		try {
		String s = sc.nextLine();	
		char column = s.charAt(0);
		int row = Integer.parseInt(s.substring(1));
		return new ChessPosition(column,row);
		}catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posição da peça: valores valido de a1 a h8");
		}
	}
	
	// limpar a tela
	public static void limpaTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	private static void imprimirPeçaCapturada(List<ChessPiece> capturada) {
		List<ChessPiece> branca = capturada.stream().filter(x -> x.getColor() == Color.White).collect(Collectors.toList());
		List<ChessPiece> preta = capturada.stream().filter(x -> x.getColor() == Color.Black).collect(Collectors.toList());
		
		System.out.println("Peças capturadas: ");
		System.out.print("Brancas:");
		System.out.println(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.println(ANSI_RESET);
		System.out.print("Pretas:");
		System.out.println(ANSI_RED);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.println(ANSI_RESET);
	}
	
}
