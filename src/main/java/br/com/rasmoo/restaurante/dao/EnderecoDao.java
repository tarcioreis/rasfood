package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;
import br.com.rasmoo.restaurante.vo.EnderecoVo;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoDao {

    private EntityManager entityManager;

    public EnderecoDao() {}

    public EnderecoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Endereco endereco) {
        this.entityManager.persist(endereco);
    }

    public Endereco consultarPorId(final Integer id) {
        String jpql = "SELECT e FROM Endereco e WHERE e.id = :id";
        return this.entityManager.createQuery(jpql, Endereco.class).setParameter("id", id).getSingleResult();
    }

    public List<ClienteVo> consultaDinamica(final String rua, final String cidade, final String estado) {

        String jpql = "SELECT new br.com.rasmoo.restaurante.vo.ClienteVo(e.cliente.cpf, e.cliente.nome) " +
                "FROM Endereco e WHERE UPPER(e.rua) = UPPER(:rua) AND " +
                "UPPER(e.cidade) = UPPER(:cidade) AND UPPER(e.estado) = UPPER(:estado)";

        return this.entityManager.createQuery(jpql, ClienteVo.class).setParameter("rua", rua)
                .setParameter("cidade", cidade).setParameter("estado", estado)
                .getResultList();
    }
}
