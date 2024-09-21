import java.util.Collection;

public interface BusinessLogicalLayer {

    Collection<Model3D> getAllModels();

    Collection<Texture> getAllTextures();

    void renderModel(Model3D model);

    void renderAllModel();

    void addModel(Model3D model);

    void removeModel(Model3D model3D);

}
