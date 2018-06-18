package trabalho;

import java.awt.EventQueue;

public class Controller {
	static TelaManterCliente telaClientes;
	static TelaVincularConta telaContas;
	static TelaManipularConta telaManipularConta;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaClientes = new TelaManterCliente();
					telaContas = new TelaVincularConta();
					telaManipularConta = new TelaManipularConta();
					openTelaClientes();
					
					//TableToolTipsDemo tela = new TableToolTipsDemo();
					//telaClientes.setVisible(false);
					//telaContas.setVisible(false);
					//telaManipularConta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void openTelaClientes() {
		telaClientes.setVisible(true);
		telaContas.setVisible(false);
		telaManipularConta.setVisible(false);
		telaClientes.renderTable();
		telaClientes.repaint();
	}
	
	public static void openTelaContas() {
		telaContas.setVisible(true);
		telaClientes.setVisible(false);
		telaManipularConta.setVisible(false);
		telaContas.renderTable();
		telaContas.repaint();
	}
	
	public static void openTelaManipularConta() {
		telaManipularConta.setVisible(true);
		telaContas.setVisible(false);
		telaClientes.setVisible(false);
		telaManipularConta.renderTable();
		telaManipularConta.repaint();
	}

}
