package metier;

import model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MetierProduitImpl implements IMetier<Product> {
    private List<Product> products;
    private final String filename;

    public MetierProduitImpl(String filename) {
        this.filename = filename;
        this.products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            products = (List<Product>) ois.readObject();
        } catch (FileNotFoundException e) {
            products = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product add(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Product findByNom(String nom) {
        return products.stream()
                .filter(p -> p.getName().equals(nom))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(String nom) {
        products = products.stream()
                .filter(p -> !p.getName().equals(nom))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}