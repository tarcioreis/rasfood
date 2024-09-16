package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        CargaDeDadosUtil.cadastrarCategoria(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CargaDeDadosUtil.cadastrarCliente(entityManager);
        CargaDeDadosUtil.cadastrarPedido(entityManager);

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        OrdemDao ordemDao = new OrdemDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);

        ordemDao.consultarMaisVendidos()
                .forEach(item -> System.out.println("Nome: " + item[0] + "\t" + "- Quantidade " + item[1]));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
