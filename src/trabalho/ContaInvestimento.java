package trabalho;

public class ContaInvestimento extends Conta {
	private double montanteMinimo;
	private double depositoMinimo;
	
	public ContaInvestimento(int numeroConta, double montanteMinimo, double depositoMinimo, double depositoInicial, Cliente donoConta) {
		super(numeroConta, depositoInicial, donoConta);
		this.montanteMinimo = montanteMinimo;
		this.depositoMinimo = depositoMinimo;
	}
	
	public double getMontanteMinimo() {
		return this.montanteMinimo;
	}
	public void setMontanteMinimo(double montanteMinimo) {
		this.montanteMinimo = montanteMinimo;
	}
	public double getDepositoMinimo() {
		return this.depositoMinimo;
	}
	public void setDepositoMinimo(double depositoMinimo) {
		this.depositoMinimo = depositoMinimo;
	}
}
