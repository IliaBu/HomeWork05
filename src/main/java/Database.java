import java.util.Collection;

interface Database {

    void load();

    void save();

    Collection<Entity> getAll();

}
