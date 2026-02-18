package sn.sadikh.intro_jpa_javafx.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import sn.sadikh.intro_jpa_javafx.Model.Emprunt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PdfService {
    public String genererFicheEmprunt(Emprunt emprunt) {
        // Nom du fichier : Fiche_Matricule_ID.pdf
        String dest = "Fiche_" + emprunt.getEtudiant().getMatricule() + "_" + emprunt.getId() + ".pdf";

        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // En-tête (Nom de la bibliothèque)
            Paragraph header = new Paragraph("BIBLIOTHÈQUE UNIVERSITAIRE")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(20);
            document.add(header);

            document.add(new Paragraph("\n")); // Espace

            // Contenu de la fiche
            document.add(new Paragraph("FICHE D'EMPRUNT").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("ÉDITÉ LE : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
            document.add(new Paragraph("--------------------------------------------------"));

            // Infos Étudiant
            document.add(new Paragraph("ÉTUDIANT : " + emprunt.getEtudiant().getPrenom() + " " + emprunt.getEtudiant().getNom()));
            document.add(new Paragraph("MATRICULE : " + emprunt.getEtudiant().getMatricule()));

            document.add(new Paragraph("\n"));

            // Infos Livre
            document.add(new Paragraph("LIVRE EMPRUNTÉ : " + emprunt.getLivre().getTitre()));
            document.add(new Paragraph("ISBN : " + emprunt.getLivre().getIsbn()));

            document.add(new Paragraph("\n"));

            // Dates
            document.add(new Paragraph("DATE D'EMPRUNT : " + emprunt.getDateEmprunt()));
            document.add(new Paragraph("DATE DE RETOUR PRÉVUE : " + emprunt.getDateRetourPrevue()).setBold());

            document.close();
            System.out.println("PDF généré : " + dest);
            return dest; // On retourne le chemin pour l'envoi d'email

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
