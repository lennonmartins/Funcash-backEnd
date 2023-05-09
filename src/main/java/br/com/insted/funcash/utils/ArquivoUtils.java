package br.com.insted.funcash.utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;
public class ArquivoUtils {
    public static byte[] abrirArquivo(String caminhoDoArquivo) throws IOException {
        return Files.readAllBytes(Paths.get(caminhoDoArquivo));
    }

    public static byte[] converterfoto(MultipartFile foto) throws IOException{
        byte[] conteudoFoto = foto.getBytes();
          return conteudoFoto;
        }
    
}
