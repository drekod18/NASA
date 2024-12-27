package cli;

import picocli.CommandLine;
import ru.sfedu.NASA.AppCli;

@CommandLine.Command(name = "space-weather", description = "Команды для управления данными о космической погоде")
public class SpaceWeatherCommands implements Runnable {

    @CommandLine.ParentCommand
    private AppCli appCli;

    @CommandLine.Command(name = "add", description = "Добавить данные о космической погоде")
    public void addSpaceWeatherData(
            @CommandLine.Option(names = "--solar-activity", required = true) int solarActivity,
            @CommandLine.Option(names = "--magnetic-field", required = true) int magneticFieldStrength,
            @CommandLine.Option(names = "--location", required = true) String location,
            @CommandLine.Option(names = "--cosmic-radiation", required = true) float cosmicRadiation,
            @CommandLine.Option(names = "--wind-speed", required = true) int windSpeed
    ) {
        System.out.println("Добавление данных о космической погоде...");
        // Реализация добавления данных через appCli.getDataProvider()
    }

    @CommandLine.Command(name = "list", description = "Список всех записей о космической погоде")
    public void listSpaceWeatherData() {
        System.out.println("Получение данных о космической погоде...");
        // Реализация получения списка данных через appCli.getDataProvider()
    }

    @Override
    public void run() {
        System.out.println("Используйте `space-weather --help` для просмотра доступных команд.");
    }
}