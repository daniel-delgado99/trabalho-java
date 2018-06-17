package trabalho;

public class ContaCorrente extends Conta {
	private double limite;
	
	public ContaCorrente(int numeroConta, double depositoInicial, Cliente donoConta) {
		super(numeroConta, depositoInicial, donoConta);
	}

	public double getLimite() {
		return this.limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
}
