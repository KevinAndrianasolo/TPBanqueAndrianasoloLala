/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanqueandrianasololala.ejb.GestionnaireCompte;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;

/**
 *
 * @author kevin
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    List<CompteBancaire> allComptes;
    /**
     * Creates a new instance of ListeComptes
     */
    @EJB
    private GestionnaireCompte gc;

    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (this.allComptes == null) {
            this.allComptes = this.gc.getAllComptes();
        }
        return this.allComptes;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        this.gc.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
    

}
