package trabalho;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableClienteModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Conta> listaContas;
    private String[] colunasEditable = {"#", "Nome", "Sobrenome", "RG", "CPF", "Endereco", "Salario"};
    private String[] colunasUneditable = {"#", "Nome", "CPF"};
      
    public TableClienteModel(){
        this.listaClientes = new ArrayList<>();
        this.listaContas = new ArrayList<>();
        
        Cliente c1 = new Cliente(1, "Ana", "Monteiro", "12.345.678-9", "123.456.789-00", "Rua A, numero 1", 1100);
        Cliente c2 = new Cliente(2, "Jo�o", "da Silva", "12.345.678-9", "123.456.789-00", "Rua B, numero 2", 2000);
        Cliente c3 = new Cliente(3, "Pedro", "Santos", "12.345.678-9", "123.456.789-00", "Rua C, numero 3", 2500);
        Cliente c4 = new Cliente(4, "Maria", "Pereira", "12.345.678-9", "123.456.789-00", "Rua D, numero 4", 4500);
        Cliente c5 = new Cliente(5, "Carlos", "Oliveira", "12.345.678-9", "123.456.789-00", "Rua E, numero 5", 8000);
        Cliente c6 = new Cliente(6, "Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 4700);
        Cliente c7 = new Cliente(7, "Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 9000);
        Cliente c8 = new Cliente(8, "Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 2300);
        Cliente c9 = new Cliente(9, "Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 1700);
        Cliente c10 = new Cliente(10, "Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 1500);
        Cliente c11 = new Cliente(11, "Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 5500);
        Cliente c12 = new Cliente(12, "Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 6900);
        
        Conta conta1 = new ContaCorrente(1, 1000, 5000, c1);
        Conta conta2 = new ContaCorrente(2, 200, 500, c2);
        Conta conta3 = new ContaInvestimento(3, 1000, 1000, 1000, c3);
        
        this.addCliente(c1);
        this.addCliente(c2);
        this.addCliente(c3);
        this.addCliente(c4);
        this.addCliente(c5);
        this.addCliente(c6);
        this.addCliente(c7);
        this.addCliente(c8);
        this.addCliente(c9);
        this.addCliente(c10);
        this.addCliente(c11);
        this.addCliente(c12);
        
        this.addConta(conta1);
        this.addConta(conta2);
        this.addConta(conta3);
        
        this.relacionarClienteConta(c1, conta1);
        this.relacionarClienteConta(c2, conta2);
        this.relacionarClienteConta(c3, conta3);
    }
    
    public ArrayList<Cliente> getListaClientes() {
    	return this.listaClientes;
    }
    
    public ArrayList<Conta> getListaContas() {
    	return this.listaContas;
    }
    
    public String[] getColunasEditable() {
    	return this.colunasEditable;
    }
    
    public String[] getColunasUneditable() {
    	return this.colunasUneditable;
    }
    
    public Cliente getCliente(int linha){
        return getListaClientes().get(linha);
    }
    
    public void addCliente (Cliente cliente) {
    	this.listaClientes.add(cliente);
    }
    
    public void removeCliente(int linha) {
    	this.listaClientes.remove(linha);
    }
    
    public void addConta (Conta conta) {
    	this.listaContas.add(conta);
    }
    
    public void removeConta(int linha) {
    	this.listaContas.remove(linha);
    }
    
    public void relacionarClienteConta(Cliente cliente, Conta conta) {
    	conta.setDonoConta(cliente);
    	cliente.setConta(conta);
    }
    
    @Override
    public int getRowCount() {
        return this.listaClientes.size();
    }
    
    @Override
    public int getColumnCount() {
        return this.colunasEditable.length;
    }
    
    @Override
    public Class<?> getColumnClass(int column)
    {
        if (column == 0) {
        	return Integer.class;
        } else if (column == 6) {
        	return Double.class;
        } else {
        	return String.class;
        }   
    }
    
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
        	case 0:
        		return this.listaClientes.get(linha).getId();
            case 1:
                return this.listaClientes.get(linha).getNome();
            case 2:
                return this.listaClientes.get(linha).getSobrenome();
            case 3:
            	return this.listaClientes.get(linha).getRg();
            case 4:
            	return this.listaClientes.get(linha).getCpf();
            case 5:
            	return this.listaClientes.get(linha).getEndereco();
            case 6:
            	return this.listaClientes.get(linha).getSalario();
            default:
                return this.listaClientes.get(linha);
        }
    }
    
    @Override
    public String getColumnName(int coluna) {
        return this.colunasEditable[coluna];
    }

}
