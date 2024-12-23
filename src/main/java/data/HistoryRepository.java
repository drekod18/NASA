package data;

import com.mongodb.client.MongoCollection;
import data.GenericRepository;
import exceptions.RepositoryException;
import model.HistoryContent;
import org.bson.Document;
import providers.IDataProvider;
import providers.MongoDataProvider;

import java.util.List;

public class HistoryRepository extends GenericRepository<HistoryContent> {
    public HistoryRepository(IDataProvider<HistoryContent> provider) {
        super(provider);
    }

    // Фабричный метод для создания репозитория с настройками по умолчанию
    public static HistoryRepository defaultMongoRepository(MongoCollection<Document> collection) {
        return new HistoryRepository(new MongoDataProvider<>(collection, HistoryContent.class));
    }

    public List<HistoryContent> findByActor(String actor) {
        try {
            return findAll().stream()
                    .filter(history -> actor.equals(history.getActor()))
                    .toList();
        } catch (Exception e) {
            throw new RepositoryException("Ошибка при поиске историй по actor: " + actor, e);
        }
    }
}