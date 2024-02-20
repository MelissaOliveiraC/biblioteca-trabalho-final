package frontInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelBeans.AlunoBeans;
import modelDao.DaoAluno;

/**
 *
 * @author melissaoliveira
 */

public class CadastroAlunoForm extends JFrame {
    private JTextField txtNome;
    private JTextField txtCurso;
    private JTextField txtMatricula;
    private JTextField txtEndereco;
    private JTextField txtCPF;
    private JTextField txtNumEndereco;
    private JTextField txtBairro;

    public CadastroAlunoForm() {
        setTitle("Cadastro de Aluno");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);
        add(lblNome);
        add(txtNome);

        JLabel lblCurso = new JLabel("Curso:");
        txtCurso = new JTextField(20);
        add(lblCurso);
        add(txtCurso);

        JLabel lblMatricula = new JLabel("Matrícula:");
        txtMatricula = new JTextField(20);
        add(lblMatricula);
        add(txtMatricula);

        JLabel lblEndereco = new JLabel("Endereço:");
        txtEndereco = new JTextField(20);
        add(lblEndereco);
        add(txtEndereco);

        JLabel lblCPF = new JLabel("CPF:");
        txtCPF = new JTextField(20);
        add(lblCPF);
        add(txtCPF);

        JLabel lblNumEndereco = new JLabel("Número do Endereço:");
        txtNumEndereco = new JTextField(20);
        add(lblNumEndereco);
        add(txtNumEndereco);

        JLabel lblBairro = new JLabel("Bairro:");
        txtBairro = new JTextField(20);
        add(lblBairro);
        add(txtBairro);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarAluno();
            }
        });
        add(btnSalvar);
    }

    private void salvarAluno() {
        AlunoBeans aluno = new AlunoBeans();
        aluno.setNome(txtNome.getText());
        aluno.setCurso(txtCurso.getText());
        aluno.setMatricula(Integer.parseInt(txtMatricula.getText()));
        aluno.setEnderecoAluno(txtEndereco.getText());
        aluno.setCpfAluno(txtCPF.getText());
        aluno.setNumeroEnderecoAluno(Integer.parseInt(txtNumEndereco.getText()));
        aluno.setBairroAluno(txtBairro.getText());

        DaoAluno dao = new DaoAluno();
        dao.salvar(aluno);
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroAlunoForm().setVisible(true);
            }
        });
    }
    */
}