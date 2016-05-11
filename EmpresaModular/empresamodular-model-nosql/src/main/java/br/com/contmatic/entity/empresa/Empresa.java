package br.com.contmatic.entity.empresa;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.caelum.stella.bean.validation.CNPJ;

public class Empresa {

    /** Constante padrão para CNPJ. */
    private static final int TAMANHO_CNPJ = 14;

    /** Constante para tamanho minimo do nome fantasia. */
    private static final int TAMANHO_MIN_NOME_FANTASIA = 8;

    /** Constante para tamanho maximo do nome fantasia. */
    private static final int TAMANHO_MAX_NOME_FANTASIA = 30;

    /** Constante para tamanho minimo do nome proprietario. */
    private static final int TAMANHO_MIN_NOME_PROPRIETARIO = 8;

    /** Constante para tamanho maximo do nome proprietario. */
    private static final int TAMANHO_MAX_NOME_PROPRIETARIO = 30;

    /** Constante para tamanho minimo do email. */
    private static final int TAMANHO_MIN_EMAIL = 9;

    /** Constante para tamanho maximo do email. */
    private static final int TAMANHO_MAX_EMAIL = 30;

    /** Recebe o nome fantasia. */

    @NotNull(message = "É necessário preencher o nome fantasia")
    @NotBlank(message = "Nome Fantasia não pode estar vazio")
    @Length(min = TAMANHO_MIN_NOME_FANTASIA, max = TAMANHO_MAX_NOME_FANTASIA, message = "Nome Fantasia tamanho incorreto")
    @Pattern(regexp = "[a-z\\-0-9]{8,30}")
    private String nomeFantasia;

    /** Recebe o nome do proprietario. */

    @NotNull(message = "É necessário preencher o nome do proprietário")
    @NotBlank(message = "Nome do proprietário não pode estar vazio")
    @Length(max = TAMANHO_MAX_NOME_PROPRIETARIO, min = TAMANHO_MIN_NOME_PROPRIETARIO, message = "Campo nome proprietário está com tamanho incorreto")
    @Pattern(regexp = "\\w{8,30}", message = "Nome proprietário não deve conter números")
    private String nomeProprietario;

    /** Recebe o cnpj da empresa. */
    @CNPJ(message = "CNPJ nao deve conter letras")
    @NotNull(message = "CNPJ deve ser preenchido")
    @NotBlank(message = "CNPJ não pode estar vazio")
    @Length(max = TAMANHO_CNPJ, min = TAMANHO_CNPJ, message = "Tamanho do CNPJ incorrreto")
    @Pattern(regexp = "\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}")
    private String cnpj;

    /** Recebe o email da empresa */
    @Email
    @NotNull(message = "É necessário preencher o campo Email")
    @NotBlank(message = "Email não pode ser vazio")
    @Size(min = TAMANHO_MIN_EMAIL, max = TAMANHO_MAX_EMAIL, message = "Tamanho do email incorreto")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    /** Recebe os numeros de telefone da empresa. */

    @Valid
    private Set<Telefone> telefone;

    /** Recebe os enderecos da empresa. */

    @Valid
    private Set<Endereco> endereco;

    /** Recebe a data de criação da empresa. */

    @NotNull(message = "Data de criacao deve ser preenchida")
    @Past
    private DateTime dataCriacao;

    /** Recebe a data de alteração da empresa. */

    @Future(message = "Data de alteração deve ser maior que a atual")
    private DateTime dataAlteracao;

    /**
     * Retorna o nome fantasia da empresa.
     * 
     * @return String
     */

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * Recebe uma String como parametro e insere esta String na variavel nomeFantasia
     *
     * @param nomeFantasia o novo nome fantasia
     */

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    /**
     * Retorna o nome do proprietario da empresa.
     *
     * @return String
     */
    public String getNomeProprietario() {
        return nomeProprietario;
    }

    /**
     * Recebe uma String como parametro e insere esta String na variavel nomeProprietari.
     *
     * @param nomeProprietario o novo nome do proprietario
     */
    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    /**
     * Recebe um Telefone como parametro e insere este Telefone na lista de telefones da empresa
     * 
     * @param telefone
     * @throws IllegalArgumentException caso o Telefone já exista na empresa ou tenha menos que 2 telefones
     * 
     */
    public void setTelefone(Set<Telefone> telefone) {
        // this.validateTelefoneIgual(telefone);
        this.telefone = telefone;

    }

    /**
     * Retorna a lista de telefone.
     *
     * @return telefone
     */
    public Set<Telefone> getTelefone() {
        return telefone;
    }

    /**
     * Retorna a lista de enderecos
     *
     * @return endereco
     */
    public Set<Endereco> getEndereco() {
        return endereco;
    }

    /**
     * Recebe um Endereco como parametro e insere este Endereco na lista de enderecos
     * 
     * @param endereco the new endereco
     * @throws IllegalArgumentException caso o Endereco já exista na empresa ou tenha menos que 2 enderecos
     */

    public void setEndereco(Set<Endereco> enderecos) {
        // this.validateEnderecoIgual(endereco);
        this.endereco = enderecos;
    }

    /**
     * Retorna o email do objeto.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Recebe uma String como parametro e insere esta String na variavel email
     * 
     * @param email o novo email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o CNPJ do objeto.
     *
     * @return String
     */

    public String getCnpj() {
        return cnpj;
    }

    /**
     * Recebe uma String como parametro e insere esta String na variavel CNPJ
     *
     * @param cnpj o novo cnpj
     */

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Retorna a data de criação do objeto (empresa).
     *
     * @return DateTime
     */
    public DateTime getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Recebe uma Data como parametro e insere esta data na variavel dataCriacao
     *
     * @param dataCriacao a nova data criacao
     */
    public void setDataCriacao(DateTime dataCriacao) {

        this.dataCriacao = dataCriacao;
    }

    /**
     * Retorna a data de alteração do objeto(empresa).
     *
     * @return DateTime
     */
    public DateTime getDataAlteracao() {
        return dataAlteracao;
    }

    /**
     * Recebe uma data como parametro e insere esta data na variavel dataAlteracao
     *
     * @param dataAlteracao the new data alteracao
     * @throws IllegalArgumentException caso a data de alteração seja menor que a data de criacao
     */

    public void setDataAlteracao(DateTime dataAlteracao) {

        this.dataAlteracao = dataAlteracao;
    }

    /**
     * Valida se o parametro recebido data de alteração é maior que a data de criação
     * 
     * @param nomeProprietario
     * @throws IllegalArgumentException
     */

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnpj).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        Empresa other = (Empresa) obj;
        return new EqualsBuilder().append(this.cnpj, other.cnpj).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        DateTimeFormatter dataFormatada = DateTimeFormat.forPattern("dd/MM/YYYY");
        String dataCriacaoString = dataFormatada.print(dataCriacao);
        String dataAlteracaoString = dataFormatada.print(dataAlteracao);
        return new ToStringBuilder(this, StandardToStringStyle.MULTI_LINE_STYLE).append(this.nomeFantasia != null ? "Nome Fantasia: " + this.nomeFantasia : null)
                .append(this.nomeProprietario != null ? "Nome do Propietário: " + this.nomeProprietario : null).append(this.cnpj != null ? "CNPJ: " + this.cnpj : null)
                .append(this.email != null ? "Email: " + this.email : null).append(this.dataCriacao != null ? "Data de criação: " + dataCriacaoString : null)
                .append(this.dataAlteracao != null ? "Data da alteração: " + dataAlteracaoString : null).append(telefone != null ? telefone.toString() : null)
                .append(endereco != null ? endereco.toString() : null).build();

    }

}
