package br.com.erick.empresa.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.entity.empresa.Endereco;
import br.com.contmatic.entity.empresa.constantes.EnderecoType;

public class EnderecoAssembler {
    private EnderecoAssembler() {

    }

    public static List<Document> toDocument(Set<Endereco> endereco) {
        if (endereco != null) {
            List<Document> doc = new ArrayList<Document>();
            for(Endereco end : endereco) {
                Document e = EnderecoAssembler.toDocument(end);
                doc.add(e);
            }

            return doc;
        }
        return null;
    }

    public static Document toDocument(Endereco endereco) {
        if (endereco != null && endereco.getTipoLogradouro() != null) {
            Document document = new Document();
            document.append("nomeLogradouro", endereco.getNomeLogradouro()).append("bairro", endereco.getBairro()).append("cep", endereco.getCep()).append("cidade", endereco.getCidade())
                    .append("estado", endereco.getEstado()).append("numero", endereco.getNumero()).append("tipoLogradouro", endereco.getTipoLogradouro().name());
            return document;
        }
        return null;
    }

    public static Endereco toObject(Document document) {
        if (document != null) {
            Endereco endereco = new Endereco();
            endereco.setBairro(document.getString("bairro"));
            endereco.setCep(document.getString("cep"));
            endereco.setCidade(document.getString("cidade"));
            endereco.setEstado(document.getString("estado"));
            endereco.setNomeLogradouro(document.getString("nomeLogradouro"));
            endereco.setNumero(document.getString("numero"));
            endereco.setTipoLogradouro(getEnderecoType(document));
            return endereco;
        }
        return null;
    }

    public static EnderecoType getEnderecoType(Document enderecoDoc) {

        if (enderecoDoc != null) {
            String enderecoType = enderecoDoc.getString("tipoLogradouro");
            return EnderecoType.valueOf(enderecoType);
        }
        return null;
    }

    public static Set<Endereco> toSet(List<Document> endereco) {
        if (endereco != null) {
            Set<Endereco> doc = new HashSet<Endereco>();
            for(Document end : endereco) {
                Endereco e = EnderecoAssembler.toObject(end);
                doc.add(e);
            }

            return doc;
        }
        return null;
    }

}
