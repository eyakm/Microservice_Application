package org.ms.factureservice.entities;
import org.ms.factureservice.model.Produit;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//import org.ms.factureservice.model.Produit;
//import javax.persistence.*;
@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor @ToString
public class FactureLigne {
 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)
 private Long id;
 private Long produitID;
 private long quantity;
 private double price;
 @Transient
 private Produit produit;
 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
 @ManyToOne
 private Facture facture;

}