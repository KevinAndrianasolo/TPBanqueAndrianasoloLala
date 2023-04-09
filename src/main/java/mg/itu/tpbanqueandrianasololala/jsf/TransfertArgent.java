/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tpbanqueandrianasololala.ejb.GestionnaireCompte;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;

/**
 *
 * @author kevin
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    private Long idSource;
    private Long idDestinataire;
    private int montant;
    @EJB
    private GestionnaireCompte gc;
    
    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }
    
    public String effectuerTransfert(){
        CompteBancaire compteSource = this.gc.find(idSource);
        CompteBancaire compteDestinataire = this.gc.find(idDestinataire);
        boolean erreur = false;
        if (compteSource.getId() == compteDestinataire.getId()) {
            Util.messageErreur("Vous ne pouvez pas transférer de l'argent à vous même!", "Vous ne pouvez pas transférer de l'argent à vous même!");
            erreur = true;
        }
        if (compteSource.getSolde() < this.getMontant()) { // à compléter pour le cas où le solde du compte source est insuffisant...
            Util.messageErreur("Solde insuffisant, solde restant : "+compteSource.getSolde(), "Solde insuffisant, solde restant : "+compteSource.getSolde());
            erreur = true;
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        boolean ok = this.gc.transferer(compteSource, compteDestinataire, this.montant);
        if(!ok) return null;
        
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes?faces-redirect=true";
    }
    
}
