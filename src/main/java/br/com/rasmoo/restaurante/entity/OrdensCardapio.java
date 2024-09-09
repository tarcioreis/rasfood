package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/* Tabela de junção */
@Entity
@Table(name = "ordens_cardapio")
public class OrdensCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Cardapio cardapio;

    @ManyToOne
    private Ordem ordem;

    private BigDecimal valor;

    private Integer quantidade;

    public OrdensCardapio() {}

    public OrdensCardapio(Cardapio cardapio, Integer quantidade) {
        this.cardapio = cardapio;
        this.quantidade = quantidade;
        this.valor = cardapio.getValor();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "OrdensCardapio{" +
                "id=" + id +
                ", cardapio_id=" + cardapio +
                ", ordensCardapio=" + ordem +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}
