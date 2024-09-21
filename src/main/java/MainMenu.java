import java.util.ArrayList;
import java.util.Arrays;

public class MainMenu extends Menu {

    // Запуск приложения
    Editor3D editor3D = new Editor3D();

    public MainMenu() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(this::showTask1, "Открыть проект"));
        menuItems.add(new MenuItem(this::showTask2, "Сохранить проект"));
        menuItems.add(new MenuItem(this::showTask3, "Отобразить параметры проекта"));
        menuItems.add(new MenuItem(this::showTask4, "Отобразить все модели проекта"));
        menuItems.add(new MenuItem(this::showTask5, "Отобразить все текстуры проекта"));
        menuItems.add(new MenuItem(this::showTask6, "Выполнить рендер всех моделей"));
        menuItems.add(new MenuItem(this::showTask7, "Выполнить рендер модели"));
        menuItems.add(new MenuItem(this::showTask8, "Добавить новую модель"));
        menuItems.add(new MenuItem(this::showTask9, "Добавить текстуру к модели"));
        menuItems.add(new MenuItem(this::showTask10, "Удалить модель"));
        menuItems.add(new MenuItem(this::quit, "ЗАВЕРШЕНИЕ РАБОТЫ ПРИЛОЖЕНИЯ"));
    }

    private void quit() {
        ConsoleUi.close();
    }

    private void showTask1() {
        ConsoleUi.print("Укажите наименование файла проекта: ", Colors.BLUE);
        String fileName = ConsoleUi.scanner.nextLine();
        editor3D.openProject(fileName);
        ConsoleUi.println("Проект успешно открыт.", Colors.GREEN);
    }

    private void showTask2() {
        editor3D.saveProject();
    }

    private void showTask3() {
        editor3D.showProjectSettings();
    }

    private void showTask4() {
        editor3D.printAllModels();
    }

    private void showTask5() {
        editor3D.printAllTextures();
    }

    private void showTask6() {
        editor3D.renderAll();
    }

    private void showTask7() {
        ConsoleUi.print("Укажите номер модели: ", Colors.BLUE);
        if (ConsoleUi.scanner.hasNextInt()) {
            int modelNo = ConsoleUi.scanner.nextInt();
            ConsoleUi.scanner.nextLine();
            editor3D.renderModel(modelNo);
        } else {
            ConsoleUi.println("Номер модели указан некорректно!", Colors.RED);
        }
    }

    private void showTask8() {
        editor3D.addModel();
    }

    private void showTask9() {
        ConsoleUi.print("Укажите номер модели: ", Colors.BLUE);
        if (ConsoleUi.scanner.hasNextInt()) {
            int modelId = ConsoleUi.scanner.nextInt();
            ConsoleUi.scanner.nextLine();
            editor3D.addTextureToModel(modelId);
        } else {
            ConsoleUi.println("Номер модели указан некорректно!", Colors.RED);
        }
    }

    private void showTask10() {
        ConsoleUi.print("Укажите номер модели: ", Colors.BLUE);
        if (ConsoleUi.scanner.hasNextInt()) {
            int modelId = ConsoleUi.scanner.nextInt();
            ConsoleUi.scanner.nextLine();
            editor3D.removeModel(modelId);
        } else {
            ConsoleUi.println("Номер модели указан некорректно!", Colors.RED);
        }
    }
}
