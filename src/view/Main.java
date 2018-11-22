package view;

import java.awt.EventQueue;

import model.TableModel;

public class Main {
	static TelaManterCliente telaClientes;
	static TelaVincularConta telaContas;
	static TelaManipularConta telaManipularConta;
	public static TableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tableModel = new TableModel();
					openTelaClientes();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void openTelaClientes() {
		telaClientes = new TelaManterCliente();
		telaClientes.setVisible(true);
	}
	
	public static void closeTelaClientes() {
		telaClientes.setVisible(false);
		telaClientes.dispose();
	}
	
	public static void openTelaContas() {
		telaContas = new TelaVincularConta();
		telaContas.setVisible(true);
	}
	
	public static void closeTelaContas() {
		telaContas.setVisible(false);
		telaContas.dispose();
	}
	
	public static void openTelaManipularConta() {
		telaManipularConta = new TelaManipularConta();
		telaManipularConta.setVisible(true);
	}
	
	public static void closeTelaManipularConta() {
		telaManipularConta.setVisible(false);
		telaManipularConta.dispose();
	}

}
