package modelDao;

import modelConnection.ConexaoBD;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import modelBeans.LivroBeans;

/**
 *
 * @author melissaoliveira
 */

public class DaoLivro {
    private final ConexaoBD conexao;

    public DaoLivro() {
        this.conexao = new ConexaoBD();
    }

    public void salvar(LivroBeans livro) {
        try (Connection con = conexao.conexao()) {
            String sql = "INSERT INTO livro(titulo_livro, isbn_livro, edicao_livro, ano_livro, area_livro, nome_livro, sobrenome_livro, quantidade_unidades, quantidade_disponivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, livro.getTitulo());
                pst.setString(2, livro.getISBN());
                pst.setString(3, livro.getEdicao());
                pst.setInt(4, livro.getAno());
                pst.setString(5, livro.getArea());
                pst.setString(6, livro.getNome());
                pst.setString(7, livro.getSobrenome());
                pst.setInt(8, livro.getQuantidadeUnidades());
                pst.setInt(9, livro.getQuantidadeUnidades()); // Define a quantidade disponível inicialmente igual à quantidade total
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoLivro.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: " + ex.getMessage());
        }
    } 
    
    // Método para atualizar a quantidade disponível do livro
    public void atualizarQuantidadeDisponivel(int codigoLivro, int quantidade) {
        try (Connection con = conexao.conexao()) {
            PreparedStatement pst = con.prepareStatement("UPDATE livro SET quantidade_disponivel = quantidade_disponivel + ? WHERE id = ?");
            pst.setInt(1, quantidade);
            pst.setInt(2, codigoLivro);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a quantidade disponível do livro: " + ex.getMessage());
        }
    }

    public LivroBeans buscarLivro(LivroBeans livro) {
        LivroBeans livroEncontrado = null;
        try (Connection con = conexao.conexao()) {
            String sql = "SELECT * FROM livro WHERE titulo_livro LIKE ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                //pst.setString(1, "%" + livro.getPesquisa() + "%");
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        livroEncontrado = new LivroBeans();
                        //livroEncontrado.setCodigo(rs.getInt("cod_livro"));
                        livroEncontrado.setTitulo(rs.getString("titulo_livro"));
                        livroEncontrado.setISBN(rs.getString("isbn_livro"));
                        livroEncontrado.setEdicao(rs.getString("edicao_livro"));
                        livroEncontrado.setAno(rs.getInt("ano_livro"));
                        livroEncontrado.setArea(rs.getString("area_livro"));
                        livroEncontrado.setNome(rs.getString("nome_livro"));
                        livroEncontrado.setSobrenome(rs.getString("sobrenome_livro"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Livro não encontrado");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoLivro.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao buscar livro: " + ex.getMessage());
        }
        return livroEncontrado;
    }
}