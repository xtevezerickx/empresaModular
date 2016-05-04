package br.com.contmatic.entity.empresa.teste;

import static br.com.contmatic.entity.empresa.util.ValidationUtil.hasErrors;
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

import br.com.contmatic.entity.empresa.Endereco;
import br.com.contmatic.entity.empresa.constantes.EnderecoType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@FixMethodOrder(NAME_ASCENDING)
public class EnderecoTeste {
    private Endereco endereco;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Metodos sendo utilizados antes de iniciar a classe Endereco Teste");

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() {
        endereco = null;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Metodos executados depois da classe Endereco Teste");
    }

    @Before
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.entity.empresa.teste.templates");
        endereco = Fixture.from(Endereco.class).gimme("valido");
    }

    @Test(timeout = 1000)
    public void deve_aceitar_nome_logradouro_valido() {
        String nomeLogradouro = "rua alves de almeida";
        endereco.setNomeLogradouro(nomeLogradouro);
        assertEquals(nomeLogradouro, endereco.getNomeLogradouro());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_tipo_logradouro_valido() {
        EnderecoType tipoLogradouro = EnderecoType.ALAMEDA;
        endereco.setTipoLogradouro(tipoLogradouro);
        assertEquals(tipoLogradouro, endereco.getTipoLogradouro());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_numero_valido() {
        String numero = "41";
        endereco.setNumero(numero);
        assertEquals(numero, endereco.getNumero());

    }

    @Test(timeout = 1000)
    public void deve_aceitar_cep_valido() {
        String cep = "03378010";
        endereco.setCep(cep);
        assertEquals(cep, endereco.getCep());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_cidade_valido() {
        String cidade = "Sao paulo";
        endereco.setCidade(cidade);
        assertEquals(cidade, endereco.getCidade());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_estado_valido() {
        String estado = "Sao Paulo";
        endereco.setEstado(estado);
        assertEquals(estado, endereco.getEstado());
    }

    @Test(timeout = 1000)
    public void deve_aceitar_bairro_valido() {
        String bairro = "jd vila formosa";
        endereco.setBairro(bairro);
        assertEquals(bairro, endereco.getBairro());

    }

    @Test
    public void nao_deve_aceitar_nome_logradouro_nulo() {
        endereco.setNomeLogradouro(null);
        assertTrue(hasErrors(endereco, "É necessário preencher o campo nome logradouro"));
    }

    @Test
    public void nao_deve_aceitar_nome_logradouro_tamanho_maximo() {
        endereco.setNomeLogradouro("fffffffffffffffffffffffffffffffffffffff");
        assertTrue(hasErrors(endereco, "campo nome logradouro está com tamanho incorreto"));

    }

    @Test
    public void nao_deve_aceitar_nome_logradouro_tamanho_minimo() {
        endereco.setNomeLogradouro("d");
        assertTrue(hasErrors(endereco, "campo nome logradouro está com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_nome_logradouro_vazio() {
        endereco.setNomeLogradouro("");
        assertTrue(hasErrors(endereco, "Nome Logradouro não pode estar vazio"));
    }

    @Test
    public void nao_deve_aceitar_tipo_logradouro_nulo() {
        endereco.setTipoLogradouro(null);
        assertTrue(hasErrors(endereco, "É necessário preencher o campo tipo logradouro"));
    }

    @Test
    public void nao_deve_aceitar_numero_nulo() {
        endereco.setNumero(null);
        assertTrue(hasErrors(endereco, "É necessário preencher o campo numero"));
    }

    @Test
    public void nao_deve_aceitar_numero_vazio() {
        endereco.setNumero("");
        assertTrue(hasErrors(endereco, "O numero não pode ser vazio"));
    }

    @Test
    public void nao_deve_aceitar_numero_tamanho_incorreto() {
        endereco.setNumero("11111111111");
        assertTrue(hasErrors(endereco, "O campo numero está com tamanha incorreto"));
    }

    @Test
    public void nao_deve_aceitar_cep_com_letras() {
        endereco.setCep("1234567k");
        assertTrue(hasErrors(endereco, "O campo cep aceita apenas numeros"));
    }

    @Test
    public void nao_deve_aceitar_cep_nulo() {
        endereco.setCep(null);
        assertTrue(hasErrors(endereco, "É necessário preencher o campo CEP"));
    }

    @Test
    public void nao_deve_aceitar_cep_vazio() {
        endereco.setCep("");
        assertTrue(hasErrors(endereco, "O campo CEP não deve ser vazio"));
    }

    @Test
    public void nao_deve_aceitar_cep_tamanho_maior() {
        endereco.setCep("123456789");
        assertTrue(hasErrors(endereco, "O campo CEP está com o tamanho incorreto"));

    }

    @Test
    public void nao_deve_aceitar_cep_tamanho_menor() {
        endereco.setCep("1234567");
        assertTrue(hasErrors(endereco, "O campo CEP está com o tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_cidade_nulo() {
        endereco.setCidade(null);
        assertTrue(hasErrors(endereco, "É necessário preencher o campo cidade"));
    }

    @Test
    public void nao_deve_aceitar_cidade_vazio() {
        endereco.setCidade("");
        assertTrue(hasErrors(endereco, "O campo cidade não pode ser vazio"));
    }

    @Test
    public void nao_deve_aceitar_cidade_com_numero() {
        endereco.setCidade("fdf33");
        assertTrue(hasErrors(endereco, "O campo cidade não deve conter números"));
    }

    @Test
    public void nao_deve_aceitar_cidade_tamanho_maior() {
        endereco.setCidade("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        assertTrue(hasErrors(endereco, "O campo cidade está com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_cidade_tamanho_menor() {
        endereco.setCidade("oi");
        assertTrue(hasErrors(endereco, "O campo cidade está com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_estado_nulo() {
        endereco.setEstado(null);
        assertTrue(hasErrors(endereco, "É necessário preencher o campo estado"));
    }

    @Test
    public void nao_deve_aceitar_estado_vazio() {
        endereco.setEstado("");
        assertTrue(hasErrors(endereco, "O campo estado não pode ser vazio"));

    }

    @Test
    public void nao_deve_aceitar_estado_com_numero() {
        endereco.setEstado("Sao Paulo0");
        assertTrue(hasErrors(endereco, "O campo estado não pode conter números"));
    }

    @Test
    public void nao_deve_aceitar_estado_com_tamanho_incorreto() {
        endereco.setEstado("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        assertTrue(hasErrors(endereco, "O campo estado está com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_bairro_nulo() {
        endereco.setBairro(null);
        assertTrue(hasErrors(endereco, "É necessário preencher o campo bairro"));
    }

    @Test
    public void nao_deve_aceitar_bairro_vazio() {
        endereco.setBairro("");
        assertTrue(hasErrors(endereco, "O campo bairro não pode ser vazio"));
    }

    @Test
    public void nao_deve_aceitar_bairro_tamanho_minimo() {
        endereco.setBairro("kk");
        assertTrue(hasErrors(endereco, "O campo bairro está com tamanho incorreto"));
    }

    @Test
    public void nao_deve_aceitar_bairro_tamanho_maximo() {
        endereco.setBairro("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        assertTrue(hasErrors(endereco, "O campo bairro está com tamanho incorreto"));
    }
}
