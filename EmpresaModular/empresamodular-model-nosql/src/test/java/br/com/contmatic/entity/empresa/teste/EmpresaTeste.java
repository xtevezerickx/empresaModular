package br.com.contmatic.entity.empresa.teste;

import static br.com.contmatic.entity.empresa.util.ValidationUtil.hasErrors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.contmatic.entity.empresa.Endereco;
import br.com.contmatic.entity.empresa.Telefone;
import br.com.contmatic.entity.empresa.util.ValidationUtil;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)

public class EmpresaTeste {

    private Empresa empresa;
    private Empresa empresaInvalida;
    private Empresa empresaTelefoneIgual;
    private Empresa empresaEnderecoIgual;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Metodos sendo utilizados antes de iniciar a classe Empresa Teste");

    }

    @After
    public void tearDown() {
        empresa = null;
        empresaInvalida = null;
        empresaTelefoneIgual = null;
        empresaEnderecoIgual = null;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Metodos executados depois da classe Empresa Teste");
    }

    @Before
    public void setUp() {

        FixtureFactoryLoader.loadTemplates("br.com.contmatic.entity.empresa.teste.templates");
        empresa = Fixture.from(Empresa.class).gimme("valido");
        empresaInvalida = Fixture.from(Empresa.class).gimme("invalido");
        empresaTelefoneIgual = Fixture.from(Empresa.class).gimme("telefone_igual");
        empresaEnderecoIgual = Fixture.from(Empresa.class).gimme("endereco_igual");

    }

    @Test
    public void nao_deve_aceitar_telefones_iguais() {
        thrown.expect(IllegalArgumentException.class);
        Telefone telefone = Fixture.from(Telefone.class).gimme("iguais");
        empresaTelefoneIgual.setTelefone(telefone);
    }

    @Test
    public void nao_deve_aceitar_enderecos_iguais() {
        thrown.expect(IllegalArgumentException.class);
        Endereco endereco = Fixture.from(Endereco.class).gimme("iguais");
        empresaEnderecoIgual.setEndereco(endereco);

    }

    @Test(timeout = 1000)
    public void deve_aceitar_nome_fantasia_valido() {
        String nomeFantasia = "nome empresa";
        empresa.setNomeFantasia(nomeFantasia);
        assertEquals(nomeFantasia, empresa.getNomeFantasia());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_nome_proprietario_valido() {
        String nomeProprietario = "Erick Maia";
        empresa.setNomeProprietario(nomeProprietario);
        assertEquals(nomeProprietario, empresa.getNomeProprietario());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_cnpj_valido() {
        String cnpj = "11123121111111";
        empresa.setCnpj(cnpj);
        assertEquals(cnpj, empresa.getCnpj());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_email_valido() {
        String email = "erick@mail.com.br";
        empresa.setEmail(email);
        assertEquals(email, empresa.getEmail());
    }

    @Test
    public void nao_deve_aceitar_nome_fantasia_nulo() {
        empresa.setNomeFantasia(null);
        assertTrue(ValidationUtil.hasErrors(empresa, "É necessário preencher o nome fantasia"));
    }

    @Test
    public void nao_deve_aceitar_nome_fantasia_vazio() {
        empresa.setNomeFantasia("");
        assertTrue(hasErrors(empresa, "Nome Fantasia não pode estar vazio"));
    }

    @Test
    public void nao_deve_aceitar_nome_fantasia_com_minimo_de_caracteres() {
        empresa.setNomeFantasia("aaaa");
        assertTrue(hasErrors(empresa, "Nome Fantasia tamanho incorreto"));

    }

    @Test
    public void nao_deve_aceitar_nome_fantasia_com_maximo_de_caracteres() {
        empresa.setNomeFantasia("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertTrue(hasErrors(empresa, "Nome Fantasia tamanho incorreto"));

    }

    @Test
    public void nao_deve_aceitar_nome_proprietario_nulo() {
        empresa.setNomeProprietario(null);
        assertTrue(hasErrors(empresa, "É necessário preencher o nome do proprietário"));
    }

    @Test
    public void nao_deve_aceitar_nome_proprietario_com_numeros() {
        empresa.setNomeProprietario("er111");
        assertTrue(hasErrors(empresa, "Nome proprietário não deve conter números"));
    }

    @Test
    public void nao_deve_aceitar_nome_proprietario_vazio() {
        empresa.setNomeProprietario("");
        assertTrue(hasErrors(empresa, "Nome do proprietário não pode estar vazio"));
    }

    @Test
    public void nao_deve_aceitar_nome_proprietario_com_tamanho_maximo() {
        empresa.setNomeProprietario("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        assertTrue(hasErrors(empresa, "Campo nome proprietário está com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_nome_proprietario_com_tamanho_minimo() {
        empresa.setNomeProprietario("ffff");
        assertTrue(hasErrors(empresa, "Campo nome proprietário está com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        empresa.setCnpj(null);
        assertTrue(hasErrors(empresa, "CNPJ deve ser preenchido"));
    }

    @Test
    public void nao_deve_aceitar_cnpj_com_letras() {
        empresa.setCnpj("1111111ff");
        assertTrue(hasErrors(empresa, "CNPJ nao deve conter letras"));

    }

    @Test
    public void nao_deve_aceitar_cnpj_vazio() {
        empresa.setCnpj("");
        assertTrue(hasErrors(empresa, "CNPJ não pode estar vazio"));
    }

    @Test
    public void nao_deve_aceitar_cnpj_com_tamanho_menor() {
        empresa.setCnpj("1");
        assertTrue(hasErrors(empresa, "Tamanho do CNPJ incorrreto"));
    }

    @Test
    public void nao_deve_aceitar_cnpj_com_tamanho_maior() {
        empresa.setCnpj("5555555555555555555555");
        assertTrue(hasErrors(empresa, "Tamanho do CNPJ incorrreto"));
    }

    @Test
    public void nao_deve_aceitar_email_nulo() {
        empresa.setEmail(null);
        assertTrue(hasErrors(empresa, "É necessário preencher o campo Email"));
    }

    @Test
    public void nao_deve_aceitar_email_vazio() {
        empresa.setEmail("");
        assertTrue(hasErrors(empresa, "Email não pode ser vazio"));
    }

    @Test
    public void nao_deve_aceitar_email_tamanho_maximo() {
        empresa.setEmail("fffffffffffffffffffffffffffffffffffffff");
        assertTrue(hasErrors(empresa, "Tamanho do email incorreto"));
    }

    @Test
    public void nao_deve_aceitar_email_tamanho_minimo() {
        empresa.setEmail("fff");
        assertTrue(hasErrors(empresa, "Tamanho do email incorreto"));
    }

    @Test
    public void nao_deve_aceitar_endereco_com_um_endereco_apenas() {
        assertThat(empresaInvalida.getEndereco().size(), lessThan(2));
    }

    @Test
    public void nao_deve_aceitar_data_criacao_nulo() {
        empresa.setDataCriacao(null);
        assertTrue(hasErrors(empresa, "Data de criacao deve ser preenchida"));
    }

    @Test
    public void deve_aceitar_data_alteracao_maior_que_data_criacao() {
        DateTime dataAlteracao = new DateTime().plusDays(2);
        DateTime dataAnterior = this.empresa.getDataAlteracao();
        empresa.setDataAlteracao(dataAlteracao);
        assertTrue(empresa.getDataAlteracao().isAfter(dataAnterior));
    }

    @Test
    public void nao_deve_aceitar_data_alteracao_menor_que_atual() {
        assertTrue(hasErrors(empresaInvalida, "Data de alteração deve ser maior que a atual"));
    }

    @Test
    public void deve_aceitar_data_criacao_valida() {
        assertThat(new DateTime(2016, 04, 25, 0, 0, 0), is(empresa.getDataCriacao()));
    }

    @Test
    public void deve_aceitar_endereco_valido() {
        assertThat(empresa.getEndereco().size(), greaterThanOrEqualTo(2));
    }

    @Test
    public void deve_aceitar_telefone_valido() {
        assertThat(empresa.getTelefone().size(), greaterThanOrEqualTo(2));
    }

    @Test
    public void nao_deve_aceitar_telefone_com_um_numero_apenas() {
        assertThat(empresaInvalida.getTelefone().size(), lessThan(2));
    }

    @Test
    public void teste_to_string() {
        System.out.println(empresa);
    }
}
