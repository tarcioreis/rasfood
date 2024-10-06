package br.com.rasmoo.restaurante.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class Contato {

    private String ddd;
    private String celular;

    public Contato() {
    }

    public Contato(String ddd, String celular) {
        this.ddd = ddd;
        this.celular = celular;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "ddd='" + ddd + '\'' +
                ", celular='" + celular + '\'' +
                '}';
    }
}
