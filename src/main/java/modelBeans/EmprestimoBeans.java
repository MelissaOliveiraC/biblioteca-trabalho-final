package modelBeans;

import java.sql.Date;

/**
 *
 * @author melissaoliveira
 */

public class EmprestimoBeans {
    private String nomeAluno;
    private String nomeLivro;
    private String status;
    private int emprestimoCodigo;
    private Date dat1;
    private Date dat2;

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmprestimoCodigo() {
        return emprestimoCodigo;
    }

    public void setEmprestimoCodigo(int emprestimoCodigo) {
        this.emprestimoCodigo = emprestimoCodigo;
    }

    public Date getDat1() {
        return dat1;
    }

    public void setDat1(Date dat1) {
        this.dat1 = dat1;
    }

    public Date getDat2() {
        return dat2;
    }

    public void setDat2(Date dat2) {
        this.dat2 = dat2;
    }
    
    public Date getData1(String emprestimoData) {
        throw new UnsupportedOperationException("Está operação não é possível.");
    }
}
