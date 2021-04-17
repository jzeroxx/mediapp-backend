package com.mitocode.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "signos_vitales")
public class SignosVitales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_signosvitales")
    Integer idSignosVitales;
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_signosvitales_paciente"))
    Paciente paciente;
    @Column(name = "fecha", nullable = false)
    LocalDateTime fecha;
    @Column(name = "temperatura")
    String temperatura;
    @Column(name = "pulso")
    String pulso;
    @Column(name = "ritmocardiaco")
    String ritmoCardiaco;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getIdSignosVitales() {
        return idSignosVitales;
    }

    public void setIdSignosVitales(Integer idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getRitmoCardiaco() {
        return ritmoCardiaco;
    }

    public void setRitmoCardiaco(String ritmoCardiaco) {
        this.ritmoCardiaco = ritmoCardiaco;
    }

    @Override
    public String toString() {
        return "SignosVitales{" +
                "idSignosVitales=" + idSignosVitales +
                ", paciente=" + paciente +
                ", temperatura='" + temperatura + '\'' +
                ", pulso='" + pulso + '\'' +
                ", ritmoCardiaco='" + ritmoCardiaco + '\'' +
                '}';
    }
}
