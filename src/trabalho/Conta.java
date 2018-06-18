package trabalho;

public abstract class Conta implements ContaI {
	private int numeroConta;
	private double saldoConta;
	private double depositoInicial;
	private Cliente donoConta;
	
	public Conta(int numeroConta, double depositoInicial, Cliente donoConta) {
		super();
		this.numeroConta = numeroConta;
		this.depositoInicial = depositoInicial;
		this.saldoConta = this.depositoInicial;
		this.donoConta = donoConta;
	}
	public int getNumero() {
		return this.numeroConta;
	}
	public void setNumero(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public double getSaldoConta() {
		return this.saldoConta;
	}
	public void setSaldoConta(double saldoConta) {
		this.saldoConta = saldoConta;
	}
	public Cliente getDonoConta() {
		return this.donoConta;
	}
	public void setDonoConta(Cliente donoConta) {
		this.donoConta = donoConta;
	}
	public double getDepositoInicial() {
		return this.depositoInicial;
	}
	public void setDepositoInicial(double depositoInicial) {
		this.depositoInicial = depositoInicial;
	}
	
	public boolean deposita (double valor) {
		if (valor > 0) {
			double saldoAtual = this.getSaldoConta();
			saldoAtual += valor;
			this.setSaldoConta((saldoAtual));
			return true;
		} else {
			return false;
		}
	}
	
	public Cliente getDono() {
		return this.donoConta;
	}

	public double getSaldo() {
		return this.saldoConta;
	}
}
