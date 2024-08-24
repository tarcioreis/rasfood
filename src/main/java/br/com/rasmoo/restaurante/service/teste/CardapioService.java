package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        cadastrarCardapio(entityManager, cadastrarCategoria(entityManager));
    }

    private static Categoria cadastrarCategoria(EntityManager entityManager) {
        Categoria pratoPrincipal = new Categoria("Prato principal");
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(pratoPrincipal);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return pratoPrincipal;
    }

    private static void cadastrarCardapio(EntityManager entityManager, Categoria categoria) {
        // Transient state
        Cardapio risoto = new Cardapio();
        risoto.setNome("Risoto ao frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e maríscos");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));
        risoto.setCategoria(categoria);

        Cardapio lasanha = new Cardapio();
        lasanha.setNome("Lasanha quatro quejos");
        lasanha.setDescricao("Lasanha quatros quejos com frango");
        lasanha.setDisponivel(true);
        lasanha.setValor(BigDecimal.valueOf(50));
        lasanha.setCategoria(categoria);

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        entityManager.getTransaction().begin();
        // Managed state
        cardapioDao.cadastrar(risoto);
        entityManager.flush();
        cardapioDao.cadastrar(lasanha);
        entityManager.flush();
        //System.out.println("Entidade consultada: " + cardapioDao.consultarPorId(1));
        //cardapioDao.deletar(lasanha);
        //entityManager.flush();
        //System.out.println("Entidade consultada: " + cardapioDao.consultarPorId(2));
        //entityManager.getTransaction().commit();
        cardapioDao.consultaJpql().forEach(entidade -> System.out.println("Entidade consultada: " + entidade));
        // Datached state
        entityManager.close();

    }
}
