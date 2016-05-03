package modelo.algoritmo;

import java.util.ArrayList;
import java.util.List;

import modelo.Jogo;

public class Heuristica {

	private Pontuacao pontuacao;

	public Heuristica() {
		pontuacao = new Pontuacao();
	}

	private int heuristicaPontuacao(char[] tabuleiro, int profundidade) {
		int vencedor = checarVencedor(tabuleiro);
		if (vencedor == 1)
			return 0;
		else if (vencedor == 2)
			return profundidade - 10;
		else
			return 10 - profundidade;
	}

	public int checarVencedor(char[] tabuleiro) {
		for (int i = 0; i <= 6; i += 3) {
			if (tabuleiro[i] == Jogo.HUMANO && tabuleiro[i + 1] == Jogo.HUMANO && tabuleiro[i + 2] == Jogo.HUMANO)
				return 2;
			if (tabuleiro[i] == Jogo.COMPUTADOR && tabuleiro[i + 1] == Jogo.COMPUTADOR
					&& tabuleiro[i + 2] == Jogo.COMPUTADOR)
				return 3;
		}

		for (int i = 0; i <= 2; i++) {
			if (tabuleiro[i] == Jogo.HUMANO && tabuleiro[i + 3] == Jogo.HUMANO && tabuleiro[i + 6] == Jogo.HUMANO)
				return 2;
			if (tabuleiro[i] == Jogo.COMPUTADOR && tabuleiro[i + 3] == Jogo.COMPUTADOR
					&& tabuleiro[i + 6] == Jogo.COMPUTADOR)
				return 3;
		}

		if ((tabuleiro[0] == Jogo.HUMANO && tabuleiro[4] == Jogo.HUMANO && tabuleiro[8] == Jogo.HUMANO)
				|| (tabuleiro[2] == Jogo.HUMANO && tabuleiro[4] == Jogo.HUMANO && tabuleiro[6] == Jogo.HUMANO))
			return 2;

		if ((tabuleiro[0] == Jogo.COMPUTADOR && tabuleiro[4] == Jogo.COMPUTADOR && tabuleiro[8] == Jogo.COMPUTADOR)
				|| (tabuleiro[2] == Jogo.COMPUTADOR && tabuleiro[4] == Jogo.COMPUTADOR
						&& tabuleiro[6] == Jogo.COMPUTADOR))
			return 3;

		for (int i = 0; i < tabuleiro.length; i++) {
			if (tabuleiro[i] != Jogo.HUMANO && tabuleiro[i] != Jogo.COMPUTADOR)
				return 0;
		}
		return 1;
	}

	private char[] voltarMovimento(char[] tabuleiro, int indice) {
		tabuleiro[indice] = Jogo.VAZIO;
		return tabuleiro;
	}

	private char[] novoMovimento(char[] tabuleiro, int indice, char jogador) {
		tabuleiro[indice] = jogador;
		return tabuleiro;
	}

	private List<Integer> movimentosValidos(char[] tabuleiro) {
		List<Integer> movimentosPossiveis = new ArrayList<Integer>();
		for (int i = 0; i < tabuleiro.length; i++) {
			if (tabuleiro[i] == Jogo.VAZIO)
				movimentosPossiveis.add(i);
		}
		return movimentosPossiveis;
	}

	public char trocarJogador(char jogador) {
		if (jogador == Jogo.COMPUTADOR)
			jogador = Jogo.HUMANO;
		else
			jogador = Jogo.COMPUTADOR;
		return jogador;
	}

	public Pontuacao alfaBetaMiniMax(char[] tabuleiro, int profundidade, int alfa, int beta, char jogador) {
		if (checarVencedor(tabuleiro) != 0) {
			pontuacao.setHeuristica(heuristicaPontuacao(tabuleiro, profundidade));
			return pontuacao;
		}

		profundidade++;
		int indice = -1;
		List<Integer> movimentosValidos = movimentosValidos(tabuleiro);

		for (int movimentoIndice : movimentosValidos) {
			char[] novoTabuleiro = novoMovimento(tabuleiro, movimentoIndice, jogador);
			jogador = trocarJogador(jogador);
			int heuristica = alfaBetaMiniMax(novoTabuleiro, profundidade, alfa, beta, jogador).getHeuristica();
			tabuleiro = voltarMovimento(tabuleiro, movimentoIndice);
			jogador = trocarJogador(jogador);

			if (jogador == Jogo.COMPUTADOR) {
				if (heuristica > alfa) {
					alfa = heuristica;
					if (profundidade == 1)
						indice = movimentoIndice;
				} else if (alfa >= beta) {
					pontuacao.setHeuristica(beta);
					return pontuacao;
				}

			} else {
				if (heuristica < beta) {
					beta = heuristica;
					if (profundidade == 1)
						indice = movimentoIndice;
				} else if (beta <= alfa) {
					pontuacao.setHeuristica(alfa);
					return pontuacao;
				}

			}
		}
		if (jogador == Jogo.COMPUTADOR) {
			pontuacao.setHeuristica(alfa);
			pontuacao.setIndice(indice);
			return pontuacao;
		} else {
			pontuacao.setHeuristica(beta);
			pontuacao.setIndice(indice);
			return pontuacao;
		}
	}
}
