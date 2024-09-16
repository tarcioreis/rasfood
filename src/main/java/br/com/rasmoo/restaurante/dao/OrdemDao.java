package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Ordem;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdemDao {

    private EntityManager entityManager;

    public OrdemDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Ordem ordem) {
        this.entityManager.persist(ordem);
    }

    public Ordem consultarPorId(Integer id) {
        return this.entityManager.find(Ordem.class, id);
    }

    public List<Object[]> consultarMaisVendidos() {
        String jpql = "SELECT c.nome, SUM(oc.quantidade) FROM Ordem o " +
                "JOIN OrdensCardapio oc ON o.id = oc.id " +
                "JOIN oc.cardapio c " +
                "GROUP BY c.nome " +
                "ORDER BY SUM(oc.quantidade) DESC";

        return this.entityManager.createQuery(jpql, Object[].class).getResultList();
    }

    public List<Object[]> consultaMaisVendidosTeste() {
        String jpql = "SELECT c.nome, SUM(oc.quantidade) FROM Cardapio c " +
                "JOIN OrdensCardapio oc ON c.id = oc.id " +
                "GROUP BY c.nome " +
                "ORDER BY SUM(oc.quantidade) DESC";

        return this.entityManager.createQuery(jpql, Object[].class).getResultList();
    }

    public void atualizar(Ordem ordem) {
        this.entityManager.merge(ordem);
    }

    public void remover(Ordem ordem) {
        this.entityManager.remove(ordem);
    }
}
