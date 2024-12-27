package cli;

import picocli.CommandLine;
import ru.sfedu.NASA.AppCli;

@CommandLine.Command(name = "user", description = "Команды для управления пользователями")
public class UserCommands implements Runnable {

    @CommandLine.ParentCommand
    private AppCli appCli;

    @CommandLine.Command(name = "create", description = "Создать нового пользователя")
    public void createUser(
            @CommandLine.Option(names = "--username", required = true) String username,
            @CommandLine.Option(names = "--email", required = true) String email,
            @CommandLine.Option(names = "--password", required = true) String password
    ) {
        System.out.println("Создание пользователя с именем: " + username);
        // Реализация создания пользователя через appCli.getDataProvider()
    }

    @CommandLine.Command(name = "list", description = "Список всех пользователей")
    public void listUsers() {
        System.out.println("Список всех пользователей:");
        // Реализация получения списка пользователей через appCli.getDataProvider()
    }

    @Override
    public void run() {
        System.out.println("Используйте `user --help` для просмотра доступных команд.");
    }
}
