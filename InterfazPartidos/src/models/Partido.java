package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partido implements Serializable {

    private String equipoLocal;
    private String equipoVisitante;
    private int puntuacionLocal;
    private int puntuacionVisitante;
    private Division division;
    private String resultado;
    private Date fecha;

    public Partido(String equipoLocal, String equipoVisitante, int puntuacionLocal, int puntuacionVisitante,
                   Division division, Date fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.puntuacionLocal = puntuacionLocal;
        this.puntuacionVisitante = puntuacionVisitante;
        this.division = division;
        this.resultado = calcularResultado();
        this.fecha = fecha;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public int getPuntuacionLocal() {
        return puntuacionLocal;
    }

    public int getPuntuacionVisitante() {
        return puntuacionVisitante;
    }

    public Division getDivision() {
        return division;
    }

    public String getResultado() {
        return resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    /**
     * Devuelve la fecha convertida en string para imprimir la propiedad en la tabla
     *
     * @return
     */
    public String getFechaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaS = sdf.format(fecha);
        return fechaS;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public void setPuntuacionLocal(int puntuacionLocal) {
        this.puntuacionLocal = puntuacionLocal;
    }

    public void setPuntuacionVisitante(int puntuacionVisitante) {
        this.puntuacionVisitante = puntuacionVisitante;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String calcularResultado() {
        String local = String.valueOf(puntuacionLocal);
        String visitante = String.valueOf(puntuacionVisitante);
        resultado = local + "-" + visitante;
        return resultado;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "equipoLocal='" + equipoLocal + '\'' +
                ", equipoVisitante='" + equipoVisitante + '\'' +
                ", puntuacionLocal=" + puntuacionLocal +
                ", puntuacionVisitante=" + puntuacionVisitante +
                ", division=" + division +
                ", resultado='" + resultado + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
