package frontInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelBeans.ModelCtrlAluno;

/**
 *
 * @author melissaoliveira
 */

public class InterfacePrincipal extends JFrame {
    private JButton btnCadastrarLivro;
    private JButton btnCadastrarAluno;
    private JButton btnCadastrarEmprestimo;
    private JButton btnStatusAluno; 
    private JButton btnRealizarDevolucao; 
    private final String senhaPadrao = "adm123"; // Senha (padrão) para acessar áreas restritas do sistema

    public InterfacePrincipal() {
        setTitle("Sistema Bibliotecário");
        setSize(400, 350); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new BorderLayout());
        JPanel btnPanel = new JPanel(new GridLayout(5, 1, 0, 10)); // 5 lin, 1 col, sem esp-horiz, 10 px de esp-vert
        
        btnCadastrarLivro = new JButton("Cadastrar Livro");
        btnCadastrarAluno = new JButton("Cadastrar Aluno");
        btnCadastrarEmprestimo = new JButton("Empréstimo");
        btnStatusAluno = new JButton("Informações do Aluno"); 
        btnRealizarDevolucao = new JButton("Devolução"); 

        
        Dimension btnSize = new Dimension(200, 50);
        btnCadastrarLivro.setPreferredSize(btnSize);
        btnCadastrarAluno.setPreferredSize(btnSize);
        btnCadastrarEmprestimo.setPreferredSize(btnSize);
        btnStatusAluno.setPreferredSize(btnSize);
        btnRealizarDevolucao.setPreferredSize(btnSize); 

        btnPanel.add(btnCadastrarLivro);
        btnPanel.add(btnCadastrarAluno);
        btnPanel.add(btnCadastrarEmprestimo);
        btnPanel.add(btnStatusAluno);
        btnPanel.add(btnRealizarDevolucao); 

        panel.add(btnPanel, BorderLayout.CENTER);

        add(panel);

        btnCadastrarLivro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormularioComSenha(new CadastroLivroForm());
            }
        });

        btnCadastrarAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormularioComSenha(new CadastroAlunoForm());
            }
        });
        
        btnCadastrarEmprestimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abrir a janela de cadastro de empréstimo
                CadastroEmprestimoForm cadastroEmprestimoForm = new CadastroEmprestimoForm();
                cadastroEmprestimoForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cadastroEmprestimoForm.setVisible(true);

                // Abrir a janela de lista de livros disponíveis
                Catalogo listaLivrosForm = new Catalogo();
                listaLivrosForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                listaLivrosForm.setPreferredSize(new Dimension(1600, 500)); // Define o tamanho preferencial da tabela
                listaLivrosForm.pack(); // Redimensiona a janela para se ajustar ao novo tamanho
                listaLivrosForm.setVisible(true);

                // Adicionar WindowListener para fechar a janela principal após fechar a janela de empréstimo
                cadastroEmprestimoForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        dispose(); // Fecha a janela principal
                    }
                });
            }
        });


        btnStatusAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirStatusAlunoForm();
            }
        });
        
        btnRealizarDevolucao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormularioComSenha(new DevolucaoForm());
            }
        });
    }

    private void abrirFormularioComSenha(JFrame form) {
        JPasswordField pwdField = new JPasswordField(10);
        int action = JOptionPane.showOptionDialog(null, pwdField, "Digite a senha para acessar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (action == JOptionPane.OK_OPTION) {
            String senha = new String(pwdField.getPassword());
            if (senha.equals(senhaPadrao)) {
                form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                form.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta!");
            }
        }
    }

    private void abrirStatusAlunoForm() {
        String[] colunas = {"Código", "Nome", "Curso", "Matricula","Endereço", "CPF", "Nº Endereço", "Bairro"};
        ModelCtrlAluno modelo = new ModelCtrlAluno(null, colunas);
        StatusAlunoForm statusAlunoForm = new StatusAlunoForm(modelo);
        statusAlunoForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfaceLogin().setVisible(true);
            }
        });
    }
}