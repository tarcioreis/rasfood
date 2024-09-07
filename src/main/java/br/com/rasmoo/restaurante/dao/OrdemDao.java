package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Ordem;

import javax.persistence.EntityManager;

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

    public void atualizar(Ordem ordem) {
        this.entityManager.merge(ordem);
    }

    public void remover(Ordem ordem) {
        this.entityManager.remove(ordem);
    }
}
