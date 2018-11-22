package model;

import javax.swing.table.AbstractTableModel;

import controller.Controller;

public class TableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
    private String[] colunasEditable = {"#", "Nome", "Sobrenome", "RG", "CPF", "Endereco", "Salario"};
    private String[] colunasUneditable = {"#", "Nome", "CPF"};
      
    public TableModel(){ }
    
    public String[] getColunasEditable() {
    	return this.colunasEditable;
    }
    
    public String[] getColunasUneditable() {
    	return this.colunasUneditable;
    }
    
    @Override
    public int getRowCount() {
        return Controller.getListaClientes().size();
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
        		return Controller.getListaClientes().get(linha).getId();
            case 1:
                return Controller.getListaClientes().get(linha).getNome();
            case 2:
                return Controller.getListaClientes().get(linha).getSobrenome();
            case 3:
            	return Controller.getListaClientes().get(linha).getRg();
            case 4:
            	return Controller.getListaClientes().get(linha).getCpf();
            case 5:
            	return Controller.getListaClientes().get(linha).getEndereco();
            case 6:
            	return Controller.getListaClientes().get(linha).getSalario();
            default:
                return Controller.getListaClientes().get(linha);
        }
    }
    
    @Override
    public String getColumnName(int coluna) {
        return this.colunasEditable[coluna];
    }

}
