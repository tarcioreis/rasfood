package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.embeddable.ClienteId;
import br.com.rasmoo.restaurante.entity.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CargaDeDadosUtil {

    public static void cadastrarCategoria(EntityManager entityManager) {

        // Transient state
        Categoria pratoPrincipal = new Categoria("Prato principal");
        Categoria pratoEntrada = new Categoria("Prato de entrada");
        Categoria sobremesa = new Categoria("Sobremesa");
        Categoria bebidas = new Categoria("Bebidas");

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        // Managed state
        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(pratoPrincipal);
        categoriaDao.cadastrar(pratoEntrada);
        categoriaDao.cadastrar(sobremesa);
        categoriaDao.cadastrar(bebidas);
        entityManager.flush();

        // Datached state
        entityManager.clear();

    }

    public static void cadastrarProdutosCardapio(EntityManager entityManager) {

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        List<Categoria> categorias = categoriaDao.consultarTodas();

        // Transient state
        Cardapio risoto = new Cardapio("Risoto ao frutos do mar",
                "Risoto acompanhado de lula, polvo e maríscos",
                true, BigDecimal.valueOf(88.50), categorias.get(1));
        
        Cardapio lasanha = new Cardapio("Lasanha quatro quejos",
                "Lasanha quatros quejos com frango", true, BigDecimal.valueOf(50),
                    categorias.get(1));

        Cardapio strogonoff = new Cardapio("Strogonoff de carne", "Strogonoff de carne e batata palha",
                    true, BigDecimal.valueOf(26), categorias.get(1));

        Cardapio mousse = new Cardapio("Mousse de chocolate", "Mousse de chocolate ao leite",
                true, BigDecimal.valueOf(10), categorias.get(2));

        Cardapio amstel = new Cardapio("Cerveja Amstel", "Cerveja Amstel lata 350ml", true,
                BigDecimal.valueOf(7.50), categorias.get(3));

        Cardapio cocaCola = new Cardapio("Coca-Cola", "Coca-Cola zero 1,5l", true,
                BigDecimal.valueOf(7.50), categorias.get(3));

        // Managed state
        cardapioDao.cadastrar(risoto);
        cardapioDao.cadastrar(lasanha);
        cardapioDao.cadastrar(strogonoff);
        cardapioDao.cadastrar(mousse);
        cardapioDao.cadastrar(amstel);
        cardapioDao.cadastrar(cocaCola);

        entityManager.flush();
        //cardapioDao.consultaJpql().forEach(entidade -> System.out.println("Entidade consultada: " + entidade));

        entityManager.clear();
    }

    public static void cadastrarCliente(EntityManager entityManager) {

        Cliente tarcio = new Cliente("tarcioreis@gmail.com", "12345678900", "Tarcio Souza Reis");
        tarcio.addEnderecosCliente(new Endereco("Rio grande", "Igreja presbiteriana",
                "000000", "Irecê", "Bahia"));

        Cliente pedro = new Cliente("pedro@gmail.com","321456987", "Pedro da Silva");
        pedro.addEnderecosCliente(new Endereco("Rua da delegacia", "complemento",
                "1234556", "Campinas","São Paulo"));

        Cliente vanessa = new Cliente("vanessa@gmail.com","098765364", "Vanessa Lima Santos");
        vanessa.addEnderecosCliente(new Endereco("Rua das árvores", "Praça da cidade",
                "987455", "Belo Horizonte","Minas Gerais"));

        Cliente mariana = new Cliente("mariana@gmail.com", "789139740", "Mariana Lima Santos");
        mariana.addEnderecosCliente(new Endereco("Rua das árvores", "Praça da cidade",
                "987455", "Timoteo", "Minas Gerais"));

        ClienteDao clienteDao = new ClienteDao(entityManager);
        clienteDao.cadastrar(tarcio);
        clienteDao.cadastrar(pedro);
        clienteDao.cadastrar(vanessa);
        clienteDao.cadastrar(mariana);
        entityManager.flush();
        entityManager.clear();
    }

    public static void cadastrarPedido(EntityManager entityManager) {

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);
        OrdemDao ordemDao = new OrdemDao(entityManager);
        List<Cliente> clientes = clienteDao.consultarTodos();

        Ordem pedido = new Ordem(clientes.get(0));
        pedido.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultarPorId(2), 3));

        Ordem pedido2 = new Ordem(clientes.get(1));
        pedido2.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultarPorId(3), 1));

        Ordem pedido3 = new Ordem(clientes.get(2));
        pedido3.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultarPorId(5), 2));

        Ordem pedido4 = new Ordem(clientes.get(3));
        pedido4.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultarPorId(4), 4));

        ordemDao.cadastrar(pedido);
        ordemDao.cadastrar(pedido2);
        ordemDao.cadastrar(pedido3);
        ordemDao.cadastrar(pedido4);
        entityManager.flush();
        entityManager.clear();
    }
}
