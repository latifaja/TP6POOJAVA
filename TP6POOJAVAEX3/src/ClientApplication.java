import metier.MetierClientImpl;
import model.Client;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        MetierClientImpl metier = new MetierClientImpl("clients.dat");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Système de gestion des clients ===");
            System.out.println("1. Afficher la liste des clients");
            System.out.println("2. Rechercher un client par son nom");
            System.out.println("3. Ajouter un nouveau client");
            System.out.println("4. Supprimer un client par nom");
            System.out.println("5. Sauvegarder les clients");
            System.out.println("6. Quitter");
            System.out.print("Choisir une option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (metier.getAll().isEmpty()) {
                        System.out.println("Aucun client trouvé.");
                    } else {
                        metier.getAll().forEach(System.out::println);
                    }
                    break;

                case 2:
                    System.out.print("Nom du client à rechercher: ");
                    String searchName = scanner.nextLine();
                    Client found = metier.findByNom(searchName);
                    if (found != null) {
                        System.out.println("client trouvé: " + found);
                    } else {
                        System.out.println("Client non trouvé");
                    }
                    break;

                case 3:
                    System.out.print("Entrer le nom: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Entrer le prenom: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Entrer l'address: ");
                    String address = scanner.nextLine();
                    System.out.print("Entrer le numero du téléphone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Entrer l'email: ");
                    String email = scanner.nextLine();
                    
                    Client newClient = new Client(firstName, lastName, address, phone, email);
                    metier.add(newClient);
                    System.out.println("Client ajouter avec succus!");
                    break;

                case 4:
                    System.out.print("Entrer le nom du client à supprimer: ");
                    String deleteName = scanner.nextLine();
                    metier.delete(deleteName);
                    System.out.println("Client(s) supprimé(s)!");
                    break;

                case 5:
                    metier.saveAll();
                    System.out.println("Clients enregistrer avec succes");
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("optin invalid");
            }
        }
        scanner.close();
    }
}