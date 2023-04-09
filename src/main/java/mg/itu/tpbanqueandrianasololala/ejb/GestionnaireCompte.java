/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueandrianasololala.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import mg.itu.tpbanqueandrianasololala.entities.CompteBancaire;

/**
 *
 * @author kevin
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public Long nbComptes() {
        Query q = em.createQuery("select count(c) from CompteBancaire c");
        return (Long) q.getSingleResult();
    }

    public void deleteAllComptes() {
        Query q = em.createNamedQuery("CompteBancaire.deleteAll");
        q.executeUpdate();
    }

    public GestionnaireCompte() {

    }

    public CompteBancaire find(Long id) {
        CompteBancaire compte = em.find(CompteBancaire.class, id);
        return compte;
    }

    public void transferer(Long idSource, Long idDestinataire, int soldeATransferer) {

        CompteBancaire compteSource = this.find(idSource);
        CompteBancaire compteDestinataire = this.find(idDestinataire);

        this.transferer(compteSource, compteDestinataire, soldeATransferer);
        
    }

    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
}
