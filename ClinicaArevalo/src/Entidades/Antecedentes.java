package Entidades;

/**
 *
 * @author BASE CCDIT
 */

public class Antecedentes {
    
    int id_antecedentes;
    int id_pacientes;
    String familiares;
    String personales;

    public Antecedentes(int id_antecedentes, int id_pacientes, String familiares, String personales) {
        this.id_antecedentes = id_antecedentes;
        this.id_pacientes = id_pacientes;
        this.familiares = familiares;
        this.personales = personales;
    }

    public Antecedentes(int id_pacientes, String familiares, String personales) {
        this.id_pacientes = id_pacientes;
        this.familiares = familiares;
        this.personales = personales;
    }

    public Antecedentes() {
        
    }
      
    public int getId_antecedentes() {
        return id_antecedentes;
    }

    public void setId_antecedentes(int id_antecedentes) {
        this.id_antecedentes = id_antecedentes;
    }

    public int getId_pacientes() {
        return id_pacientes;
    }

    public void setId_pacientes(int id_pacientes) {
        this.id_pacientes = id_pacientes;
    }

    public String getFamiliares() {
        return familiares;
    }

    public void setFamiliares(String familiares) {
        this.familiares = familiares;
    }

    public String getPersonales() {
        return personales;
    }

    public void setPersonales(String personales) {
        this.personales = personales;
    }

    @Override
    public String toString() {
        return "Antecedentes{" + "id_antecedentes=" + id_antecedentes + ", id_pacientes=" + id_pacientes + ", familiares=" + familiares + ", personales=" + personales + '}';
    }
    
}
