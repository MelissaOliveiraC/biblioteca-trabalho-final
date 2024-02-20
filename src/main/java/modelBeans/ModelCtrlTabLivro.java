package modelBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelConnection.ConexaoBD;

/**
 *
 * @author melissaoliveira
 */

public class ModelCtrlTabLivro {
    private final ConexaoBD conexao;

    public ModelCtrlTabLivro() {
        this.conexao = new ConexaoBD();
    }

    /**
     * Obtém os livros disponíveis para empréstimo (aqueles com quantidade disponível maior que zero).
     * @return Lista de livros disponíveis.
     */
    public List<LivroBeans> getLivrosDisponiveis() {
        List<LivroBeans> livros = new ArrayList<>();
    
        try (Connection con = conexao.conexao()) {
            String sql = "SELECT *, quantidade_disponivel FROM livro WHERE quantidade_disponivel > 0";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    LivroBeans livro = new LivroBeans();
                    livro.setTitulo(rs.getString("titulo_livro"));
                    livro.setISBN(rs.getString("isbn_livro"));
                    livro.setEdicao(rs.getString("edicao_livro"));
                    livro.setArea(rs.getString("area_livro"));
                    livro.setNome(rs.getString("nome_livro"));
                    livro.setSobrenome(rs.getString("sobrenome_livro"));

                    livro.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                    livros.add(livro);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return livros;
    }
}