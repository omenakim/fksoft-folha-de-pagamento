package br.com.fksoft.controller.view.model;

import java.io.Serializable;

public class SumarioDeFuncionario implements Serializable {

    private static final long serialVersionUID = 4149683347056980579L;

    private Long id;
    private String nomeCompleto;

    public SumarioDeFuncionario() {
    }

    public SumarioDeFuncionario(Long id, String nomeCompleto) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SumarioDeFuncionario that = (SumarioDeFuncionario) o;

        if (!getId().equals(that.getId())) return false;
        return getNomeCompleto().equals(that.getNomeCompleto());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getNomeCompleto().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SumarioDeFuncionario{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                '}';
    }
}
