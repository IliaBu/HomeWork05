import java.util.ArrayList;

public class Editor3D implements UILayer {

    private ProjectFile projectFile;
    private BusinessLogicalLayer businessLogicalLayer;
    private DatabaseAccess databaseAccess;
    private Database database;

    public void initialize() {

        database = new EditorDatabase(projectFile);
        databaseAccess = new EditorDatabaseAccess(database);
        businessLogicalLayer = new EditorBusinessLogicalLayer(databaseAccess);

    }

    @Override
    public void openProject(String fileName) {
        projectFile = new ProjectFile(fileName);
        initialize();
    }

    @Override
    public void showProjectSettings() {

        // Предусловие
        checkProjectFile();

        ConsoleUi.println("**** Project v1 **", Colors.YELLOW);
        ConsoleUi.println("******************", Colors.YELLOW);
        ConsoleUi.printf("fileName: %s\n", projectFile.getFileName(), Colors.YELLOW);
        ConsoleUi.printf("setting1: %d\n", projectFile.getSetting1(), Colors.YELLOW);
        ConsoleUi.printf("setting2: %s\n", projectFile.getSetting2(), Colors.YELLOW);
        ConsoleUi.printf("setting3: %s\n", projectFile.getSetting3(), Colors.YELLOW);
        ConsoleUi.println("******************", Colors.YELLOW);

    }

    private void checkProjectFile() {
        if (projectFile == null) {
            throw new RuntimeException("Файл проекта не определен!");
        }
    }

    @Override
    public void saveProject() {

        // Предусловие
        checkProjectFile();

        database.save();
        ConsoleUi.println("Изменения успешно изменены", Colors.GREEN);

    }

    @Override
    public void printAllModels() {

        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>) businessLogicalLayer.getAllModels();
        ConsoleUi.println("**** Models **", Colors.MAGENTA);
        for (int i = 0; i < models.size(); i++) {
            ConsoleUi.printf("===%d===\n", i, Colors.YELLOW);
            ConsoleUi.println(models.get(i).toString(), Colors.YELLOW);
            for (Texture texture : models.get(i).getTextures()) {
                ConsoleUi.printf("\t%s\n", texture, Colors.YELLOW);
            }
        }

    }

    @Override
    public void printAllTextures() {

        // Предусловие
        checkProjectFile();

        ArrayList<Texture> textures = (ArrayList<Texture>) businessLogicalLayer.getAllTextures();
        ConsoleUi.println("**** Textures **", Colors.MAGENTA);
        for (int i = 0; i < textures.size(); i++) {
            ConsoleUi.printf("===%d===\n", i, Colors.YELLOW);
            ConsoleUi.println(textures.get(i).toString(), Colors.YELLOW);
        }

    }

    @Override
    public void renderAll() {

        // Предусловие
        checkProjectFile();

        ConsoleUi.println("Подождите...", Colors.MAGENTA);
        long startTime = System.currentTimeMillis();
        businessLogicalLayer.renderAllModel();
        long endTime = (System.currentTimeMillis() - startTime);
        ConsoleUi.printf("Рендер завершен за %d миллисекунд.\n", endTime, Colors.YELLOW);

    }

    @Override
    public void renderModel(int i) {

        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>) businessLogicalLayer.getAllModels();
        if (i < 0 || i > models.size() - 1) {
            throw new RuntimeException(Colors.RED + "Индекс модели выходит за границы!" + Colors.RESET);
        }
        ConsoleUi.println("Подождите...", Colors.MAGENTA);
        long startTime = System.currentTimeMillis();
        businessLogicalLayer.renderModel(models.get(i));
        long endTime = (System.currentTimeMillis() - startTime);
        ConsoleUi.printf("Рендер модели %d завершен за %d миллисекунд.\n", i, endTime, Colors.YELLOW);

    }

    @Override
    public void addModel() {
        // Предусловие
        checkProjectFile();

        // Создание новой модели
        Model3D newModel = new Model3D();

        // Добавление модели в бизнес-логику
        businessLogicalLayer.addModel(newModel);

        // Добавление текстуры к новой модели
        addTextureToModel(newModel.getId());

        ConsoleUi.println("Новая модель успешно добавлена", Colors.GREEN);
    }

    @Override
    public void addTextureToModel(int modelId) {
        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>) businessLogicalLayer.getAllModels();
        if (modelId < 1 || modelId > (1 + models.size() - 1)) {
            throw new RuntimeException(Colors.RED + "Индекс модели выходит за границы!" + Colors.RESET);
        }

        Texture newTexture = new Texture();
        models.get(modelId - 1).getTextures().add(newTexture);
        ConsoleUi.println("Новая текстура успешно добавлена к модели", Colors.GREEN);
    }

    @Override
    public void removeModel(int i) {
        ArrayList<Model3D> models = (ArrayList<Model3D>) businessLogicalLayer.getAllModels();
        if (i < 0 || i > models.size() - 1)
            throw new RuntimeException(Colors.RED + "Номер модели указан некорректно!" + Colors.RESET);
        businessLogicalLayer.removeModel(models.get(i));
        ConsoleUi.printf("Модель с id: %s успешно удалена!\n", i, Colors.GREEN);
    }

}
