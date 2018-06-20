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
        
        Cliente c1  = new Cliente( 1, "Ana", 	"Monteiro", 	"22.576.794-6", "205.825.024-97", "Rua A, numero 01", 1100);
        Cliente c2  = new Cliente( 2, "Marcos", "Freitas", 		"34.300.315-6", "851.801.994-09", "Rua B, numero 02", 2000);
        Cliente c3  = new Cliente( 3, "Pedro", 	"Santos", 		"47.731.413-2", "639.852.994-47", "Rua C, numero 03", 2500);
        Cliente c4  = new Cliente( 4, "Maria", 	"Pereira", 		"17.741.866-7", "365.517.724-04", "Rua D, numero 04", 4500);
        Cliente c5  = new Cliente( 5, "Carlos", "Oliveira", 	"22.529.531-3", "023.951.924-81", "Rua E, numero 05", 8000);
        Cliente c6  = new Cliente( 6, "Yuri", 	"Bernardes",	"13.449.951-7", "095.458.174-10", "Rua G, numero 06", 4700);
        Cliente c7  = new Cliente( 7, "Camila",	"Souza", 		"12.402.480-4", "502.215.854-00", "Rua H, numero 07", 9000);
        Cliente c8  = new Cliente( 8, "Renan", 	"Nascimento", 	"15.741.928-9", "085.387.904-47", "Rua I, numero 08", 2300);
        Cliente c9  = new Cliente( 9, "Isabela","Novaes", 		"30.159.890-3", "646.416.814-22", "Rua J, numero 09", 1700);
        Cliente c10 = new Cliente(10, "Carla", 	"Ribeiro", 		"46.836.572-2", "463.227.294-97", "Rua K, numero 10", 1500);
        Cliente c11 = new Cliente(11, "Vilson", "Gonçalves",	"41.949.172-7", "887.163.294-06", "Rua L, numero 11", 5500);
        Cliente c12 = new Cliente(12, "Emanuel","Souza", 		"22.584.220-8", "109.896.684-87", "Rua M, numero 12", 6900);
        
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
    
    public Conta getContaFromClienteId(int i) {
    	return this.getCliente(i).getConta();
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
    
    public Cliente getClienteById (int id) {
    	for (int i=0; i<this.getListaClientes().size(); i++) {
    		if (this.getCliente(i).getId() == id) {
    			return this.getCliente(i);
    		}
    	} 
    	return null;
    }
    public Conta getContaByNumero (int numero) {
        for (int i=0; i<this.getListaContas().size(); i++) {
            if (this.getListaContas().get(i).getNumero() == numero) {
                return this.getListaContas().get(i);
            }
        }
        return null;
    }
    
    public void removeCliente (int linha) {
        Cliente c = (Controller.tableModel.getClienteById(linha));
    	if (c.getConta() == null) {
    		this.listaClientes.remove(c);
    	} else {
    		this.listaContas.remove(this.getContaFromClienteId(linha));
    		this.listaClientes.remove(c);
    	}
    }
    
    public void setCliente(int index, Cliente cliente) {
    	this.listaClientes.set(index, cliente);
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
