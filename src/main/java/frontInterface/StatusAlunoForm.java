package frontInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelBeans.ModelCtrlAluno;

/**
 *
 * @author melissaoliveira
 */

public class StatusAlunoForm extends JFrame {
    private JTextField txtMatriculaAluno;
    private JTextArea txtAreaDados;
    private JButton btnBuscar;

    private ModelCtrlAluno modelo;

    public StatusAlunoForm(ModelCtrlAluno modelo) {
        this.modelo = modelo;

        setTitle("Visualizar Informações do Aluno(a)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout());

        txtMatriculaAluno = new JTextField(10);
        panel.add(new JLabel("Matrícula do Aluno(a):"));
        panel.add(txtMatriculaAluno);

        btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar);

        add(panel, BorderLayout.NORTH);

        txtAreaDados = new JTextArea();
        txtAreaDados.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(txtAreaDados);
        add(scrollPane, BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarAluno();
            }
        });
    }

    private void buscarAluno() {
        int matriculaAluno = Integer.parseInt(txtMatriculaAluno.getText());
        Object[] aluno = modelo.buscarAlunoPorMatricula(matriculaAluno);

        if (aluno != null) {
            exibirDadosAluno(aluno);
        } else {
            JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
        }
    }

    private void exibirDadosAluno(Object[] aluno) {
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < aluno.length; i++) {
            dados.append(modelo.getColunaNome(i)).append(": ").append(aluno[i]).append("\n");
        }
    
        dados.append("Empréstimos Ativos:\n");
        Object[][] emprestimos = modelo.buscarEmprestimosAtivosPorCodigoAluno((int) aluno[0]); 
        if (emprestimos != null && emprestimos.length > 0) {
            for (Object[] emprestimo : emprestimos) {
                dados.append("Livro: ").append(emprestimo[0]).append(", Código do Empréstimo: ").append(emprestimo[1]).append(", Situação: ").append(emprestimo[2]).append("\n");
            }
        } else {
            dados.append("Nenhum empréstimo ativo encontrado para este aluno.\n");
        }
    
        txtAreaDados.setText(dados.toString());
    }
}