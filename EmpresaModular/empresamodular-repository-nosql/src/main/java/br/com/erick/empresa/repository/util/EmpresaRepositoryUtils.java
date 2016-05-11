package br.com.erick.empresa.repository.util;

import java.util.ArrayList;
import java.util.List;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.erick.empresa.assembler.EnderecoAssembler;
import br.com.erick.empresa.assembler.TelefoneAssembler;


/**
 * The Class EmpresaRepositoryUtils.
 */
public final class EmpresaRepositoryUtils {

    /**
     * Gets the projection.
     *
     * @param empresa the empresa
     * @return the projection
     */
    public static List<String> getProjection(Empresa empresa) {
        if (empresa != null) {
            List<String> lista = new ArrayList<String>();
            if (empresa.getCnpj() != null) {
                lista.add("_id");
            }
            if (empresa.getEmail() != null) {
                lista.add("email");

            }
            if (empresa.getNomeFantasia() != null) {
                lista.add("nomeFantasia");

            }
            if (empresa.getNomeProprietario() != null) {
                lista.add("nomeProprietario");

            }
            if (empresa.getDataAlteracao() != null) {
                lista.add("dataAlteracao");

            }
            if (empresa.getDataCriacao() != null) {
                lista.add("dataCriacao");

            }
            if (empresa.getEndereco() != null) {
                lista.add("endereco");

            }
            if (empresa.getTelefone() != null) {
                lista.add("telefone");

            }
            return lista;
        }
        return null;
    }
}
