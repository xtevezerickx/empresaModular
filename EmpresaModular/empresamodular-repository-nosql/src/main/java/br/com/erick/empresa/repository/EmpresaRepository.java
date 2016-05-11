package br.com.erick.empresa.repository;

import static br.com.erick.empresa.assembler.EmpresaAssembler.empresaFiltro;
import static br.com.erick.empresa.repository.util.EmpresaCodecs.codecOptions;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.erick.empresa.assembler.EmpresaAssembler;

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

        Document empresadoc = EmpresaAssembler.toDocument(empresa);
        MongoClient mongoClient = criaConexao();
        try {
            MongoDatabase collection = mongoClient.getDatabase(database);
            collection.getCollection("empresas").insertOne(empresadoc);
        } finally {
            mongoClient.close();
        }

    }

    public void removerEmpresaCNPJ(String cnpj) {
        if (cnpj != null) {
            Document busca = new Document("_id", cnpj);
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            collection.getCollection("empresas").deleteOne(busca);
            mongoClient.close();
        }
    }

    public void removerEmpresaFiltro(Empresa filtro) {
        if (filtro != null) {
            Document busca = EmpresaAssembler.empresaFiltro(filtro);
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            collection.getCollection("empresas").deleteOne(busca);
            mongoClient.close();
        }
    }

    public List<Empresa> procuraEmpresaFiltro(Empresa filtro) {
        if (filtro != null) {
            Document busca = EmpresaAssembler.empresaFiltro(filtro);
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            FindIterable<Document> iterable = collection.getCollection("empresas").find(busca);
            List<Empresa> listaEmpresa = new ArrayList<Empresa>();
            adicionaTudoEncontrado(iterable, listaEmpresa);
            mongoClient.close();
            return listaEmpresa;
        }
        return null;
    }

    public List<Empresa> pesquisaEmpresasPaginadas(int pagina, int itensPorPagina) {

        MongoClient mongoClient = criaConexao();
        MongoDatabase collection = mongoClient.getDatabase(database);
        FindIterable<Document> iterable = collection.getCollection("empresas").find().skip(itensPorPagina * (pagina - 1)).limit(itensPorPagina);
        List<Empresa> listaEmpresa = new ArrayList<Empresa>();
        adicionaTudoEncontrado(iterable, listaEmpresa);
        mongoClient.close();

        return listaEmpresa;

    }

    public List<Empresa> procuraEmpresaCNPJ(String cnpj) {
        if (cnpj != null) {
            Document busca = new Document("_id", cnpj);
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            FindIterable<Document> iterable = collection.getCollection("empresas").find(busca);
            List<Empresa> listaEmpresa = new ArrayList<Empresa>();
            adicionaTudoEncontrado(iterable, listaEmpresa);
            mongoClient.close();
            return listaEmpresa;
        }
        return null;

    }

    public List<Empresa> pesquisaPorFiltro(Empresa filtro, List<String> projection) {
        if (filtro != null) {
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            FindIterable<Document> iterable = collection.getCollection("empresas").find(empresaFiltro(filtro)).projection(include(projection)).limit(10);
            List<Empresa> listaEmpresa = new ArrayList<Empresa>();
            listaEmpresa = adicionaTudoEncontrado(iterable, listaEmpresa);
            mongoClient.close();
            return listaEmpresa;
        }

        return null;
    }

    private List<Empresa> adicionaTudoEncontrado(FindIterable<Document> iterable, List<Empresa> listaEmpresa) {
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                listaEmpresa.add(EmpresaAssembler.toObject(document));
            }
        });
        return listaEmpresa;
    }

    public void atualizaEmpresaCNPJ(String cnpj, Empresa empresaAtualizada) {
        if (cnpj != null && empresaAtualizada != null) {
            Document empresadoc = EmpresaAssembler.toDocumentUpdate(empresaAtualizada);
            Document busca = new Document("_id", cnpj);
            Document atualiza = new Document("$set", empresadoc);
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            collection.getCollection("empresas").updateOne(busca, atualiza);
            mongoClient.close();
        }

    }

    public void atualizaEmpresaFiltro(Empresa filtro, Empresa novoValor) {
        if (filtro != null && novoValor != null) {
            Document busca = EmpresaAssembler.empresaFiltro(filtro);
            Document atualiza = EmpresaAssembler.toDocumentUpdate(novoValor);
            MongoClient mongoClient = criaConexao();
            MongoDatabase collection = mongoClient.getDatabase(database);
            collection.getCollection("empresas").updateOne(busca, new Document("$set", atualiza));
            mongoClient.close();
        }
    }

}
