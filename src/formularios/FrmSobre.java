package formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import componentesVisuais.ClsTextPane;

public class FrmSobre extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel pnlBotoes = null;
	private JButton btnFechar = null;
	private JTextPane tpnSobre = null;

	public FrmSobre()
	{
		super();
		initialize();
	}

	private void initialize()
	{
		this.setSize(566, 466);
		this.setContentPane(getJContentPane());
		this.setTitle("Sobre");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPnlBotoes(), BorderLayout.SOUTH);
			jContentPane.add(getTpnSobre(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private JPanel getPnlBotoes()
	{
		if (pnlBotoes == null)
		{
			pnlBotoes = new JPanel();
			pnlBotoes.setLayout(new FlowLayout());
			pnlBotoes.add(getBtnFechar(), null);
		}
		return pnlBotoes;
	}

	private JButton getBtnFechar()
	{
		if (btnFechar == null)
		{
			btnFechar = new JButton();
			btnFechar.setText("Fechar");
			btnFechar.addActionListener(
					                        new java.awt.event.ActionListener()
											{
												public void actionPerformed(java.awt.event.ActionEvent e)
												{
													dispose();
												}
											}
					                    );
		}
		return btnFechar;
	}

	private JTextPane getTpnSobre()
	{
		if (tpnSobre == null)
		{
			tpnSobre = new JTextPane();
			int intPosicaoInicial = 0;
			ClsTextPane objTextPane = new ClsTextPane(tpnSobre);
			String strTextoFinal;
			String strDescricao = "Software desenvolvido na disciplina de Processamento Digital de Sinais(PDS)\n\n";
			String strCabecalho = "SSF\n" +
		                          "Simulador da s�rie de Fourier\n\n";

			String strAutor = "Desenvolvido por:\n" +
				              "\t\t\t\t\t\t\t\t\t\tClaudio Barbosa do Nascimento Neto.\n"+
				              "\t\t\t\t\t\t\t\t\t\tDavid Allan dos Reis Borelli.\n"+
				              "\t\t\t\t\t\t\t\t\t\tGuilherme da Silva Oliveira.\n"+
				              "\t\t\t\t\t\t\t\t\t\tJessiclei Ferreira Rodrigues.\n"+
				              "\t\t\t\t\t\t\t\t\t\tWilliam Cardoso Simas.\n\n"+
				              "\t\t\t\t\t\t\t\t\t\tEngenharia da Computa��o - 8� termo.\n" +
				              "\t\t\t\t\t\t\t\t\t\tCentro Universit�rio Cat�lico Salesiano Auxilium - Ara�atuba(SP).\n\n" +
				              "\t\t\t\t\t\t\t\t\t\tTamiris Ayumi Aoe.\n\n"+
				              "\t\t\t\t\t\t\t\t\t\tEngenharia de Telecomunica��es\n" +
				              "\t\t\t\t\t\t\t\t\t\tCentro Universit�rio Cat�lico Salesiano Auxilium - Ara�atuba(SP).\n\n";
			String strOrientadores = "Orientado por:\n" +
            						 "\t\t\t\t\t\t\t\t\t\tMsc. Jos� Vital Ferraz Le�o.\n"+
                                     "\t\t\t\t\t\t\t\t\t\tCentro Universit�rio Cat�lico Salesiano Auxilium - Ara�atuba(SP).\n\n";

			strTextoFinal = strCabecalho + strDescricao + strAutor + strOrientadores;

			tpnSobre.setText(strTextoFinal);

			objTextPane.alinharTextoAoCentro(intPosicaoInicial, strCabecalho.length());
			objTextPane.aplicarNegrito(intPosicaoInicial, strCabecalho.length(), true);
			objTextPane.aplicarTamanhoDaFonte(intPosicaoInicial, strCabecalho.length(), 16);

			objTextPane.aplicarCorDaFonte(intPosicaoInicial, strCabecalho.length(), Color.BLACK);

			intPosicaoInicial += strCabecalho.length();
			objTextPane.alinharTextoAoCentro(intPosicaoInicial, intPosicaoInicial + strAutor.length());
			objTextPane.aplicarTamanhoDaFonte(intPosicaoInicial, intPosicaoInicial + strAutor.length(), 12);
			objTextPane.aplicarNegrito(intPosicaoInicial, intPosicaoInicial + strAutor.length(), false);

			intPosicaoInicial += strDescricao.length();
			objTextPane.alinharTextoJustificar(intPosicaoInicial, intPosicaoInicial + strAutor.length());
			objTextPane.aplicarTamanhoDaFonte(intPosicaoInicial, intPosicaoInicial + strAutor.length(), 12);
			objTextPane.aplicarNegrito(intPosicaoInicial, intPosicaoInicial + 3, true);
			objTextPane.aplicarNegrito(intPosicaoInicial + 18, intPosicaoInicial + strAutor.length(), false);

			intPosicaoInicial += strAutor.length();
			objTextPane.alinharTextoJustificar(intPosicaoInicial, intPosicaoInicial + strOrientadores.length());
			objTextPane.aplicarTamanhoDaFonte(intPosicaoInicial, intPosicaoInicial + strOrientadores.length(), 12);
			objTextPane.aplicarNegrito(intPosicaoInicial, intPosicaoInicial + 14, true);
			objTextPane.aplicarNegrito(intPosicaoInicial + 15, intPosicaoInicial + strOrientadores.length(), false);

			tpnSobre.setEditable(false);
		}
		return tpnSobre;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
