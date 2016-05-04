package br.com.contmatic.entity.empresa.constantes;

public enum EnderecoType {

                          /** Constante para tipo de endereco avenida. */
                          AVENIDA("Avenida"),

                          /** Constante para tipo de endereco rua. */
                          RUA("Filial"),

                          /** Constante para tipo de endereco alameda. */
                          ALAMEDA("Alameda"),

                          /** Constante para tipo de endereco largo. */
                          LARGO("Largo"),

                          /** Constante para tipo de endereco praça. */
                          PRACA("Praca"),

                          /** Constante para tipo de endereco quadra. */
                          QUADRA("Quadra"),

                          /** Constante para tipo de endereco rodovia. */
                          RODOVIA("Rodovia"),

                          /** Constante para tipo de endereco travessa */
                          TRAVESSA("Travessa"),

                          /** Constante para tipo de endereco bloco. */
                          BLOCO("Bloco");

    /** Recebe a descricao. */
    String descricao;

    /**
     * Construtor privado para um tipo de endereco.
     *
     * @param descricao
     */
    private EnderecoType(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retrona a descrição do tipo de endereço.
     *
     * @return descricao
     */
    public String getDescricao() {
        return this.descricao;
    }
}
