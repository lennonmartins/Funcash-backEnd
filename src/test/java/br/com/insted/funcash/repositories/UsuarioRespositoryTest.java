package br.com.insted.funcash.repositories;

import static org.mockito.ArgumentMatchers.any;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.insted.funcash.builders.ResponsavelBuilder;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.user.Usuario;

@DataJpaTest
public class UsuarioRespositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository = Mockito.mock(ResponsavelRepository.class);

    @BeforeEach
    @AfterEach
    void setUp() {
        usuarioRepository.deleteAll();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deve_retornar_um_usuario_ao_passar_email_e_senha() throws Exception {
        String emailEsperado = "teste@gmail.com";
        String senhaEsperada = "adm123";
        Usuario usuario = new Usuario(emailEsperado, senhaEsperada);

        Responsavel responsavel = new ResponsavelBuilder()
                .comUsuario(usuario)
                .construir();

        responsavelRepository.save(responsavel);
        //when(responsavelRepository.save(any(Responsavel.class))).thenReturn(responsavel);

        Responsavel responsavelRetornado = responsavelRepository.findById(usuarioRepository.obterPorEmailESenha(emailEsperado, senhaEsperada).get().getId()).get();

        Assertions.assertThat(responsavel.getUsuario().getEmail()).isEqualTo(responsavelRetornado.getUsuario().getEmail());
    }
}
