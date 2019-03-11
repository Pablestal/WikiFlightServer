package es.udc.lbd.asi.restexample.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.domain.Tag;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.PostDAO;
import es.udc.lbd.asi.restexample.model.repository.TagDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.UserService;

@Configuration
public class DatabaseLoader {
    private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private TagDAO tagDAO;

    @Autowired
    private DatabaseLoader databaseLoader;

    /*
     * Para hacer que la carga de datos sea transacional, hay que cargar el propio
     * objeto como un bean y lanzar el método una vez cargado, ya que en el
     * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
     * transaciones.
     */
    @PostConstruct
    public void init() {
        try {
            databaseLoader.loadData();
        } catch (UserLoginExistsException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void loadData() throws UserLoginExistsException {
        userService.registerUser("pepe", "pepe", true);
        userService.registerUser("maria", "maria", true);
        userService.registerUser("laura", "laura");
        userService.registerUser("pedro", "pedro");

        Tag news = new Tag("news");
        Tag podcast = new Tag("podcast");
        Tag tech = new Tag("tech");

        tagDAO.save(news);
        tagDAO.save(podcast);
        tagDAO.save(tech);

        Post post = new Post("Título 1", "Texto del primer post", userDAO.findByLogin("pepe"));
        post.getTags().add(news);
        post.getTags().add(podcast);
        postDAO.save(post);
        post = new Post("Título 2", "Texto del segundo post", userDAO.findByLogin("maria"));
        post.getTags().add(news);
        post.getTags().add(tech);
        postDAO.save(post);
        post = new Post("Título 3", "Texto del tercero post", userDAO.findByLogin("maria"));
        postDAO.save(post);
        post = new Post("Título 4", "Texto del cuarto post", userDAO.findByLogin("maria"));
        postDAO.save(post);
        post = new Post("Título 5", "Texto del quinto post", userDAO.findByLogin("pepe"));
        postDAO.save(post);
        post = new Post("Título 6", "Texto del sexto post", userDAO.findByLogin("pepe"));
        postDAO.save(post);
    }
}
