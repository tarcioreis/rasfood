package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cliente;

import javax.persistence.EntityManager;

public class ClienteDao {

    private EntityManager entityManager;

    public ClienteDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    public Cliente consultarPorCpfId(String cpf) {
        return this.entityManager.find(Cliente.class, cpf);
    }

    public void atualizar(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    public void remover(Cliente cliente) {
        this.entityManager.remove(cliente);
    }
}
