package br.com.contmatic.entity.empresa.constantes;

public enum TelefoneType {

                          /** Constante para telefone celular. */
                          CELULAR("Celular", 9),

                          /** Constante para telefone fixo. */
                          FIXO("Fixo", 8);

    /** Recebe a descricao do tipo de telefone. */
    private String descricao;

    /** Recebe o tamanho do tipo de telefone. */
    private int tamanho;

    /**
     * Construtor privado para o novo tipo de telefone
     *
     * @param descricao
     * @param tamanho
     */
    private TelefoneType(String descricao, int tamanho) {
        this.descricao = descricao;
        this.tamanho = tamanho;
    }

    /**
     * Retora a descricao.
     *
     * @return descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Retorna o tamanho.
     *
     * @return tamanho
     */
    public int getTamanho() {
        return this.tamanho;
    }

}
