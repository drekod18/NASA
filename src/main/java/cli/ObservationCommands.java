package cli;

import picocli.CommandLine;
import ru.sfedu.NASA.AppCli;

@CommandLine.Command(name = "observation", description = "Команды для управления условиями наблюдений")
public class ObservationCommands implements Runnable {

    @CommandLine.ParentCommand
    private AppCli appCli;

    @CommandLine.Command(name = "add", description = "Добавить условия наблюдения")
    public void addObservation(
            @CommandLine.Option(names = "--cloud-coverage", required = true) int cloudCoverage,
            @CommandLine.Option(names = "--seeing", required = true) int seeingQuality,
            @CommandLine.Option(names = "--location", required = true) String location,
            @CommandLine.Option(names = "--temperature", required = true) float temperature,
            @CommandLine.Option(names = "--humidity", required = true) float humidity
    ) {
        System.out.println("Добавление условий наблюдения...");
        // Реализация добавления условий через appCli.getDataProvider()
    }

    @CommandLine.Command(name = "list", description = "Список всех условий наблюдений")
    public void listObservations() {
        System.out.println("Получение условий наблюдений...");
        // Реализация получения списка условий через appCli.getDataProvider()
    }

    @Override
    public void run() {
        System.out.println("Используйте `observation --help` для просмотра доступных команд.");
    }
}
