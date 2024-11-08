package iset.master.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import iset.master.spring.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
	@Query ("select p from Produit p where p.designation like %:x% ") 
	public List<Produit> findByDesignation(@Param ("x") String mc);
	

	
	@Query ("select p from Produit p where p.designation like :x and p.prix > :y")
	public List<Produit> findByDesignationAndPrix(@Param ("x") String mc, @Param ("y") double prix);
	
	
	
	@Query("update Produit p set p.designation =:designation where p.id = :id") 
	@Modifying 
	@Transactional 
	public int mettreAJourDesignation( @Param("designation")String designation, @Param("id") Long idProduit);
	List<Produit> findByPrixGreaterThan(double prixMin);
	
	
	public List<Produit>OrderByPrixAsc();
	public List<Produit> findAllByDateAchatAfter(java.util.Date dateX);
	//  les produits dont le prix ne dépassent pas 100 et la quantité minimal est 20
	public List<Produit> findAllByPrixLessThanAndQuantiteGreaterThan(double prix, int quantite);
	
	//les produits dont le prix ne dépassent pas 100, acheter après 2019 et la quantité minimal est 20
	public List<Produit> findAllByPrixLessThanAndQuantiteGreaterThanAndDateAchatAfter(double prix, int quantite, java.util.Date date2019);

	//les produits dans l'ordre ascendant selon le prix dont le prix ne dépassent pas 100, acheter après 2019 et la quantité minimal est 20
	public List<Produit> findAllByPrixLessThanAndQuantiteGreaterThanAndDateAchatAfterOrderByPrixAsc(double prix, int quantite, java.util.Date date2019);
	
	//les produits de l'année 2021 ayant comme désignation une valeur qui commence par la lettre "a" dans un ordre descendant selon le prix
	public List<Produit> findAllByDateAchatBetweenAndDesignationStartingWithOrderByPrixDesc(java.util.Date datedebut, java.util.Date datefin, String lettre );

}











