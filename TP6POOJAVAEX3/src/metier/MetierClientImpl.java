package metier;

import model.Client;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MetierClientImpl implements IMetier<Client> {
    private List<Client> clients;
    private final String filename;

    public MetierClientImpl(String filename) {
        this.filename = filename;
        this.clients = new ArrayList<>();
        loadClients();
    }

    private void loadClients() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            clients = (List<Client>) ois.readObject();
        } catch (FileNotFoundException e) {
            clients = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client add(Client client) {
        clients.add(client);
        return client;
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(clients);
    }

    @Override
    public Client findByNom(String nom) {
        return clients.stream()
                .filter(c -> c.getLastName().equals(nom))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(String nom) {
        clients = clients.stream()
                .filter(c -> !c.getLastName().equals(nom))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(clients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}