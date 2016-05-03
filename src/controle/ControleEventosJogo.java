package controle;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingWorker;

import modelo.Jogo;
import visual.EventosJanela;

public class ControleEventosJogo extends ControleAbstract {

	public ControleEventosJogo(EventosJanela eventos, Jogo jogo) {
		super(eventos, jogo);
	}

	@Override
	protected void novoJogo() {
		eventos.getTextArea().append("Começando outro jogo!\n");
		jogo.comecarJogo();
		eventos.limpar();
	}

	private int humanoMovimentacao(int pos) {
		if (!jogo.GameOver() && jogo.getTabuleiro()[pos] == Jogo.VAZIO) {
			jogo.getTabuleiro()[pos] = jogo.mudarTurnoJogador();
			eventos.printTabuleiro(jogo.getTabuleiro());
			if (!jogo.GameOver()) {
				return computadorMovimentacao();
			}
			jogo.mudarTurnoJogador();
			return pos;
		}
		return -1;
	}

	private int computadorMovimentacao() {
		int indice = jogo.getHeuristica()
				.alfaBetaMiniMax(jogo.getTabuleiro(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, jogo.getTurnoJogador())
				.getIndice();
		jogo.getTabuleiro()[indice] = jogo.mudarTurnoJogador();
		eventos.printTabuleiro(jogo.getTabuleiro());
		return indice;
	}

	@Override
	protected void computadores() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				novoJogo();
				eventos.getNovoJogo().setEnabled(false);
				eventos.getComputadores().setEnabled(false);
				for (int i = 0; i < jogo.getTabuleiro().length; i++) {
					int indice = computadorMovimentacao();
					jogo.mudarTurnoJogador();
					eventos.getBotoes().get(indice)
							.setIcon(new ImageIcon("imagens/" + jogo.mudarTurnoJogador() + ".png"));
					eventos.getTextArea().append("Computador jogou na casa: " + indice + "\n\n");
					eventos.printGameOver(jogo.getHeuristica().checarVencedor(jogo.getTabuleiro()));
					Thread.sleep(500);
				}
				return null;
			}

			@Override
			protected void done() {
				eventos.getNovoJogo().setEnabled(true);
				eventos.getComputadores().setEnabled(true);
			}
		}.execute();
	}

	@Override
	protected void Algoritmo(ActionEvent e) {
		if (eventos.getComputadores().isEnabled()) {
			JButton botao = (JButton) e.getSource();
			int indice = humanoMovimentacao(eventos.getBotoes().indexOf(botao));
			if (indice != -1) {
				botao.setIcon(new ImageIcon("imagens/" + jogo.mudarTurnoJogador() + ".png"));
				eventos.getTextArea().append("Você jogou na casa: " + eventos.getBotoes().indexOf(botao) + "\n\n");
				if (eventos.getBotoes().indexOf(botao) != indice) {
					eventos.getBotoes().get(indice)
							.setIcon(new ImageIcon("imagens/" + jogo.mudarTurnoJogador() + ".png"));
					eventos.getTextArea().append("Computador jogou na casa: " + indice + "\n\n");
				}
			}
			eventos.printGameOver(jogo.getHeuristica().checarVencedor(jogo.getTabuleiro()));
		}
	}
}
