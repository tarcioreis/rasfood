package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        CargaDeDadosUtil.cadastrarCategoria(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        //System.out.println("Entidades com o mesmo valor: " +
                //cardapioDao.consultarPorPreco(BigDecimal.valueOf(7.50)));

        //System.out.println("Entidade consultada: " + cardapioDao.consultarPorNome("Lasanha quatro quejos"));
        entityManager.close();
    }


}
