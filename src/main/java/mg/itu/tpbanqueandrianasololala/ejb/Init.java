/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;

/**
 *
 * @author kevin
 */
@Singleton
@Startup
public class Init {

    @EJB
    GestionnaireCompte gc;

    public Init() {
    }
    
    @PostConstruct
    public void initialisationComptes() {
        List<CompteBancaire> comptes = new ArrayList<CompteBancaire>();
        comptes.add(new CompteBancaire("John Lennon", 150000));
        comptes.add(new CompteBancaire("Paul McCartney", 950000));
        comptes.add(new CompteBancaire("Ringo Starr", 20000));
        comptes.add(new CompteBancaire("Georges Harrisson", 100000));
        Long nbComptes = this.gc.nbComptes();
        if(nbComptes != 0) return;
        
        for(int i=0; i< comptes.size(); i++){
            this.gc.creerCompte(comptes.get(i));
        }
    }
}
