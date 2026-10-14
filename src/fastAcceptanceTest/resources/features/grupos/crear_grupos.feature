# language: es

Característica: Crear Grupo para repartir gastos

  Regla: Los grupos están compuestos por al menos dos miembros

    Escenario: No puedo crear un grupo con un único miembro
      Cuando el usuario intenta crear un grupo indicando un único miembro
      Entonces no debería crear el grupo con un único miembro

  Regla: Los grupos no tienen miembros repetidos

    Escenario: Se crea un grupo con dos miembros del mismo nombre que se distinguen por la inicial del apellido
      Cuando el usuario crea un grupo indicando que sus miembros son 'mariano f', 'mariano v' y 'juancruz'
      Entonces deberia crear el grupo con los miembros indicados

    Escenario: No puede crear un grupo con un miembro repetido
      Cuando el usuario intenta crear un grupo indicando que sus miembros son 'mariano', 'juancruz' y 'mariano'
      Entonces no deberia crear el grupo con miembros repetidos

    Escenario: No puede crear un grupo con un miembro repetido que solo difiere en mayúsculas
      Cuando el usuario intenta crear un grupo indicando que sus miembros son 'mariano', 'juancruz' y 'Mariano'
      Entonces no deberia crear el grupo con miembros repetidos
