package model;

public class ContaCorrente extends Conta {
	private double limite;
	
	public ContaCorrente() {
		super();
	}
	
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
	
	public boolean saca (double valor) {
		if (valor > 0) {
			double saldoAtual = this.getSaldo();
			if (saldoAtual-valor < this.getLimite()*(-1)) {
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
	
	public void remunera() {
		this.setSaldo(this.getSaldo() * 1.01);
	}
}
