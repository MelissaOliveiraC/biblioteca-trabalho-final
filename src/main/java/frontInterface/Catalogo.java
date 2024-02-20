package frontInterface;

import modelBeans.LivroBeans;
import modelBeans.ModelCtrlTabLivro;
import modelConnection.ConexaoBD; 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author melissaoliveira
 */

public class Catalogo extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public Catalogo() {
        setTitle("Catalogo de Livros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        ConexaoBD conexaoBD = new ConexaoBD();
        conexaoBD.conexao(); 

        ModelCtrlTabLivro modelCtrlTabLivro = new ModelCtrlTabLivro();
        List<LivroBeans> livrosDisponiveis = modelCtrlTabLivro.getLivrosDisponiveis();

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Título");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("Edição");
        tableModel.addColumn("Área");
        tableModel.addColumn("Nome do Autor");
        tableModel.addColumn("Sobrenome do Autor");
        tableModel.addColumn("Quantidade Disponível");

        for (LivroBeans livro : livrosDisponiveis) {
            Object[] row = {
                    livro.getTitulo(),
                    livro.getISBN(),
                    livro.getEdicao(),
                    livro.getArea(),
                    livro.getNome(),
                    livro.getSobrenome(),
                    livro.getQuantidadeDisponivel()
            };
            tableModel.addRow(row);
        }

        // Cria a tabela
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTable() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela

        ModelCtrlTabLivro modelCtrlTabLivro = new ModelCtrlTabLivro();
        List<LivroBeans> livrosDisponiveis = modelCtrlTabLivro.getLivrosDisponiveis();

        for (LivroBeans livro : livrosDisponiveis) {
            Object[] row = {
                livro.getTitulo(),
                livro.getISBN(),
                livro.getEdicao(),
                livro.getArea(),
                livro.getNome(),
                livro.getSobrenome(),
                livro.getQuantidadeDisponivel()
            };
            tableModel.addRow(row); // Adiciona uma nova linha à tabela com os dados do livro
        }
    }
/* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Catalogo form = new Catalogo();
            form.setVisible(true);
        });
    }
*/
}