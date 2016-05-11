package br.com.erick.empresa.assembler;

import java.util.List;

import org.bson.Document;
import org.joda.time.DateTime;

import br.com.contmatic.entity.empresa.Empresa;

public class EmpresaAssembler {
    private EmpresaAssembler() {

    }

    public static Document toDocument(Empresa empresa) {
        if (empresa != null) {
            Document doc = new Document();
            doc.append("_id", empresa.getCnpj()).append("nomeFantasia", empresa.getNomeFantasia()).append("nomeProprietario", empresa.getNomeProprietario()).append("email", empresa.getEmail())
                    .append("dataAlteracao", empresa.getDataAlteracao()).append("dataCriacao", empresa.getDataCriacao()).append("endereco", EnderecoAssembler.toDocument(empresa.getEndereco()))
                    .append("telefone", TelefoneAssembler.toDocument(empresa.getTelefone()));
            return doc;
        }
        return null;
    }

    public static Document toDocumentUpdate(Empresa empresa) {
        if (empresa != null) {
            Document doc = new Document();
            doc.append("nomeFantasia", empresa.getNomeFantasia()).append("nomeProprietario", empresa.getNomeProprietario()).append("email", empresa.getEmail())
                    .append("dataAlteracao", empresa.getDataAlteracao()).append("dataCriacao", empresa.getDataCriacao()).append("endereco", EnderecoAssembler.toDocument(empresa.getEndereco()))
                    .append("telefone", TelefoneAssembler.toDocument(empresa.getTelefone()));
            return doc;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Empresa toObject(Document document) {
        if (document != null) {
            Empresa emp = new Empresa();
            emp.setCnpj(document.getString("_id"));
            emp.setEmail(document.getString("email"));
            emp.setEndereco(EnderecoAssembler.toSet((List<Document>) document.get("endereco")));
            emp.setTelefone(TelefoneAssembler.toSet((List<Document>) document.get("telefone")));
            emp.setNomeFantasia(document.getString("nomeFantasia"));
            emp.setNomeProprietario(document.getString("nomeProprietario"));
            emp.setDataCriacao((DateTime) document.get("dataCriacao"));
            emp.setDataAlteracao((DateTime) document.get("dataAlteracao"));
            return emp;
        }
        return null;
    }

    public static Document empresaFiltro(Empresa empresa) {
        Document doc = new Document();
        if (empresa != null) {
            if (empresa.getCnpj() != null) {
                doc.append("_id", empresa.getCnpj());
            }
            if (empresa.getEmail() != null) {
                doc.append("email", empresa.getEmail());

            }
            if (empresa.getNomeFantasia() != null) {
                doc.append("nomeFantasia", empresa.getNomeFantasia());

            }
            if (empresa.getNomeProprietario() != null) {
                doc.append("nomeProprietario", empresa.getNomeProprietario());

            }
            if (empresa.getDataAlteracao() != null) {
                doc.append("dataAlteracao", empresa.getDataAlteracao());

            }
            if (empresa.getDataCriacao() != null) {
                doc.append("dataCriacao", empresa.getDataCriacao());

            }
            if (empresa.getEndereco() != null) {
                doc.append("endereco", EnderecoAssembler.toDocument(empresa.getEndereco()));

            }
            if (empresa.getTelefone() != null) {
                doc.append("telefone", TelefoneAssembler.toDocument(empresa.getTelefone()));

            }
            return doc;

        }
        return null;

    }

}
