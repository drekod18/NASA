package cli;

import picocli.CommandLine;
import ru.sfedu.NASA.AppCli;
@CommandLine.Command(name = "education", description = "Команды для управления образовательным контентом")
public class EducationalContentCommands implements Runnable {

    @CommandLine.ParentCommand
    private AppCli appCli;

    @CommandLine.Command(name = "add", description = "Добавить образовательный контент")
    public void addEducationalContent(
            @CommandLine.Option(names = "--title", required = true) String title,
            @CommandLine.Option(names = "--type", required = true) String type,
            @CommandLine.Option(names = "--difficulty", required = true) String difficulty,
            @CommandLine.Option(names = "--estimated-time", required = true) int estimatedTime
    ) {
        System.out.println("Добавление образовательного контента...");
        // Реализация добавления контента через appCli.getDataProvider()
    }

    @CommandLine.Command(name = "list", description = "Список всех образовательных материалов")
    public void listEducationalContent() {
        System.out.println("Получение образовательного контента...");
        // Реализация получения списка контента через appCli.getDataProvider()
    }

    @Override
    public void run() {
        System.out.println("Используйте `education --help` для просмотра доступных команд.");
    }
}
