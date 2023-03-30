package grafico;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PnlGrafico extends JPanel
{
	private static final long serialVersionUID = 0L;
	private int qtdOndas;
	private ClsOnda ondas[];

	public PnlGrafico(int qtdOndas, ClsOnda ondas[])
	{
		this.qtdOndas = qtdOndas;
		this.ondas = ondas;
	}

	public void atualizarTela(int qtdOndas, ClsOnda ondas[], Graphics g)
	{
		this.qtdOndas = qtdOndas;
		this.ondas = ondas;
		paintAll(g);
	}

	public void paintAll(Graphics g)
	{
		super.paintComponent(g);
		super.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		int eixoXX[] = {0, 558};
		int eixoXY[] = {100, 100};
		int eixoYX[] = {279, 279};
		int eixoYY[] = {0, 200};

		ClsEixos eixos = new ClsEixos(eixoXX, eixoXY, eixoYX, eixoYY, g, Color.BLACK);

		for (int x = 0; x < qtdOndas; x++)
		{
			g.setColor(ondas[x].getCor());
			g.drawPolyline(ondas[x].obterX(), ondas[x].obterY(), ondas[x].getLargura());
		}
	}

	public void paintComponent(Graphics g)
	{
		paintAll(g);
	}
}
