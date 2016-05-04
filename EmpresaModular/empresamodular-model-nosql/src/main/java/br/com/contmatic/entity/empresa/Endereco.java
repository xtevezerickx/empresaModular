package br.com.contmatic.entity.empresa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import br.com.contmatic.entity.empresa.constantes.EnderecoType;

public class Endereco {
    /**
     * Constante para tamanho maximo nome do logradouro
     */
    private static final int TAMANHO_MAX_NOME_LOGRADOURO = 30;
    /**
     * Constante para tamanho minimo nome do logradouro
     */
    private static final int TAMANHO_MIN_NOME_LOGRADOURO = 4;
    /**
     * Constante para tamanho do cep
     */
    private static final int TAMANHO_CEP = 8;
    /**
     * Constante para tamanho maximo do campo cidade
     */
    private static final int TAMANHO_MAX_CIDADE = 30;
    /**
     * Constante para tamanho maximo do campo estado
     */
    private static final int TAMANHO_MAX_ESTADO = 15;
    /**
     * Constante para o tamanho maximo do campo bairro
     */
    private static final int TAMANHO_MAX_BAIRRO = 25;
    /**
     * Constante para o tamanho do numero
     */
    private static final int TAMANHO_NUMERO = 10;
    /**
     * Contante para o tamanho minimo do bairro
     */
    private static final int TAMANHO_MIN_BAIRRO = 4;
    /**
     * Contante para o tamanho minimo da cidade
     */
    private static final int TAMANHO_MIN_CIDADE = 3;

    /**
     * Recebe o nome do logradouro
     */

    @NotNull(message = "É necessário preencher o campo nome logradouro")
    @NotBlank(message = "Nome Logradouro não pode estar vazio")
    @Length(min = TAMANHO_MIN_NOME_LOGRADOURO, max = TAMANHO_MAX_NOME_LOGRADOURO, message = "campo nome logradouro está com tamanho incorreto")
    @Pattern(regexp = "[a-zA-Z_0-9]{4,30}")
    private String nomeLogradouro;
    /**
     * Recebe o tipo de logradouro
     */
    @NotNull(message = "É necessário preencher o campo tipo logradouro")

    private EnderecoType tipoLogradouro;
    /**
     * Recebe o numero
     */
    @NotNull(message = "É necessário preencher o campo numero")
    @Range(max = TAMANHO_NUMERO, message = "O campo numero está com tamanha incorreto")
    @NotBlank(message = "O numero não pode ser vazio")
    @Pattern(regexp = "\\d{1,10}")
    private String numero;

    /**
     * Recebe o cep
     */

    @NotNull(message = "É necessário preencher o campo CEP")
    @Length(max = TAMANHO_CEP, min = TAMANHO_CEP, message = "O campo CEP está com o tamanho incorreto")
    @NotBlank(message = "O campo CEP não deve ser vazio")
    @Pattern(regexp = "\\d{8}", message = "O campo cep aceita apenas numeros")
    private String cep;
    /**
     * Recebe a cidade
     */
    @NotNull(message = "É necessário preencher o campo cidade")
    @NotBlank(message = "O campo cidade não pode ser vazio")
    @Range(min = TAMANHO_MIN_CIDADE, max = TAMANHO_MAX_CIDADE, message = "O campo cidade está com tamanho incorreto")
    @Pattern(regexp = "\\D{3,30}", message = "O campo cidade não deve conter números")
    private String cidade;
    /**
     * Recebe o estado
     */
    @NotNull(message = "É necessário preencher o campo estado")
    @Size(max = TAMANHO_MAX_ESTADO, message = "O campo estado está com tamanho incorreto")
    @NotEmpty(message = "O campo estado não pode ser vazio")
    @Pattern(regexp = "\\w{3,15}", message = "O campo estado não pode conter números")
    private String estado;
    /**
     * Recebe o bairro
     */
    @NotNull(message = "É necessário preencher o campo bairro")
    @Size(min = TAMANHO_MIN_BAIRRO, max = TAMANHO_MAX_BAIRRO, message = "O campo bairro está com tamanho incorreto")
    @NotBlank(message = "O campo bairro não pode ser vazio")
    @Pattern(regexp = "[a-zA-Z_0-9]{4,25}")
    private String bairro;

    /**
     * Retorna o nome do logradouro
     * 
     * @return String nomeLogradouro
     */
    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    /**
     * Recebe uma String nome logradouro como parametro e insere esta String na variavel nomeLogradouro
     * 
     * @param nomeLogradouro
     * 
     */
    public void setNomeLogradouro(String nomeLogradouro) {

        this.nomeLogradouro = nomeLogradouro;
    }

    /**
     * Retorna o tipoLogradouro do objeto(endereco)
     * 
     * @return String tipoLogradouro
     */

    public EnderecoType getTipoLogradouro() {
        return tipoLogradouro;
    }

    /**
     * Recebe uma String tipo logradouro como parametro e insere esta String na variavel tipoLogradouro
     * 
     * @param tipoLogradouro
     */
    public void setTipoLogradouro(EnderecoType tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    /**
     * Retorna o numero do objeto(endereco)
     * 
     * @return String numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Recebe uma String numero como parametro e insere esta String na variavel numero
     * 
     * @param nomeLogradouro
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Retorna o CEP do objeto(endereço)
     * 
     * @return String cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Recebe uma String cep como parametro e insere esta String na variavel cep.
     *
     * @param cep
     * 
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Retorna a cidade do objeto(endereço)
     * 
     * @return String cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Recebe uma String cidade como parametro e insere esta String na variavel cidade *
     * 
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Retorna o estado do objeto(endereço)
     * 
     * @return String estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Recebe uma String estado como parametro e insere esta String na variavel estado
     * 
     * @param nomeLogradouro
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Retorna o bairro do objeto(endereço)
     * 
     * @return String objeto
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Recebe uma String bairro como parametro e insere esta String na variavel bairro
     * 
     * @param bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.cep).append(this.numero).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        Endereco other = (Endereco) obj;
        return new EqualsBuilder().append(this.cep, other.cep).append(this.numero, other.numero).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, StandardToStringStyle.MULTI_LINE_STYLE).append(this.nomeLogradouro != null ? "Nome Logradouro " + this.nomeLogradouro : null)
                .append(this.tipoLogradouro != null ? "Tipo Logradouro " + this.tipoLogradouro : null).append(this.numero != null ? "Numero " + this.numero : null)
                .append(this.cep != null ? "CEP " + this.cep : null).append(this.cidade != null ? "Cidade " + this.cidade : null).append(this.estado != null ? "Estado " + this.estado : null)
                .append(this.bairro != null ? "Bairro " + this.bairro : null).toString();
    }

}
