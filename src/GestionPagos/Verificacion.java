package GestionPagos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verificacion implements Pago {
     private static final double saldo = 14547.45;
     private double montoTotal;

    public String efectuarPago(double monto, String numero) {
        String mensaje = "Pendiente";
        if(validarNumeroCuenta(numero)){
            montoTotal = saldo + monto;
            mensaje="Devolución realizada con éxito";
        }else{
            mensaje = "Número de cuenta no válido";
        }

        return mensaje;
    }

    public boolean validarNumeroCuenta(String numero){
        boolean flag = false;
        Pattern pat = Pattern.compile("\\d{10}");
        Matcher mat = pat.matcher(numero);
        if(mat.matches()){
            flag = true;
        }
        return flag;
    }

    public String efectuarCobro(double monto, String numero){
        String mensaje="Pendiente";
            if((monto < saldo) && validarNumeroCuenta(numero)){
                montoTotal= saldo - monto;
                mensaje = "Pago realizado con éxito";
            }else{
                mensaje = "No se ha podido efectuar el pago";
            }
            return mensaje;
        }

}
