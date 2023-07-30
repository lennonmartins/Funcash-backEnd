package br.com.insted.funcash.builders;

import br.com.insted.funcash.dto.ResponsavelRequestDTO;
import br.com.insted.funcash.models.Genero;

public class ResponsavelRequestDTOBuilder {
    private String nome = "Charmayanne";

    private String email = "charmayanne@gmail";

    private String cpf = "123456";

    private String dataDeNascimentoResponsavelString = "2023-02-21";

    private Genero genero = Genero.FEMININO;

    private String senha = "1234567";

    private String foto = Image.getBytes();

    public ResponsavelRequestDTOBuilder comData(String dataEmString) {
        this.dataDeNascimentoResponsavelString = dataEmString;
        return this;
    }

    public ResponsavelRequestDTO construir() {
        return new ResponsavelRequestDTO(nome, email, cpf, dataDeNascimentoResponsavelString, genero, senha, foto);
    }
    public ResponsavelRequestDTOBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }
}
