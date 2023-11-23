package br.com.insted.funcash.services;

import javax.mail.event.MailEvent;
import javax.validation.constraints.Email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.insted.funcash.dtos.UsuarioResponseDto;
import br.com.insted.funcash.models.user.Usuario;
import br.com.insted.funcash.seguranca.TokenService;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public UsuarioResponseDto login(String email, String senha) throws EmailException {

        var usernamePassword = new UsernamePasswordAuthenticationToken(email, senha);
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        var userDetails = (Usuario) auth.getPrincipal();

        var idDaPessoa = obterIdDaPessoaValida(userDetails);

        var usuarioResponse = new UsuarioResponseDto(userDetails.getId(),
                idDaPessoa,
                userDetails.getRole(),
                token);

        // if (usuarioResponse != null) {
        // org.apache.commons.mail.Email objetoEmail = new SimpleEmail();
        // objetoEmail.setHostName("smtp.gmail.com");
        // objetoEmail.getSmtpPort();
        // objetoEmail.setAuthenticator(new DefaultAuthenticator(
        // "funcash.edu@gmail.com", "funcash123"));
        // objetoEmail.setSSLOnConnect(true);
        // //objetoEmail.setStartTLSRequired(true);
        // objetoEmail.setFrom("no-reply@funcash.com");
        // objetoEmail.addTo("lennonmcarlos@gmail.com");
        // objetoEmail.setSubject("Teste");
        // objetoEmail.setMsg("Entrei?");
        // objetoEmail.send();
        // }

        return usuarioResponse;
    }

    private Long obterIdDaPessoaValida(Usuario userDetails) {
        if (userDetails.getCrianca() == null)
            return userDetails.getResponsavel().getId();
        else {
            return userDetails.getCrianca().getId();
        }
    }

    // public String sendEmail(Projeto projeto,String to) {
    // String from = "'Aprovação Digital
    // (Não-Responda)'<aprovacaodigital.semadur@gmail.com>";
    // String host = "smtp.googlemail.com";

    // // String from = "nao-responda@capital.ms.gov.br";
    // // String host = "smtp.capital.ms.gov.br";
    // // to = "willianyamaura@hotmail.com";

    // Properties properties = System.getProperties();
    // properties.setProperty("mail.smtp.host", host);
    // properties.setProperty("mail.smtp.auth", "true");
    // properties.setProperty("mail.smtp.starttls.enable","true");
    // Session session = Session.getDefaultInstance(properties, new Autenticacao());
    // try{
    // MimeMessage message = new MimeMessage(session);
    // message.setFrom(new InternetAddress(from));
    // message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
    // message.setSubject("Aviso do Sistema de Aprovação de Projetos On-line -
    // PMCG", "UTF-8");
    // String body = "<h1>Aviso de Encaminhamento</h1>"+
    // "<p><b>Seu projeto arquitetônico, se encontra em sua caixa de entrada do
    // sistema de aprovação.</p>"+
    // "<p>O profissional pode consultar o projeto encaminhado a qualquer momento,
    // em qualquer lugar, visitando <a
    // href=\"http://capital.ms.gov.br/aprovacaodigital\">http://capital.ms.gov.br/aprovacaodigital</a>
    // e utilizando o seu login.</p>"+
    // "<p>PROJETO: " + projeto.getProcesso() + "</p>" +
    // "<p>ÚLTIMA TRAMITAÇÃO EM: " + formatterdate.format(projeto.getDataulttram())
    // + "</p>" +
    // "<p><i>Lembrando que, conforme artigo 35, " +
    // "parágrafo 2º da Lei 1866/79 (Código de Obras) - " +
    // "Não sendo atendidas as exigências no prazo de 60 " +
    // "(sessenta) dias, a contar da data do último trâmite de exigência, o processo
    // será INDEFERIDO.</i></p><br/><br/>"+
    // "Atenciosamente,<br/>"+
    // "Secretaria Municipal do Meio Ambiente e de Desenvolvimento Urbano -
    // SEMADUR"+
    // "<br/><br/>"+
    // "----------"+
    // "<h4>Atenção: Esta é uma mensagem automática. Favor não responder.</h4>";

    // Multipart mp = new MimeMultipart();
    // MimeBodyPart mbp = new MimeBodyPart();
    // mbp.setContent(body, "text/html;charset=utf-8");
    // mp.addBodyPart(mbp);
    // message.setContent(mp);
    // Transport.send(message);
    // } catch (MessagingException mex) {
    // mex.printStackTrace();
    // }
    // return null;
    // }

}
