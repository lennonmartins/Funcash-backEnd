package br.com.insted.funcash.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ResponsavelRespositoryTest {

    @Autowired
    ResponsavelRepository responsavelRepository;

    @BeforeEach
    @AfterEach
    void setUp() {
        responsavelRepository.deleteAll();
    }

    // @Test
    // void deve_buscar_um_responsavel_por_email_e_senha() throws Exception{
    //     String emailEsperado = "teste@gmail.com";
    //     String senhaEsperada = "adm123";
    //     Responsavel responsavel = 
    //             new ResponsavelBuilder()
    //                     .comEmail(emailEsperado)
    //                     .comSenha(senhaEsperada) 
    //                     .construir();

    //     responsavelRepository.save(responsavel);

    //     Responsavel responsavelRetornado = 
    //     (responsavelRepository.encontrarPorEmailESenha(emailEsperado, senhaEsperada)).get();

    //     Assertions.assertThat(responsavel.getUsuario().getEmail()).isEqualTo(responsavelRetornado.getUsuario().getEmail());
    // }
}
