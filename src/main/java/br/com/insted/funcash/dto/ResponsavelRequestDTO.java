package br.com.insted.funcash.dto;


import org.springframework.web.multipart.MultipartFile;

import br.com.insted.funcash.models.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsavelRequestDTO {
    private String nome;
    private String email;
    private String cpf;
    private String dataDeNascimentoResponsavel;
    private Genero genero;
    private String senha;
    // private MultipartFile foto;
    private byte[] foto;

}
