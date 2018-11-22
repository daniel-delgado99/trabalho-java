package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;

public class ContaDAO {
static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static void insertContaInvestimento(ContaInvestimento ci) {
		PreparedStatement pst;
		try {
			int numeroConta = insertConta(ci);
			pst = con.prepareStatement("INSERT INTO contaInvestimento (montanteMinimo, depositoMinimo, numero) values (?,?,?);");
			pst.setDouble(1, ci.getMontanteMinimo());
			pst.setDouble(2, ci.getDepositoMinimo());
			pst.setInt(3, numeroConta);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertContaCorrente(ContaCorrente cc) {
		PreparedStatement pst;
		try {
			int numeroConta = insertConta(cc);
			pst = con.prepareStatement("INSERT INTO contaCorrente (limite, numero) values (?,?);");
			pst.setDouble(1, cc.getLimite());
			pst.setInt(2, numeroConta);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int insertConta(Conta c) {
		PreparedStatement pst;
		int numeroContaGerado = 0;
		try {
			pst = con.prepareStatement("INSERT INTO conta (saldo, depositoInicial, tipo, id_cliente) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, c.getSaldo());
			pst.setDouble(2, c.getDepositoInicial());
			pst.setString(3, c.getTipo());
			pst.setInt(4, c.getDono().getId());
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				numeroContaGerado = rs.getInt(1);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numeroContaGerado;
	}

	public static ContaCorrente buscarContaCorrentePorNumero(int numero) {
		PreparedStatement pst;
		ContaCorrente cc = new ContaCorrente();
		try {
			pst = con.prepareStatement("SELECT c.numero, c.saldo, c.depositoInicial, cc.limite, c.id_cliente "
					+ "FROM contaCorrente AS cc INNER JOIN conta AS c ON cc.numero = c.numero "
					+ "WHERE c.numero = ?;");
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				cc.setNumero(rs.getInt("numero"));
				cc.setSaldo(rs.getDouble("saldo"));
				cc.setDepositoInicial(rs.getDouble("depositoInicial"));
				cc.setLimite(rs.getDouble("limite"));
				cc.setTipo("Conta corrente");
				cc.setDono(ClienteDAO.buscarClientePorId(rs.getInt("id_cliente")));
				Controller.relacionarClienteConta(cc.getDono(), cc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cc;
	}
	
	public static List<ContaCorrente> buscarContasCorrente() {
		PreparedStatement pst;
		List<ContaCorrente> lista = new ArrayList<ContaCorrente>();
		try {
			pst = con.prepareStatement("SELECT c.numero, c.saldo, c.depositoInicial, cc.limite, c.id_cliente "
					+ "FROM contaCorrente AS cc INNER JOIN conta AS c ON cc.numero = c.numero;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ContaCorrente cc = new ContaCorrente();
				cc.setNumero(rs.getInt("numero"));
				cc.setSaldo(rs.getDouble("saldo"));
				cc.setDepositoInicial(rs.getDouble("depositoInicial"));
				cc.setLimite(rs.getDouble("limite"));
				cc.setTipo("Conta corrente");
				cc.setDono(ClienteDAO.buscarClientePorId(rs.getInt("id_cliente")));
				Controller.relacionarClienteConta(cc.getDono(), cc);
				
				lista.add(cc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static ContaInvestimento buscarContaInvestimentoPorNumero(int numero) {
		PreparedStatement pst;
		ContaInvestimento ci = new ContaInvestimento();
		try {
			pst = con.prepareStatement("SELECT c.numero, c.saldo, c.depositoInicial, ci.depositoMinimo, ci.montanteMinimo, c.id_cliente "
					+ "FROM contaInvestimento AS ci INNER JOIN conta AS c ON ci.numero = c.numero "
					+ "WHERE c.numero = ?;");
			pst.setInt(1, numero);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ci.setNumero(rs.getInt("numero"));
				ci.setSaldo(rs.getDouble("saldo"));
				ci.setDepositoInicial(rs.getDouble("depositoInicial"));
				ci.setDepositoMinimo(rs.getDouble("depositoMinimo"));
				ci.setMontanteMinimo(rs.getDouble("montanteMinimo"));
				ci.setTipo("Conta investimento");
				ci.setDono(ClienteDAO.buscarClientePorId(rs.getInt("id_cliente")));
				Controller.relacionarClienteConta(ci.getDono(), ci);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ci;
	}
	
	public static List<ContaInvestimento> buscarContasInvestimento() {
		PreparedStatement pst;
		List<ContaInvestimento> lista = new ArrayList<ContaInvestimento>();
		try {
			pst = con.prepareStatement("SELECT c.numero, c.saldo, c.depositoInicial, ci.depositoMinimo, ci.montanteMinimo, c.id_cliente "
					+ "FROM contaInvestimento AS ci INNER JOIN conta AS c ON ci.numero = c.numero;");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ContaInvestimento ci = new ContaInvestimento();
				ci.setNumero(rs.getInt("numero"));
				ci.setSaldo(rs.getDouble("saldo"));
				ci.setDepositoInicial(rs.getDouble("depositoInicial"));
				ci.setDepositoMinimo(rs.getDouble("depositoMinimo"));
				ci.setMontanteMinimo(rs.getDouble("montanteMinimo"));
				ci.setTipo("Conta investimento");
				ci.setDono(ClienteDAO.buscarClientePorId(rs.getInt("id_cliente")));
				Controller.relacionarClienteConta(ci.getDono(), ci);
				
				lista.add(ci);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public static List<Conta> buscarTodasContas() {
		List<Conta> lista = new ArrayList<Conta>();
		List<ContaCorrente> listaCC = ContaDAO.buscarContasCorrente();
		List<ContaInvestimento> listaCI = ContaDAO.buscarContasInvestimento();
		
		lista.addAll(listaCC);
		lista.addAll(listaCI);
				
		return lista;
	}

	public static void alterarContaCorrente(ContaCorrente cc) {
		PreparedStatement pst;
		try {
			alterarConta(cc);
			pst = con.prepareStatement("UPDATE contaCorrente SET limite = ? WHERE numero = ?;");
			pst.setDouble(1, cc.getLimite());
			pst.setInt(2, cc.getNumero());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void alterarContaInvestimento(ContaInvestimento ci) {
		PreparedStatement pst;
		try {
			alterarConta(ci);
			pst = con.prepareStatement("UPDATE contaInvestimento SET montanteMinimo = ?, depositoMinimo = ? WHERE numero = ?;");
			pst.setDouble(1, ci.getMontanteMinimo());
			pst.setDouble(2, ci.getDepositoMinimo());
			pst.setInt(3, ci.getNumero());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void alterarConta(Conta c) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE conta SET saldo = ?, depositoInicial = ?, tipo = ?, id_cliente = ? WHERE numero = ?;");
			pst.setDouble(1, c.getSaldo());
			pst.setDouble(2, c.getDepositoInicial());
			pst.setString(3, c.getTipo());
			pst.setInt(4, c.getDono().getId());
			pst.setInt(5, c.getNumero());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removerConta(int id) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("DELETE FROM conta WHERE numero = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
