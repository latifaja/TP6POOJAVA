import metier.MetierProduitImpl;
import model.Product;
import java.util.Scanner;

public class ProductApplication {
    public static void main(String[] args) {
        MetierProduitImpl metier = new MetierProduitImpl("products.dat");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Système de gestion des produits ===");
            System.out.println("1. Afficher tous les produits");
            System.out.println("2. Rechercher un produit par nom");
            System.out.println("3. Ajouter un nouveau produit");
            System.out.println("4. Supprimer un produit par nom");
            System.out.println("5. Enregistrer les produits");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne

            switch (choix) {
                case 1:
                    if (metier.getAll().isEmpty()) {
                        System.out.println("Aucun produit trouvé.");
                    } else {
                        metier.getAll().forEach(System.out::println);
                    }
                    break;

                case 2:
                    System.out.print("Entrez le nom du produit : ");
                    String nomRecherche = scanner.nextLine();
                    Product trouve = metier.findByNom(nomRecherche);
                    if (trouve != null) {
                        System.out.println("Produit trouvé : " + trouve);
                    } else {
                        System.out.println("Produit non trouvé");
                    }
                    break;

                case 3:
                    System.out.print("Entrez le nom du produit : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la marque : ");
                    String marque = scanner.nextLine();
                    System.out.print("Entrez le prix : ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Entrez la description : ");
                    String description = scanner.nextLine();
                    System.out.print("Entrez la quantité en stock : ");
                    int stock = scanner.nextInt();

                    Product nouveauProduit = new Product(nom, marque, prix, description, stock);
                    metier.add(nouveauProduit);
                    System.out.println("Produit ajouté avec succès");
                    break;

                case 4:
                    System.out.print("Entrez le nom du produit à supprimer : ");
                    String nomSuppression = scanner.nextLine();
                    metier.delete(nomSuppression);
                    System.out.println("Produit(s) supprimé(s) avec succès");
                    break;

                case 5:
                    metier.saveAll();
                    System.out.println("Produits enregistrés avec succès");
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("Option invalide");
            }
        }
        scanner.close();
    }
}
