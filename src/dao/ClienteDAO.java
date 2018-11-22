package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Cliente;

import java.util.ArrayList;

public class ClienteDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static void insertCliente(Cliente c) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO cliente (nome, sobrenome, cpf, rg, endereco, salario ) values (?,?,?,?,?,?)");
			pst.setString(1, c.getNome());
			pst.setString(2, c.getSobrenome());
			pst.setString(3, c.getCpf());
			pst.setString(4, c.getRg());
			pst.setString(5, c.getEndereco());
			pst.setDouble(6, c.getSalario());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Cliente buscarClientePorId(int id) {
		PreparedStatement pst;
		Cliente u = new Cliente();
		try {
			pst = con.prepareStatement("SELECT * FROM cliente WHERE id=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setSobrenome(rs.getString("sobrenome"));
				u.setCpf(rs.getString("cpf"));
				u.setRg(rs.getString("rg"));
				u.setEndereco(rs.getString("endereco"));
				u.setSalario(rs.getDouble("salario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public static List<Cliente> buscarClientes() {
		PreparedStatement pst;
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			pst = con.prepareStatement("SELECT * FROM cliente;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setSobrenome(rs.getString("sobrenome"));
				c.setCpf(rs.getString("cpf"));
				c.setRg(rs.getString("rg"));
				c.setEndereco(rs.getString("endereco"));
				c.setSalario(rs.getDouble("salario"));

				clientes.add(c);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public static void alterarCliente(Cliente c) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE cliente SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, endereco = ?, salario = ? WHERE id = ?;");
			pst.setString(1, c.getNome());
			pst.setString(2, c.getSobrenome());
			pst.setString(3, c.getCpf());
			pst.setString(4, c.getRg());
			pst.setString(5, c.getEndereco());
			pst.setDouble(6, c.getSalario());
			pst.setInt(7, c.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removerCliente(int id) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("DELETE FROM cliente WHERE id = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
