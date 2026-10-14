package com.commerce;

import com.commerce.entities.*;
import com.commerce.service.CrudService;
import com.commerce.service.ProduitService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {
        CrudService<Categorie, Long> catService = new CrudService<>(Categorie.class);
        ProduitService prodService = new ProduitService();
        CrudService<User, Long> userService = new CrudService<>(User.class);
        CrudService<Commande, Long> cmdService = new CrudService<>(Commande.class);

        long timestamp = System.currentTimeMillis() % 100000;
        Categorie informatique = new Categorie("Informatique_" + timestamp);
        catService.create(informatique);

        Categorie bureautique = new Categorie("Bureautique_" + timestamp);
        catService.create(bureautique);

        Produit p1 = new Produit("Clavier mecanique", new BigDecimal("79.99"), informatique);
        Produit p2 = new Produit("Souris sans fil", new BigDecimal("29.50"), informatique);
        Produit p3 = new Produit("Chaise de bureau", new BigDecimal("149.00"), bureautique);

        prodService.create(p1);
        prodService.create(p2);
        prodService.create(p3);

        System.out.println("Categorie creee : " + informatique.getNom() + " (ID: " + informatique.getId() + ")");
        System.out.println("Produits crees : " + p1.getId() + ", " + p2.getId() + ", " + p3.getId());

        Client client = new Client("Karim Benzema", LocalDate.now());
        userService.create(client);

        Fournisseur fournisseur = new Fournisseur("TechDistrib", "12 rue de la Paix, Paris");
        userService.create(fournisseur);

        System.out.println("Client cree : " + client.getNom() + " (ID: " + client.getId() + ")");
        System.out.println("Fournisseur cree : " + fournisseur.getNom() + " (ID: " + fournisseur.getId() + ")");

        Commande cmd = new Commande(LocalDate.now());
        cmdService.create(cmd);

        cmd.addProduit(p1, 2);
        cmd.addProduit(p2, 3);
        cmdService.update(cmd);

        System.out.println("Commande creee : ID = " + cmd.getId() + " avec " + cmd.getLignes().size() + " lignes");

        System.out.println("\n--- Test NamedQuery (Produits de la categorie Informatique) ---");
        List<Produit> prodsInfo = prodService.findByCategorie(informatique.getId());
        for (Produit p : prodsInfo) {
            System.out.println("- " + p.getLibelle() + " (" + p.getPrix() + " EUR)");
        }

        System.out.println("\n--- Test Criteria API (prix entre 20 et 100 EUR) ---");
        List<Produit> searchResults = prodService.search(null, new BigDecimal("20.00"), new BigDecimal("100.00"));
        for (Produit p : searchResults) {
            System.out.println("- " + p.getLibelle() + " (" + p.getPrix() + " EUR)");
        }

        System.out.println("\n--- Test Pagination (page 1, taille 2) ---");
        List<Produit> page1 = prodService.findPage(1, 2);
        for (Produit p : page1) {
            System.out.println("- " + p.getLibelle());
        }

        com.commerce.dao.GenericDAO.closeEntityManagerFactory();
        try {
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Exception ignored) {
        }
    }
}
