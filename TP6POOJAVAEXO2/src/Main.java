import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DossierContact annuaire = new DossierContact("annuaire");

        boolean modifie = false;

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Rechercher un numéro de téléphone");
            System.out.println("2. Ajouter un nouveau contact");
            System.out.println("3. Supprimer un contact");
            System.out.println("4. Changer le numéro de téléphone d’un contact");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    System.out.print("Entrez le nom du contact : ");
                    String nomRecherche = scanner.nextLine();
                    String numero = annuaire.rechercherContact(nomRecherche);
                    if (numero != null) {
                        System.out.println("Numéro de " + nomRecherche + " : " + numero);
                    } else {
                        System.out.println("Contact non trouvé.");
                    }
                    break;

                case 2:
                    System.out.print("Entrez le nom du nouveau contact : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Entrez le numéro de téléphone : ");
                    String nouveauNumero = scanner.nextLine();
                    if (annuaire.ajouterContact(nouveauNom, nouveauNumero)) {
                        System.out.println("Contact ajouté avec succès.");
                        modifie = true;
                    } else {
                        System.out.println("Le contact existe déjà.");
                    }
                    break;

                case 3:
                    System.out.print("Entrez le nom du contact à supprimer : ");
                    String nomSupprime = scanner.nextLine();
                    if (annuaire.supprimerContact(nomSupprime)) {
                        System.out.println("Contact supprimé avec succès.");
                        modifie = true;
                    } else {
                        System.out.println("Contact non trouvé.");
                    }
                    break;

                case 4:
                    System.out.print("Entrez le nom du contact à modifier : ");
                    String nomAModifier = scanner.nextLine();
                    System.out.print("Entrez le nouveau numéro de téléphone : ");
                    String nouveauNum = scanner.nextLine();
                    if (annuaire.modifierContact(nomAModifier, nouveauNum)) {
                        System.out.println("Contact modifié avec succès.");
                        modifie = true;
                    } else {
                        System.out.println("Contact non trouvé.");
                    }
                    break;

                case 5:
                    if (modifie) {
                        annuaire.sauvegarderContacts();
                        System.out.println("Modifications sauvegardées.");
                    }
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}
