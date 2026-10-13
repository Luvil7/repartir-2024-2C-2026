# language: es

Característica: Agregar gastos a un grupo

  Regla: El monto de un gasto debe ser mayor a cero

    Escenario: Agrega un gasto con monto mayor a cero
      Dado que existe un grupo con un total de $ 12000
      Cuando el usuario agrega un gasto de $ 3500
      Entonces el total del grupo debería ser $ 15500

    Escenario: No puede agregar un gasto con monto negativo
      Dado que existe un grupo con un total de $ 12000
      Cuando el usuario intenta agregar un gasto de $ -3500
      Entonces no debería agregarse el gasto
      Y el total del grupo debería seguir siendo $ 12000

    Escenario: No puede agregar un gasto con monto cero
      Dado que existe un grupo con un total de $ 12000
      Cuando el usuario intenta agregar un gasto de $ 0
      Entonces no debería agregarse el gasto
      Y el total del grupo debería seguir siendo $ 12000
