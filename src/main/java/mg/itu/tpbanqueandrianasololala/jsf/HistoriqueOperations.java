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
import mg.itu.tpbanqueandrianasololala.entities.OperationBancaire;


/**
 *
 * @author kevin
 */
@Named(value = "historiqueOperations")
@ViewScoped
public class HistoriqueOperations  implements Serializable {

     @EJB
    private GestionnaireCompte gc;

    private Long idCompte;
    private CompteBancaire compte;
    private List<OperationBancaire> allOperations;
    
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
     * Creates a new instance of HistoriqueOperations
     */
    public HistoriqueOperations() {
    }
    public void loadCompteBancaire() {
        this.compte = this.gc.find(this.getIdCompte());
    }

    public List<OperationBancaire> getAllOperations() {
        if(this.allOperations ==null){
            this.allOperations = this.compte.getOperations();
        }
        return this.allOperations;
    }
    
}
