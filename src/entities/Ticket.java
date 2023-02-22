/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
public class Ticket {
    private int IdTicket;
    private float PrixTicket;
    private String NomEvent;
    private String TypeTicket;
    

    public Ticket() {
    }

    public Ticket(int IdTicket, float PrixTicket, String NomEvent, String TypeTicket) {
        this.IdTicket = IdTicket;
        this.PrixTicket = PrixTicket;
        this.NomEvent = NomEvent;
        this.TypeTicket = TypeTicket;
    }

    public Ticket(int IdTicket, float PrixTicket, int idEvent, String TypeTicket) {
        this.IdTicket = IdTicket;
        this.PrixTicket = PrixTicket;
        this.NomEvent = NomEvent;
        this.TypeTicket = TypeTicket;
    }

    public Ticket(float PrixTicket, int idEvent, String TypeTicket) {
        this.PrixTicket = PrixTicket;
        this.NomEvent = NomEvent;
        this.TypeTicket = TypeTicket;
    }

    public int getIdTicket() {
        return IdTicket;
    }

    public float getPrixTicket() {
        return PrixTicket;
    }

    public String getNomEvent() {
        return NomEvent;
    }

    public String getTypeTicket() {
        return TypeTicket;
    }

    public void setIdTicket(int IdTicket) {
        this.IdTicket = IdTicket;
    }

    public void setPrixTicket(float PrixTicket) {
        this.PrixTicket = PrixTicket;
    }

    public void setNomEvent(String NomEvent) {
        this.NomEvent = NomEvent;
    }

    public void setTypeTicket(String TypeTicket) {
        this.TypeTicket = TypeTicket;
    }

    @Override
    public String toString() {
        return "Ticket{" + "IdTicket=" + IdTicket + ", PrixTicket=" + PrixTicket + ", NomEvent=" + NomEvent + ", TypeTicket=" + TypeTicket + '}';
    }
    
}
