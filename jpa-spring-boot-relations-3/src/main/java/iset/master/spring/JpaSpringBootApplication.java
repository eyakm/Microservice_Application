package iset.master.spring;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import iset.master.spring.model.Categorie;
import iset.master.spring.model.Produit;
import iset.master.spring.model.Responsable;
import iset.master.spring.model.Stock;
import iset.master.spring.repository.CategorieRepository;
import iset.master.spring.repository.ProduitRepository;
import iset.master.spring.repository.ResponsableRepository;
import iset.master.spring.repository.StockRepository;
@SpringBootApplication
public class JpaSpringBootApplication {
	
	
		//Déclaration d'un repository pour gérer les stocks
			static StockRepository stockRepos;
			 //Déclaration d'un repository pour gérer les responsables
			static ResponsableRepository responsableRepos;
	
	public static void main(String[] args) {
		
		
		// référencer le contexte
		ApplicationContext contexte =
		SpringApplication.run(JpaSpringBootApplication.class, args);
		
		// Récupérer une implémentation de l'interface "StockRepository" par injection de dépendance
		stockRepos = contexte.getBean(StockRepository.class);
		// Récupérer une implémentation de l'interface "ResponsableRepository" par injection de dépendance
		responsableRepos = contexte.getBean(ResponsableRepository.class);
		Responsable r1 = new Responsable ("Ben Saleh", "Ali");
		Stock s1 = new Stock("1", "Tunis",r1);
		stockRepos.save(s1);
	
		Responsable r2 = new Responsable ("Ben Ahmed", "Omar");
		Stock s2 = new Stock("2", "Sousse",r2);
		stockRepos.save(s2);
		
		Responsable r3 = new Responsable ("Sallemi", "Samira");
		Stock s3 = new Stock("3", "Sfax",r3);
		stockRepos.save(s3);

		Responsable r4 = new Responsable ("Zouari", "Zied");
		//responsableRepos.save(r4);

		Stock s4 = new Stock("4", "Gabes",r4);
		stockRepos.save(s4);
		
		s1.setResponsable(null);
		stockRepos.save(s1);
		s4.setResponsable(r1);
		stockRepos.save(s4);
		
		s1.setResponsable(r4);
		stockRepos.save(s1);

		
		
		// Afficher la liste des responsables (chaque responsable avec le stock qui lui est associé s’il existe)
		System.out.println("***********************************************");
		System.out.println("Afficher tous les responsables avec leurs stocks");
		Collection <Responsable> lr = responsableRepos.findAll();
		for (Responsable r : lr)
		{
		System.out.println(r);
		}
		System.out.println("***************************************");
		
	}
	
	
	
	
	
}
