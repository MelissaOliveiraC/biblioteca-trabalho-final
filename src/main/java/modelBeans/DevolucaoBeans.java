package modelBeans;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author melissaoliveira
 */

public class DevolucaoBeans extends AbstractTableModel {
    private ArrayList<Object[]> linhas;
    private String[] colunas;

    public DevolucaoBeans(ArrayList<Object[]> dados, String[] colunas) {
        this.linhas = dados;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] linha = linhas.get(rowIndex);
        return linha[columnIndex];
    }

    public void setLinhas(ArrayList<Object[]> linhas) {
        this.linhas = linhas;
        fireTableDataChanged();
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
        fireTableStructureChanged();
    }
}