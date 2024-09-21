import java.util.Collection;
import java.util.Random;

public class EditorBusinessLogicalLayer implements BusinessLogicalLayer {

    private final DatabaseAccess databaseAccess;

    public EditorBusinessLogicalLayer(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    @Override
    public Collection<Model3D> getAllModels() {
        return databaseAccess.getAllModels();
    }

    @Override
    public Collection<Texture> getAllTextures() {
        return databaseAccess.getAllTextures();
    }

    @Override
    public void renderModel(Model3D model) {
        processRender(model);
    }

    @Override
    public void renderAllModel() {
        for (Model3D model : getAllModels()) {
            processRender(model);
        }
    }

    @Override
    public void removeModel(Model3D model3D) {
        databaseAccess.removeEntity(model3D);

    }

    private final Random random = new Random();

    private void processRender(Model3D model) {
        try {
            Thread.sleep(2500 - random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addModel(Model3D model) {
        databaseAccess.addEntity(model);
    }

    public void addTextureToModel(Model3D model, Texture texture) {
        model.getTextures().add(texture);
        databaseAccess.addEntity(texture);
    }

}
