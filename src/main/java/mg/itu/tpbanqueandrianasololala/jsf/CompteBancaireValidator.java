/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import mg.itu.tpbanqueandrianasololala.ejb.GestionnaireCompte;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;

/**
 *
 * @author kevin
 */
@FacesValidator(value="compteBancaireValidator", managed=true)
// We use (..., managed=true) to inject EJB, to avoid null pointer on this EJB
public class CompteBancaireValidator  implements Validator{
    @EJB
    private GestionnaireCompte gc;
    
    @Override
    public void validate(FacesContext context, UIComponent composant, Object id) {
        CompteBancaire compte = this.gc.find((Long) id);
        if (compte == null) {
            FacesMessage message
                    = new FacesMessage("Pas de compte avec id : " + id);
            throw new ValidatorException(message);
        }
    }
}
