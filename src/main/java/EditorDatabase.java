import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class  EditorDatabase implements Database {

    private final Random random = new Random();
    private final ProjectFile projectFile;
    private Collection<Entity> entities;

    public EditorDatabase(ProjectFile projectFile) {
        this.projectFile = projectFile;
        load();
    }

    @Override
    public void load() {
        // TODO: Загрузка всех сущностей проекта (модели, текстуры и т.д.)
    }

    @Override
    public void save() {
        // TODO: Сохранение текущего состояния всех сущностей проекта
    }

    // @Override
    public Collection<Entity> getAll() {
        if (entities == null) {
            entities = new ArrayList<>();
            int entCount = random.nextInt(5, 11);
            int i = 0;
            while (i < entCount) {
                generateModelsAndTextures();
                i++;
            }
        }
        return entities;
    }

    private void generateModelsAndTextures() {
        Model3D model3d = new Model3D();
        int txCount = random.nextInt(3);
        int i = 0;
        while (i < txCount) {
            Texture texture = new Texture();
            model3d.getTextures().add(texture);
            entities.add(texture);
            i++;
        }
        entities.add(model3d);
    }

}

