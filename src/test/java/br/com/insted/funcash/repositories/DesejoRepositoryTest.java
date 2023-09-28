package br.com.insted.funcash.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.insted.funcash.builders.DesejoBuilder;
import br.com.insted.funcash.models.Desejo;

@DataJpaTest
public class DesejoRepositoryTest {
    
    @Autowired
    private DesejoRepository desejoRepository;

    @BeforeEach
    void setUp(){
        desejoRepository.deleteAll();
    }

    @Test 
    void deve_registrar_um_desejo_no_banco_com_id() throws Exception{
        Desejo desejo = new DesejoBuilder().construir();

        desejoRepository.save(desejo);

        Assertions.assertThat(desejo.getId()).isNotNull();
    }
}
