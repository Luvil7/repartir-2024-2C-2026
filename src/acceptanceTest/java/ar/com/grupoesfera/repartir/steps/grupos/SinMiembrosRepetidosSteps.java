package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SinMiembrosRepetidosSteps extends CucumberSteps {

    private String mensajeInformado;

    @Cuando("el usuario intenta crear un grupo indicando que sus miembros son {miembros}")
    public void elUsuarioIntentaCrearUnGrupoIndicandoQueSusMiembrosSon(List<String> miembros) {

        driver.findElement(By.id("crearGruposButton")).click();

        driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys("Fútbol de los jueves");

        var miembrosInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        miembros.forEach(miembro -> {
            miembrosInput.sendKeys(miembro);
            miembrosInput.sendKeys(Keys.ENTER);
        });

        driver.findElement(By.id("guardarGrupoNuevoButton")).click();

        var wait = new WebDriverWait(driver, 2);
        var mensaje = wait.until(visibilityOfElementLocated(By.cssSelector("#mensajesToast .p-toast-message")));
        mensajeInformado = mensaje.getText();
    }

    @Entonces("no puede crear el grupo con miembros repetidos")
    public void noPuedeCrearElGrupoConMiembrosRepetidos() {

        assertThat(baseDeDatos.cantidadDeGrupos())
                .as("Cantidad de grupos creados")
                .isZero();
    }

    @Y("debe ser informado que los miembros no pueden repetirse")
    public void debeSerInformadoQueLosMiembrosNoPuedenRepetirse() {

        assertThat(mensajeInformado)
                .as("Mensaje informado al usuario")
                .contains("Error")
                .contains("Los miembros no pueden repetirse");
    }
}
