/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tpbanqueandrianasololala.ejb.GestionnaireCompte;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;


/**
 *
 * @author kevin
 */
@Named(value = "modificationNom")
@ViewScoped
public class ModificationNom implements Serializable {

     @EJB
    private GestionnaireCompte gc;

    private Long idCompte;
    private CompteBancaire compte;

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }
    /**
     * Creates a new instance of ModificationNom
     */
    public ModificationNom() {
    }
    public void loadCompteBancaire() {
        this.compte = this.gc.find(this.getIdCompte());
    }

    public String update() {
        this.compte = this.gc.update(this.compte);
        Util.addFlashInfoMessage("Nom modifié avec succès");
        
        return "listeComptes?faces-redirect=true";
    }

}
