package ru.sfedu.NASA;

import cli.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import providers.JdbcDataProvider;
import utils.PostgresUtil;

import java.sql.SQLException;

@Slf4j
@Getter
@CommandLine.Command(
        name = "NASA",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "NASA"
)
public class AppCli implements Runnable {

    private JdbcDataProvider dataProvider;

    public AppCli() {
        try {
            this.dataProvider = new JdbcDataProvider(PostgresUtil.getConnection());
        } catch (SQLException e) {
            log.error("Error initializing database connection: {}", e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new AppCli())
                .addSubcommand("user", new UserCommands())
                .addSubcommand("space-weather", new SpaceWeatherCommands())
                .addSubcommand("space-picture", new SpacePictureCommands())
                .addSubcommand("observation", new ObservationCommands())
                .addSubcommand("education", new EducationalContentCommands())
                .execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("NASA CLI. Используйте --help для получения информации о доступных командах.");
    }
}
