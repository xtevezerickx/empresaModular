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
                add("ddd", random(Integer.class, range(11L, 99L)));
                add("numeroTelefone", regex("[3-5]{1}\\d{7}"));
                add("tipoTelefone", random(TelefoneType.FIXO));
            }
        });
        
        Fixture.of(Telefone.class).addTemplate("celular", new Rule() {
            {
                add("ddd",  random(Integer.class, range(11L, 99L)));
                add("numeroTelefone", regex("[9]{1}[4-9]{1}\\d{7}"));
                add("tipoTelefone", random(TelefoneType.CELULAR));
            }
        });
        /**
         * Cria um objeto invalido de telefone
         */
        Fixture.of(Telefone.class).addTemplate("invalido", new Rule() {
            {
                add("ddd", range(11,99));
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
