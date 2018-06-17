package trabalho;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class TelaVincularConta extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField campoNome;
	private JLabel labelCampo1;
	private JTextField campo1;
	private JLabel labelCPF;
	private JTextField campoCPF;
	private JLabel labelNovoCliente;
	private TableClienteModel tableModel;
	private JTextField campo2;
	private JTextField campo3;
	private JComboBox<?> comboBox;
	private Object[][] comboOptions = new Object[][] { {"Conta Corrente", "Conta Investimento"}, {1, 2} };
	
	public ArrayList<Cliente> getListaClientes() {
		return tableModel.getListaClientes();
	}
	
	public void renderTable() {
		Object[][] dados = new Object[this.getListaClientes().size()][3];
		
		
		for (int i=0; i < this.getListaClientes().size(); i++) {
			dados[i][0] = (int)    this.getListaClientes().get(i).getId();
			dados[i][1] = (String) this.getListaClientes().get(i).getNome() + " " + this.getListaClientes().get(i).getSobrenome();
			dados[i][2] = (String) this.getListaClientes().get(i).getCpf();
				
			for (int j=0; j<3; j++) {
				tableModel.isCellEditable(i, j);
				tableModel.getColumnClass(j);
			}
		}
		
		table.setModel(new javax.swing.table.DefaultTableModel(dados, this.tableModel.getColunasUneditable()));
		table.setAutoCreateRowSorter(true);
	}
	
	public boolean validaCampos(String campo1, String campo2, String campo3, String campo4, String campo5) {
		if (campo1.equals("") || campo2.equals("") || campo3.equals("") || campo4.equals("") || campo5.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public TelaVincularConta() {
		this.tableModel = new TableClienteModel();
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);
		
		renderTable();	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 30, 258, 380);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JLabel title = new JLabel("Clientes");
		title.setBounds(126, 12, 70, 15);
		contentPane.add(title);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setBounds(298, 57, 70, 15);
		contentPane.add(labelNome);
		
		campoNome = new JTextField();
		campoNome.setBounds(297, 71, 127, 19);
		campoNome.setEditable(false);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		labelCPF = new JLabel("CPF");
		labelCPF.setBounds(442, 57, 70, 15);
		contentPane.add(labelCPF);
		
		campoCPF = new JTextField();
		campoCPF.setBounds(443, 71, 123, 19);
		campoCPF.setEditable(false);
		contentPane.add(campoCPF);
		campoCPF.setColumns(10);
		
		labelNovoCliente = new JLabel("Criar conta para cliente");
		labelNovoCliente.setBounds(347, 12, 123, 14);
		contentPane.add(labelNovoCliente);
		
		JButton buttonSalvarAlteracoes = new JButton("Criar conta");
		buttonSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.out.println(comboBox.getSelectedIndex());
				System.out.println(comboOptions[1][comboBox.getSelectedIndex()]);
				
				if (comboOptions[1][comboBox.getSelectedIndex()] == (Object) 1) {	
					System.out.println("Corrente DESUUU");
				} else if (comboOptions[1][comboBox.getSelectedIndex()] == (Object) 2) {
					System.out.println("INVESTIMENTOS DESUUU");
					labelCampo1 = new JLabel("Campo 1");
					labelCampo1.setBounds(298, 215, 70, 15);
					contentPane.add(labelCampo1);
					
					campo1 = new JTextField();
					campo1.setBounds(295, 229, 250, 19);
					contentPane.add(campo1);
					campo1.setColumns(10);
					
					JLabel labelCampo2 = new JLabel("Campo 2");
					labelCampo2.setBounds(298, 259, 70, 15);
					contentPane.add(labelCampo2);
					
					campo2 = new JTextField();
					campo2.setColumns(10);
					campo2.setBounds(295, 273, 250, 19);
					contentPane.add(campo2);
					
					JLabel labelCampo3 = new JLabel("Campo 3");
					labelCampo3.setBounds(298, 307, 70, 15);
					contentPane.add(labelCampo3);
					
					campo3 = new JTextField();
					campo3.setColumns(10);
					campo3.setBounds(295, 321, 250, 19);
					contentPane.add(campo3);
					
					contentPane.repaint();
					
				}
			}
		});
		buttonSalvarAlteracoes.setBounds(415, 385, 159, 25);
		contentPane.add(buttonSalvarAlteracoes);
		
		comboBox = new JComboBox<Object>(comboOptions[0]);
		comboBox.setBounds(298, 167, 159, 22);
		contentPane.add(comboBox);
		
		JLabel labelTipoDeConta = new JLabel("Tipo de conta");
		labelTipoDeConta.setBounds(298, 149, 100, 14);
		contentPane.add(labelTipoDeConta);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(298, 31, 70, 15);
		contentPane.add(lblCliente);
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setBounds(298, 124, 46, 14);
		contentPane.add(lblConta);
	}
}
