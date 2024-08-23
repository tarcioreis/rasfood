package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Categoria categoria) {
        this.entityManager.persist(categoria);
    }

    public Categoria consultar(Integer id) {
        return this.entityManager.find(Categoria.class, id);
    }

    public void atualizar(Categoria categoria) {
        this.entityManager.merge(categoria);
    }

    public void deletar(Categoria categoria) {
        this.entityManager.remove(categoria);
    }
}
