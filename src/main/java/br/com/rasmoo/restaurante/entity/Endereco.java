package br.com.rasmoo.restaurante.entity;

import javax.naming.ldap.ExtendedRequest;
import javax.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rua;
    private String complemento;
    private String cep;
    private String estado;

    public Endereco() {

    }

    public Endereco(String rua, String complemento, String cep, String estado) {
        this.rua = rua;
        this.complemento = complemento;
        this.cep = cep;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + rua + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
