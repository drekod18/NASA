package ru.sfedu.NASA;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.PostgresUtil;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class PostgresUtilTest {

    private static final String CONFIG_FILE_PATH = "./src/main/resources/environment.properties"; // Путь к конфиг файлу для тестов

    @BeforeAll
    public static void setUp() {
        // Устанавливаем путь к конфигурационному файлу перед всеми тестами
        System.setProperty("config.file", CONFIG_FILE_PATH);
    }

    @AfterAll
    public static void tearDown() {
        // Убираем свойство, если оно было установлено
        System.clearProperty("config.file");
    }

    @Test
    public void testGetConnection_Success() {
        try (Connection connection = PostgresUtil.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("SQLException occurred while getting the connection: " + e.getMessage());
        }
    }


    @Test
    public void testCloseConnection_ConnectionIsClosed() {
        try (Connection connection = PostgresUtil.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");

            // Закрываем соединение явно, проверяя логирование
            PostgresUtil.closeConnection(connection);

            assertTrue(connection.isClosed(), "Connection should be closed after closeConnection is called");
        } catch (SQLException e) {
            fail("SQLException occurred while closing the connection: " + e.getMessage());
        }
    }

    @Test
    public void testCloseConnection_ConnectionIsAlreadyClosed() {
        try (Connection connection = PostgresUtil.getConnection()) {
            connection.close();
            assertTrue(connection.isClosed(), "Connection should be closed after close()");

            // Попытка закрыть уже закрытое соединение
            PostgresUtil.closeConnection(connection);
        } catch (SQLException e) {
            fail("SQLException occurred while closing the connection: " + e.getMessage());
        }
    }
}