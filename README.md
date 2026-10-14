# TP JPA Hibernate : Gestion Commerciale

Projet de gestion commerciale avec JPA / Hibernate et MySQL :
- Mapping d'entites avec relations (ManyToOne, OneToMany)
- Heritage JPA avec la strategie JOINED (User, Client, Fournisseur)
- Relation ManyToMany avec attributs et cle composite (Commande, CommandeProduit, CommandeProduitPK)
- DAO generique et couche service (CrudService, ProduitService)
- Named Queries, recherche dynamique Criteria API et pagination JPQL

## Execution
```bash
mvn clean compile exec:java
```
