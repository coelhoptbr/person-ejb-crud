/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Person;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import sessionbean.PersonFacadeLocal;

/**
 *
 * @author coelh
 */
@Named(value = "personController")
@SessionScoped
public class PersonController implements Serializable {
    
    @EJB
    private PersonFacadeLocal personFacade;
    
    private Person selectedPerson = new Person();
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
    
    

    /**
     * Creates a new instance of PersonController
     */
    public PersonController() {
    }
    
    public void emptyVariables() {
        this.selectedPerson = new Person();
        this.name = "";
    }
    
    public String createPerson() {
        this.selectedPerson = new Person();
        this.selectedPerson.setName(this.name);
        this.personFacade.create(selectedPerson);
        emptyVariables();
        return "index.xhtml?faces-redirect=true";
    }
    
    public List<Person> getPeople() {
        return this.personFacade.findAll();
    }
    
    public String updatePerson() {
        this.personFacade.edit(selectedPerson);
        emptyVariables();
        return "index.xhtml?faces-redirect=true";
    }
    
    public String deletePerson(Person person) {
        this.personFacade.remove(person);
        return "index.xhtml?faces-redirect=true";
    }
}
