package businessLogic;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import domain.Tarjeta;

import java.util.Calendar;
import java.util.Date;

public class TarjetaBL{

    private final static Logger log = LoggerFactory.getLogger(TarjetaBL.class);
    private static TarjetaBL instance = null;

    public static TarjetaBL getInstance(){
        if(instance == null){
            instance = new TarjetaBL();
        }
        return instance;
    }

    /**
     * Verifica si el importe ingresado es menor al limite estipulado y si la fecha de vencimiento
     * es menor a la fecha actual
     * @param importe
     * @param importeLimite
     * @param fechaVto
     * @return True si y solo si el importe es menor al limite y la fecha de vencimiento es menor al dia actual
     */
    public boolean validaDatos(Double importe, Double importeLimite, Date fechaVto){
        return operacionValida(importe, importeLimite) && tarjetaValida(fechaVto);
    }

    /**
     * Verifica si el importre ingresado es menor al limite estipulado
     * @param importe
     * @param importeLimite
     * @return True si y solo si el importe es menor al limite
     */
    public boolean operacionValida(Double importe, Double importeLimite){
        if(importe < importeLimite){
            return true;
        }
        log.error("Importe ingresado supera el limite: " + importe);
        return false;
    }

    /**
     * Verifica que la fecha de vencimiento de la tarjeta sea mayor a la fecha actual
     * @param fechaVto
     * @return True si y solo si la fecha de vencimiento es meyor a la fecha actual
     */
    public boolean tarjetaValida(Date fechaVto){
        // Se que esta mal usar estos metodos deprecados pero no encontre forma de hacerlo que funcione bien
        if(fechaVto.getMonth() == Calendar.getInstance().getTime().getMonth()
            && fechaVto.getYear() == Calendar.getInstance().getTime().getYear()){
            return true;
        }
        log.error("Fecha ingresada menor a la actual: " + fechaVto.getTime());
        return false;
    }

    /**
     * Metodo que sirve para saber si una tarjeta es igual a otra
     * @param tarjeta1
     * @param tarjeta2
     * @return
     */
    public String compararTarjetas(Tarjeta tarjeta1, Tarjeta tarjeta2){
        if(tarjeta1.equals(tarjeta2)){
            return "Las tarjetas son iguales";
        } else {
            return "Las tarjetas son distintas";
        }
    }

    /**
     * Obtiene la tasa de una operacion para una tarjeta dada
     * @param marca
     * @return
     */
    private Double obtenerTasaOperacion(String marca){
        Calendar time = Calendar.getInstance();
        Double tasa = 0D;
        switch (marca){
            case "VISA":
                tasa = getTasaVisa(time.get(Calendar.YEAR), time.get(Calendar.MONTH));
                break;
            case "NARA":
                tasa = getTasaNara(time.get(Calendar.DAY_OF_MONTH) + 1);
                break;
            case "AMEX":
                tasa = getTasaAmex(time.get(Calendar.MONTH) + 1);
                break;
        }
        return getPorcentajes(tasa);
    }

    /**
     * Metodo usado para los limites minimos y maximos permitidos por las tarjetas
     * @param tasa
     * @return
     */
    private Double getPorcentajes(Double tasa){
        if(tasa < 0.3){
            tasa =  0.3;
        } else if(tasa > 5.0){
            tasa = 5.0;
        }
        return tasa;
    }

    private Double getTasaVisa(Integer anio, Integer mes){
        Double tasa = 0D;
        try {
            tasa = Double.valueOf(Integer.valueOf(anio.toString().substring(2)) / (mes + 1));
        } catch (NumberFormatException error){
            log.error("NumberFormatException: " + error.getMessage());
        } catch(NullPointerException error){
            log.error("NullPointerException: " + error.getMessage());
        } catch (Exception error){
            log.error("Exception: " + error.getMessage());
        }
        return tasa;
    }

    private Double getTasaNara(Integer diaMes){
        Double tasa = 0D;
        try {
            tasa = Double.valueOf(diaMes * 0.5);
        } catch (NumberFormatException error){
            log.error("NumberFormatException: " + error.getMessage());
        } catch(NullPointerException error){
            log.error("NullPointerException: " + error.getMessage());
        } catch (Exception error){
            log.error("Exception: " + error.getMessage());
        }
        return tasa;
    }

    private Double getTasaAmex(Integer mes){
        Double tasa = 0D;
        try {
            tasa = Double.valueOf(mes * 0.1);
        } catch (NumberFormatException error){
            log.error("NumberFormatException: " + error.getMessage());
        } catch(NullPointerException error){
            log.error("NullPointerException: " + error.getMessage());
        } catch (Exception error){
            log.error("Exception: " + error.getMessage());
        }
        return tasa;
    }

    /**
     * Informa si una operacion es valida o no. Si es valida, retorna la tasa de la operacion.
     * Si no es valida. retorna un mensaje de error
     * @param importe
     * @param importeLimte
     * @param tarjeta
     * @return
     */
    public String informarOperacion(Double importe, Double importeLimte, Tarjeta tarjeta){
        return validaDatos(importe, importeLimte, tarjeta.getFechaVto())
                ? "Tasa de la operacion: " + obtenerTasaOperacion(tarjeta.getMarca()) + "%"
                : "La operacion no es valida";
    }

}
