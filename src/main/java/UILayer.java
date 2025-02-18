public interface UILayer {

    void openProject(String fileName);

    void showProjectSettings();

    void saveProject();

    void printAllModels();

    void printAllTextures();

    void renderAll();

    void renderModel(int i);

    void addModel();

    void addTextureToModel(int modelId);

    void removeModel(int i);
}