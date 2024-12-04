import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Entrez le chemin du répertoire :");
        String chemin = scanner.nextLine();
        scanner.close();

        // Créer un objet File pour le répertoire donné
        File repertoire = new File(chemin);

        // Vérifier si le chemin est valide
        if (repertoire.exists() && repertoire.isDirectory()) {
            // Lister les fichiers et répertoires de manière récursive
            listerContenu(repertoire, "");
        } else {
            System.out.println("Le chemin spécifié n'est pas un répertoire valide !");
        }
    }

    public static void listerContenu(File fichierOuRepertoire, String indent) {
        // Vérifier si c'est un répertoire
        if (fichierOuRepertoire.isDirectory()) {
            System.out.println(indent + fichierOuRepertoire.getPath() + " <DIR> " + getModes(fichierOuRepertoire));
            // Lister les contenus du répertoire
            File[] fichiers = fichierOuRepertoire.listFiles();
            if (fichiers != null) {
                for (File fichier : fichiers) {
                    listerContenu(fichier, indent + "    ");
                }
            }
        } else if (fichierOuRepertoire.isFile()) {
            // Afficher les détails pour un fichier
            System.out.println(indent + fichierOuRepertoire.getPath() + " <FILE> " + getModes(fichierOuRepertoire));
        }
    }

    public static String getModes(File fichier) {
        StringBuilder modes = new StringBuilder();
        // Vérifier les permissions
        modes.append(fichier.canRead() ? "r" : "-");
        modes.append(fichier.canWrite() ? "w" : "-");
        modes.append(fichier.isHidden() ? "h" : "-");
        return modes.toString();
    }
}
