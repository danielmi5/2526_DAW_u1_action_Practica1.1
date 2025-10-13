# Práctica 1.1 documentación con github actions

### Documentación de las herramientas y comandos utilizados

Para la creación automática de documentación he utilizado GitHub Actions como herramienta de integración continua. El proyecto está desarrollado con Java 21 y la documentación del código he seguido el estilo Javadoc. La generación de la documentación se realiza mediante un actions `mattnotmitt/doxygen-action@v1.9.5` usado en el workflow, que crea la documentación en la carpeta docs en formato HTML y LATEX.   


Actions utilizados:
- **actions/checkout@v3**: Clona el repositorio en el entorno del runner.
- **actions/setup-java@v5**: Instala y configura Java 21 (con distribución Temurin).  
- **mattnotmitt/doxygen-action@v1.9.5**: Genera la documentación del proyecto usando Doxygen
- **stefanzweifel/git-auto-commit-action@v4**: Realiza commits automáticos.


 ### Formatos generados de la documentación
  - HTML: Genero la documentación en formato HTML mediante Doxygen, esta se genera el la carpeta docs/html ([enlace a la carpeta](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/tree/main/docs/html))
  - LATEX: Genero la documentación en formato LATEX mediante Doxygen, esta se genera el la carpeta docs/latex ([enlace a la carpeta](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/tree/main/docs/latex))

### Funcionamiento de los dos workflows  
El workflow tienen permisos de escritura para que puedan modificar el contenido del repositorio.

- Explicación del [workflow](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/blob/main/.github/workflows/ci.yaml) con Doxygen (`ci.yaml`)

Este workflow se encarga de generar automáticamente la documentación del proyecto en formato HTML y LaTeX mediante **Doxygen**.

Eventos que disparan el workflow:  
- Hacer push a la rama main.
- workflow_dispatch: permite ejecutar manualmente desde GitHub en la pestaña [Actions](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/actions/workflows/ci.yaml).

Pasos del job auto-doc:

1. **Checkout del repositorio**
- Acción: `actions/checkout@v3`
- Clona el repositorio dentro del entorno de GitHub Actions.

2. **Configuración de Java**
- Acción: `actions/setup-java@v5`
- Configura el entorno con Java 21 (Temurin), requerido para compilar o analizar el código fuente Java si fuera necesario.

3. **Creación del archivo Doxyfile**
- Comando:
```
cat > Doxyfile <<EOF
PROJECT_NAME = "Proyecto"
OUTPUT_DIRECTORY = docs
INPUT = ./src
FILE_PATTERNS = *.java
RECURSIVE = YES
GENERATE_HTML = YES
GENERATE_LATEX = YES
EOF
```
- Crea el archivo de configuración Doxyfile con los parámetros necesarios para generar la documentación correctamente.


4. **Generación automática de la documentación**
- Acción: `mattnotmitt/doxygen-action@v1.9.5`
- Utiliza el Doxyfile generado previamente para generar la documentación.
- Se crean dos carpetas:
  - `docs/html/`: documentación en formato HTML navegable.
  - `docs/latex/`: documentación en formato LaTeX (compilable en PDF).

5. **Commit automático de la documentación**
- Acción: `stefanzweifel/git-auto-commit-action@v4`
- Realiza automáticamente un commit con los cambios generados en la carpeta `docs/`.
- Mensaje del commit:** `"Documentación actualizada."`


## Preguntas

### a. Identificación de herramientas de generación de documentación
¿Qué herramienta o generador (p. ej., Sphinx, pdoc, Javadoc, Doxygen, Dokka) utilizaste en el workflow para crear la documentación en /docs?  
Para generar la documentación en la carpeta docs utilicé Doxygen.
En el workflow de GitHub Actions lo utilicé con el action `mattnotmitt/doxygen-action@v1.9.5`, que permite ejecutar Doxygen automáticamente. Este action genera la documentación HTML y LaTeX directamente en la carpeta docs configurada mediante el archivo Doxyfile.

### b. Documentación de componentes
Muestra un fragmento del código con comentarios/docstrings estructurados (p. ej., :param, :return: o etiquetas equivalentes) que haya sido procesado por la herramienta.  
Comenta qué estilo de documentación has utilizado (p. ej., reStructuredText, Google Style, KDoc).  
Ejemplo:   
https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/blob/633c41bb62c170fbff0a9dca890dd6c99719481c/src/main/java/Main.java#L78-L95
He utilizado Javadoc como estilo de documentación para el código. Al principio del comentario utilizo la descripción de lo que hace el método (en este caso: factorial) y utilizo etiquetas de Javadoc como:
  - `@param` para describir el parámetro que recibe el método.
  - `@return` para describir el valor que se devuelve.
  - `@throw` para indicar la excepción que se lanza y el por qué.

### c. Multiformato
¿Qué segundo formato (además de HTML) generaste?  
Explica la configuración o comandos del workflow y herramientas que lo producen.  

El segundo formato generado fue LateX, el cual se genera a partir del action `mattnotmitt/doxygen-action@v1.9.5` y la propiedad usada en el archivo Doxyfile.

```
GENERATE_LATEX = YES
```

Este parámetro dentro del archivo `Doxyfile` indica a **Doxygen** que, además del formato HTML, también debe generar la documentación en formato Latex con archivos .tex. 

