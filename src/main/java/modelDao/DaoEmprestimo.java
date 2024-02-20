package modelDao;

import modelConnection.ConexaoBD;
import java.sql.*;
import modelBeans.EmprestimoBeans;
import javax.swing.JOptionPane;

/**
 *
 * @author melissaoliveira
 */

public class DaoEmprestimo {
    EmprestimoBeans situacao = new EmprestimoBeans();
    ConexaoBD conex = new ConexaoBD();
    ConexaoBD conexAluno = new ConexaoBD();
    ConexaoBD conexLivro = new ConexaoBD();
    int codAluno;
    int codLivro;
    
    public void Salvar(EmprestimoBeans emprestimo) {
        BuscaAluno(emprestimo.getNomeAluno());
        BuscaLivro(emprestimo.getNomeLivro());
    
        try (Connection con = conex.conexao()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO emprestimo(emp_codaluno, emp_codlivro, emp_dataempres, emp_situacao) VALUES (?, ?, ?, ?)");
            pst.setInt(1, codAluno);
            pst.setInt(2, codLivro);
            pst.setDate(3, emprestimo.getDat1());
            pst.setString(4, emprestimo.getStatus());
            pst.execute();
    
            // Atualiza a quantidade disponível do livro (--livro)
            PreparedStatement pstUpdate = con.prepareStatement("UPDATE livro SET quantidade_disponivel = quantidade_disponivel - 1 WHERE id = ?");
            pstUpdate.setInt(1, codLivro);
            pstUpdate.executeUpdate();
    
            //JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar empréstimo: " + ex);
        }
    }    
    
    public void BuscaAluno(String nomeAluno){
        conexAluno.conexao();
        conexAluno.executaSql("select *from alunos where nome_aluno ='"+nomeAluno+"'");
        try {
            conexAluno.rs.first();
            codAluno = conexAluno.rs.getInt("cod_aluno");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"aluno nao encontrado");
        }
        
    }
    
    public void BuscaLivro(String nomeLivro){
        conexLivro.conexao();
        conexLivro.executaSql("select *from livro where titulo_livro ='"+nomeLivro+"'");
        try {
            conexLivro.rs.first();
            codLivro = conexLivro.rs.getInt("id"); 
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"livro nao encontrado");
        }
    }
}
