package frontInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import modelBeans.EmprestimoBeans;
import modelConnection.ConexaoBD;
import modelDao.DaoEmprestimo;

/**
 *
 * @author melissaoliveira
 */

public class CadastroEmprestimoForm extends JFrame { 
    private JTextField txtNomeAluno;
    private JTextField txtNomeLivro;
    private JTextField txtDataEmprestimo;

    public CadastroEmprestimoForm() {
        setTitle("Realizar Empréstimo");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblNomeAluno = new JLabel("Nome do Aluno:");
        txtNomeAluno = new JTextField(20);
        add(lblNomeAluno);
        add(txtNomeAluno);

        JLabel lblNomeLivro = new JLabel("Nome do Livro:");
        txtNomeLivro = new JTextField(20);
        add(lblNomeLivro);
        add(txtNomeLivro);

        JLabel lblDataEmprestimo = new JLabel("Data do Empréstimo:");
        txtDataEmprestimo = new JTextField(20);
        add(lblDataEmprestimo);
        add(txtDataEmprestimo);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarEmprestimo();
            }
        });
        buttonPanel.add(btnSalvar); 

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        buttonPanel.add(btnCancelar); 

        add(buttonPanel); 
    }

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        try {
            java.util.Date parsed = format.parse(dateString); 
            return new java.sql.Date(parsed.getTime()); 
        } catch (ParseException e) {
            e.printStackTrace();
            return null; 
        }
    }

    private void salvarEmprestimo() {
        EmprestimoBeans emprestimo = new EmprestimoBeans();
        emprestimo.setNomeAluno(txtNomeAluno.getText());
        emprestimo.setNomeLivro(txtNomeLivro.getText());
        emprestimo.setDat1(convertStringToDate(txtDataEmprestimo.getText()));
        emprestimo.setStatus("Ativo");

        DaoEmprestimo dao = new DaoEmprestimo();
        dao.Salvar(emprestimo);

        // Após salvar o empréstimo, atualiza a tabela Catalogo
        ConexaoBD.updateCatalogoTable();

        JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
        dispose();
    }

/* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroEmprestimoForm().setVisible(true);
            }
        });
    }
    */
}
