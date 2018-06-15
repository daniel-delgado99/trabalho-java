package trabalho;

public class ContaCorrente extends Conta {
	private double limite;
	
	public ContaCorrente(int numeroConta, double saldoConta, double depositoInicial, Cliente donoConta) {
		super(numeroConta, saldoConta, depositoInicial, donoConta);
	}

	public double getLimite() {
		return this.limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	
}
