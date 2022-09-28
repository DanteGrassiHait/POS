import businessLogic.TarjetaBL;
import domain.Cardholder;
import domain.Tarjeta;

import java.util.Calendar;

public class main {
    public static void main(String[] args) {

        TarjetaBL tarjetaBL = TarjetaBL.getInstance();

        Tarjeta Visa = new Tarjeta("VISA", "1234567812345678", new Cardholder("Dante", "Grassi"), Calendar.getInstance().getTime());
        Tarjeta Nara = new Tarjeta("NARA", "8765432187654321", new Cardholder("Cosme", "Fulanito"), Calendar.getInstance().getTime());
        Tarjeta Amex = new Tarjeta("AMEX", "1111222233334444", new Cardholder("Rick", "Sanchez"), Calendar.getInstance().getTime());

        System.out.println(Visa.toString());

        tarjetaBL.operacionValida(1000.0, 1000.0);
        tarjetaBL.tarjetaValida(Visa.getFechaVto());
        System.out.println(tarjetaBL.compararTarjetas(Visa, Visa));
        System.out.println(tarjetaBL.informarOperacion(900.0, 1000.0, Visa));


    }
}
