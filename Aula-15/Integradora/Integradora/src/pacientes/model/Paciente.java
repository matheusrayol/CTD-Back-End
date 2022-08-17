package pacientes.model;

import java.util.Date;

public class Paciente {

    private String nome;
    private String sobrenome;
    private String RG;
    private Date dataCadastro;

    public Paciente(String nome, String sobrenome, String RG, Date dataCadastro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.RG = RG;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
