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
    private Cardapio cardapio_id;

    @ManyToOne
    private Ordem ordensCardapio_id;

    private BigDecimal valor;

    private Integer quantidade;

    public OrdensCardapio() {}

    public OrdensCardapio(Integer id, Cardapio cardapio_id, Ordem ordensCardapio_id, BigDecimal valor) {
        this.id = id;
        this.cardapio_id = cardapio_id;
        this.ordensCardapio_id = ordensCardapio_id;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cardapio getCardapio_id() {
        return cardapio_id;
    }

    public void setCardapio_id(Cardapio cardapio_id) {
        this.cardapio_id = cardapio_id;
    }

    public Ordem getOrdensCardapio_id() {
        return ordensCardapio_id;
    }

    public void setOrdensCardapio_id(Ordem ordensCardapio_id) {
        this.ordensCardapio_id = ordensCardapio_id;
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
                ", cardapio_id=" + cardapio_id +
                ", ordensCardapio=" + ordensCardapio_id +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}
