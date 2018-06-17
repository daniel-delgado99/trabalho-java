package trabalho;

import java.awt.EventQueue;

public class Controller {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManterCliente telaClientes = new TelaManterCliente();
					TelaVincularConta telaConta = new TelaVincularConta();
					//TableToolTipsDemo tela = new TableToolTipsDemo();
					telaClientes.setVisible(false);
					telaConta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
