package visual;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class Janela {

	protected JPanel contentPane;
	protected JPanel panel;
	protected JLabel imagemLog;
	protected JLabel imagemJogo;
	protected JTextArea textArea;
	protected JScrollPane scrollPane;
	protected List<JButton> botoes;
	protected JButton novoJogo;
	protected JButton computadores;
	protected JButton limpar;
	protected JFrame frame;

	public Janela() {
		contentPane = new JPanel();
		panel = new JPanel();
		imagemLog = new JLabel();
		imagemJogo = new JLabel();
		textArea = new JTextArea();
		scrollPane = new JScrollPane();
		botoes = new ArrayList<JButton>();
		novoJogo = new JButton();
		computadores = new JButton();
		limpar = new JButton();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 711, 553);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(57, 123, 273, 273);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 3, 3, 3));
		scrollPane.setBounds(380, 123, 286, 273);
		contentPane.add(scrollPane);
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		scrollPane.setViewportView(textArea);
		novoJogo.setIcon(new ImageIcon("imagens/novo.png"));
		novoJogo.setBounds(57, 419, 131, 40);
		contentPane.add(novoJogo);
		computadores.setIcon(new ImageIcon("imagens/computadores.png"));
		computadores.setBounds(198, 419, 131, 40);
		contentPane.add(computadores);
		imagemJogo.setIcon(new ImageIcon("imagens/jogoVelha.png"));
		imagemJogo.setBounds(89, 40, 208, 54);
		contentPane.add(imagemJogo);
		imagemLog.setIcon(new ImageIcon("imagens/log.png"));
		imagemLog.setBounds(419, 40, 208, 54);
		contentPane.add(imagemLog);
		limpar.setIcon(new ImageIcon("imagens/limpar.png"));
		limpar.setBounds(458, 419, 131, 40);
		contentPane.add(limpar);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getImagemLog() {
		return imagemLog;
	}

	public JLabel getImagemJogo() {
		return imagemJogo;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public List<JButton> getBotoes() {
		return botoes;
	}

	public JButton getNovoJogo() {
		return novoJogo;
	}

	public JButton getComputadores() {
		return computadores;
	}

	public JButton getLimpar() {
		return limpar;
	}

	public JFrame getFrame() {
		return frame;
	}

}
