package grafico;
import java.awt.Color;
import java.text.DecimalFormat;

public class ClsOnda
{
	private int xValues[];
	private int yValues[];
	private double orgY[];
	private Color cor;
	private int largura;
	private int amplitude;
	private double periodo;

	public ClsOnda(int X[], double Y[], Color cor, int largura, int inicio, double zoom)
	{
		this.amplitude = 0;
		this.periodo = 0;
		this.largura = largura;
		this.cor = cor;
		xValues = X;
		orgY = Y;
		yValues = new int[Y.length];
		for (int x = 0; x < Y.length; x++)
		{
			xValues[x] /= zoom; 
			yValues[x] = Math.round(inicio + (int)((orgY[x]) * zoom));
		}
	}

	public ClsOnda(int amplitude, double periodo, int largura, int altura, int inicio, Color cor, double zoom)
	{
		this.amplitude = amplitude;
		this.periodo = periodo;
		this.cor = cor;
		this.largura = largura;
		xValues = new int[largura];
		yValues = new int[largura];
		orgY = new double[largura];

		for(int x = 0; x < largura/2; x++)
		{
			xValues[largura/2 - x] = (int) ((largura/2 - x));
			yValues[largura/2 - x] = (int) (amplitude * Math.sin(((x * Math.pow((2 *Math.PI),2))/((largura/2) * periodo * zoom)))*zoom + inicio);
			orgY[largura/2 - x] = (int) (amplitude * Math.sin(((x * Math.pow((2 *Math.PI),2))/((largura/2) * periodo))));

			xValues[x + largura/2] = (int) ((largura/2 + x));
			yValues[x + largura/2] = (int) (-amplitude * Math.sin(((x * Math.pow((2 *Math.PI),2))/((largura/2) * periodo * zoom)))*zoom + inicio);
			orgY[x + largura/2] = (int) (-amplitude * Math.sin(((x * Math.pow((2 *Math.PI),2))/((largura/2) * periodo))));
		}
	}
	public Color getCor()
	{
		return cor;
	}

	public int[] obterX()
	{
		return xValues;
	}

	public int[] obterY()
	{
		return yValues;
	}

	public double[] obterOrgY()
	{
		return orgY;
	}
	public int getLargura()
	{
		return largura;
	}

	public int obterAmplitude()
	{
		return Math.abs(amplitude);
	}
	public double obterPeriodo()
	{
		return (periodo/Math.PI);
	}
	public String obterPeriodoFormatado()
	{
		DecimalFormat fmt = new DecimalFormat("0.0000");
		String part[] = fmt.format(periodo/Math.PI).split("[,]");
		return (part[0] + "." + part[1]);
	}
}
