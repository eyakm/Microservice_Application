package org.ms.factureservice.web;
import java.util.Hashtable;
import java.util.Map;

import org.ms.factureservice.entities.Facture;
import org.ms.factureservice.feign.ClientServiceClient;
import org.ms.factureservice.feign.ProduitServiceClient;
import org.ms.factureservice.model.Client;
import org.ms.factureservice.model.Produit;
import org.ms.factureservice.repository.FactureLigneRepository;
import org.ms.factureservice.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FactureRestController {
 private FactureRepository factureRepository;
 private FactureLigneRepository factureLigneRepository;
 private ClientServiceClient clientServiceClient;
 private ProduitServiceClient produitServiceClient;
 public FactureRestController(FactureRepository factureRepository,
 FactureLigneRepository
factureLigneRepository,
 ClientServiceClient clientServiceClient,
 ProduitServiceClient produitServiceClient) {
 this.factureRepository = factureRepository;
 this.factureLigneRepository = factureLigneRepository;
 this.clientServiceClient = clientServiceClient;
 this.produitServiceClient = produitServiceClient;
 }
 /*
 @GetMapping (path="/full-facture/{id}")
 public Facture getFacture(@PathVariable(name = "id") Long id)
 {
 Facture facture= factureRepository.findById(id).get();
 Client client =
clientServiceClient.findClientById(facture.getClientID());
 facture.setClient(client);
 facture.getFacturekignes().forEach(fl-> {
 Produit product
=produitServiceClient.findProductById(fl.getProduitID());
 fl.setProduit(product);
 });
 return facture;
 }
 */
 @Value("${globalParam}")
 private int globalParam;
 @Value("${monFacture}")
 private int monFacture;
 @Value("${factureClientEmail}")
 private String factureClientEmail;

 @GetMapping("config")
 public Map<String,Object> config () {
 Map<String,Object> params = new Hashtable<>();
 params.put("globalParam", globalParam);
 params.put("facture", monFacture);
 params.put("email", factureClientEmail);
 //params.put("threadName", Thread.currentThread().toString());
 return params;
 }
}