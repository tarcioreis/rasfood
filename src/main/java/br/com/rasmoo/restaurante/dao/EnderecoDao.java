package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;
import br.com.rasmoo.restaurante.vo.EnderecoVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

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

        String jpql = "SELECT new br.com.rasmoo.restaurante.vo.ClienteVo(e.cliente.clienteId.cpf, e.cliente.nome) " +
                "FROM Endereco e WHERE 1=1";

        if(Objects.nonNull(rua)) {
            jpql = jpql.concat(" AND UPPER(e.rua) = UPPER(:rua)");
        }

        if(Objects.nonNull(cidade)) {
            jpql = jpql.concat(" AND UPPER(e.cidade) = UPPER(:cidade)");
        }

        if(Objects.nonNull(estado)) {
            jpql = jpql.concat(" AND UPPER(e.estado) = UPPER(:estado)");
        }

        TypedQuery typedQuery = this.entityManager.createQuery(jpql, ClienteVo.class);

        if(Objects.nonNull(rua)) {
            typedQuery = typedQuery.setParameter("rua", rua);
        }

        if(Objects.nonNull(cidade)) {
            typedQuery = typedQuery.setParameter("cidade", cidade);
        }

        if(Objects.nonNull(estado)) {
            typedQuery = typedQuery.setParameter("estado", estado);
        }

        return typedQuery.getResultList();
    }

    public List<ClienteVo> consultaDinamicaUsandoCriteria(String rua, String cidade, String estado) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ClienteVo> criteriaQuery = builder.createQuery(ClienteVo.class);
        Root<Endereco> root = criteriaQuery.from(Endereco.class);
        criteriaQuery.multiselect(root.get("cliente").get("clienteId").get("cpf"), root.get("cliente").get("nome"));
        Predicate predicate = builder.and();

        if(Objects.nonNull(rua)) {
            predicate = builder.and(predicate, builder.equal(root.get("rua"), rua));
        }

        if(Objects.nonNull(cidade)) {
            predicate = builder.and(builder.equal(root.get("cidade"), cidade));
        }

        if(Objects.nonNull(estado)) {
            predicate = builder.and(builder.equal(root.get("estado"), estado));
        }

        criteriaQuery.where(predicate);

        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }
}
