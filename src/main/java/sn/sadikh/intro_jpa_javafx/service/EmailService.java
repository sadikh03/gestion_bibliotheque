package sn.sadikh.intro_jpa_javafx.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import sn.sadikh.intro_jpa_javafx.Model.Emprunt;

import java.util.Properties;
import java.io.File;

public class EmailService {

    public void envoyerFicheEmail(Emprunt emprunt, String cheminPdf) {
        // Configuration du serveur SMTP (Exemple avec Gmail)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        final String username = "ton-email@gmail.com"; // Ton email
        final String password = "votre-mot-de-passe-application"; // Ton mot de passe d'application

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emprunt.getEtudiant().getEmail()));

            // Sujet personnalisé comme demandé
            message.setSubject("Fiche d'emprunt : " + emprunt.getLivre().getTitre());

            // Corps du message personnalisé
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String contenu = "Bonjour " + emprunt.getEtudiant().getPrenom() + " " + emprunt.getEtudiant().getNom() + ",\n\n"
                    + "Veuillez trouver en pièce jointe votre fiche pour l'emprunt du livre : "
                    + emprunt.getLivre().getTitre() + ".\n"
                    + "La date de retour prévue est le : " + emprunt.getDateRetourPrevue() + ".\n\n"
                    + "Cordialement,\nLa Bibliothèque.";
            messageBodyPart.setText(contenu);

            // Pièce jointe (le PDF)
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(cheminPdf));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Envoi asynchrone pour ne pas bloquer l'interface JavaFX
            new Thread(() -> {
                try {
                    Transport.send(message);
                    System.out.println("E-mail envoyé avec succès !");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}