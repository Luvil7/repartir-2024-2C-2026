package ar.com.grupoesfera.repartir.steps.gastos;

import ar.com.grupoesfera.repartir.exceptions.GastoInvalidoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.services.MontosService;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MontoMayorACeroSteps extends FastCucumberSteps {

    private final MontosService montos = new MontosService();

    private Grupo grupo;

    private boolean gastoRechazado;

    @Dado("que existe un grupo con un total de $ {int}")
    public void queExisteUnGrupoConUnTotalDe$(int total) {

        grupo = new Grupo();
        grupo.setTotal( $(total) );
    }

    @Cuando("el usuario agrega un gasto de $ {int}")
    public void elUsuarioAgregaUnGastoDe$(int monto) {

        montos.acumularAlTotal( grupo, gastoDe(monto) );
    }

    @Cuando("el usuario intenta agregar un gasto de $ {int}")
    public void elUsuarioIntentaAgregarUnGastoDe$(int monto) {

        try {

            montos.acumularAlTotal( grupo, gastoDe(monto) );
            gastoRechazado = false;

        } catch (GastoInvalidoException e) {

            gastoRechazado = true;
        }
    }

    @Entonces("el total del grupo debería ser $ {int}")
    public void elTotalDelGrupoDeberiaSer$(int total) {

        assertThat( grupo.getTotal() ).isEqualByComparingTo( $(total) );
    }

    @Entonces("no debería agregarse el gasto")
    public void noDeberiaAgregarseElGasto() {

        assertThat( gastoRechazado ).isTrue();
    }

    @Y("el total del grupo debería seguir siendo $ {int}")
    public void elTotalDelGrupoDeberiaSeguirSiendo$(int total) {

        assertThat( grupo.getTotal() ).isEqualByComparingTo( $(total) );
    }

    private Gasto gastoDe(int monto) {

        Gasto gasto = new Gasto();
        gasto.setMonto( $(monto) );

        return gasto;
    }

    private BigDecimal $(int pesos) {

        return BigDecimal.valueOf(pesos * 100L, 2);
    }
}
