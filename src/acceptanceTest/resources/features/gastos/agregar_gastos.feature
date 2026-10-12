# language: es

Característica: Agregar gastos a un grupo

  Regla: El monto de un gasto debe ser mayor a cero

    Escenario: No puede agregar un gasto con monto negativo
      Dado que existe un grupo con un total de $ 12000
      Cuando el usuario intenta agregar un gasto de $ -3500
      Entonces no debería agregarse el gasto
      Y debería ser informado que el monto debe ser mayor a cero

    Escenario: No puede agregar un gasto con monto cero
      Dado que existe un grupo con un total de $ 12000
      Cuando el usuario intenta agregar un gasto de $ 0
      Entonces no debería agregarse el gasto
      Y debería ser informado que el monto debe ser mayor a cero
