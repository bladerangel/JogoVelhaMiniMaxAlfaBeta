package principal;

import controle.ControleEventosJogo;
import modelo.Jogo;
import visual.EventosJanela;

public class Main {

	public static void main(String[] args) {
		EventosJanela eventos = new EventosJanela();
		Jogo jogo = new Jogo();
		ControleEventosJogo jogoJanela = new ControleEventosJogo(eventos, jogo);
		jogoJanela.show();
	}
}
