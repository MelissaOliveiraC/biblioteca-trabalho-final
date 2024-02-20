package frontInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelDao.DaoDevolucao;

/**
 *
 * @author melissaoliveira
 */

public class DevolucaoForm extends JFrame {
    private JButton btnRealizarDevolucao;
    private JTextField txtCodigoEmprestimo;
    private DaoDevolucao daoDevolucao;

    public DevolucaoForm() {
        setTitle("Realizar Devolução");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10)); // 2 lin, 1 col, sem esp-horiz, 10 px de esp-vert

        JLabel lblCodigoEmprestimo = new JLabel("Código do Empréstimo:");
        txtCodigoEmprestimo = new JTextField();
        btnRealizarDevolucao = new JButton("Realizar Devolução");

        panel.add(lblCodigoEmprestimo);
        panel.add(txtCodigoEmprestimo);

        add(panel, BorderLayout.CENTER);
        add(btnRealizarDevolucao, BorderLayout.SOUTH);

        daoDevolucao = new DaoDevolucao();

        btnRealizarDevolucao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigoEmprestimoStr = txtCodigoEmprestimo.getText();
                if (!codigoEmprestimoStr.isEmpty()) {
                    int codigoEmprestimo = Integer.parseInt(codigoEmprestimoStr);
                    daoDevolucao.realizarDevolucao(codigoEmprestimo);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o código do empréstimo.");
                }
            }
        });
    }
}