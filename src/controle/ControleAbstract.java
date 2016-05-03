package controle;

import java.awt.event.ActionEvent;

import modelo.Jogo;
import visual.EventosJanela;

public abstract class ControleAbstract {

	protected EventosJanela eventos;
	protected Jogo jogo;

	public ControleAbstract(EventosJanela eventos, Jogo jogo) {
		this.eventos = eventos;
		this.jogo = jogo;
		acoesBotoes();
	}

	private void acoesBotoes() {
		eventos.getBotoes().forEach(b -> b.addActionListener(this::Algoritmo));
		eventos.getNovoJogo().addActionListener(e -> novoJogo());
		eventos.getComputadores().addActionListener(e -> computadores());
		eventos.getLimpar().addActionListener(e -> eventos.getTextArea().setText(null));
	}

	protected abstract void novoJogo();

	protected abstract void computadores();

	protected abstract void Algoritmo(ActionEvent e);

	public void show() {
		eventos.getFrame().setVisible(true);
	}
}
