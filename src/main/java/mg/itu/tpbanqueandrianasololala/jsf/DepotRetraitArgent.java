/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.jsf;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import java.io.Serializable;
import mg.itu.tpbanqueandrianasololala.ejb.GestionnaireCompte;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;

/**
 *
 * @author kevin
 */
@Named(value = "depotRetraitArgent")
@ViewScoped
public class DepotRetraitArgent implements Serializable {

    @EJB
    private GestionnaireCompte gc;

    private Long idCompte;
    private CompteBancaire compte;
    private int montant;

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    /**
     * Creates a new instance of DepotRetraitArgent
     */
    public DepotRetraitArgent() {
    }

    public void loadCompteBancaire() {
        this.compte = this.gc.find(this.getIdCompte());
    }

    public String update() {
        try {
            CompteBancaire compte = this.getCompte();
            boolean erreur = false;
            if (this.getMontant() < 0) {
                compte.retirer(this.getMontant() * -1);
            } else {
                compte.deposer(this.getMontant());
            }

            compte = this.gc.update(compte);
            if (erreur) { // en cas d'erreur, rester sur la même page
                return null;
            }
            Util.addFlashInfoMessage("Dépot/Retrait effectué");

            return "listeComptes?faces-redirect=true";
        } catch (EJBException ex) {
            Throwable cause = ex.getCause();
            if (cause != null) {
                if (cause instanceof OptimisticLockException) {
                    Util.messageErreur("Le compte de " + compte.getNom()
                            + " a été modifié ou supprimé par un autre utilisateur !");
                } else { // Afficher le message de ex si la cause n'est pas une OptimisticLockException
                    Util.messageErreur(cause.getMessage());
                }
            } else { // Pas de cause attachée à l'EJBException
                Util.messageErreur(ex.getMessage());
            }
            return null; // pour rester sur la page s'il y a une exception
        }
    }

}
