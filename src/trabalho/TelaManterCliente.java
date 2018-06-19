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
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TelaManterCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField campoNome;
	private JTextField campoSobrenome;
	private JTextField campoRG;
	private JLabel labelRG;
	private JLabel labelEndereco;
	private JTextField campoEndereco;
	private JLabel labelCPF;
	private JTextField campoCPF;
	private JLabel labelNovoCliente;
	private JLabel labelSalario;
	private JTextField campoSalario;
	private JButton buttonIrParaAcoes;
	private JButton buttonIrParaContas;
	
	public ArrayList<Cliente> getListaClientes() {
		return Controller.tableModel.getListaClientes();
	}
	
	public void renderTable() {
		Object[][] dados = new Object[this.getListaClientes().size()][7];
		
		for (int i=0; i < this.getListaClientes().size(); i++) {
			dados[i][0] = (int)    this.getListaClientes().get(i).getId();
			dados[i][1] = (String) this.getListaClientes().get(i).getNome();
			dados[i][2] = (String) this.getListaClientes().get(i).getSobrenome();
			dados[i][3] = (String) this.getListaClientes().get(i).getRg();
			dados[i][4] = (String) this.getListaClientes().get(i).getCpf();
			dados[i][5] = (String) this.getListaClientes().get(i).getEndereco();
			dados[i][6] = (double) this.getListaClientes().get(i).getSalario();//String.valueOf(this.tableModel.getListaClientes().get(i).getSalario());
				
			for (int j=0; j<7; j++) {
				Controller.tableModel.isCellEditable(i, j);
				Controller.tableModel.getColumnClass(j);
			}
		}
		
		table.setModel(new javax.swing.table.DefaultTableModel(dados, Controller.tableModel.getColunasEditable()));
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.getColumnModel().getColumn(0).setMaxWidth(25);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setMaxWidth(80);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(120);
		table.getColumnModel().getColumn(5).setMaxWidth(135);
		table.getColumnModel().getColumn(6).setMaxWidth(60);
	}
	
	public boolean validaCampos(String campo1, String campo2, String campo3, String campo4, String campo5) {
		if (campo1.equals("") || campo2.equals("") || campo3.equals("") || campo4.equals("") || campo5.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public double validaSalario(String salarioS) {
		double salarioD;
		try {
			salarioD = Double.parseDouble(salarioS);
		} catch (NumberFormatException e) {
			return 0;
		}
		return salarioD;
	}

	public TelaManterCliente() {
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
			  if (column == 0) {
				  return false;
			  } else {
				  return true;
			  }
			}
		};
		table.setModel(Controller.tableModel);
		
		renderTable();	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 30, 560, 200);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JLabel title = new JLabel("Clientes");
		title.setBounds(252, 12, 70, 15);
		contentPane.add(title);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setBounds(20, 300, 70, 15);
		contentPane.add(labelNome);
		
		campoNome = new JTextField();
		campoNome.setBounds(20, 315, 123, 19);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel labelSobrenome = new JLabel("Sobrenome");
		labelSobrenome.setBounds(155, 300, 123, 15);
		contentPane.add(labelSobrenome);
		
		campoSobrenome = new JTextField();
		campoSobrenome.setBounds(155, 315, 131, 19);
		contentPane.add(campoSobrenome);
		campoSobrenome.setColumns(10);
		
		labelRG = new JLabel("RG");
		labelRG.setBounds(20, 340, 70, 15);
		contentPane.add(labelRG);
		
		campoRG = new JTextField();
		campoRG.setBounds(20, 355, 123, 19);
		contentPane.add(campoRG);
		campoRG.setColumns(10);
		
		labelEndereco = new JLabel("Endereco");
		labelEndereco.setBounds(316, 300, 70, 15);
		contentPane.add(labelEndereco);
		
		campoEndereco = new JTextField();
		campoEndereco.setBounds(316, 315, 250, 19);
		contentPane.add(campoEndereco);
		campoEndereco.setColumns(10);
		
		labelCPF = new JLabel("CPF");
		labelCPF.setBounds(155, 340, 70, 15);
		contentPane.add(labelCPF);
		
		campoCPF = new JTextField();
		campoCPF.setBounds(155, 355, 136, 19);
		contentPane.add(campoCPF);
		campoCPF.setColumns(10);
		
		labelSalario = new JLabel("Salario");
		labelSalario.setBounds(316, 340, 70, 15);
		contentPane.add(labelSalario);
		
		campoSalario = new JTextField();
		campoSalario.setBounds(316, 355, 136, 19);
		contentPane.add(campoSalario);
		campoSalario.setColumns(10);
		
		
		JButton buttonAdicionar = new JButton("Adicionar");
		buttonAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				int id;
				if (getListaClientes().size() == 0) {
					id = 0;
				} else {
					id = getListaClientes().get(getListaClientes().size()-1).getId() + 1;
				}
				String nome = campoNome.getText();
				String sobrenome = campoSobrenome.getText();
				String rg = campoRG.getText();
				String cpf = campoCPF.getText();
				String endereco = campoEndereco.getText();
				double salario;
				
				try {
					salario = Double.parseDouble(campoSalario.getText());
				} catch (NumberFormatException e) {
					salario = 0;
				}
				
				if (!validaCampos(nome, sobrenome, rg, cpf, endereco)) {
					JOptionPane.showMessageDialog (contentPane, "Favor preencher todos os campos corretamente!");
				} else {
					if (salario <= 0) {
						JOptionPane.showMessageDialog(contentPane, "Digite um salario valido");
					}
					else {
						Cliente cliente = new Cliente(id, nome, sobrenome, rg, cpf, endereco, salario);
						Controller.tableModel.addCliente(cliente);
						
						renderTable();
						JOptionPane.showMessageDialog (contentPane, "Ciente adicionado!");
						
						campoNome.setText("");
						campoSobrenome.setText("");
						campoRG.setText("");
						campoCPF.setText("");
						campoEndereco.setText("");
						campoSalario.setText("");
					}
				}
			}
		});
		buttonAdicionar.setBounds(465, 345, 100, 30);
		contentPane.add(buttonAdicionar);
		
		labelNovoCliente = new JLabel("Novo cliente");
		labelNovoCliente.setBounds(252, 272, 123, 14);
		contentPane.add(labelNovoCliente);
		
		JButton buttonSalvarAlteracoes = new JButton("Salvar alteracoes");
		buttonSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int numLinhas = Controller.tableModel.getListaClientes().size();
				String nome, sobrenome, rg, cpf, endereco, salarioS;
				double salario;
				int id;
				boolean hasInvalidValue=false;
				
				// loop validar
				for (int i=0; i<numLinhas; i++) {
					nome = (String) table.getValueAt(i, 1);
					sobrenome = (String) table.getValueAt(i, 2);
					cpf = (String) table.getValueAt(i, 3);
					rg = (String) table.getValueAt(i, 4);
					endereco = (String) table.getValueAt(i, 5);
					salarioS = table.getValueAt(i, 6).toString();
					
					salario = validaSalario(salarioS);
					
					if (!validaCampos(nome, sobrenome, cpf, rg, endereco) || salario <= 0) {						
						hasInvalidValue = true;
						break;
					}
				}
				
				// loop adicionar
				if (!hasInvalidValue) {
					//Controller.tableModel.getListaClientes().clear();
					for (int i=0; i<numLinhas; i++) {
						id = (int) table.getValueAt(i, 0);
						nome = (String) table.getValueAt(i, 1);
						sobrenome = (String) table.getValueAt(i, 2);
						rg = (String) table.getValueAt(i, 3);
						cpf = (String) table.getValueAt(i, 4);
						endereco = (String) table.getValueAt(i, 5);
						salarioS = table.getValueAt(i, 6).toString();
						
						salario = validaSalario(salarioS);
						
						Cliente c = Controller.tableModel.getClienteById(id);
						c.setNome(nome);
						c.setSobrenome(sobrenome);
						c.setRg(rg);
						c.setCpf(cpf);
						c.setEndereco(endereco);
						c.setSalario(salario);
						Controller.tableModel.setCliente(i, c);
						if (c.getConta() != null) {
							c.getConta().setDonoConta(c);
							c.getConta().getDono();
						}
					}
					
					renderTable();
					JOptionPane.showMessageDialog (contentPane, "Alteracoes salvas!");
				} else {
					JOptionPane.showMessageDialog (contentPane, "Alteracoes invalidas!");
				}
				
			}
		});
		buttonSalvarAlteracoes.setBounds(420, 230, 159, 25);
		contentPane.add(buttonSalvarAlteracoes);
		
		JButton buttonExcluir = new JButton("Excluir Registro");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha > -1) {
					int dialogResult = JOptionPane.showConfirmDialog (contentPane, "Deseja realmente excluir o cliente?\nIsso apagara o cliente e sua conta","Aviso!", 0);
					if (dialogResult == 0) {
						Controller.tableModel.removeCliente(linha);
						renderTable();
						JOptionPane.showMessageDialog (contentPane, "Cliente removido");
					}
				} else {
					JOptionPane.showMessageDialog (contentPane, "Selecione um cliente");
				}
			}
		});
		buttonExcluir.setBounds(20, 231, 150, 23);
		contentPane.add(buttonExcluir);
		
		buttonIrParaContas = new JButton("Ir para acoes");
		buttonIrParaContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.closeTelaClientes();
				Controller.openTelaManipularConta();
			}
		});
		buttonIrParaContas.setBounds(420, 387, 160, 23);
		contentPane.add(buttonIrParaContas);
		
		buttonIrParaAcoes = new JButton("Ir para contas");
		buttonIrParaAcoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.closeTelaClientes();
				Controller.openTelaContas();
			}
		});
		buttonIrParaAcoes.setBounds(20, 387, 160, 23);
		contentPane.add(buttonIrParaAcoes);
	}
}
