package br.com.erick.empresa.assembler;

import java.util.List;

import org.bson.Document;
import org.joda.time.DateTime;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.contmatic.entity.empresa.Endereco;
import br.com.contmatic.entity.empresa.Telefone;


public class EmpresaAssembler {
    private EmpresaAssembler(){
        
    }
    
    public static Document toDocument(Empresa empresa){
        if (empresa!=null){
           Document doc= new Document();
           doc.append("_id",empresa.getCnpj())
           .append("nomeFantasia",empresa.getNomeFantasia())
           .append("nomeProprietario",empresa.getNomeProprietario())
           .append("email",empresa.getEmail())
           .append("dataAlteracao",empresa.getDataAlteracao())
           .append("dataCriacao",empresa.getDataCriacao())
           .append("endereco",EnderecoAssembler.toDocument(empresa.getEndereco()))
           .append("telefone",TelefoneAssembler.toDocument(empresa.getTelefone()));
           return doc;
        }
        return null;
    }
    
    
    @SuppressWarnings("unchecked")
    public static Empresa toObject(Document document){
        if (document!=null){
            Empresa emp = new Empresa();
            emp.setCnpj(document.getString("_id"));
            emp.setEmail(document.getString("email"));
            emp.setEndereco(EnderecoAssembler.toSet((List<Document>) document.get("endereco")));
           // emp.setTelefone(document.get("telefone"));
            emp.setNomeFantasia(document.getString("nomeFantasia"));
            emp.setNomeProprietario(document.getString("nomeProprietario"));
            emp.setDataCriacao((DateTime) document.get("dataCriacao"));
            emp.setDataAlteracao((DateTime) document.get("dataAlteracao"));
            return emp;
        }
        return null;
    }
    
    
}
