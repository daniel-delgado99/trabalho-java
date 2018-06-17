package trabalho;

public class ContaCorrente extends Conta {
	private double limite;
	
	public ContaCorrente(int numeroConta, double depositoInicial, double limite, Cliente donoConta) {
		super(numeroConta, depositoInicial, donoConta);
		this.limite = limite;
	}

	public double getLimite() {
		return this.limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
}
