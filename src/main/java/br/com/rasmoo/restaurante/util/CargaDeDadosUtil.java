package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;

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
        entityManager.flush();
        categoriaDao.cadastrar(pratoEntrada);
        entityManager.flush();
        categoriaDao.cadastrar(sobremesa);
        entityManager.flush();
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
}
