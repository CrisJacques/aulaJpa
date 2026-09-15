package aplicacao;

import dominio.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

    public static void main(String[] args){
        Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com"); // Colocando id = null porque o próprio banco de dados vai atribuir o id no momento da criação
        Pessoa p2 = new Pessoa(null, "Joaquim Torres", "joaquim@gmail.com");
        Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa"); // Esse foi o nome configurado no resources/META-INF/persistence.xml
        EntityManager em = emf.createEntityManager(); // Aqui vou ter implementada a conexão com o banco de dados e o contexto de persistência

        // Persistindo os objetos no banco de dados (para isso, o JPA precisa de uma transação, por isso as linhas antes e depois da chamada ao em.persist())
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.getTransaction().commit();
        // O JPA precisa de uma transação sempre que a operação a ser feita não for uma simples consulta

        // Buscando no banco de dados a pessoa de id = 2
        Pessoa p = em.find(Pessoa.class, 2);
        System.out.println(p);

        System.out.println("Pronto!");
        em.close(); // Fechando o entity manager
        emf.close(); // Fechando o entity manager factory
    }

    // =========================== IMPORTANTE! Sobre remoção de objetos do banco de dados ===========================
    // O JPA só permite a remoção de entidades monitoradas (ou seja, entidades com as quais ele interagiu dentro do programa, seja porque recém
    //foram criadas, ou foram consultadas em algum momento).
    // Por exemplo, se eu tentar executar em.remove(p), sendo que o objeto p não foi consultado nem inserido dentro da execução do programa, vai dar erro
    // Para a remoção dar certo, posso por exemplo consultar a entidade antes:
    //
    // Pessoa p = em.find(Pessoa.class, 2);
    // em.getTransaction().begin();
    // em.remove(p);
    // em.getTransaction().commit();
    // ==============================================================================================================

    // Obs.: em resources/META-INF/persistence.xml, foi configurado <property name="hibernate.hbm2ddl.auto" value="create" />
    // Dessa forma, a cada vez que a aplicação for executada, o banco é recriado novamente. Para evitar que isso aconteça, basta trocar
    //"create" por "update"

}
