package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.vo.ItensPrincipaisVo;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdemDao {

    private EntityManager entityManager;

    public OrdemDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Ordem ordem) {
        this.entityManager.persist(ordem);
    }

    public Ordem consultarPorId(final Integer id) {
        return this.entityManager.find(Ordem.class, id);
    }

    // consulta eager sem alterar a propriedade LAZY do FetchType
    public Ordem joinFetchCliente(final Integer filtro_id) {
        String jpql = "SELECT o FROM Ordem o JOIN FETCH o.cliente WHERE o.id = :id";
        return this.entityManager.createQuery(jpql, Ordem.class).setParameter("id", filtro_id).getSingleResult();
    }

    public List<ItensPrincipaisVo> consultarMaisVendidos() {
        String jpql = "SELECT new br.com.rasmoo.restaurante.vo.ItensPrincipaisVo(c.nome, SUM(oc.quantidade)) " +
                "FROM Ordem o " +
                "JOIN OrdensCardapio oc ON o.id = oc.id " +
                "JOIN oc.cardapio c " +
                "GROUP BY c.nome " +
                "ORDER BY SUM(oc.quantidade) DESC";

        return this.entityManager.createQuery(jpql, ItensPrincipaisVo.class).getResultList();
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
