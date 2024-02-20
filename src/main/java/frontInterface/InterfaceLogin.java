package frontInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelBeans.ModelCtrlAluno;

/**
 *
 * @author melissaoliveira
 */

public class InterfaceLogin extends JFrame {
    private JLabel lblUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private final String senhaPadrao = "adm123"; 

    public InterfaceLogin() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        lblUsuario = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        btnLogin = new JButton("Logar");

        add(lblUsuario);
        add(txtSenha);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String senhaDigitada = new String(txtSenha.getPassword());
        if (senhaDigitada.equals(senhaPadrao)) {
            abrirTelaPrincipal();
        } else {
            // Se a senha não for a padrão, verificar se é a matrícula de um aluno
            String matricula = senhaDigitada;
            ModelCtrlAluno modelCtrlAluno = new ModelCtrlAluno();
            if (modelCtrlAluno.verificarSenha(matricula)) {
                abrirTelaPrincipal();
            } else {
                JOptionPane.showMessageDialog(null, "Senha inválida!");
            }
        }
    }

    private void abrirTelaPrincipal() {
        InterfacePrincipal telaPrincipal = new InterfacePrincipal();
        telaPrincipal.setVisible(true);
        dispose(); // Fecha a tela de login após abrir a tela principal
    }
/* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfaceLogin().setVisible(true);
            }
        });
    }
*/
}