package br.com.erick.empresa.repository;

import static br.com.erick.empresa.assembler.EmpresaAssembler.toDocument;
import static br.com.erick.empresa.repository.util.EmpresaCodecs.codecOptions;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.erick.empresa.assembler.EmpresaAssembler;
import br.com.erick.empresa.assembler.EnderecoAssembler;

public class EmpresaRepository {
    private String host;
    private int porta;
    private String database;

    public EmpresaRepository(String host, int porta, String database) {
        this.host = host;
        this.porta = porta;
        this.database = database;
    }

    private MongoClient criaConexao() {
        MongoClient mongo = new MongoClient(this.host + ":" + this.porta, codecOptions());
        return mongo;
    }

    public void adicionarEmpresa(Empresa empresa) {
        Document empresadoc = toDocument(empresa);
        MongoClient mongoClient = criaConexao();
        MongoDatabase collection = mongoClient.getDatabase(database);
        collection.getCollection("empresas").insertOne(empresadoc);
    }

    public void removerEmpresaCNPJ(String cnpj) {
        if (cnpj != null) {
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            collection.getCollection("empresas").deleteOne(new Document("cnpj", cnpj));
        }
    }

    public List<Empresa> procuraEmpresaCNPJ(String cnpj) {
        if (cnpj != null) {
            Document busca = new Document("_id", cnpj);
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            FindIterable<Document> iterable = collection.getCollection("empresas").find(busca);
            List<Empresa> listaEmpresa = new ArrayList<Empresa>();
            iterable.forEach(new Block<Document>() {
                @Override
                public void apply(Document t) {
                    listaEmpresa.add(EmpresaAssembler.toObject(t));
                }
            });
            return listaEmpresa;
        }
        return null;

    }

    public void procurarTodasEmpresas() {
        MongoClient mongoClient = criaConexao();
        MongoDatabase collection = mongoClient.getDatabase(database);
        FindIterable<Document> iterable = collection.getCollection("empresas").find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document);
            }

        });

    }

}
