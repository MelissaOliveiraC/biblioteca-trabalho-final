package frontInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelBeans.LivroBeans;
import modelDao.DaoLivro;

/**
 *
 * @author melissaoliveira
 */

public class CadastroLivroForm extends JFrame {
    private JTextField txtTitulo;
    private JTextField txtISBN;
    private JTextField txtEdicao;
    private JTextField txtAno;
    private JTextField txtArea;
    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JTextField txtQuantidadeUnidades; 

    public CadastroLivroForm() {
        setTitle("Cadastro de Livro");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Título:");
        txtTitulo = new JTextField(20);
        add(lblTitulo);
        add(txtTitulo);

        JLabel lblISBN = new JLabel("ISBN:");
        txtISBN = new JTextField(20);
        add(lblISBN);
        add(txtISBN);

        JLabel lblEdicao = new JLabel("Edição:");
        txtEdicao = new JTextField(20);
        add(lblEdicao);
        add(txtEdicao);

        JLabel lblAno = new JLabel("Ano:");
        txtAno = new JTextField(20);
        add(lblAno);
        add(txtAno);

        JLabel lblArea = new JLabel("Área:");
        txtArea = new JTextField(20);
        add(lblArea);
        add(txtArea);

        JLabel lblNome = new JLabel("Nome do Autor(a):");
        txtNome = new JTextField(20);
        add(lblNome);
        add(txtNome);

        JLabel lblSobrenome = new JLabel("Sobrenome do Autor(a):");
        txtSobrenome = new JTextField(20);
        add(lblSobrenome);
        add(txtSobrenome);

        JLabel lblQuantidadeUnidades = new JLabel("Quantidade de Unidades:");
        txtQuantidadeUnidades = new JTextField(20);
        add(lblQuantidadeUnidades);
        add(txtQuantidadeUnidades);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarLivro();
            }
        });
        add(btnSalvar);
    }

    private void salvarLivro() {
        LivroBeans livro = new LivroBeans();
        livro.setTitulo(txtTitulo.getText());
        livro.setISBN(txtISBN.getText());
        livro.setEdicao(txtEdicao.getText());
        livro.setAno(Integer.parseInt(txtAno.getText()));
        livro.setArea(txtArea.getText());
        livro.setNome(txtNome.getText());
        livro.setSobrenome(txtSobrenome.getText());
        livro.setQuantidadeUnidades(Integer.parseInt(txtQuantidadeUnidades.getText()));
        livro.setQuantidadeUnidades(Integer.parseInt(txtQuantidadeUnidades.getText())); 
        livro.setQuantidadeDisponivel(Integer.parseInt(txtQuantidadeUnidades.getText())); // Inicializa a quantidade disponível com a mesma quantidade total

        DaoLivro dao = new DaoLivro();
        dao.salvar(livro);
        JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
    }      
/* 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroLivroForm().setVisible(true);
            }
        });
    }
    */
}