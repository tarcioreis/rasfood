package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.entity.OrdensCardapio;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        CargaDeDadosUtil.cadastrarCategoria(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        OrdemDao ordemDao = new OrdemDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);

        Cliente tarcio = new Cliente("12345678900", "Tarcio Souza Reis", "44900000");
        Ordem pedido = new Ordem(tarcio);
        pedido.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultarPorId(1), 2));

        clienteDao.cadastrar(tarcio);
        ordemDao.cadastrar(pedido);
        System.out.println(pedido.getOrdensCardapioList());
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
