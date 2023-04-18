package br.com.insted.funcash.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArquivoUtils {
    public static byte[] abrirArquivo(String caminho) throws IOException {
        return Files.readAllBytes(Paths.get(caminho));
    }
    
}
