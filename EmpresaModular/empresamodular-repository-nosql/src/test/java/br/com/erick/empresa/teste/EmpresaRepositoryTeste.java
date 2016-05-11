package br.com.erick.empresa.teste;

import static br.com.erick.empresa.repository.util.EmpresaRepositoryUtils.getProjection;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertNotSame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.contmatic.entity.empresa.Endereco;
import br.com.contmatic.entity.empresa.Telefone;
import br.com.contmatic.entity.empresa.constantes.EnderecoType;
import br.com.erick.empresa.repository.EmpresaRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class EmpresaRepositoryTeste {
    private Empresa empresa;
    private Empresa filtro;
    private Empresa empresaAtualizada;
    private EmpresaRepository empresaRepository;

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.entity.empresa.teste.templates");
    }

    @Before
    public void setUp() {
        empresa = Fixture.from(Empresa.class).gimme("valido");
        filtro = new Empresa();
        empresaAtualizada = Fixture.from(Empresa.class).gimme("valido");
        empresaRepository = new EmpresaRepository("localhost", 27017, "Empresa");

    }

    @Test
    public void deve_adicionar_empresa() {

        empresa = Fixture.from(Empresa.class).gimme("valido");
        empresaRepository.adicionarEmpresa(empresa);

    }

    @Test
    public void deve_remover_empresa_pelo_cnpj() {
        empresaRepository.removerEmpresaCNPJ("99999999999999");
    }

    @Test
    public void deve_buscar_empresa_pelo_cnpj() {
        System.out.println(empresaRepository.procuraEmpresaCNPJ("66321413000186"));
    }

    @Test
    public void deve_atualizar_empresa_pelo_cnpj() {
        empresaAtualizada.setNomeFantasia("EMPRESA ATUALIZADA COM SUCESSO");
        empresaRepository.atualizaEmpresaCNPJ("99999999999999", empresaAtualizada);
    }

    @Test
    public void deve_atualizar_empresa_pesquisada_pelo_filtro() {
        filtro.setNomeFantasia("empresa A");
        empresaAtualizada.setNomeFantasia("filtro funcionou 2");
        empresaRepository.atualizaEmpresaFiltro(filtro, empresaAtualizada);
    }

    @Test
    public void deve_remover_empresa_pelo_filtro() {
        filtro.setNomeFantasia("Boyle");
        empresaRepository.removerEmpresaFiltro(filtro);
    }

    @Test
    public void deve_buscar_empresa_pelo_filtro() {
        filtro.setEmail("42564353000164.hotmail.com");
        System.out.println(empresaRepository.procuraEmpresaFiltro(filtro));
    }

    @Test
    public void deve_fazer_busca_paginada() {
        System.out.println(empresaRepository.pesquisaEmpresasPaginadas(1, 10));
    }

    @Test
    public void nao_deve_conter_itens_duplicados_na_busca_paginada(){
        List<Empresa> lista1 = empresaRepository.pesquisaEmpresasPaginadas(1, 5);
        List<Empresa> lista2 = empresaRepository.pesquisaEmpresasPaginadas(2, 5);
        assertNotSame(lista1,hasItem(lista2));
     }

    @Test
    public void deve_retornar_apenas_nome_fantasia_1() {
        filtro.setNomeFantasia("Eloy Jast");
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));
    }

    @Test
    public void deve_retornar_apenas_nome_proprietario_2() {
        filtro.setNomeProprietario("");
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_email_3() {
        filtro.setEmail("email");
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_data_criacao_4() {
        filtro.setDataCriacao(null);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_data_alteracao_5() {
        filtro.setDataAlteracao(null);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_fantasia_e_nome_proprietario_6() {
        filtro.setNomeFantasia("");
        filtro.setNomeProprietario("");
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_fantasia_e_email_7() {
        filtro.setNomeFantasia("");
        filtro.setEmail("");
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_fantasia_e_data_criacao_8() {
        filtro.setNomeFantasia("");
        filtro.setDataCriacao(null);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_fantasia_e_data_alteracao_9() {
        filtro.setNomeFantasia("");
        filtro.setDataAlteracao(null);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_proprietario_e_email_10() {
        filtro.setNomeProprietario("");
        filtro.setEmail("");
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_proprietario_e_data_criacao_11() {
        filtro.setNomeProprietario("");
        filtro.setDataCriacao(null);

        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_proprietario_e_data_alteracao_12() {
        filtro.setNomeProprietario("");
        filtro.setDataAlteracao(null);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_fantasia_e_nome_proprietario_e_email_13() {
        filtro.setNomeFantasia(null);
        filtro.setNomeProprietario("");
        filtro.setEmail("");
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_nome_fantasia_e_nome_proprietario_e_data_criacao_14() {
        filtro.setNomeFantasia("Silas Price");
        filtro.setNomeProprietario("Titus Morar");
        filtro.setDataCriacao(new DateTime(2016, 04, 25, 0, 0, 0));
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_endereco_15() {
        Endereco endereco = new Endereco();
        endereco.setBairro("bairro dois");
        endereco.setCep("08847511");
        endereco.setCidade("Rio de janeiro");
        endereco.setEstado("Minas Gerais");
        endereco.setNumero("3");
        endereco.setTipoLogradouro(EnderecoType.RUA);
        endereco.setNomeLogradouro("rua x");
        Set<Endereco> enderecos = new HashSet<Endereco>();
        enderecos.add(endereco);

        filtro.setEndereco(enderecos);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_endereco11_15() {
        Endereco endereco = new Endereco();
        endereco.setBairro("bairro um");
        endereco.setCep("08847511");
        endereco.setCidade("Rio de janeiro");
        endereco.setEstado("Rio");
        endereco.setNumero("2");
        endereco.setTipoLogradouro(EnderecoType.BLOCO);
        endereco.setNomeLogradouro("rua z");
        Set<Endereco> enderecos = new HashSet<Endereco>();
        enderecos.add(endereco);

        filtro.setEndereco(enderecos);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_endereco1_15() {
        Endereco endereco = Fixture.from(Endereco.class).gimme("valido");
        Set<Endereco> enderecos = new HashSet<Endereco>();
        enderecos.add(endereco);

        filtro.setEndereco(enderecos);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_telefone_16() {
        Telefone telefone = Fixture.from(Telefone.class).gimme("valido");
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(telefone);

        filtro.setTelefone(telefones);
        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_17() {

        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_18() {

        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_19() {

        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

    @Test
    public void deve_retornar_apenas_20() {

        System.out.println(empresaRepository.pesquisaPorFiltro(filtro, getProjection(filtro)));

    }

}
