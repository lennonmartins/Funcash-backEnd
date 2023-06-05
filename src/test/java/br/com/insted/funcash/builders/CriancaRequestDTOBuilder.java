package br.com.insted.funcash.builders;

import br.com.insted.funcash.dto.CriancaRequestDTO;
import br.com.insted.funcash.models.Genero;

public class CriancaRequestDTOBuilder {
    private String nome = "Pluto";
    private String email = "pluto@gmail";
    private String senha = "1234";
    private double saldo = 100.00;
    private String apelido = "toinho";
    private String dataDeNascimentoemString = "2023-03-23";
    private Genero genero = Genero.MASCULINO;
    private Long idDoResponsavel = 1L;

    public CriancaRequestDTOBuilder comData(String dataEmString) {
        this.dataDeNascimentoemString = dataEmString;
        return this;
    }
    
    public CriancaRequestDTO construir(){
        return new CriancaRequestDTO(dataDeNascimentoemString, email,senha,saldo, nome, apelido, genero, idDoResponsavel);
    }

    public CriancaRequestDTOBuilder comResponsavel(Long idDoResponsavel) {
        this.idDoResponsavel = idDoResponsavel;
        return this;
    }

    public CriancaRequestDTOBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }
}
