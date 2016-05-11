package br.com.contmatic.entity.empresa.teste.templates;

import org.joda.time.DateTime;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.contmatic.entity.empresa.Endereco;
import br.com.contmatic.entity.empresa.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EmpresaTemplate implements TemplateLoader {

    /**
     * Metodo para carregar os templates
     */

    public void load() {
        /**
         * Cria um objeto valido de Empresa
         */
        Fixture.of(Empresa.class).addTemplate("valido", new Rule() {
            {
                add("cnpj", cnpj());
                add("email", random("${cnpj}.gmail.com", "${cnpj}.hotmail.com"));
                add("nomeProprietario", name());
                add("nomeFantasia", name());
                add("dataCriacao", new DateTime(2016, 04, 25, 0, 0, 0));
              add("dataAlteracao", new DateTime(2016, 04, 25, 0, 0, 0).plusDays(2));
                add("telefone", has(2).of(Telefone.class, "valido","celular"));
                add("endereco", has(2).of(Endereco.class, "valido"));
            }
        });
        
        
        
        Fixture.of(Empresa.class).addTemplate("atualizar", new Rule() {
            {
                add("nomeFantasia", random("empresa 1", "empresa 2", "empresa 3"));
                add("nomeProprietario", random("Erick", "Maia", "Silva"));
                add("email", random("${nomeProprietario}.gmail.com", "${nomeProprietario}.hotmail.com"));
                add("dataCriacao", new DateTime(2016, 04, 25, 0, 0, 0));
                add("dataAlteracao", new DateTime(2016, 04, 25, 0, 0, 0).plusDays(2));
                add("telefone", has(2).of(Telefone.class, "valido"));
                add("endereco", has(2).of(Endereco.class, "valido"));
            }
        });
        /**
         * Cria um objeto invalido de Empresa
         */
        Fixture.of(Empresa.class).addTemplate("invalido", new Rule() {
            {
                add("cnpj", random("11", "5gdf5", "333333333f3333"));
                add("email", random("1", ""));
                add("nomeFantasia", random("ff", ""));
                add("nomeProprietario", random("Erick", "Maia", "Silva"));
                add("endereco", has(1).of(Endereco.class, "invalido"));
                add("telefone", has(1).of(Telefone.class, "invalido"));
                add("dataAlteracao", new DateTime(2016, 04, 25, 0, 0, 0).minusDays(2));
            }
        });
        /**
         * Cria um objeto de Empresa com telefone igual
         */
        Fixture.of(Empresa.class).addTemplate("telefone_igual", new Rule() {
            {
                add("cnpj", random("11", "5gdf5", "333333333f3333"));
                add("email", random("1", ""));
                add("nomeFantasia", random("ff", ""));
                add("nomeProprietario", random("Erick", "Maia", "Silva"));
                add("telefone", has(2).of(Telefone.class, "iguais"));
            }
        });
        /**
         * Cria um objeto de Empresa com endereço igual
         */
        Fixture.of(Empresa.class).addTemplate("endereco_igual", new Rule() {
            {
                add("cnpj", random("11", "5gdf5", "333333333f3333"));
                add("email", random("1", ""));
                add("nomeFantasia", random("ff", ""));
                add("nomeProprietario", random("Erick", "Maia", "Silva"));
                add("endereco", has(2).of(Endereco.class, "iguais"));
            }
        });

    }

}
