package trabalho;

public class Cliente {
	private int id;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String rg;
	private String endereco;
	private double salario;
	private Conta conta;
	
	public Cliente(int id, String nome, String sobrenome, String cpf, String rg, String endereco, double salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.salario = salario;
		this.conta=null;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return this.sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return this.rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEndereco() {
		return this.endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Conta getConta() {
		return this.conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
