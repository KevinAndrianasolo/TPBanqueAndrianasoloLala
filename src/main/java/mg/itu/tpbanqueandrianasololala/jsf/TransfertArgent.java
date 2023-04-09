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

/**
 *
 * @author kevin
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    private Long idSource;
    private Long idDestinataire;
    private int solde;
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

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }
    
    public String effectuerTransfert(){
        this.gc.transferer(this.idSource, this.idDestinataire, this.solde);
        return "listeComptes";
    }
    
}
