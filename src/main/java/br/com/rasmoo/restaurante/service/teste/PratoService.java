package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.PratoDao;
import br.com.rasmoo.restaurante.entity.Prato;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PratoService {

    public static void main(String[] args) {

        // Transient state
        Prato risoto = new Prato();
        risoto.setNome("Risoto ao frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e maríscos");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));

        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        PratoDao pratoDao = new PratoDao(entityManager);
        entityManager.getTransaction().begin();
        // Managed state
        pratoDao.cadastrar(risoto);
        entityManager.getTransaction().commit();
        // Datached state
        entityManager.close();
    }
}
