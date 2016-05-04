package br.com.contmatic.entity.empresa.teste;

import static br.com.contmatic.entity.empresa.util.ValidationUtil.hasErrors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.contmatic.entity.empresa.Telefone;
import br.com.contmatic.entity.empresa.constantes.TelefoneType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)
public class TelefoneTeste {
    private Telefone telefone;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpBeforeClass() {

        System.out.println("Metodos sendo utilizados antes de iniciar a classe Telefone Teste");

    }

    @Before
    public void setUp() {

        FixtureFactoryLoader.loadTemplates("br.com.contmatic.entity.empresa.teste.templates");
        telefone = Fixture.from(Telefone.class).gimme("valido");

    }

    @After
    public void tearDown() {
        telefone = null;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Metodos executados depois da classe Telefone Teste");
    }

    @Test(timeout = 1000)
    public void deve_aceitar_numero_telefone_valido() {
        String numeroTelefone = "94978905";
        telefone.setNumeroTelefone(numeroTelefone);
        assertThat(numeroTelefone, is(telefone.getNumeroTelefone()));
    }

    @Test(timeout = 1000)
    public void deve_aceitar_ddd_valido() {
        String ddd = "011";
        telefone.setDdd(ddd);
        assertEquals(ddd, telefone.getDdd());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_tipo_telefone_valido() {
        TelefoneType tipoTelefone = TelefoneType.FIXO;
        telefone.setTipoTelefone(tipoTelefone);
        assertEquals(tipoTelefone, telefone.getTipoTelefone());
    }

    @Test
    public void nao_deve_aceitar_numero_telefone_nulo() {
        telefone.setNumeroTelefone(null);
        assertTrue(hasErrors(telefone, "É necessário preencher o campo numero de telefone"));
    }

    @Test
    public void nao_deve_aceitar_numero_telefone_vazio() {
        telefone.setNumeroTelefone("");
        assertTrue(hasErrors(telefone, "Campo numero telefone não deve ser vazio"));
    }

    @Test
    public void nao_deve_aceitar_numero_telefone_tamanho_incorreto() {
        telefone.setNumeroTelefone("11111");
        assertTrue(hasErrors(telefone, "numero de telefone com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_numero_telefone_com_letras() {
        telefone.setNumeroTelefone("fff");
        assertTrue(hasErrors(telefone, "O numero de telefone apenas deve contem números"));
    }

    @Test
    public void nao_deve_aceitar_ddd_nulo() {
        telefone.setDdd(null);
        assertTrue(hasErrors(telefone, "É necessário preencher o campo DDD"));
    }

    @Test
    public void nao_deve_aceitar_ddd_vazio() {
        telefone.setDdd("");
        assertTrue(hasErrors(telefone, "Campo DDD não pode ser vazio"));
    }

    @Test
    public void nao_deve_aceitar_ddd_com_tamanho_errado() {
        telefone.setDdd("44444");
        assertTrue(hasErrors(telefone, "DDD com tamanho incorreto"));

    }

    @Test
    public void nao_deve_aceitar_ddd_com_letras() {
        telefone.setDdd("01d");
        assertTrue(hasErrors(telefone, "DDD não pode conter letras"));
    }

    @Test
    public void nao_deve_aceitar_tipo_telefone_nulo() {
        telefone.setTipoTelefone(null);
        assertTrue(hasErrors(telefone, "É necessário preencher o campo tipo de telefone"));
    }

}
