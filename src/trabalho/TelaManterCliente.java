package trabalho;

import java.awt.EventQueue;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TelaManterCliente extends JFrame {

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
	private JLabel labelInvalido;
	
	private TableClienteModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManterCliente frame = new TelaManterCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void renderTable() {
		Object[][] dados = new Object[this.tableModel.listaClientes.size()][5];
		
		for (int i=0; i < this.tableModel.listaClientes.size(); i++) {
			dados[i][0] = this.tableModel.listaClientes.get(i).getNome();
			dados[i][1] = this.tableModel.listaClientes.get(i).getSobrenome();
			dados[i][2] = this.tableModel.listaClientes.get(i).getRg();
			dados[i][3] = this.tableModel.listaClientes.get(i).getCpf();
			dados[i][4] = this.tableModel.listaClientes.get(i).getEndereco();
		}	
		
		table.setModel(new javax.swing.table.DefaultTableModel(dados, this.tableModel.colunas));
	}
	
	public boolean validaCampos(String campo1, String campo2, String campo3, String campo4, String campo5) {
		if (campo1.equals("") || campo2.equals("") || campo3.equals("") || campo4.equals("") || campo5.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public TelaManterCliente() {
		this.tableModel = new TableClienteModel();
		table = new JTable();
		table.setModel(tableModel);
		
		renderTable();	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 30, 560, 200);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
//		table.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent ev) {
//			}
//		});
		
		JLabel title = new JLabel("Clientes");
		title.setBounds(252, 12, 70, 15);
		contentPane.add(title);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setBounds(20, 270, 70, 15);
		contentPane.add(labelNome);
		
		campoNome = new JTextField();
		campoNome.setBounds(20, 285, 123, 19);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel labelSobrenome = new JLabel("Sobrenome");
		labelSobrenome.setBounds(155, 270, 123, 15);
		contentPane.add(labelSobrenome);
		
		campoSobrenome = new JTextField();
		campoSobrenome.setBounds(155, 285, 131, 19);
		contentPane.add(campoSobrenome);
		campoSobrenome.setColumns(10);
		
		labelRG = new JLabel("RG");
		labelRG.setBounds(20, 310, 70, 15);
		contentPane.add(labelRG);
		
		campoRG = new JTextField();
		campoRG.setBounds(20, 325, 123, 19);
		contentPane.add(campoRG);
		campoRG.setColumns(10);
		
		labelEndereco = new JLabel("EndereÃ§o");
		labelEndereco.setBounds(316, 270, 70, 15);
		contentPane.add(labelEndereco);
		
		campoEndereco = new JTextField();
		campoEndereco.setBounds(316, 285, 250, 19);
		contentPane.add(campoEndereco);
		campoEndereco.setColumns(10);
		
		labelCPF = new JLabel("CPF");
		labelCPF.setBounds(155, 310, 70, 15);
		contentPane.add(labelCPF);
		
		campoCPF = new JTextField();
		campoCPF.setBounds(155, 325, 136, 19);
		contentPane.add(campoCPF);
		campoCPF.setColumns(10);
		
		JButton buttonSalvar = new JButton("Salvar");
		buttonSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String nome = campoNome.getText();
				String sobrenome = campoSobrenome.getText();
				String rg = campoRG.getText();
				String cpf = campoCPF.getText();
				String endereco = campoEndereco.getText();
				
				if (!validaCampos(nome, sobrenome, rg, cpf, endereco)) {
					JOptionPane.showMessageDialog (null, "Favor preencher todos os campos corretamente!");
				} else {
					Cliente cliente = new Cliente(nome, sobrenome, rg, cpf, endereco);
					tableModel.addCliente(cliente);
					
					renderTable();
					JOptionPane.showMessageDialog (null, "Ciente adicionado!");
					
					labelInvalido.setText("");
					
					campoNome.setText("");
					campoSobrenome.setText("");
					campoRG.setText("");
					campoCPF.setText("");
					campoEndereco.setText("");
				}
			}
		});
		buttonSalvar.setBounds(316, 315, 117, 30);
		contentPane.add(buttonSalvar);
		
		labelNovoCliente = new JLabel("Novo cliente");
		labelNovoCliente.setBounds(252, 242, 123, 14);
		contentPane.add(labelNovoCliente);
		
		labelInvalido = new JLabel("");
		labelInvalido.setBounds(20, 360, 546, 15);
		contentPane.add(labelInvalido);
		
		JButton buttonSalvarAlteracoes = new JButton("Salvar alteraÃ§Ãµes");
		buttonSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				tableModel.listaClientes.clear();
				String nome, sobrenome, rg, cpf, endereco;
				for (int i=0; i<table.getRowCount(); i++) {
					nome = (String) table.getValueAt(i, 0);
					sobrenome = (String) table.getValueAt(i, 1);
					cpf = (String) table.getValueAt(i, 2);
					rg = (String) table.getValueAt(i, 3);
					endereco = (String) table.getValueAt(i, 4);
					Cliente c = new Cliente(nome, sobrenome, rg, cpf, endereco);
					tableModel.listaClientes.add(c);
				}
				JOptionPane.showMessageDialog (null, "Alterações salvas!");
				renderTable();
			}
		});
		buttonSalvarAlteracoes.setBounds(420, 230, 159, 25);
		contentPane.add(buttonSalvarAlteracoes);
		
		JButton buttonExcluir = new JButton("Excluir Registro");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha > -1) {
					int dialogResult = JOptionPane.showConfirmDialog (null, "Deseja realmente excluir o registro?","Aviso!", 0);
					if (dialogResult == 0) {
						tableModel.removeCliente(linha);
						JOptionPane.showMessageDialog (null, "Registro removido");
						renderTable();
					}
				} else {
					JOptionPane.showConfirmDialog (null, "Selecione um registro!", "Aviso!", -1);
				}
			}
		});
		buttonExcluir.setBounds(20, 231, 150, 23);
		contentPane.add(buttonExcluir);
	}
}
