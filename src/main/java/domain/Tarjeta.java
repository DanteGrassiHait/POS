package domain;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Tarjeta {

    private String marca;
    private String numero;
    private Cardholder cardholder;
    private Date fechaVto;

    public Tarjeta (String marca, String numero, Cardholder cardholder, Date fechaVto){
        this.marca = marca;
        this.numero = numero;
        this.cardholder = cardholder;
        this.fechaVto = fechaVto;
    }

    public Tarjeta(){
        clear();
    }

    public void clear(){
        this.marca = "";
        this.numero = "";
        this.cardholder = new Cardholder();
        this.fechaVto = Calendar.getInstance().getTime();
    }

    public String getMarca() {
        return marca;
    }
    public String getNumero() {
        return numero;
    }
    public Cardholder getCardholder() {
        return cardholder;
    }
    public Date getFechaVto() {
        return fechaVto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarjeta)) return false;
        Tarjeta tarjeta = (Tarjeta) o;
        return Objects.equals(getMarca(), tarjeta.getMarca())
                && Objects.equals(getNumero(), tarjeta.getNumero())
                && Objects.equals(getCardholder(), tarjeta.getCardholder())
                && Objects.equals(getFechaVto(), tarjeta.getFechaVto());
    }

    @Override
    public String toString() {
        return "marca: " + marca + "\n" +
                "numero: " + numero + "\n" +
                "cardholder: " + cardholder.toString() +
                "fechaVto: " + fechaVto.getMonth() + "/" + fechaVto.getYear() + "\n";
    }
}