Paso del Workflow con el action utilizado para generar la documentación con Doxygen:

```yaml
- name: Documentar con doxygen en formato HTML y LATEX
  uses: mattnotmitt/doxygen-action@v1.9.5
  with:
    doxyfile-path: ./Doxyfile
```

Esta acción del workflow utiliza la herramienta Doxygen para ejecutar automáticamente el proceso de documentación. El parámetro `doxyfile-path` le indica a la acción que utilice el archivo `Doxyfile` creado anteriormente, donde se definieron las propiedades del proyecto y los formatos de salida.  

De esta manera, este action genera la documentación completa tanto en formato HTML como en LaTeX, siguiendo las configuraciones establecidas en el archivo Doxyfile.


### d. Colaboración
Explica cómo GitHub facilita mantener la documentación (actualizaciones del README.md y de /docs) cuando colaboran varias personas (PRs, reviews, checks de CI, protección de ramas).  
Cuando hay varios colaborando en un mismo repositorio se usa principalmente pull requests para proponer cambios desde una rama diferente a la rama principal. Permite que otros colaboradores revisen esos cambios mediante reviews, donde pueden dejar comentarios, sugerir mejoras o aprobarlos. También para mantener la actualización de la documentación se utilizan workflows de integración continua que ejecutan automáticamente procesos, como en este caso.

### e. Control de versiones
Muestra mensajes de commit que evidencien el nuevo workflow.  
¿Son claros y descriptivos? Justifícalo. Además, incluye un conjunto de mensajes de tus commits.

Lista de los commits:
- **9a96efc** "Workflow actualizado para generar la documentación con Doxygen mediante un actions.  El primer paso añadido es crear el archivo Doxyfile y después se genera la documentación mediante el actions: mattnotmitt/doxygen-action@v1.9.5"
- **220c744** "Workflow actualizado para generar documentación con wkhtmltopdf en formato pdf automáticamente. Añadido tres pasos: instalar el paquete  wkhtmltopdf,  convertir algunos archivos html de la documentación de JavaDoc a pdf y hace commit de los cambios."
- **e7b805b** "Añadido al workflow el paso para hacer commit  automáticamente de la actualización de JavaDoc."
- **56162b7** "Workflow actualizado para generar documentación con Javadoc automáticamente, configurando también java. He añadido dos pasos, configurar java y un run que ejecuta un comando para crear la documentación."

Los mensajes son claros y descriptivos, ya que siguen una estructura coherente y expresan las acciones realizadas en cada commit. Cada mensaje indica qué se modificó o añadió en el workflow, especificando los pasos añadidos.   
Aunque, se podría haber detallado más. con qué exactamente se añadió o se modificó en algunos commits y dividir los cambios en varios commits más pequeños, haciendo que el historial de commits sea más ordenado y sencillo de revisar.


### f. Accesibilidad y seguridad
¿Qué medidas/configuración del repositorio garantizan que solo personal autorizado accede al código y la documentación?  
(p. ej., repositorio privado, equipos, roles, claves/secretos, branch protection).  
La principal medida de seguridad es que el repositorio esté en privado, de esta manera solo tienen acceso las personas autorizadas por el propietario.
La gestión de estas autorizaciones se realiza mediante equipos y roles, lo que permite asignar permisos específicos a cada persona o equipo, definiendo qué acciones pueden realizar cada rol dentro del proyecto.


### g. Instalación/uso documentados
Indica dónde en el README.md explicas el funcionamiento del workflow y dónde detallas las herramientas y comandos de documentación.    
El funcionamiento del workflow se explican [aquí](#funcionamiento-de-los-dos-workflows) (línea 20).  
Las herramientas y comandos se detallan [aquí](#documentación-de-las-herramientas-y-comandos-utilizados) (línea 3).  

### h. Integración continua
Justifica por qué el workflow utilizado es CI.  
¿Qué evento dispara automáticamente la generación/actualización de la documentación (p. ej., push, pull_request, workflow_dispatch)?

Es de integración continua porque se encarga de generar la documentación automáticamente sin que tenga que hacerlo el usuario manualmente. El workflow de ci.yaml se activa cuando se hace push a la rama main o cuando se ejecuta manualmente en Actions (workflow_dispatch). 

## Evidencia de la conexión a github mediante SSH  
![Prueba de conexión SSH](imagenes/pruebaSSH.png)

## Enlaces a la documentación de las herramientas y actions utilizados.
Herramientas utilizadas
- [Estilo de documentación javadoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)    

Actions utilizados
- [actions/checkout@v3](https://github.com/actions/checkout)  
- [actions/setup-java@v5](https://github.com/actions/setup-java)  
- [stefanzweifel/git-auto-commit-action@v4](https://github.com/stefanzweifel/git-auto-commit-action)   
- [mattnotmitt/doxygen-action@v1.9.5](https://github.com/mattnotmitt/doxygen-action)


## Como usar el repositorio para reproducir la generación de documentación
Primero hay que hacer fork del repositorio [aquí](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/fork).  
Para generar la documentación del repositorio no hace falta clonarlo, se puede hacer manualmente. Para generar la documentación manualmente, en el repo en la pestaña Actions se puede acceder a los workflows utilizados. Debes elegir el workflow llamado "CI con documentación automática mediante Doxygen" y dentro, aparece una opción para poder ejecutarlo y generar la documentación.  