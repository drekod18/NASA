package ru.sfedu.NASA;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import providers.MongoDataProvider;
import ru.sfedu.NASA.HistoryContentTest;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.MongoDBUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MongoDataProviderTest {

    private static final MongoDBUtil MongoDBUtil = new MongoDBUtil();
    private MongoDataProvider<HistoryContentTest> dataProvider;
    private MongoCollection<Document> collection;

    // Подключаемся к реальной базе данных перед каждым тестом
    @BeforeEach
    void setup() {
        MongoDatabase database = MongoDBUtil.getDatabase("testdb");
        collection = database.getCollection("historyContent");
        dataProvider = new MongoDataProvider<>(collection, HistoryContentTest.class);
    }

    // Очищаем коллекцию после каждого теста
    @AfterEach
    void clearCollection() {
        // Очищаем коллекцию, чтобы каждый тест был независимым
        collection.deleteMany(new Document());
    }

    @Test
    void testSaveAndGetRecord() {
        HistoryContentTest record = new HistoryContentTest("10", "Natalya", "pass the lab", "testing");

        // Сохраняем запись
        dataProvider.saveRecord(record);

        // Получаем запись по ID
        HistoryContentTest fetched = dataProvider.getRecordById(Long.parseLong("10"));

        assertNotNull(fetched, "Запись не должна быть null");
        assertEquals(record.getId(), fetched.getId(), "ID записи должен совпадать");
        assertEquals(record.getActor(), fetched.getActor(), "Актер должен совпадать");
        assertEquals(record.getAction(), fetched.getAction(), "Действие должно совпадать");
        assertEquals(record.getContent(), fetched.getContent(), "Содержание должно совпадать");
    }


    @Test
    void testDeleteRecord() {
        HistoryContentTest record = new HistoryContentTest("10", "Natalya", "pass the lab", "testing");

        // Сохраняем запись
        dataProvider.saveRecord(record);

        // Удаляем запись
        dataProvider.deleteRecord(1);

        // Проверяем, что запись удалена
        assertThrows(Exception.class, () -> dataProvider.getRecordById(1), "Запись должна быть удалена");
    }

    @Test
    void testUpdateRecord() {
        HistoryContentTest record = new HistoryContentTest("10", "Natalya", "pass the lab", "testing");

        // Сохраняем запись
        dataProvider.saveRecord(record);

        // Обновляем запись
        HistoryContentTest updatedRecord = new HistoryContentTest("10", "Natalya", "update", "Updated content");
        dataProvider.saveRecord(updatedRecord);

        // Получаем все записи
        List<HistoryContentTest> records = dataProvider.getAllRecords();

        assertEquals(1, records.size(), "В коллекции должна быть только одна запись");
        assertTrue(records.contains(updatedRecord), "Обновленная запись должна быть в коллекции");
        assertFalse(records.contains(record), "Старая запись не должна быть в коллекции");
    }

    @Test
    void testGetAllRecords() {
        HistoryContentTest record1 = new HistoryContentTest("10", "Natalya", "create", "Test content");

        // Сохраняем записи
        dataProvider.saveRecord(record1);

        // Получаем все записи
        List<HistoryContentTest> records = dataProvider.getAllRecords();

        assertNotNull(records, "Список записей не должен быть null");
        assertEquals(2, records.size(), "Количество записей должно быть равно 2");
        assertTrue(records.contains(record1), "Запись 1 должна быть в коллекции");
    }

}