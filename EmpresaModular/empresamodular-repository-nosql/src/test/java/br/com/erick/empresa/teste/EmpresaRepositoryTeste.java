package br.com.erick.empresa.teste;

import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.entity.empresa.Empresa;
import br.com.contmatic.entity.empresa.constantes.EnderecoType;
import br.com.erick.empresa.repository.EmpresaRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class EmpresaRepositoryTeste {
    private Empresa empresa;
    private Empresa empresaAtualizada;
    private EmpresaRepository empresaRepository;
    @Before
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.entity.empresa.teste.templates");
        empresa = Fixture.from(Empresa.class).gimme("valido");
        empresaAtualizada = Fixture.from(Empresa.class).gimme("valido");
        empresaRepository = new EmpresaRepository("localhost", 27017, "Empresa");
    }
    
    @Test
    public void deve_adicionar_empresa(){
        empresaRepository.adicionarEmpresa(empresa);
        
    }
    
    @Test
    public void deve_remover_empresa_pelo_cnpj(){
        empresaRepository.removerEmpresaCNPJ("33333333333333");
    }
    
    @Test
    public void deve_buscar_empresa_pelo_cnpj(){
       System.out.println(empresaRepository.procuraEmpresaCNPJ("33333333333333"));
    }
    
      
    @Test
    public void deve_atualizar_empresa_pelo_cnpj(){
       //empresaRepository.atualizaEmpresaCNPJ("55555555555555",empresaAtualizada);
    }
    
    @Test
    public void deve_exibir_todas_as_empresas_cadastradas(){
        empresaRepository.procurarTodasEmpresas();
    }
    
    
}
