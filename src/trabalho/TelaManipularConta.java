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

public class TelaManipularConta extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField campoNome;
	private JLabel labelSaque;
	private JTextField campoSaque;
	private JLabel labelDeposito;
	private JTextField campoDeposito;
	private JLabel labelCPF;
	private JTextField campoCPF;
	private JLabel labelNovoCliente;
	private JLabel labelNumeroConta;
	private JLabel lblSaldo;
	private JLabel label1;
	private JLabel label2;
	private JLabel labelTipoDeConta;
	private JButton buttonIrParaClientes;
	private JButton buttonIrParaContas;
	
	public ArrayList<Cliente> getListaClientes() {
		return Controller.tableModel.getListaClientes();
	}
	
	public Cliente getCliente(int i) {
		return Controller.tableModel.getCliente(i);
	}
	
	public ArrayList<Conta> getListaContas() {
		return Controller.tableModel.getListaContas();
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
		Object[][] dados = new Object[this.getListaContas().size()][3];
		for (int i=0; i < this.getListaContas().size(); i++) {
			dados[i][0] = (int)    this.getListaContas().get(i).getNumero();
			dados[i][1] = (String) this.getListaContas().get(i).getDono().getNome() + " " + this.getListaContas().get(i).getDono().getSobrenome();
			dados[i][2] = (String) this.getListaContas().get(i).getDono().getCpf();
				
			for (int j=0; j<3; j++) {
				Controller.tableModel.isCellEditable(i, j);
			}
		}
		
		table.setModel(new javax.swing.table.DefaultTableModel(dados, Controller.tableModel.getColunasUneditable()));
		table.setAutoCreateRowSorter(true);
	}

	public TelaManipularConta() {
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
				campoNome.setText(getListaContas().get(table.getSelectedRow()).getDono().getNome() + " " + getListaContas().get(table.getSelectedRow()).getDono().getSobrenome());
				campoCPF.setText(getListaContas().get(table.getSelectedRow()).getDono().getCpf());
				
				String tipoConta = getListaContas().get(table.getSelectedRow()).getDono().getTipoConta();
				labelTipoDeConta.setText("Tipo de conta: " + tipoConta);
				lblSaldo.setText("Saldo: " + getListaContas().get(table.getSelectedRow()).getSaldo());
				labelNumeroConta.setText("Numero da conta: " + getListaContas().get(table.getSelectedRow()).getNumero());
				
				if (tipoConta == "Conta corrente") {
					label2.setVisible(false);
					label1.setText("Limite: " + ((ContaCorrente) getListaContas().get(table.getSelectedRow())).getLimite());
				} else if (tipoConta == "Conta investimento") {
					label2.setVisible(true);
					label1.setText("Montante minimo: " + ((ContaInvestimento) getListaContas().get(table.getSelectedRow())).getMontanteMinimo());
					label2.setText("Deposito minimo: " + ((ContaInvestimento) getListaContas().get(table.getSelectedRow())).getDepositoMinimo());
				}
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
		scrollPane.setBounds(20, 30, 258, 335);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JLabel title = new JLabel("Contas");
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
		
		labelNovoCliente = new JLabel("Manipular conta de cliente");
		labelNovoCliente.setBounds(332, 12, 218, 14);
		contentPane.add(labelNovoCliente);
		
		labelTipoDeConta = new JLabel("Tipo de conta:");
		labelTipoDeConta.setBounds(298, 101, 247, 14);
		contentPane.add(labelTipoDeConta);
		
		labelSaque = new JLabel("Saque");
		labelSaque.setBounds(298, 246, 110, 15);
		contentPane.add(labelSaque);
		
		campoSaque = new JTextField();
		campoSaque.setColumns(10);
		campoSaque.setBounds(295, 261, 162, 19);
		contentPane.add(campoSaque);
		
		labelDeposito = new JLabel("Deposito");
		labelDeposito.setBounds(298, 291, 110, 15);
		contentPane.add(labelDeposito);
		
		campoDeposito = new JTextField();
		campoDeposito.setColumns(10);
		campoDeposito.setBounds(295, 305, 162, 19);
		contentPane.add(campoDeposito);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(298, 31, 70, 15);
		contentPane.add(lblCliente);
		
		JLabel lblAcoes = new JLabel("Acoes");
		lblAcoes.setBounds(411, 227, 46, 14);
		contentPane.add(lblAcoes);
		
		labelNumeroConta = new JLabel("Numero da conta:");
		labelNumeroConta.setBounds(298, 126, 268, 14);
		contentPane.add(labelNumeroConta);
		
		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(298, 151, 268, 14);
		contentPane.add(lblSaldo);
		
		label1 = new JLabel("Montante minimo:");
		label1.setBounds(298, 176, 268, 14);
		contentPane.add(label1);
		
		label2 = new JLabel("Dep\u00F3sito minimo");
		label2.setBounds(298, 202, 268, 14);
		contentPane.add(label2);
		
		JButton btnSacar = new JButton("Sacar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valor = converteValor(campoSaque.getText());
				if (table.getSelectedRow() > -1) {
					if (valor > 0) {
						if (getListaContas().get(table.getSelectedRow()).saca(valor)) {
							lblSaldo.setText("Saldo: " + getListaContas().get(table.getSelectedRow()).getSaldo());
							JOptionPane.showMessageDialog (contentPane, "Saque realizado");
							campoSaque.setText("");
						} else {
							JOptionPane.showMessageDialog (contentPane, "N�o foi poss�vel realizar o saque");
						}
					} else {
						JOptionPane.showMessageDialog (contentPane, "Insira um valor v�lido!");
					}
				} else {
					JOptionPane.showMessageDialog (contentPane, "Selecione uma conta!");
				}
			}
		});
		btnSacar.setBounds(477, 260, 89, 23);
		contentPane.add(btnSacar);
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valor = converteValor(campoDeposito.getText());
				if (table.getSelectedRow() > -1) {
					if (valor > 0) {
						if (getListaContas().get(table.getSelectedRow()).deposita(valor)) {
							lblSaldo.setText("Saldo: " + getListaContas().get(table.getSelectedRow()).getSaldo());
							JOptionPane.showMessageDialog (contentPane, "Dep�sito realizado");
							campoDeposito.setText("");
						} else {
							JOptionPane.showMessageDialog (contentPane, "Favor depositar mais que o valor m�nimo");
						}
					} else {
						JOptionPane.showMessageDialog (contentPane, "Insira um valor v�lido!");
					}
				} else {
					JOptionPane.showMessageDialog (contentPane, "Selecione uma conta!");
				}
			}
		});
		btnDepositar.setBounds(477, 304, 89, 23);
		contentPane.add(btnDepositar);
		
		JButton btnRemunera = new JButton("Remunerar");
		btnRemunera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					if (getListaContas().get(table.getSelectedRow()).getSaldo() > 0) {
						getListaContas().get(table.getSelectedRow()).remunera();
						JOptionPane.showMessageDialog (contentPane, "Remunerado!");
						lblSaldo.setText("Saldo: " + getListaContas().get(table.getSelectedRow()).getSaldo());
						contentPane.repaint();
					}
					JOptionPane.showMessageDialog (contentPane, "Saldo negativo, não é possível remunerar");
				} else {
					JOptionPane.showMessageDialog (contentPane, "Selecione uma conta!");
				}
				
			}
		});
		btnRemunera.setBounds(434, 338, 132, 23);
		contentPane.add(btnRemunera);
		
		JLabel lblNewLabel = new JLabel("Remuneracao");
		lblNewLabel.setBounds(298, 342, 126, 14);
		contentPane.add(lblNewLabel);
		
		buttonIrParaClientes = new JButton("Ir para clientes");
		buttonIrParaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.closeTelaManipularConta();
				Controller.openTelaClientes();
			}
		});
		buttonIrParaClientes.setBounds(20, 387, 170, 23);
		contentPane.add(buttonIrParaClientes);
		
		buttonIrParaContas = new JButton("Ir para contas");
		buttonIrParaContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.closeTelaManipularConta();
				Controller.openTelaContas();
			}
		});
		buttonIrParaContas.setBounds(396, 387, 170, 23);
		contentPane.add(buttonIrParaContas);
	}
}
