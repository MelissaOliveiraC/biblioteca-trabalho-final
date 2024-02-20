package modelConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import frontInterface.Catalogo;

/**
 *
 * @author melissaoliveira
 */

public class ConexaoBD {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123"; 
    private static Connection conexao;
    private static Catalogo catalogoInstance;
    public Statement stm;
    public ResultSet rs;

    public Connection conexao(){ 
        Connection connection = null; 
        
        System.setProperty("jdbc.Drivers", DRIVER);
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao; 
    }
    
    
    
    public void executaSql(String sql){
        try {
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Erro ao executar SQL" +ex.getMessage());
        }
    }
    
    public void desconecta(){
        try {
            conexao.close();
            //JOptionPane.showMessageDialog(null, "Banco de Dados foi desconectado.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o Banco de Dados \n." +ex.getMessage());
        }
    }

    public static Connection getInstance() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexao;
    }

    public static void setCatalogoInstance(Catalogo catalogo) {
        catalogoInstance = catalogo;
    }

    public static void updateCatalogoTable() {
        if (catalogoInstance != null) {
            catalogoInstance.updateTable();
        }
    }
}