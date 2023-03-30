package grafico;
import java.awt.Color;
import java.awt.Graphics;

public class ClsEixos
{
	public ClsEixos(int eixoXX[], int eixoXY[], int eixoYX[], int eixoYY[], Graphics g, Color cor)
	{
		//EIXO X

		g.setColor(cor);
		g.drawPolyline(eixoXX, eixoXY, 2);

		//EIXO Y

		g.setColor(cor);
		g.drawPolyline(eixoYX, eixoYY, 2);
	}
}
