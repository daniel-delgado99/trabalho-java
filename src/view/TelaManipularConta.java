package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.Controller;
import controller.Main;

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
		List<Conta> contas = Controller.getListaContas();
		Object[][] dados = new Object[contas.size()][3];
		for (int i=0; i < contas.size(); i++) {
			dados[i][0] = (int)    contas.get(i).getNumero();
			dados[i][1] = (String) contas.get(i).getDono().getNome() + " " + contas.get(i).getDono().getSobrenome();
			dados[i][2] = (String) contas.get(i).getDono().getCpf();
				
			for (int j=0; j<3; j++) {
				Main.tableModel.isCellEditable(i, j);
			}
		}
		
		table.setModel(new javax.swing.table.DefaultTableModel(dados, Main.tableModel.getColunasUneditable()));
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.getColumnModel().getColumn(0).setMaxWidth(25);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
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
				String tipoConta = Controller.getListaContas().get(table.getSelectedRow()).getDono().getTipoConta();
				Conta conta = Controller.getContaByNumero(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()), tipoConta);
				campoNome.setText(conta.getDono().getNome());
				campoCPF.setText(conta.getDono().getCpf());
				
				labelTipoDeConta.setText("Tipo de conta: " + tipoConta);
				lblSaldo.setText("Saldo: " + conta.getSaldo());
				labelNumeroConta.setText("Numero da conta: " + conta.getNumero());
				
				if (tipoConta == "Conta corrente") {
					label2.setVisible(false);
					label1.setText("Limite: " + ((ContaCorrente) conta).getLimite());
				} else if (tipoConta == "Conta investimento") {
					label2.setVisible(true);
					label1.setText("Montante minimo: " + ((ContaInvestimento) conta).getMontanteMinimo());
					label2.setText("Deposito minimo: " + ((ContaInvestimento) conta).getDepositoMinimo());
				}
			}
		});
		table.setModel(Main.tableModel);
		
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
						Conta contaSelecionada = Controller.getListaContas().get(table.getSelectedRow());
						if (contaSelecionada.saca(valor)) {
							Controller.setConta(contaSelecionada);
							lblSaldo.setText("Saldo: " + Controller.getListaContas().get(table.getSelectedRow()).getSaldo());
							JOptionPane.showMessageDialog (contentPane, "Saque realizado");
							campoSaque.setText("");
						} else {
							JOptionPane.showMessageDialog (contentPane, "Nao foi possivel realizar o saque");
						}
					} else {
						JOptionPane.showMessageDialog (contentPane, "Insira um valor valido!");
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
						Conta contaSelecionada = Controller.getListaContas().get(table.getSelectedRow());
						if (contaSelecionada.deposita(valor)) {
							Controller.setConta(contaSelecionada);
							lblSaldo.setText("Saldo: " + contaSelecionada.getSaldo());
							JOptionPane.showMessageDialog (contentPane, "Deposito realizado");
							campoDeposito.setText("");
						} else {
							JOptionPane.showMessageDialog (contentPane, "Favor depositar ao menos o valor minimo");
						}
					} else {
						JOptionPane.showMessageDialog (contentPane, "Insira um valor valido!");
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
					Conta contaSelecionada = Controller.getListaContas().get(table.getSelectedRow());
					if (contaSelecionada.getSaldo() > 0) {
						contaSelecionada.remunera();
						Controller.setConta(contaSelecionada);
						JOptionPane.showMessageDialog (contentPane, "Remunerado!");
						lblSaldo.setText("Saldo: " + contaSelecionada.getSaldo());
						contentPane.repaint();
					} else {
					    JOptionPane.showMessageDialog (contentPane, "Saldo negativo, não é possível remunerar");
					}
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
				Main.closeTelaManipularConta();
				Main.openTelaClientes();
			}
		});
		buttonIrParaClientes.setBounds(20, 387, 170, 23);
		contentPane.add(buttonIrParaClientes);
		
		buttonIrParaContas = new JButton("Ir para contas");
		buttonIrParaContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.closeTelaManipularConta();
				Main.openTelaContas();
			}
		});
		buttonIrParaContas.setBounds(396, 387, 170, 23);
		contentPane.add(buttonIrParaContas);
	}
}
