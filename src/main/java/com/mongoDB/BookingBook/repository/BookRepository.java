package com.mongoDB.BookingBook.repository;

import com.mongoDB.BookingBook.model.Book;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationPipeline;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final MongoTemplate mongoTemplate;

    public BookRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        MongoClientURI connectionString = new MongoClientURI("mongodb://mongo:mongo@192.168.2.180:27017");
        this.mongoClient = new MongoClient(connectionString);
        this.mongoDatabase = mongoClient.getDatabase("booking");
    }

    public Book save(Book book) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("booking");
        Document document = new Document();
        document.put("name", book.getName());
        document.put("authorName", book.getAuthorName());
        collection.insertOne(document);
        String ObjID = document.get("_id").toString();
        book.setId(ObjID);
        return book;
    }

    public void update(Book book) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("booking");
        Document document = new Document();
        document.append("_id", new ObjectId(book.getId()));
        Bson updates = Updates.combine(
                Updates.set("name", book.getName()),
                Updates.set("authorName", book.getAuthorName()));
        UpdateOptions options = new UpdateOptions().upsert(true);
        collection.updateOne(document, updates, options);
    }

    public void delete(String id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("booking");
        Document document = new Document();
        document.put("_id", new ObjectId(id));
        collection.deleteOne(document);
    }

    public List<Book> findAll() {
        MongoCollection<Document> collection = mongoDatabase.getCollection("booking");
        return collection.find().batchSize(10)
                .into(new ArrayList<>())
                .stream()
                .map(doc -> {
                    Book book = new Book();
                    book.setAuthorName(doc.getString("authorName"));
                    book.setId(doc.getObjectId("_id").toString());
                    book.setName(doc.getString("name"));
                    return book;
                })
                .collect(Collectors.toList());
    }

    public Book findById(String id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("booking");
        Document document = new Document();
        document.put("_id", new ObjectId(id));
        Document doc = collection.find(document)
                .first();
        Book book = new Book();
        book.setName(doc.getString("name"));
        book.setAuthorName(doc.getString("authorName"));
        return book;
    }

    public List<Book> search(Book book) {
        String name = "name";
        String authorName = "authorName";
        List<BasicDBObject> aggregationInput = new ArrayList<>();
        aggregationInput.add(
                new BasicDBObject("$match",
                        new BasicDBObject("$or",
                                Arrays.asList(
                                        new BasicDBObject(name, book.getName()),
                                        new BasicDBObject(authorName, book.getAuthorName())
                                )
                        )
                )
        );
        List<Document> documents = mongoClient.getDatabase("booking").getCollection("booking")
                .aggregate(aggregationInput).into(new ArrayList<>());
        List<Book> books = new ArrayList<>();
        for (Document d:documents) {
            Book finalBook = new Book();
            finalBook.setName(d.getString(name));
            finalBook.setAuthorName(d.getString(authorName));
            books.add(finalBook);
        }
        return books;
    }
}
