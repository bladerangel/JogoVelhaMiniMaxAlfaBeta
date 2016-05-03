package modelo;

import modelo.algoritmo.Heuristica;

public class Jogo {

	private char[] tabuleiro;
	public static char VAZIO = ' ';
	public static char HUMANO = 'O';
	public static char COMPUTADOR = 'X';
	private char turnoJogador;
	private Heuristica heuristica;

	public Jogo() {
		tabuleiro = new char[9];
		heuristica = new Heuristica();
		comecarJogo();
	}

	public void comecarJogo() {
		for (int i = 0; i < tabuleiro.length; i++) {
			tabuleiro[i] = VAZIO;
		}
		turnoJogador = HUMANO;
	}

	public boolean GameOver() {
		int vencedor = heuristica.checarVencedor(tabuleiro);
		if (vencedor == 0) {
			return false;
		}
		return true;
	}

	public char mudarTurnoJogador() {
		if (turnoJogador == COMPUTADOR) {
			turnoJogador = HUMANO;
			return 'X';
		} else {
			turnoJogador = COMPUTADOR;
			return 'O';
		}
	}

	public char[] getTabuleiro() {
		return tabuleiro;
	}

	public Heuristica getHeuristica() {
		return heuristica;
	}

	public char getTurnoJogador() {
		return turnoJogador;
	}
}
