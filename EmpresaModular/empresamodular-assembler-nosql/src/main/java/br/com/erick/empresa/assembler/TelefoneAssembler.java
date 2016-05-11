package br.com.erick.empresa.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.entity.empresa.Telefone;
import br.com.contmatic.entity.empresa.constantes.TelefoneType;

public class TelefoneAssembler {
    private TelefoneAssembler() {

    }

    public static Document toDocument(Telefone telefone) {
        if (telefone != null) {
            Document document = new Document();
            document.append("ddd", telefone.getDdd()).append("numeroTelefone", telefone.getNumeroTelefone()).append("tipoTelefone", telefone.getTipoTelefone().name());
            return document;
        }
        return null;
    }

    public static Telefone toObject(Document document) {
        if (document != null) {
            Telefone telefone = new Telefone();
            telefone.setDdd(document.getInteger("ddd"));
            telefone.setTipoTelefone(getTelefoneType(document));
            telefone.setNumeroTelefone(document.getString("numeroTelefone"));
            return telefone;
        }
        return null;
    }

    public static List<Document> toDocument(Set<Telefone> telefone) {
        if (telefone != null) {
            List<Document> doc = new ArrayList<Document>();
            for(Telefone tel : telefone) {
                Document e = TelefoneAssembler.toDocument(tel);
                doc.add(e);
            }
            return doc;
        }
        return null;
    }

    public static Set<Telefone> toSet(List<Document> telefone) {
        if (telefone != null) {
            Set<Telefone> doc = new HashSet<Telefone>();
            for(Document end : telefone) {
                Telefone e = TelefoneAssembler.toObject(end);
                doc.add(e);
            }

            return doc;
        }
        return null;
    }

    public static TelefoneType getTelefoneType(Document telefoneDoc) {
        if (telefoneDoc != null) {
            String telefoneType = telefoneDoc.getString("tipoTelefone");
            return TelefoneType.valueOf(telefoneType);
        }
        return null;
    }
}
