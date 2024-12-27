package cli;

import picocli.CommandLine;
import ru.sfedu.NASA.AppCli;

@CommandLine.Command(name = "space-picture", description = "Команды для управления изображениями космоса")
public class SpacePictureCommands implements Runnable {

    @CommandLine.ParentCommand
    private AppCli appCli;

    @CommandLine.Command(name = "add", description = "Добавить изображение космоса")
    public void addSpacePicture(
            @CommandLine.Option(names = "--url", required = true) String url,
            @CommandLine.Option(names = "--description", required = true) String description,
            @CommandLine.Option(names = "--location", required = true) String location
    ) {
        System.out.println("Добавление изображения...");
        // Реализация добавления изображения через appCli.getDataProvider()
    }

    @CommandLine.Command(name = "list", description = "Список всех изображений космоса")
    public void listSpacePictures() {
        System.out.println("Получение изображений...");
        // Реализация получения списка изображений через appCli.getDataProvider()
    }

    @Override
    public void run() {
        System.out.println("Используйте `space-picture --help` для просмотра доступных команд.");
    }
}
