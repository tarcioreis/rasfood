package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Endereco;

import javax.persistence.EntityManager;

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
}
