package modelBeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import modelConnection.ConexaoBD;

/**
 *
 * @author melissaoliveira
 */

public class ModelCtrlAluno {
    
    private ArrayList linhas = null;
    private String[] colunas = null;
    private ConexaoBD conexao;

    public ModelCtrlAluno(ArrayList l, String[] c) {
        setLinhas(l);
        setColunas(c);
    }

    public ModelCtrlAluno() {
        this.conexao = new ConexaoBD();
    }
    
    public Object getValueAt(int numL, int numC){
        Object [] lin = (Object[])getLinhas().get(numL);
        return lin[numC];
    }
    
    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }
    
    public String[] getColunas() {
        return colunas;
    }
    
    public void setColunas(String[] colunas){
        this.colunas = colunas;
    }
    
    public int getNomeColunaCount() {
        return colunas.length;
    }
    
    public int getLinhaCount(){
        return linhas.size();
    }
    
    public String getColunaNome(int numC) {
        return colunas[numC];
    }

     // Método para buscar aluno por matrícula
     public Object[] buscarAlunoPorMatricula(int matricula) {
        ConexaoBD conex = new ConexaoBD();
        Connection con = conex.conexao();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Object[] alunoEncontrado = null;

        try {
            String sql = "SELECT * FROM alunos WHERE matricula_aluno = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, matricula);
            rs = pst.executeQuery();

            if (rs.next()) {
                alunoEncontrado = new Object[colunas.length];
                for (int i = 0; i < colunas.length; i++) {
                    alunoEncontrado[i] = rs.getObject(i + 1); 
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar aluno por matrícula: " + ex.getMessage());
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelCtrlAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelCtrlAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelCtrlAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return alunoEncontrado;
    }

    public Object[][] buscarEmprestimosAtivosPorCodigoAluno(int codigoAluno) {
        ConexaoBD conex = new ConexaoBD();
        Connection con = conex.conexao();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Object[][] emprestimos = null;
    
        try {
            String sql = "SELECT livro.titulo_livro, emprestimo.emp_codigo, emprestimo.emp_situacao " + 
                         "FROM emprestimo " +
                         "JOIN livro ON emprestimo.emp_codlivro = livro.id " +
                         "WHERE emprestimo.emp_codaluno = ? AND emprestimo.emp_situacao = 'Ativo'";
            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, codigoAluno);
            rs = pst.executeQuery();
            
            rs.last(); 
            int rowCount = rs.getRow(); 
            rs.beforeFirst(); 
            
            // Inicializando o array para armazenar os resultados
            emprestimos = new Object[rowCount][3]; // 3 col: titulo_livro, emp_codigo, emp_situacao
            
            int index = 0;
            while (rs.next()) {
                String tituloLivro = rs.getString("titulo_livro");
                int codigoEmprestimo = rs.getInt("emp_codigo");
                String situacaoEmprestimo = rs.getString("emp_situacao");
                
                // Armazenando os resultados no array
                emprestimos[index][0] = tituloLivro;
                emprestimos[index][1] = codigoEmprestimo;
                emprestimos[index][2] = situacaoEmprestimo;
                
                index++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar empréstimos ativos por código do aluno: " + ex.getMessage());
        } finally {
            // Fechando recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelCtrlAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelCtrlAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ModelCtrlAluno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return emprestimos;
    }
    
    // Método para verificar se a matrícula do aluno está cadastrada
    public boolean verificarSenha(String senha) {
        ConexaoBD conexao = new ConexaoBD(); 
        try (Connection con = conexao.conexao()) {
            // Verifica se a senha corresponde à senha padrão do administrador
            if (senha.equals("adm123")) {
                return true; // A senha é válida para o administrador
            }
    
            // Verifica se a senha corresponde a alguma matrícula de aluno cadastrada
            String sqlAluno = "SELECT COUNT(*) FROM alunos WHERE matricula_aluno = ?";
            try (PreparedStatement pstAluno = con.prepareStatement(sqlAluno)) {
                pstAluno.setInt(1, Integer.parseInt(senha));
                try (ResultSet rsAluno = pstAluno.executeQuery()) {
                    if (rsAluno.next() && rsAluno.getInt(1) > 0) {
                        return true; // A senha é válida para um aluno cadastrado
                    }
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return false; // A senha não corresponde a nenhum registro.
    }
    
}