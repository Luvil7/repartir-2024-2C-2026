package ar.com.grupoesfera.repartir.services;

import ar.com.grupoesfera.repartir.exceptions.GastoInvalidoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MontosService {

    public void inicializarTotal(Grupo grupo) {

        grupo.setTotal(BigDecimal.valueOf(0,2));
    }

    public void acumularAlTotal(Grupo grupo, Gasto gasto) {

        if (!gasto.tieneMontoMayorACero()) {
            throw new GastoInvalidoException();
        }

        BigDecimal total = grupo.getTotal();
        total = total.add(gasto.getMonto());
        grupo.setTotal(total);
    }

}
