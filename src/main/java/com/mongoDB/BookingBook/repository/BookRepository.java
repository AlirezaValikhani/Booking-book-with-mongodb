package com.mongoDB.BookingBook.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongoDB.BookingBook.dto.PaginationDto;
import com.mongoDB.BookingBook.model.Book;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
        Document query = new Document();
        query.put("name", book.getName());
        query.put("authorName", book.getAuthorName());
        query.put("isReserve", book.getIsReserve());
        collection.insertOne(query);
        String ObjID = query.get("_id").toString();
        book.setId(ObjID);
        return book;
    }

    public void update(Book book) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("booking");
        Document query = new Document();
        query.append("_id", new ObjectId(book.getId()));

        Map bookMap = new ObjectMapper().convertValue(book, Map.class);
        BasicDBObject update = new BasicDBObject("$set", new BasicDBObject(bookMap));
        UpdateOptions options = new UpdateOptions().upsert(true);
        collection.updateOne(query, update, options);
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
        book.setIsReserve(doc.getBoolean("isReserve"));
        return book;
    }

    public List<Book> search(Book book) {
        String name = "name";
        String authorName = "authorName";
        List<BasicDBObject> aggregationInput = new ArrayList<>();
        aggregationInput.add(
                new BasicDBObject("$match",
                        new BasicDBObject("$and",
                                Arrays.asList(
                                        new BasicDBObject(name, book.getName()),
                                        new BasicDBObject(authorName, book.getAuthorName())
                                )
                        )
                )
        );
        List<Document> documents = mongoDatabase.getCollection("booking")
                .aggregate(aggregationInput).into(new ArrayList<>());
        List<Book> books = new ArrayList<>();
        for (Document d : documents) {
            Book finalBook = new Book();
            finalBook.setName(d.getString(name));
            finalBook.setAuthorName(d.getString(authorName));
            books.add(finalBook);
        }
        return books;
    }

    public List<Book> pagination(Integer size,Integer page) {
        List<BasicDBObject> aggregationInput = new ArrayList<>();
        aggregationInput.add(
                new BasicDBObject("$sort",
                        new BasicDBObject("_id",1)
                )
        );
        aggregationInput.add(
                new BasicDBObject("$skip",
                        size * page)
        );
        aggregationInput.add(
                new BasicDBObject("$limit",
                        size)
        );
        List<Document> documents = mongoDatabase.getCollection("booking")
                .aggregate(aggregationInput).into(new ArrayList<>());
        List<Book> books = new ArrayList<>();
        for (Document d : documents) {
            Book finalBook = new Book();
            finalBook.setName(d.getString("name"));
            finalBook.setAuthorName(d.getString("authorName"));
            finalBook.setIsReserve(d.getBoolean("isReserve"));
            finalBook.setId(String.valueOf(d.getObjectId("_id")));
            books.add(finalBook);
        }
        return books;
    }
}
