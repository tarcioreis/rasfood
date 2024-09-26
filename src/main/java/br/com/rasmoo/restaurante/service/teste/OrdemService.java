package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Ordem;
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


        OrdemDao ordemDao = new OrdemDao(entityManager);

        Ordem ordem = ordemDao.joinFetchCliente(1);
        ClienteDao clienteDao = new ClienteDao(entityManager);
        //System.out.println(ordemDao.consultarMaisVendidos());
        //System.out.println(clienteDao.consultarTodos());
        EnderecoDao enderecoDao = new EnderecoDao(entityManager);
        System.out.println(enderecoDao.consultaDinamica("Rio grande", "Irecê", "Bahia"));
        entityManager.getTransaction().commit();
        entityManager.close();
        //System.out.println(ordem.getCliente().getNome());
    }
}
