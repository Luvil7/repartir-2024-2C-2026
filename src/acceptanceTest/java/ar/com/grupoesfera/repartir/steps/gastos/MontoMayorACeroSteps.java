package ar.com.grupoesfera.repartir.steps.gastos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MontoMayorACeroSteps extends CucumberSteps {

    private static final int ID_GRUPO = 34;

    private int totalInicial;

    private String mensajeInformado;

    @Dado("que existe un grupo con un total de $ {int}")
    public void queExisteUnGrupoConUnTotalDe$(int total) {

        totalInicial = total;
        baseDeDatos.tieneElGrupoConTotal(ID_GRUPO, "Asado del domingo", total);

        iniciarLaAplicacion();
    }

    @Cuando("el usuario intenta agregar un gasto de $ {int}")
    public void elUsuarioIntentaAgregarUnGastoDe$(int monto) {

        var wait = new WebDriverWait(driver, 2);
        var agregarGastoButton = wait.until(visibilityOfElementLocated(By.id("agregarGastoGruposButton-" + ID_GRUPO)));
        agregarGastoButton.click();

        completarElMonto(monto);

        driver.findElement(By.id("guardarGastoNuevoButton")).click();

        var mensaje = wait.until(visibilityOfElementLocated(By.cssSelector("#mensajesToast .p-toast-message")));
        mensajeInformado = mensaje.getText();
    }

    @Entonces("no debería agregarse el gasto")
    public void noDeberiaAgregarseElGasto() {

        iniciarLaAplicacion();

        var wait = new WebDriverWait(driver, 2);
        wait.until(visibilityOfElementLocated(By.id("agregarGastoGruposButton-" + ID_GRUPO)));

        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));
        var campoTDs = grupoTR.get(1).findElements(By.tagName("td"));

        assertThat(campoTDs.get(2).getText())
                .as("Total del grupo")
                .contains(enPesos(totalInicial));
    }

    @Y("debería ser informado que el monto debe ser mayor a cero")
    public void deberiaSerInformadoQueElMontoDebeSerMayorACero() {

        assertThat(mensajeInformado)
                .as("Mensaje informado al usuario")
                .contains("Error")
                .contains("El monto debe ser mayor a cero");
    }

    private void completarElMonto(int monto) {

        var montoInput = driver.findElement(By.id("montoGastoNuevoInput"));
        montoInput.clear();
        montoInput.sendKeys(String.valueOf(Math.abs(monto)));

        if (monto < 0) {
            // el campo solo acepta el signo con el cursor al inicio
            montoInput.sendKeys(Keys.HOME);
            montoInput.sendKeys("-");
        }
    }

    private void iniciarLaAplicacion() {

        driver.navigate().to(url("/"));
        driver.findElement(By.id("iniciarBienvenidaButton")).click();
    }

    private String enPesos(int monto) {

        return String.format(new Locale("es", "AR"), "%,d,00", monto);
    }
}
