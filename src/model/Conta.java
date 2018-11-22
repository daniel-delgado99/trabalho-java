package model;

public abstract class Conta implements ContaI {
	private int numero;
	private double saldo;
	private double depositoInicial;
	private String tipo;
	private Cliente dono;
	
	public Conta() {}
	
	public Conta(int numeroConta, double depositoInicial, Cliente donoConta) {
		super();
		this.numero = numeroConta;
		this.depositoInicial = depositoInicial;
		this.saldo = this.depositoInicial;
		this.dono = donoConta;
	}
	public int getNumero() {
		return this.numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getSaldo() {
		return this.saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getDepositoInicial() {
		return this.depositoInicial;
	}
	public void setDepositoInicial(double depositoInicial) {
		this.depositoInicial = depositoInicial;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Cliente getDono() {
		return this.dono;
	}
	public void setDono(Cliente dono) {
		this.dono = dono;
	}
	
	public boolean deposita (double valor) {
		if (valor > 0) {
			double saldoAtual = this.getSaldo();
			saldoAtual += valor;
			this.setSaldo((saldoAtual));
			return true;
		} else {
			return false;
		}
	}

	
}
