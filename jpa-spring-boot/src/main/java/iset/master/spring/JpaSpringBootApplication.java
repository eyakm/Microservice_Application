package iset.master.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext; 
import iset.master.spring.model.Produit; 
import iset.master.spring.repository.ProduitRepository;

@SpringBootApplication
public class JpaSpringBootApplication {

	public static void main(String[] args) {
		// référencer le contexte 
		ApplicationContext contexte = SpringApplication.run(JpaSpringBootApplication.class, args);
		String lettre = "a";
		
		// Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance 
		ProduitRepository produitRepos = contexte.getBean(ProduitRepository.class); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateX = null;
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		java.util.Date date3 = null;
		java.util.Date date4 = null;
		java.util.Date date2019 = null;
		java.util.Date datedebut = null;
		java.util.Date datefin = null;
		

		
		try {
			dateX = sdf.parse("2020-03-15");
			date1 = sdf.parse("2020-04-15");
			date2 = sdf.parse("2020-02-15");
			date3 = sdf.parse("2020-05-15");
			date4 = sdf.parse("2021-05-15");
			date2019 = sdf.parse("2019-12-31");
			datedebut = sdf.parse("2020-12-31");
			datefin = sdf.parse("2021-12-31");
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		// Insérer 3 produits
		produitRepos.save(new Produit("Yaourt", 0.400, 20, date1)); 
		produitRepos.save(new Produit("Farine", 1.200, 30, date2)); 
		produitRepos.save(new Produit("Chocolat", 2000.0, 5, date3)); 
		produitRepos.save(new Produit ("marshmellow", 2500, 100, date4));
		// Lister l'ensemble des produits 
		
		List<Produit> lp = produitRepos.findAll(); 
		System.out.println("******Liste des produits****"); 
		for (Produit p : lp)
		{
			 System.out.print("Designation:"+ p.getDesignation()+" , ");
			 System.out.println("Prix:"+ p.getPrix());
			
		}
		
		 
		System.out.println("-----------------------");
		System.out.println("******Liste des produits dont la désignation contient 'h' et prix 0 ****"); 
		List<Produit> lp1 = produitRepos.findByDesignation("h"); 
		for (Produit p : lp1)
		{
			System.out.print("Designation:"+ p.getDesignation()+" , ");
			System.out.println("Prix:"+ p.getPrix());
		}
		System.out.println("-----------------------");
		System.out.println("****Liste des produits dont la désignation contient 'h' et leur prix *****");
		List<Produit> lp2 = produitRepos.findByDesignationAndPrix("%h%", 10.2);
		for (Produit p : lp2) {
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix());
			
		}
		
		 
		System.out.println("-----------------------");
		//Mette à jour la désignation (Farine → Pain) 
		produitRepos.mettreAJourDesignation("Pain", 2L); 
		//Afficher le produit modifié s'il est présent 
		Produit pm= produitRepos.findById(2L).get(); 
		if (pm!=null)
			
			{
			System.out.println("Désignation:"+pm.getDesignation());
			}
		else
			{
			System.out.println("Produit non existant..."); 
			}
		
		
		// Lister tous les produits ayant un prix > 1.5 en utilisant une méthode requête " 
		System.out.println("******Liste des produits ayant un prix > 1.5 en utilisant une méthode requête ****"); 
		List<Produit> lp4 = produitRepos.findByPrixGreaterThan(1.5); 
		for (Produit p : lp4) 
			{
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix());
			} 
		System.out.println("-----------------------");
		
		
		
		System.out.println("****** Liste de tous les produits en ordre croissant selon le prix ****"); 
		List<Produit> lp5 = produitRepos.OrderByPrixAsc(); 
		for (Produit p : lp5) 
			{
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix());
			} 
		System.out.println("-----------------------");
		
		System.out.println("****** Liste des produits dont la date d’achat est supérieure à « 2020-03-15 » ****"); 
		List<Produit> lp6 = produitRepos.findAllByDateAchatAfter(dateX); 
		for (Produit p : lp6) 
			{
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix()+ " , ");
			System.out.println("Date Achat" + p.getDateAchat());
			} 
		System.out.println("-----------------------");
		
		
		System.out.println("****** Liste des produits dont le prix ne dépassent pas 100 et la quantité minimal est 20 ****"); 
		List<Produit> lp7 = produitRepos.findAllByPrixLessThanAndQuantiteGreaterThan(1000.0,20); 
		for (Produit p : lp7) 
			{
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix()+ " , ");
			System.out.println("Date Achat" + p.getDateAchat());
			} 
		System.out.println("-----------------------");
		
		System.out.println("****** Liste des produits dont le prix ne dépassent pas 100, acheter après 2019 et la quantité minimal est 20 ****");
		List<Produit> lp8 = produitRepos.findAllByPrixLessThanAndQuantiteGreaterThanAndDateAchatAfter(1000.0, 20, date2019); 
		for (Produit p : lp8) 
			{
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix()+ " , ");
			System.out.println("Date Achat" + p.getDateAchat());
			} 
		System.out.println("-----------------------");
		
		
		
		System.out.println("****** Liste des produits dans l'ordre ascendant selon le prix dont le prix ne dépassent pas 100, acheter après 2019 et la quantité minimal est 20 ****");
		List<Produit> lp9 = produitRepos.findAllByPrixLessThanAndQuantiteGreaterThanAndDateAchatAfterOrderByPrixAsc(1000.0, 20, date2019); 
		for (Produit p : lp9) 
			{
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix()+ " , ");
			System.out.println("Date Achat" + p.getDateAchat());
			} 
		System.out.println("-----------------------");
		
	
		System.out.println("****** Liste des produits de l'année 2021 dans l'ordre descendant selon le prix et sa désignation commence par a  ****");
		List<Produit> lp10 = produitRepos.findAllByDateAchatBetweenAndDesignationStartingWithOrderByPrixDesc(datedebut, datefin, lettre); 
		for (Produit p : lp10) 
			{
			System.out.print("Designation:" + p.getDesignation() + " , ");
			System.out.println("Prix:" + p.getPrix()+ " , ");
			System.out.println("Date Achat" + p.getDateAchat());
			} 
		System.out.println("-----------------------");
		
		

		
		
		
		
		
		
	}
		

}
