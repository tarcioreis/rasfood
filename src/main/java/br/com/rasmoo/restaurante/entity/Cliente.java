package br.com.rasmoo.restaurante.entity;

import br.com.rasmoo.restaurante.embeddable.ClienteId;
import br.com.rasmoo.restaurante.embeddable.Contato;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @EmbeddedId
    private ClienteId clienteId;

    private String nome;

    @Embedded
    private Contato contato;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecoList = new ArrayList<>();

    public Cliente() {}

    public Cliente(String email, String cpf, String nome) {
        this.clienteId = new ClienteId(email, cpf);
        this.nome = nome;
    }

    public void addEnderecosCliente(Endereco endereco) {
        endereco.setCliente(this);
        this.enderecoList.add(endereco);
    }

    public String getCpf() {
        return this.clienteId.getCpf();
    }

    public void setCpf(String cpf) {
        this.clienteId.setCpf(cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", nome='" + nome + '\'' +
                ", contato=" + contato +
                ", enderecoList=" + enderecoList +
                '}';
    }
}
