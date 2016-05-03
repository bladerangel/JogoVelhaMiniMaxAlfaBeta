package visual;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EventosJanela extends Janela {

	public EventosJanela() {
		preencherPanel();
	}

	public void printTabuleiro(char[] tabuleiro) {
		for (int i = 0; i < 9; i += 3) {
			if (i > 0)
				textArea.append("---+---+---\n");
			textArea.append(" " + tabuleiro[i] + " | " + tabuleiro[i + 1] + " | " + tabuleiro[i + 2] + "\n");
		}
	}

	public void printGameOver(int vencedor) {
		if (vencedor == 0) {
			// textArea.append("sua vez de jogar!\n");
		} else if (vencedor == 1) {
			textArea.append("Empate!\n");
			JOptionPane.showMessageDialog(null, "Empate!");
		} else if (vencedor == 2) {
			textArea.append("Você ganhou!\n");
			JOptionPane.showMessageDialog(null, "Você ganhou!");
		} else {
			textArea.append("Você perdeu!\n");
			JOptionPane.showMessageDialog(null, "Você perdeu!");
		}
	}

	public void preencherPanel() {
		for (int i = 0; i < 9; i++) {
			JButton botao = new JButton();
			botao.setBackground(Color.BLACK);
			panel.add(botao);
			botoes.add(botao);
		}
	}

	public void limpar() {
		panel.removeAll();
		botoes.forEach(b -> {
			b.setIcon(null);
			panel.add(b);
		});
		panel.validate();
		panel.repaint();
	}
}
