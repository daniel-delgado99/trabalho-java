package trabalho;

import java.awt.EventQueue;

public class Controller {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManterCliente frame = new TelaManterCliente();
					//TableToolTipsDemo tela = new TableToolTipsDemo();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
