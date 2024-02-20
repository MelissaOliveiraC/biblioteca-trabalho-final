package modelDao;

import modelConnection.ConexaoBD;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author melissaoliveira
 */

public class DaoDevolucao {
    private ConexaoBD conexao;

    public DaoDevolucao() {
        this.conexao = new ConexaoBD();
    }

    public void realizarDevolucao(int codigoEmprestimo) {
        try (Connection con = conexao.conexao()) {
            PreparedStatement pst = con.prepareStatement("UPDATE emprestimo SET emp_situacao = 'Devolvido' WHERE emp_codigo = ?");
            pst.setInt(1, codigoEmprestimo);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                // Atualiza a quantidade disponível do livro
                atualizarQuantidadeDisponivelLivro(codigoEmprestimo);
                
                JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao realizar a devolução. Empréstimo não encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar a devolução: " + ex.getMessage());
        }
    }
    
    // Método para atualizar a quantidade disponível do livro após a devolução
    private void atualizarQuantidadeDisponivelLivro(int codigoEmprestimo) {
        try (Connection con = conexao.conexao()) {
            PreparedStatement pst = con.prepareStatement("SELECT emp_codlivro FROM emprestimo WHERE emp_codigo = ?");
            pst.setInt(1, codigoEmprestimo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int codigoLivro = rs.getInt("emp_codlivro");
                DaoLivro daoLivro = new DaoLivro();
                daoLivro.atualizarQuantidadeDisponivel(codigoLivro, 1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a quantidade disponível do livro: " + ex.getMessage());
        }
    }
}