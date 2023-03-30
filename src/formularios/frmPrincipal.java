package formularios;

import grafico.ClsOnda;
import grafico.PnlGrafico;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.WindowConstants;
import java.text.DecimalFormat;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.lang.Object;
import javax.swing.JTextField;

public class frmPrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private PnlGrafico pnlGrafico = null;
	private PnlGrafico pnlGraficoTotal = null;
	private JPanel pnlGraficos = null;
	private JPanel pnlControles = null;
	private JPanel pnlRodape = null;
	private JButton btnSobre = null;
	private JButton btnSair = null;
	private ClsOnda objOndas[];
	private JLabel lblFourier = null;
	private JLabel lblOndasSenoidais = null;
	private int intQtdOndas = 0;
	ClsOnda objSoma[];
	private int intAmplitudeInicial = 0;
	private double dblPeriodoInicial = 0;
	private float fltZoomSenoidal = 1;
	private float fltZoomSuperposicao = 1;
	private boolean blnQuadrada = false;
	private boolean blnTriangular = false;
	private JPanel pnlZoomOndasSenoidais = null;
	private JButton btnZoomMaisOndasSenoidais = null;
	private JButton btnZoomMenosOndasSenoidais = null;
	private JButton btnZoomNormalOndasSenoidais = null;
	private JPanel pnlZoomSuperposicao = null;
	private JPanel pnlCriacaoOndas = null;
	private JRadioButton rdbOndaTriangular = null;
	private JRadioButton rdbOndaQuadrada = null;
	private JSlider sldQtdOndas = null;
	private JLabel lblqtdOndas = null;
	private JButton btnMenosOndas = null;
	private JButton btnMaisOndas = null;
	private JScrollPane scrOndas = null;
	private JTable tblOndas = null;
	private JLabel lblAmplitude = null;
	private JTextField txtAmplitude = null;
	private JLabel lblPeriodo = null;
	private JTextField txtPeriodo = null;
	public frmPrincipal()
	{
		super();
		initialize();
	}

	private Color defineCor(int x)
	{
		Color retorno = Color.WHITE;
		if (x%15 ==1)
			retorno = Color.RED;
		if (x%15 ==2)
			retorno = Color.BLUE;
		if (x%15 ==3)
			retorno = Color.BLACK;
			//retorno = Color.YELLOW;
		if (x%15 ==4)
			retorno = Color.GREEN;
		if (x%15 ==5)
			retorno = Color.ORANGE;
		if (x%15 ==6)
			retorno = new Color(139,0,139);
		if (x%15 ==7)
			retorno = new Color(255,20,147);
		if (x%15 ==8)
			retorno = new Color(0,206,209);
		if (x%15 ==9)
			retorno = new Color(255,140,0);
		if (x%15 ==10)
			retorno = new Color(255,215,0);
		if (x%15 ==11)
			retorno = new Color(128,128,0);
		if (x%15 ==12)
			retorno = new Color(173,255,47);
		if (x%15 ==13)
			retorno = new Color(218,165,32);
		if (x%15 ==14)
			retorno = new Color(210,105,30);
		if (x%15 ==0)
			retorno = new Color(220,20,60);
		return retorno;
	}

	private void definirOndas()
	{
		objOndas = new ClsOnda[intQtdOndas];
		Color cor = Color.BLACK;
		for(int x=1; x<=intQtdOndas; x++)
		{
			cor = defineCor(x);
			int amplitude = 0;
			double periodo = 0;
			if(blnQuadrada)
			{
				amplitude = (int)(Math.round(1.57 * ((intAmplitudeInicial)/(Math.PI * x))*(1-Math.cos(x*Math.PI))));
				periodo = (dblPeriodoInicial*Math.PI)/x;
			}
			if(blnTriangular)
			{
				amplitude = (int)(Math.round(9.9 * (intAmplitudeInicial)/Math.pow(Math.PI, 2)) * ((Math.pow((-1),(x-1)/2))/Math.pow(x, 2)));
				periodo = (dblPeriodoInicial*Math.PI)/x;
			}
			objOndas[x-1] = new ClsOnda(amplitude, periodo, 558, 558, 100, cor, fltZoomSenoidal);
		}
	}
	private void definirSoma()
	{
		int X[] = new int[558];
		double Y[] = new double[558];
		for(int x=0; x<intQtdOndas; x++)
		{
			for(int y=0; y<558; y++)
			{
				if(x==0)
				{
					Y[y] = 0;
				}
				X[y] = y;
				Y[y] += objOndas[x].obterOrgY()[y];
			}
		}
		objSoma = new ClsOnda[1];
		objSoma[0] = new ClsOnda(X, Y, Color.RED, 558, 100, fltZoomSuperposicao);
	}

	private void aplicarZoomSenoidal()
	{
		definirOndas();
		DecimalFormat fmt = new DecimalFormat("0.0");
		String part[] = fmt.format(fltZoomSenoidal).split("[,]");
		btnZoomNormalOndasSenoidais.setText(part[0]+ "." +part[1]);
		pnlGrafico.atualizarTela(intQtdOndas, objOndas, getPnlGrafico().getGraphics());
	}

	private void alterarQtdOndas()
	{
		definirOndas();
		definirSoma();
		lblqtdOndas.setText("Qtd Ondas: " + sldQtdOndas.getValue());
		pnlGrafico.atualizarTela(intQtdOndas, objOndas, getPnlGrafico().getGraphics());
		pnlGraficoTotal.atualizarTela(1, objSoma, getPnlGraficoTotal().getGraphics());
		tblOndas.updateUI();
	}

	private PnlGrafico getPnlGrafico()
	{
		definirOndas();
		if (pnlGrafico == null)
		{
			pnlGrafico = new PnlGrafico(intQtdOndas, objOndas);
			pnlGrafico.setLayout(null);
			pnlGrafico.setBounds(new Rectangle(0, 28, 560, 200));
			pnlGrafico.setBackground(Color.WHITE);
			pnlGrafico.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		}
		return pnlGrafico;
	}

	private PnlGrafico getPnlGraficoTotal()
	{
		definirSoma();
		if (pnlGraficoTotal == null)
		{
			pnlGraficoTotal = new PnlGrafico(1, objSoma);
			pnlGraficoTotal.setLayout(null);
			pnlGraficoTotal.setBounds(new Rectangle(0, 255, 560, 200));
			pnlGraficoTotal.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			pnlGraficoTotal.setBackground(Color.WHITE);
		}
		return pnlGraficoTotal;
	}

	private JPanel getPnlGraficos()
	{
		if (pnlGraficos == null)
		{
			lblOndasSenoidais = new JLabel();
			lblOndasSenoidais.setBounds(new Rectangle(0, 4, 620, 24));
			lblOndasSenoidais.setHorizontalAlignment(SwingConstants.CENTER);
			lblOndasSenoidais.setText("Ondas senoidais");
			lblOndasSenoidais.setFont(new Font("Dialog", Font.BOLD, 18));
			lblFourier = new JLabel();
			lblFourier.setBounds(new Rectangle(0, 229, 620, 24));
			lblFourier.setFont(new Font("Dialog", Font.BOLD, 18));
			lblFourier.setHorizontalAlignment(SwingConstants.CENTER);
			lblFourier.setText("Superposição");
			pnlGraficos = new JPanel();
			pnlGraficos.setBounds(new Rectangle(0, 0, 620, 455));
			pnlGraficos.setLayout(null);
			pnlGraficos.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			pnlGraficos.add(getPnlGrafico(), null);
			pnlGraficos.add(getPnlGraficoTotal(), null);
			pnlGraficos.add(lblFourier, null);
			pnlGraficos.add(lblOndasSenoidais, null);
			pnlGraficos.add(getPnlZoomOndasSenoidais(), null);
			pnlGraficos.add(getPnlZoomSuperposicao(), null);
		}
		return pnlGraficos;
	}

	private JPanel getPnlControles()
	{
		if (pnlControles == null)
		{
			pnlControles = new JPanel();
			pnlControles.setLayout(null);
			pnlControles.setBounds(new Rectangle(618, 0, 376, 455));
			pnlControles.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			pnlControles.add(getPnlCriacaoOndas(), null);
			pnlControles.add(getScrOndas(), null);
		}
		return pnlControles;
	}

	private JPanel getPnlRodape()
	{
		if (pnlRodape == null)
		{
			pnlRodape = new JPanel();
			pnlRodape.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pnlRodape.setBounds(new Rectangle(0, 455, 995, 39));
			pnlRodape.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			pnlRodape.add(getBtnSobre(), null);
			pnlRodape.add(getBtnSair(), null);
		}
		return pnlRodape;
	}

	private JButton getBtnSobre()
	{
		if (btnSobre == null)
		{
			btnSobre = new JButton();
			btnSobre.setText("Sobre...");
			btnSobre.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					FrmSobre janelaSobre = new FrmSobre();
					janelaSobre.setVisible(true);
				}
			}
			);
		}
		return btnSobre;
	}

	private JButton getBtnSair()
	{
		if (btnSair == null)
		{
			btnSair = new JButton();
			btnSair.setText("Sair");
			btnSair.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					dispose();
				}
			}
			);
		}
		return btnSair;
	}

	private JPanel getPnlZoomOndasSenoidais()
	{
		if (pnlZoomOndasSenoidais == null)
		{
			pnlZoomOndasSenoidais = new JPanel();
			pnlZoomOndasSenoidais.setLayout(null);
			pnlZoomOndasSenoidais.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			pnlZoomOndasSenoidais.setBounds(new Rectangle(558, 28, 62, 200));
			pnlZoomOndasSenoidais.add(getBtnZoomMaisOndasSenoidais(), null);
			pnlZoomOndasSenoidais.add(getBtnZoomMenosOndasSenoidais(), null);
			pnlZoomOndasSenoidais.add(getBtnZoomNormalOndasSenoidais(), null);
		}
		return pnlZoomOndasSenoidais;
	}

	private JButton getBtnZoomMaisOndasSenoidais()
	{
		if (btnZoomMaisOndasSenoidais == null)
		{
			btnZoomMaisOndasSenoidais = new JButton();
			btnZoomMaisOndasSenoidais.setBounds(new Rectangle(2, 2, 58, 65));
			btnZoomMaisOndasSenoidais.setFont(new Font("Dialog", Font.PLAIN, 24));
			btnZoomMaisOndasSenoidais.setText("+");
			btnZoomMaisOndasSenoidais
					.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							fltZoomSenoidal += 0.1;
							aplicarZoomSenoidal();
						}
					}
					);
		}
		return btnZoomMaisOndasSenoidais;
	}

	private JButton getBtnZoomMenosOndasSenoidais()
	{
		if (btnZoomMenosOndasSenoidais == null)
		{
			btnZoomMenosOndasSenoidais = new JButton();
			btnZoomMenosOndasSenoidais.setBounds(new Rectangle(2, 132, 58, 66));
			btnZoomMenosOndasSenoidais.setFont(new Font("Dialog", Font.PLAIN, 24));
			btnZoomMenosOndasSenoidais.setText("-");
			btnZoomMenosOndasSenoidais
					.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							if (fltZoomSenoidal > 1)
								fltZoomSenoidal -= 0.1;
							aplicarZoomSenoidal();
						}
					}
					);
		}
		return btnZoomMenosOndasSenoidais;
	}

	private JButton getBtnZoomNormalOndasSenoidais()
	{
		if (btnZoomNormalOndasSenoidais == null)
		{
			btnZoomNormalOndasSenoidais = new JButton();
			btnZoomNormalOndasSenoidais.setBounds(new Rectangle(2, 66, 58, 66));
			btnZoomNormalOndasSenoidais.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnZoomNormalOndasSenoidais.setText(Double.toString(fltZoomSenoidal));
			btnZoomNormalOndasSenoidais
					.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							fltZoomSenoidal = 1;
							aplicarZoomSenoidal();
						}
					}
					);
		}
		return btnZoomNormalOndasSenoidais;
	}

	private JPanel getPnlZoomSuperposicao()
	{
		if (pnlZoomSuperposicao == null)
		{
			pnlZoomSuperposicao = new JPanel();
			pnlZoomSuperposicao.setLayout(null);
			pnlZoomSuperposicao.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			pnlZoomSuperposicao.setBounds(new Rectangle(557, 255, 63, 200));
			pnlZoomSuperposicao.setBackground(Color.GRAY);
		}
		return pnlZoomSuperposicao;
	}

	private JPanel getPnlCriacaoOndas()
	{
		if (pnlCriacaoOndas == null)
		{
			lblPeriodo = new JLabel();
			lblPeriodo.setBounds(new Rectangle(200, 43, 126, 16));
			lblPeriodo.setText("Período (T / s) (\u03c0 rad)");
			lblAmplitude = new JLabel();
			lblAmplitude.setBounds(new Rectangle(7, 43, 116, 16));
			lblAmplitude.setText("Amplitude Inicial (u)");
			lblqtdOndas = new JLabel();
			lblqtdOndas.setBounds(new Rectangle(7, 77, 90, 16));
			lblqtdOndas.setText("Qtd Ondas: 0");
			pnlCriacaoOndas = new JPanel();
			pnlCriacaoOndas.setLayout(null);
			pnlCriacaoOndas.setBounds(new Rectangle(0, 0, 376, 109));
			pnlCriacaoOndas.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			pnlCriacaoOndas.add(getRdbOndaTriangular(), null);
			pnlCriacaoOndas.add(getRdbOndaQuadrada(), null);
			pnlCriacaoOndas.add(getSldQtdOndas(), null);
			pnlCriacaoOndas.add(lblqtdOndas, null);
			pnlCriacaoOndas.add(getBtnMenosOndas(), null);
			pnlCriacaoOndas.add(getBtnMaisOndas(), null);
			pnlCriacaoOndas.add(lblAmplitude, null);
			pnlCriacaoOndas.add(getTxtAmplitude(), null);
			pnlCriacaoOndas.add(lblPeriodo, null);
			pnlCriacaoOndas.add(getTxtPeriodo(), null);
		}
		return pnlCriacaoOndas;
	}

	private JRadioButton getRdbOndaTriangular()
	{
		if (rdbOndaTriangular == null)
		{
			rdbOndaTriangular = new JRadioButton();
			rdbOndaTriangular.setBounds(new Rectangle(42, 5, 115, 21));
			rdbOndaTriangular.setText("Onda triangular");
			rdbOndaTriangular.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					rdbOndaTriangular.setSelected(true);
					rdbOndaQuadrada.setSelected(false);
					blnQuadrada = false;
					blnTriangular = true;
					alterarQtdOndas();
					definirOndas();
					definirSoma();
					pnlGrafico.atualizarTela(intQtdOndas, objOndas, getPnlGrafico().getGraphics());
					pnlGraficoTotal.atualizarTela(1, objSoma, getPnlGraficoTotal().getGraphics());
				}
			}
			);
		}
		return rdbOndaTriangular;
	}

	private JRadioButton getRdbOndaQuadrada()
	{
		if (rdbOndaQuadrada == null)
		{
			rdbOndaQuadrada = new JRadioButton();
			rdbOndaQuadrada.setBounds(new Rectangle(201, 5, 113, 24));
			rdbOndaQuadrada.setText("Onda quadrada");
			rdbOndaQuadrada.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					rdbOndaTriangular.setSelected(false);
					rdbOndaQuadrada.setSelected(true);
					blnQuadrada = true;
					blnTriangular = false;
					alterarQtdOndas();
					definirOndas();
					definirSoma();
					pnlGrafico.atualizarTela(intQtdOndas, objOndas, getPnlGrafico().getGraphics());
					pnlGraficoTotal.atualizarTela(1, objSoma, getPnlGraficoTotal().getGraphics());
				}
			}
			);
		}
		return rdbOndaQuadrada;
	}

	private JSlider getSldQtdOndas()
	{
		if (sldQtdOndas == null)
		{
			sldQtdOndas = new JSlider();
			sldQtdOndas.setValue(0);
			sldQtdOndas.setMaximum(50);
			sldQtdOndas.setBounds(new Rectangle(100, 77, 162, 16));
			sldQtdOndas.addChangeListener(new javax.swing.event.ChangeListener()
			{
				public void stateChanged(javax.swing.event.ChangeEvent e)
				{
					intQtdOndas = sldQtdOndas.getValue();
					alterarQtdOndas();
				}
			}
			);
		}
		return sldQtdOndas;
	}

	private JButton getBtnMenosOndas()
	{
		if (btnMenosOndas == null)
		{
			btnMenosOndas = new JButton();
			btnMenosOndas.setBounds(new Rectangle(277, 74, 45, 20));
			btnMenosOndas.setText("-");
			btnMenosOndas.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					sldQtdOndas.setValue(intQtdOndas -1);
				}
			}
			);
		}
		return btnMenosOndas;
	}

	private JButton getBtnMaisOndas()
	{
		if (btnMaisOndas == null)
		{
			btnMaisOndas = new JButton();
			btnMaisOndas.setBounds(new Rectangle(322, 74, 45, 20));
			btnMaisOndas.setText("+");
			btnMaisOndas.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					sldQtdOndas.setValue(intQtdOndas + 1);
				}
			}
			);
		}
		return btnMaisOndas;
	}

	private JScrollPane getScrOndas()
	{
		if (scrOndas == null)
		{
			scrOndas = new JScrollPane();
			scrOndas.setBounds(new Rectangle(0, 106, 376, 349));
			scrOndas.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			scrOndas.setViewportView(getTblOndas());
		}
		return scrOndas;
	}

	private JTable getTblOndas()
	{
		if (tblOndas == null)
		{
			tblOndas = new JTable();
			tblOndas.setBounds(new Rectangle(0, 11, 375, 65));
			tblOndas.setModel(new AbstractTableModel()
							  {
				                    private static final long serialVersionUID = 2L;
									private String colunas[] = {"POSIÇÃO", "COR", "AMPLITUDE", "PERÍODO"};
									public String getColumnName(int num)
								    {
								        return colunas[num];
								    }
									public int getColumnCount()
									{
										return 4;
									}
								    public int getRowCount()
								    {
								    	return intQtdOndas;
								    }
								    public Object getValueAt(int row, int col)
								    {
								    	String strRetorno = "";
								    	switch(col)
								    	{
								    		case 0:
								    			   strRetorno = Integer.toString(row + 1) + "ª";
								    			   break;
								    		case 1:
								    			   strRetorno = "";
								    			   break;
								    		case 2:
								    			   strRetorno = Integer.toString(objOndas[row].obterAmplitude()) + "u";
								    			   break;
								    		case 3:
								    			   strRetorno = objOndas[row].obterPeriodoFormatado() + "\u03c0 rad";
								    			   break;
								    	}
								    	return strRetorno;
								    }
								}
								);
			tblOndas.setDefaultRenderer(Object.class,
					new DefaultTableCellRenderer()
					{
				        private static final long serialVersionUID = 2L;
				        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
				        {
				            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				            setHorizontalAlignment(CENTER);
				            if (col == 1)
				            {
				            	setBackground(objOndas[row].getCor());
				            }
				            else
				            {
				            	setBackground(Color.WHITE);
				            }
				            return this;
				        }
					}
				 );
		}

		return tblOndas;
	}

	private JTextField getTxtAmplitude()
	{
		if (txtAmplitude == null)
		{
			txtAmplitude = new JTextField();
			txtAmplitude.setBounds(new Rectangle(125, 42, 59, 20));
			txtAmplitude.addCaretListener(new javax.swing.event.CaretListener()
			{
				public void caretUpdate(javax.swing.event.CaretEvent e)
				{
					try
					{
						intAmplitudeInicial = Integer.parseInt(txtAmplitude.getText());
						alterarQtdOndas();
					}
					catch(Exception erro)
					{
						if (txtAmplitude.getText().equals(""))
						{
							intAmplitudeInicial = 0;
						}
						else
						{
							intAmplitudeInicial = (int)(Double.parseDouble(txtAmplitude.getText()));
						}
						alterarQtdOndas();
					}
				}
			}
			);
		}
		return txtAmplitude;
	}

	private JTextField getTxtPeriodo()
	{
		if (txtPeriodo == null)
		{
			txtPeriodo = new JTextField();
			txtPeriodo.setBounds(new Rectangle(329, 42, 39, 20));
			txtPeriodo.addCaretListener(new javax.swing.event.CaretListener()
			{
				public void caretUpdate(javax.swing.event.CaretEvent e)
				{
					try
					{
						dblPeriodoInicial = Double.parseDouble(txtPeriodo.getText());
						alterarQtdOndas();
					}
					catch(Exception erro)
					{
						dblPeriodoInicial = 0;
						alterarQtdOndas();
					}
				}
			}
			);
		}
		return txtPeriodo;
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				frmPrincipal thisClass = new frmPrincipal();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}

		}
		);
	}

	private void initialize()
	{
		this.setContentPane(getJContentPane());
		this.setTitle("SSF - Simulador da série de Fourier");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(new Rectangle(0, 0, 1000, 524));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setVisible(true);
	}

	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPnlGraficos(), null);
			jContentPane.add(getPnlControles(), null);
			jContentPane.add(getPnlRodape(), null);
		}
		return jContentPane;
	}

}