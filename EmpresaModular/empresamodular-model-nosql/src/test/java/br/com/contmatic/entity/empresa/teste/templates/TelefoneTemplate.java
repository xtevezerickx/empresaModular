package br.com.contmatic.entity.empresa.teste.templates;

import br.com.contmatic.entity.empresa.Telefone;
import br.com.contmatic.entity.empresa.constantes.TelefoneType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TelefoneTemplate implements TemplateLoader {
    /**
     * Metodo para carregar os templates
     */
    public void load() {
        /**
         * Cria um objeto valido de telefone
         */
        Fixture.of(Telefone.class).addTemplate("valido", new Rule() {
            {
                add("ddd", random("011", "015", "056", "018"));
                add("numeroTelefone", random("123456789", "987654321", "654789123"));
                add("tipoTelefone", random(TelefoneType.class));
            }
        });
        /**
         * Cria um objeto invalido de telefone
         */
        Fixture.of(Telefone.class).addTemplate("invalido", new Rule() {
            {
                add("ddd", random("01", "05", "06", "08"));
                add("numeroTelefone", random("123456", "987654324441", "654789l23"));
                add("tipoTelefone", random("oo", "c54778", ""));
            }
        });
        /**
         * Cria um objeto telefone igual
         */
        Fixture.of(Telefone.class).addTemplate("iguais", new Rule() {
            {
                add("ddd", "1");
                add("numeroTelefone", "949789056");
                add("tipoTelefone", "celular");
            }
        });
    }

}
