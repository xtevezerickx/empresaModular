package br.com.erick.empresa.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.entity.empresa.Telefone;
import br.com.contmatic.entity.empresa.constantes.TelefoneType;

public class TelefoneAssembler {
    private TelefoneAssembler() {

    }
    public static Document toDocument(Telefone telefone){
        if (telefone!=null){
            Document document= new Document();
            document.append("ddd",telefone.getDdd())
            .append("numeroTelefone", telefone.getNumeroTelefone())
            .append("tipoTelefone",telefone.getTipoTelefone().name());
            return document;
        }
        return null;
    }
    
    public static Telefone toObjet (Document document){
        if(document!=null){
            Telefone telefone = new Telefone();
            telefone.setDdd(document.getString("telefone.ddd"));
            telefone.setTipoTelefone(document.get("telefone.tipoTelefone",TelefoneType.class));
            telefone.setNumeroTelefone(document.getString("telefone.numeroTelefone"));
            return telefone;
        }
        return null;
    }
    
    public static List<Document> toDocument(Set<Telefone> telefone){
        if(telefone!=null){
            List<Document> doc = new ArrayList<Document>();
            for(Telefone tel : telefone) {
                Document e = TelefoneAssembler.toDocument(tel);
                doc.add(e);
            }
            return doc;
        }
        return null;
    }
}
