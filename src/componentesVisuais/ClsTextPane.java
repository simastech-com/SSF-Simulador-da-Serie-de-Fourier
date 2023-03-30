//http://docs.oracle.com/javase/7/docs/api/javax/swing/JTextPane.html
//http://www.java2s.com/Tutorials/Java/Swing_How_to/JTextPane/Pad_text_in_JTextPane.htm
//http://www.java2s.com/Tutorial/Java/0240__Swing/SimpleAttributeBoldItalic.htm
//https://docs.oracle.com/javase/tutorial/uiswing/components/editorpane.html

package componentesVisuais;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ClsTextPane
{
	private JTextPane  tpnTextPane;
	private Style stlEstilo;
	private StyledDocument stdDocumento;
	private SimpleAttributeSet sasAtributos;
	/*------------------------------------------------------------------------------------------------------------------------*/
	public ClsTextPane(JTextPane pTextPane)
	{
		tpnTextPane = pTextPane;
		stlEstilo = tpnTextPane.addStyle("stlEstilo", null);
		stdDocumento = tpnTextPane.getStyledDocument();
		sasAtributos = new SimpleAttributeSet();
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void alinharTextoADireita(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto)
	{
		StyleConstants.setAlignment(sasAtributos, StyleConstants.ALIGN_RIGHT);
		tpnTextPane.getStyledDocument().setParagraphAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, sasAtributos, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void alinharTextoAEsquerda(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto)
	{
		StyleConstants.setAlignment(sasAtributos, StyleConstants.ALIGN_LEFT);
		tpnTextPane.getStyledDocument().setParagraphAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, sasAtributos, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void alinharTextoAoCentro(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto)
	{
		StyleConstants.setAlignment(sasAtributos, StyleConstants.ALIGN_CENTER);
		tpnTextPane.getStyledDocument().setParagraphAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, sasAtributos, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void alinharTextoJustificar(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto)
	{
		StyleConstants.setAlignment(sasAtributos, StyleConstants.ALIGN_JUSTIFIED);
		tpnTextPane.getStyledDocument().setParagraphAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, sasAtributos, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarNegrito(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, boolean pblnAplicar)
	{
		StyleConstants.setBold(stlEstilo, pblnAplicar);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarItalico(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, boolean pblnAplicar)
	{
		StyleConstants.setItalic(stlEstilo, pblnAplicar);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarSublinhado(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, boolean pblnAplicar)
	{
		StyleConstants.setUnderline(stlEstilo, pblnAplicar);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarTamanhoDaFonte(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, int pintTamanho)
	{
		StyleConstants.setFontSize(stlEstilo, pintTamanho);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarCorDaFonte(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, Color pclrCor)
	{
		StyleConstants.setForeground(stlEstilo, pclrCor);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarCorDaFonteEItalico(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, Color pclrCor)
	{
		StyleConstants.setForeground(stlEstilo, pclrCor);
		StyleConstants.setItalic(stlEstilo, true);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarCorDaFonteENegrito(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, Color pclrCor)
	{
		StyleConstants.setForeground(stlEstilo, pclrCor);
		StyleConstants.setBold(stlEstilo, true);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void formatarFonte(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto, Color pclrCor, boolean pblnNegrito, boolean pblnItalico, boolean pblnSublinhado, int pintTamanho, String strFonte)
	{
		StyleConstants.setForeground(stlEstilo, pclrCor);
		StyleConstants.setBold(stlEstilo, pblnNegrito);
		StyleConstants.setItalic(stlEstilo, pblnItalico);
		StyleConstants.setUnderline(stlEstilo, pblnSublinhado);
		StyleConstants.setFontSize(stlEstilo, pintTamanho);
		StyleConstants.setFontFamily(stlEstilo, strFonte);
		stdDocumento.setCharacterAttributes(0, tpnTextPane.getText().length(), stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void removerFormatacao(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto)
	{
		StyleConstants.setForeground(stlEstilo, Color.BLACK);
		StyleConstants.setBold(stlEstilo, false);
		StyleConstants.setItalic(stlEstilo, false);
		StyleConstants.setUnderline(stlEstilo, false);
		StyleConstants.setFontSize(stlEstilo, 12);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarCorDeFundo(Color pclrCor)
	{
		tpnTextPane.setBackground(pclrCor);
	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	public void aplicarCorDeFundo(int pintPosicaoInicialDoTexto, int pintPosicaoFinalDoTexto,Color pclrCor)
	{
		StyleConstants.setBackground(stlEstilo, pclrCor);
		stdDocumento.setCharacterAttributes(pintPosicaoInicialDoTexto, pintPosicaoFinalDoTexto, stlEstilo, false);
	}
}
