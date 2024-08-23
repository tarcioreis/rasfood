package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {

    public static void main(String[] args) {

        // Transient state
        Cardapio risoto = new Cardapio();
        risoto.setNome("Risoto ao frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e maríscos");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));

        Cardapio lasanha = new Cardapio();
        lasanha.setNome("Lasanha quatro quejos");
        lasanha.setDescricao("Lasanha quatros quejos com frango");
        lasanha.setDisponivel(true);
        lasanha.setValor(BigDecimal.valueOf(50));

        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        CardapioDao cardapioDao = new CardapioDao(entityManager);
        entityManager.getTransaction().begin();
        // Managed state
        cardapioDao.cadastrar(risoto);
        entityManager.flush();
        cardapioDao.cadastrar(lasanha);
        entityManager.flush();
        System.out.println("Entidade consultada: " + cardapioDao.consultar(2));
        cardapioDao.deletar(lasanha);
        entityManager.flush();
        //System.out.println("Entidade consultada: " + pratoDao.consultar(2));
        //entityManager.getTransaction().commit();
        // Datached state
        //entityManager.close();

        entityManager.clear();
        risoto.setValor(BigDecimal.valueOf(75.90));
        cardapioDao.atualizar(risoto);
        System.out.println("Entidade atualizada: " + cardapioDao.consultar(1));
        System.out.println("Entidade deletada: " + cardapioDao.consultar(2));
    }
}
