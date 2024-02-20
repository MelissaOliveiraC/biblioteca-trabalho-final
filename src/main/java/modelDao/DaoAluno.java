package modelDao;

import modelConnection.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelBeans.AlunoBeans;

/**
 *
 * @author melissaoliveira
 */

public class DaoAluno {
    private final ConexaoBD conex;

    public DaoAluno() {
        this.conex = new ConexaoBD();
    }

    public void salvar(AlunoBeans aluno) {
        try (Connection con = conex.conexao()) {
            String sql = "INSERT INTO alunos(nome_aluno, curso_aluno, matricula_aluno, endereco_aluno, cpf_aluno, numend_aluno, bairro_aluno) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, aluno.getNome());
                pst.setString(2, aluno.getCurso());
                pst.setInt(3, aluno.getMatricula());
                pst.setString(4, aluno.getEnderecoAluno());
                pst.setString(5, aluno.getCpfAluno());
                pst.setInt(6, aluno.getNumeroEnderecoAluno());
                pst.setString(7, aluno.getBairroAluno());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAluno.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: " + ex.getMessage());
        }
    }
    

        public AlunoBeans buscarAlunoPorMatricula(int matricula) {
            AlunoBeans alunoEncontrado = null;
            try (Connection con = conex.conexao()) {
                String sql = "SELECT * FROM alunos WHERE matricula_aluno = ?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, matricula);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            alunoEncontrado = new AlunoBeans();
                            alunoEncontrado.setCodigo(rs.getInt("cod_aluno"));
                            alunoEncontrado.setNome(rs.getString("nome_aluno"));
                            alunoEncontrado.setMatricula(rs.getInt("matricula_aluno"));
                            alunoEncontrado.setCurso(rs.getString("curso_aluno"));
                            alunoEncontrado.setEnderecoAluno(rs.getString("endereco_aluno"));
                            alunoEncontrado.setCpfAluno(rs.getString("cpf_aluno"));
                            alunoEncontrado.setNumeroEnderecoAluno(rs.getInt("numend_aluno"));
                            alunoEncontrado.setBairroAluno(rs.getString("bairro_aluno"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoAluno.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + ex.getMessage());
            }
            return alunoEncontrado;
        }
}