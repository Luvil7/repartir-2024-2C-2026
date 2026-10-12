package ar.com.grupoesfera.repartir.model;

import java.math.BigDecimal;

public class Gasto {

    private BigDecimal monto;

    public BigDecimal getMonto() {
        return  monto;
    }

    public void setMonto(BigDecimal monto){
        this.monto = monto;
    }

    public boolean tieneMontoMayorACero() {

        return (monto != null) && (monto.signum() > 0);
    }
}
