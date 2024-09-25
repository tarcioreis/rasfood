package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Cliente> consultarTodos() {
        String jpql = "SELECT c FROM Cliente c";
        return this.entityManager.createQuery(jpql, Cliente.class).getResultList();
    }

    public List<Cliente> consultarPorNome(String nome) {
        String jpql = "SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(:nome)";
        return this.entityManager.createQuery(jpql, Cliente.class).setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public void atualizar(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    public void remover(Cliente cliente) {
        this.entityManager.remove(cliente);
    }
}
