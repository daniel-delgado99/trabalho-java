package trabalho;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableClienteModel extends AbstractTableModel {
	private ArrayList<Cliente> listaClientes;
    private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Endereco"};
      
    public TableClienteModel(){
        this.listaClientes = new ArrayList<>();
        
        Cliente c1 = new Cliente("Ana", "Monteiro", "12.345.678-9", "123.456.789-00", "Rua A, número 1");
        Cliente c2 = new Cliente("João", "da Silva", "12.345.678-9", "123.456.789-00", "Rua B, número 2");
        Cliente c3 = new Cliente("Pedro", "Santos", "12.345.678-9", "123.456.789-00", "Rua C, número 3");
        Cliente c4 = new Cliente("Maria", "Pereira", "12.345.678-9", "123.456.789-00", "Rua D, número 4");
        Cliente c5 = new Cliente("Carlos", "Oliveira", "12.345.678-9", "123.456.789-00", "Rua E, número 5");
        Cliente c6 = new Cliente("Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, número 6");
        
        this.addCliente(c1);
        this.addCliente(c2);
        this.addCliente(c3);
        this.addCliente(c4);
        this.addCliente(c5);
        this.addCliente(c6);
    }
    
    public ArrayList<Cliente> getListaClientes() {
    	return this.listaClientes;
    }
    
    public String[] getColunas() {
    	return this.colunas;
    }
    
    public Cliente getCliente(int linha){
        return this.listaClientes.get(linha);
    }
    
    public void addCliente (Cliente cliente) {
    	this.listaClientes.add(cliente);
    	fireTableDataChanged();
    }
    
    public void removeCliente(int linha) {
    	this.listaClientes.remove(linha);
    	fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return this.listaClientes.size();
    }
    
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return this.listaClientes.get(linha).getNome();
            case 1:
                return this.listaClientes.get(linha).getSobrenome();
            case 2:
            	return this.listaClientes.get(linha).getRg();
            case 3:
            	return this.listaClientes.get(linha).getCpf();
            case 4:
            	return this.listaClientes.get(linha).getEndereco();
            default:
                return this.listaClientes.get(linha);
        }
    }
    
    @Override
    public String getColumnName(int coluna) {
        return this.colunas[coluna];
    }

}
