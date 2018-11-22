package controller;

import java.util.List;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import dao.*;

public class Controller {
	
	public Controller () {      
	}
	
	public static List<Cliente> getListaClientes() {
    	return ClienteDAO.buscarClientes();
    }
    
    public static List<Conta> getListaContas() {
    	return ContaDAO.buscarTodasContas();
    }
    
    public static void addCliente (Cliente cliente) {
    	ClienteDAO.insertCliente(cliente);
    }
    
    public static Cliente getClienteById (int id) {
    	return ClienteDAO.buscarClientePorId(id);
    }
    
    public static void removeCliente (int id) {
    	ClienteDAO.removerCliente(id);
    }
    
    public static void setCliente(Cliente cliente) {
    	ClienteDAO.alterarCliente(cliente);
    	
    }
    
    public static Conta getContaByNumero (int numero, String tipo) {
    	if (tipo.equals("Conta corrente")) {
    		return ContaDAO.buscarContaCorrentePorNumero(numero);
    	} else {
    		return ContaDAO.buscarContaInvestimentoPorNumero(numero);
    	}
    }
    
    public static void addConta (Conta conta) {
    	if (conta.getTipo().equals("Conta corrente")) {
    		ContaDAO.insertContaCorrente((ContaCorrente) conta);    		
    	} else {
    		ContaDAO.insertContaInvestimento((ContaInvestimento) conta);
    	}
    }
    
    public static void setConta (Conta conta) {
    	if (conta.getTipo().equals("Conta corrente")) {
    		ContaDAO.alterarContaCorrente((ContaCorrente) conta);    		
    	} else {
    		ContaDAO.alterarContaInvestimento((ContaInvestimento) conta);
    	}
    }
    
    public static void removeConta(int id) {
    	ContaDAO.removerConta(id);
    }
    
    public static void relacionarClienteConta(Cliente cliente, Conta conta) {
    	conta.setDono(cliente);
    	cliente.setConta(conta);
    }
}
