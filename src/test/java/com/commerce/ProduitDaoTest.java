package com.commerce;

import com.commerce.entities.Categorie;
import com.commerce.entities.Produit;
import com.commerce.service.CrudService;
import com.commerce.service.ProduitService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProduitDaoTest {

    private static CrudService<Categorie, Long> catService;
    private static ProduitService prodService;

    @BeforeClass
    public static void init() {
        catService = new CrudService<>(Categorie.class);
        prodService = new ProduitService();
    }

    @Test
    public void testCreateProduitAvecCategorie() {
        String nomCat = "Papeterie_" + System.currentTimeMillis();
        Categorie cat = new Categorie(nomCat);
        catService.create(cat);

        Produit p = new Produit("Stylo a bille", new BigDecimal("1.50"), cat);
        prodService.create(p);

        assertNotNull(p.getId());
        Produit fetched = prodService.getById(p.getId());
        assertNotNull(fetched);
        assertEquals(nomCat, fetched.getCategorie().getNom());
    }
}
