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
	private JLabel labelCampo2;
	private JTextField campo2;
	private JLabel labelCampo3;
	private JTextField campo3;
	private JLabel labelCPF;
	private JTextField campoCPF;
	private JLabel labelNovoCliente;
	private JComboBox<?> comboBox;
	private Object[][] comboOptions = new Object[][] { {"Conta Corrente", "Conta Investimento"}, {1, 2} };
	private JButton buttonIrParaClientes;
	private JButton buttonIrParaAcoes;
	
	public ArrayList<Cliente> getListaClientes() {
		return Controller.tableModel.getListaClientes();
	}
	
	public Cliente getCliente(int i) {
		return Controller.tableModel.getCliente(i);
	}
	
	public ArrayList<Conta> getListaContas() {
		return Controller.tableModel.getListaContas();
	}
	
	public boolean validaContaInvestimento(double depositoMinimo, double montanteMinimo, double depositoInicial) {
		if (depositoMinimo > 0 && montanteMinimo > 0 && depositoInicial > 0) {
			if (depositoInicial >= depositoMinimo) {
				if (depositoInicial >= montanteMinimo) {
					return true;
				} else {
					JOptionPane.showMessageDialog (contentPane, "Deposito inicial deve ser maior ou igual ao montante minimo");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog (contentPane, "Deposito inicial deve ser maior ou igual ao deposito minimo");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog (contentPane, "Por favor insira valores validos");
			return false;
		}		
	}
	
	public boolean validaContaCorrente(double depositoInicial, double limite) {
		if (depositoInicial > 0 && limite > 0) {
			return true;
		} else {
			JOptionPane.showMessageDialog (contentPane, "Por favor insira valores validos");
			return false;
		}
	}
	
	public double converteValor (String texto) {
		double valor;
		try {
			valor = Double.parseDouble(texto);
		} catch (NumberFormatException e) {
			valor = 0;
		}
		
		return valor;
	}
	
	public void renderTable() {
		Object[][] dados = new Object[this.getListaClientes().size()][3];
		for (int i=0; i < this.getListaClientes().size(); i++) {
			dados[i][0] = (int)    this.getListaClientes().get(i).getId();
			dados[i][1] = (String) this.getListaClientes().get(i).getNome() + " " + this.getListaClientes().get(i).getSobrenome();
			dados[i][2] = (String) this.getListaClientes().get(i).getCpf();
				
			for (int j=0; j<3; j++) {
				Controller.tableModel.isCellEditable(i, j);
				Controller.tableModel.getColumnClass(j);
			}
		}
		
		table.setModel(new javax.swing.table.DefaultTableModel(dados, Controller.tableModel.getColunasUneditable()));
		table.setAutoCreateRowSorter(true);
	}

	public TelaVincularConta() {
		table = new JTable() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoNome.setText(getCliente(table.getSelectedRow()).getNome() + " " + getCliente(table.getSelectedRow()).getSobrenome());
				campoCPF.setText(getCliente(table.getSelectedRow()).getCpf());
			}
		});
		table.setModel(Controller.tableModel);
		
		renderTable();	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 30, 258, 341);
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
		labelNovoCliente.setBounds(350, 12, 200, 14);
		contentPane.add(labelNovoCliente);
		
		JButton buttonCriar = new JButton("Criar conta");
		buttonCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				
				if (linha > -1) {
					Cliente cliente = getCliente(linha);
					if (cliente.getConta() == null) {
						if (comboOptions[1][comboBox.getSelectedIndex()] == (Object) 1) {
							int numeroConta;
							if (Controller.tableModel.getListaContas().size() == 0) {
								numeroConta=0;
							} else {
								numeroConta = Controller.tableModel.getListaContas().get(getListaContas().size()-1).getNumero() + 1;
							}
							double depositoInicial, limite;						
							depositoInicial = converteValor(campo1.getText());
							limite = converteValor(campo2.getText());
							
							if (validaContaCorrente(depositoInicial, limite)) {
								Conta conta = new ContaCorrente(numeroConta, depositoInicial, limite, cliente);
								Controller.tableModel.addConta(conta);
								Controller.tableModel.relacionarClienteConta(cliente, conta);
								
								campoNome.setText("");
								campoCPF.setText("");
								campo1.setText("");
								campo2.setText("");
								campo3.setText("");
								
								renderTable();
								
								JOptionPane.showMessageDialog (contentPane, "Conta salva!");
							}
						} else if (comboOptions[1][comboBox.getSelectedIndex()] == (Object) 2) {
							double depositoInicial, montanteMinimo, depositoMinimo;
							int numeroConta;
							if (Controller.tableModel.getListaContas().size() == 0) {
								numeroConta=0;
							} else {
								numeroConta = Controller.tableModel.getListaContas().get(getListaContas().size()-1).getNumero() + 1;
							}
							montanteMinimo = converteValor(campo1.getText());
							depositoMinimo = converteValor(campo2.getText());
							depositoInicial = converteValor(campo3.getText());
							
							
							if (validaContaInvestimento(depositoMinimo, montanteMinimo, depositoInicial)) {
								Conta conta = new ContaInvestimento(numeroConta, montanteMinimo, depositoMinimo, depositoInicial, cliente);
								Controller.tableModel.addConta(conta);
								Controller.tableModel.relacionarClienteConta(cliente, conta);
								campoNome.setText("");
								campoCPF.setText("");
								campo1.setText("");
								campo2.setText("");
								campo3.setText("");
								
								renderTable();
								
								JOptionPane.showMessageDialog (contentPane, "Conta salva!");
							}
						}
					} else {
						JOptionPane.showMessageDialog (contentPane, "Este cliente ja possui uma conta");
					}
				} else {
					JOptionPane.showMessageDialog (contentPane, "Selecione um cliente");
				}				
			}
		});
		buttonCriar.setBounds(415, 351, 159, 25);
		contentPane.add(buttonCriar);
		
		comboBox = new JComboBox<Object>(comboOptions[0]);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
				if (comboOptions[1][comboBox.getSelectedIndex()] == (Object) 1) {
					campo1.setText("");
					campo2.setText("");
					campo3.setText("");
					labelCampo1.setText("Deposito inicial");
					labelCampo2.setText("Limite");
					labelCampo3.setVisible(false);
					campo3.setVisible(false);
					
					contentPane.repaint();
					
				} else if (comboOptions[1][comboBox.getSelectedIndex()] == (Object) 2) {
					campo1.setText("");
					campo2.setText("");
					campo3.setText("");
					labelCampo1.setText("Montante minimo");
					labelCampo2.setText("Deposito minimo");
					labelCampo3.setVisible(true);
					campo3.setVisible(true);
					labelCampo3.setText("Deposito inicial");
					
					contentPane.repaint();
				}
			}
		});
		comboBox.setBounds(298, 167, 159, 22);
		contentPane.add(comboBox);
		
		JLabel labelTipoDeConta = new JLabel("Tipo de conta");
		labelTipoDeConta.setBounds(298, 149, 100, 14);
		contentPane.add(labelTipoDeConta);
		
		labelCampo1 = new JLabel("Deposito inicial");
		labelCampo1.setBounds(298, 215, 110, 15);
		contentPane.add(labelCampo1);
		
		campo1 = new JTextField();
		campo1.setBounds(295, 229, 250, 19);
		contentPane.add(campo1);
		campo1.setColumns(10);
		
		labelCampo2 = new JLabel("Limite");
		labelCampo2.setBounds(298, 259, 110, 15);
		contentPane.add(labelCampo2);
		
		campo2 = new JTextField();
		campo2.setColumns(10);
		campo2.setBounds(295, 273, 250, 19);
		contentPane.add(campo2);
		
		labelCampo3 = new JLabel("Deposito inicial");
		labelCampo3.setBounds(298, 307, 110, 15);
		labelCampo3.setVisible(false);
		contentPane.add(labelCampo3);
		
		
		campo3 = new JTextField();
		campo3.setColumns(10);
		campo3.setBounds(295, 321, 250, 19);
		campo3.setVisible(false);
		contentPane.add(campo3);
		
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(298, 31, 70, 15);
		contentPane.add(lblCliente);
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setBounds(298, 124, 46, 14);
		contentPane.add(lblConta);
		
		buttonIrParaClientes = new JButton("Ir para clientes");
		buttonIrParaClientes.setBounds(10, 387, 165, 23);
		buttonIrParaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.closeTelaContas();
				Controller.openTelaClientes();
			}
		});
		contentPane.add(buttonIrParaClientes);
		
		buttonIrParaAcoes = new JButton("Ir para acoes");
		buttonIrParaAcoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.closeTelaContas();
				Controller.openTelaManipularConta();
			}
		});
		buttonIrParaAcoes.setBounds(409, 387, 165, 23);
		contentPane.add(buttonIrParaAcoes);
	}
}
