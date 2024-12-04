import java.io.*;
import java.util.*;

public class DossierContact {
    private Map<String, String> contacts;
    private File dossier;

    public DossierContact(String cheminDossier) {
        contacts = new HashMap<>();
        dossier = new File(cheminDossier);

        if (!dossier.exists()) {
            dossier.mkdirs();
        } else {
            chargerContacts();
        }
    }

    private void chargerContacts() {
        File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".txt"));
        if (fichiers != null) {
            for (File fichier : fichiers) {
                try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
                    String nom = fichier.getName().replace(".txt", "");
                    String numero = br.readLine();
                    if (numero != null) {
                        contacts.put(nom, numero);
                    }
                } catch (IOException e) {
                    System.err.println("Erreur de lecture du fichier : " + fichier.getName());
                }
            }
        }
    }

    public String rechercherContact(String nom) {
        return contacts.getOrDefault(nom, null);
    }

    public boolean ajouterContact(String nom, String numero) {
        if (contacts.containsKey(nom)) {
            return false;
        }
        contacts.put(nom, numero);

        // Créer un fichier pour le nouveau contact
        File fichier = new File(dossier, nom + ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))) {
            bw.write(numero);
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier pour le contact : " + nom);
            return false;
        }
        return true;
    }

    public boolean modifierContact(String nom, String nouveauNumero) {
        if (contacts.containsKey(nom)) {
            contacts.put(nom, nouveauNumero);

            // Mettre à jour le fichier correspondant
            File fichier = new File(dossier, nom + ".txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))) {
                bw.write(nouveauNumero);
            } catch (IOException e) {
                System.err.println("Erreur lors de la mise à jour du fichier pour le contact : " + nom);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean supprimerContact(String nom) {
        if (contacts.containsKey(nom)) {
            contacts.remove(nom);

            // Supprimer le fichier correspondant
            File fichier = new File(dossier, nom + ".txt");
            if (fichier.exists() && !fichier.delete()) {
                System.err.println("Erreur lors de la suppression du fichier pour le contact : " + nom);
                return false;
            }
            return true;
        }
        return false;
    }

    public void sauvegarderContacts() {
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            File fichier = new File(dossier, entry.getKey() + ".txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))) {
                bw.write(entry.getValue());
            } catch (IOException e) {
                System.err.println("Erreur d'écriture dans le fichier : " + fichier.getName());
            }
        }
    }
}
