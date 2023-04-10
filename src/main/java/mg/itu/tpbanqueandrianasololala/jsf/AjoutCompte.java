/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanqueandrianasololala.ejb.GestionnaireCompte;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;


/**
 *
 * @author kevin
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    @EJB
    private GestionnaireCompte gc;
    private String nom;
    @PositiveOrZero
    private int solde;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    
    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
    public String creerCompte(){
        CompteBancaire compte = new CompteBancaire(this.getNom(), this.getSolde());
        /*boolean erreur = false;
        if(this.getSolde() < 0){
            Util.messageErreur("Le solde ne doit pas être négatif", "Le solde ne doit pas être négatif", "form:solde");
            erreur = true;
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }*/
        this.gc.creerCompte(compte);
        Util.addFlashInfoMessage("Ajout de compte effectué");
        return "listeComptes?faces-redirect=true"; 
    }
}
