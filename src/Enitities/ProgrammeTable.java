/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitities;

/**
 *
 * @author ASUS
 */
public class ProgrammeTable {
    private int id ;
    private String prog ;

    public ProgrammeTable(int id, String prog) {
        this.id = id;
        this.prog = prog;
    }

    public ProgrammeTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }
    
    
    
}
