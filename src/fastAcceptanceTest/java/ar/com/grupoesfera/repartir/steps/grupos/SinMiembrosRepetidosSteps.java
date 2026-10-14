package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.exceptions.GrupoInvalidoException;
import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.repositories.GruposRepository;
import ar.com.grupoesfera.repartir.services.GruposService;
import ar.com.grupoesfera.repartir.services.MontosService;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SinMiembrosRepetidosSteps extends FastCucumberSteps {

    private final GruposRepository repository = mock(GruposRepository.class);

    private final GruposService grupos = crearServicioDeGrupos();

    private Grupo grupo;

    private boolean grupoRechazado;

    @Cuando("el usuario crea un grupo indicando que sus miembros son {miembros}")
    public void elUsuarioCreaUnGrupoIndicandoQueSusMiembrosSon(List<String> miembros) {

        grupo = grupoCon( miembros );

        grupos.crear( grupo );
    }

    @Cuando("el usuario intenta crear un grupo indicando que sus miembros son {miembros}")
    public void elUsuarioIntentaCrearUnGrupoIndicandoQueSusMiembrosSon(List<String> miembros) {

        grupo = grupoCon( miembros );

        try {

            grupos.crear( grupo );
            grupoRechazado = false;

        } catch (GrupoInvalidoException e) {

            grupoRechazado = true;
        }
    }

    @Entonces("deberia crear el grupo con los miembros indicados")
    public void deberiaCrearElGrupoConLosMiembrosIndicados() {

        verify( repository ).save( grupo );
    }

    @Entonces("no deberia crear el grupo con miembros repetidos")
    public void noDeberiaCrearElGrupoConMiembrosRepetidos() {

        assertThat( grupoRechazado ).isTrue();
        verify( repository, never() ).save( any() );
    }

    private Grupo grupoCon(List<String> miembros) {

        Grupo nuevoGrupo = new Grupo();
        nuevoGrupo.setNombre( "Fútbol de los jueves" );
        nuevoGrupo.setMiembros( miembros );

        return nuevoGrupo;
    }

    private GruposService crearServicioDeGrupos() {

        GruposService servicio = new GruposService();
        ReflectionTestUtils.setField( servicio, "repository", repository );
        ReflectionTestUtils.setField( servicio, "montos", new MontosService() );

        return servicio;
    }
}
