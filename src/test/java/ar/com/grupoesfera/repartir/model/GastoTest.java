package ar.com.grupoesfera.repartir.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class GastoTest {

    @Test
    void tieneMontoMayorACeroCuandoElMontoEsPositivo() {

        Gasto gasto = new Gasto();
        gasto.setMonto(BigDecimal.valueOf(350000, 2));

        assertThat(gasto.tieneMontoMayorACero()).isTrue();
    }

    @Test
    void tieneMontoMayorACeroCuandoElMontoEsUnCentavo() {

        Gasto gasto = new Gasto();
        gasto.setMonto(BigDecimal.valueOf(1, 2));

        assertThat(gasto.tieneMontoMayorACero()).isTrue();
    }

    @Test
    void noTieneMontoMayorACeroCuandoElMontoEsCero() {

        Gasto gasto = new Gasto();
        gasto.setMonto(BigDecimal.valueOf(0, 2));

        assertThat(gasto.tieneMontoMayorACero()).isFalse();
    }

    @Test
    void noTieneMontoMayorACeroCuandoElMontoEsNegativo() {

        Gasto gasto = new Gasto();
        gasto.setMonto(BigDecimal.valueOf(-350000, 2));

        assertThat(gasto.tieneMontoMayorACero()).isFalse();
    }

    @Test
    void noTieneMontoMayorACeroCuandoElMontoEsNulo() {

        Gasto gasto = new Gasto();

        gasto.setMonto(null);

        assertThat(gasto.tieneMontoMayorACero()).isFalse();
    }
}
