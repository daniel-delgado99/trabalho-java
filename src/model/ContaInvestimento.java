package model;

public class ContaInvestimento extends Conta {
	private double montanteMinimo;
	private double depositoMinimo;
	
	public ContaInvestimento() {
		super();
	}
	
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
	
	public boolean saca (double valor) {
		if (valor > 0) {
			double saldoAtual = this.getSaldo();
			if (valor > saldoAtual || saldoAtual-valor < this.getMontanteMinimo()) {
				return false;
			} else {
				saldoAtual -= valor;
				this.setSaldo((saldoAtual));
				return true;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public boolean deposita (double valor) {
		if (valor >= this.getDepositoMinimo()) {
			double saldoAtual = this.getSaldo();
			saldoAtual += valor;
			this.setSaldo((saldoAtual));
			return true;
		} else {
			return false;
		}
	}
	
	public void remunera() {
		this.setSaldo(this.getSaldo() * 1.02);
	}
}
