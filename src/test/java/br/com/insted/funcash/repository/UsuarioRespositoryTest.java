package br.com.insted.funcash.repository;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.insted.funcash.builders.ResponsavelBuilder;
import br.com.insted.funcash.models.Responsavel;
import br.com.insted.funcash.models.Usuario;

@DataJpaTest
public class UsuarioRespositoryTest {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private ResponsavelRepository responsavelRepository;

    @BeforeEach
    @AfterEach
    void setUp(){
        usuarioRepository.deleteAll();
    }

    @Test
    void deve_retornar_um_usuario_ao_passar_email_e_senha() throws Exception{
      String emailEsperado = "teste@gmail.com";
      String senhaEsperada = "adm123";
      Usuario usuario = new Usuario(emailEsperado, senhaEsperada);
      
      Responsavel responsavel = 
      new ResponsavelBuilder()
      .comUsuario(usuario) 
      .construir();
      // when(responsavelRepository.save(responsavel)).thenReturn(responsavel);
      responsavelRepository.save(responsavel);
        Responsavel responsavelRetornadoNome = (responsavelRepository.findByNomeContainingIgnoreCase(responsavel.getNome())).get(0);
        Usuario responsavelRetornado = 
        (usuarioRepository.obterPorEmailESenha(emailEsperado, senhaEsperada)).get();

        Assertions.assertThat(responsavel.getUsuario().getEmail()).isEqualTo(responsavelRetornado.getEmail());  
    }
}
