# Práctica 1.1 documentación con github actions


## Documentación

- **Herramientas usadas y comandos ejecutados**:

Para la creación automática de documentación he utilizado GitHub Actions como herramienta de integración continua. El proyecto está desarrollado con Java 21 y la documentación se genera utilizando JavaDoc, siguiendo el estilo Javadoc en la documentación del código. La generación de la documentación se realiza mediante el comando `javadoc -d docs src/main/java/*.java` usado en el workflow, que crea la documentación en formato HTML en la carpeta docs.   
Y para convertir la documentación generada por JavaDoc a PDF utilizo el paquete wkhtmltopdf, utilizando estos comandos las creo en la carpeta docs/pdf:
```
mkdir -p docs/pdf
wkhtmltopdf --enable-local-file-access docs/package-summary.html docs/pdf/documentacion-package.pdf
wkhtmltopdf --enable-local-file-access docs/index-all.html docs/pdf/documentacion-index-all.pdf
wkhtmltopdf --enable-local-file-access docs/allclasses-index.html docs/pdf/allclasses-index.pdf
wkhtmltopdf --enable-local-file-access docs/Main.html docs/pdf/documentacion-Main.pdf
```
 
- **Formatos generados de la documentación**:
  - HTML: Genero la documentación en formato HTML mediante JavaDoc, esta se genera el la carpeta docs/ ([enlace a la carpeta](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/tree/main/docs))
  - PDF: Convierto la documentación en formato HTML generada por JavaDoc a PDF. Los archivos convertidos se crean en la carpeta docs/pdf ([enlace a la carpeta](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/tree/main/docs/pdf))

- **Explicación del [workflow](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/blob/main/.github/workflows/ci.yaml)**:  
Los eventos que disparan el workflow son:
    - Hacer push a la rama main.
    - workflow_dispatch: permite ejecutar manualmente desde GitHub en [Actions](https://github.com/danielmi5/2526_DAW_u1_action_Practica1.1/actions/workflows/ci.yaml).
      
Pasos del job **auto-doc**:
1. **Checkout del repositorio**
   - Acción: `actions/checkout@v3`
   - Clona el repositorio.

2. **Configuración de Java**
   - Acción: `actions/setup-java@v5` (La versión es la última y también la única que soporta Java 21)
   - Configura Java 21 (Temurin).

3. **Generación de la documentación**

   * Comando: `javadoc -d docs src/main/java/*.java`
   * Genera la documentación HTML en la carpeta docs/.

4. **Commit automático**

   * Acción: `stefanzweifel/git-auto-commit-action@v4`
   * Hace commit de los cambios en la carpeta docs/ con el mensaje "Documentación JavaDoc en formato HTML actualizada".

5. **Instalación de wkhtmltopdf**  
   - Comando: `sudo apt install wkhtmltopdf`
   - Instala el paquete wkhtmltopdf
     

6. **Conversión de HTML a PDF**  
   - Comando: 
     ```
     mkdir -p docs/pdf
     wkhtmltopdf --enable-local-file-access docs/package-summary.html docs/pdf/documentacion-package.pdf
     wkhtmltopdf --enable-local-file-access docs/index-all.html docs/pdf/documentacion-index-all.pdf
     wkhtmltopdf --enable-local-file-access docs/allclasses-index.html docs/pdf/allclasses-index.pdf
     wkhtmltopdf --enable-local-file-access docs/Main.html docs/pdf/documentacion-Main.pdf
     ```
    - Convierte varios archivos html generados en la documentación de JavaDoc a pdf.

7. **Commit automático de la documentación PDF**  
   - Acción: `stefanzweifel/git-auto-commit-action@v4`  
   - Hace commit de los cambios en la carpeta docs/pdf/ con el mensaje "Documentación en formato PDF actualizada"

- **Evidencia de la conexión a github mediante SSH**:
![Prueba de conexión SSH](imagenes/pruebaSSH.png)

## Preguntas

### a. Identificación de herramientas de generación de documentación
¿Qué herramienta o generador (p. ej., Sphinx, pdoc, Javadoc, Doxygen, Dokka) utilizaste en el workflow para crear la documentación en /docs?
Para generar la documentación en la carpeta docs, utilicé JavaDoc para extraer los comentarios (con el estilo Javadoc) del código y generar la documentación en formato HTML. Después, utilicé la herramienta wkhtmltopdf para convertir esos archivos HTML en documentos PDF, guardándose en la carpeta docs/pdf.

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



### d. Colaboración
Explica cómo GitHub facilita mantener la documentación (actualizaciones del README.md y de /docs) cuando colaboran varias personas (PRs, reviews, checks de CI, protección de ramas).


### e. Control de versiones
Muestra mensajes de commit que evidencien el nuevo workflow.  
¿Son claros y descriptivos? Justifícalo. Además, incluye un conjunto de mensajes de tus commits.


### f. Accesibilidad y seguridad
¿Qué medidas/configuración del repositorio garantizan que solo personal autorizado accede al código y la documentación?  
(p. ej., repositorio privado, equipos, roles, claves/secretos, branch protection).



### g. Instalación/uso documentados
Indica dónde en el README.md explicas el funcionamiento del workflow y dónde detallas las herramientas y comandos de documentación.


### h. Integración continua
Justifica por qué el workflow utilizado es CI.  
¿Qué evento dispara automáticamente la generación/actualización de la documentación (p. ej., push, pull_request, workflow_dispatch)?

## Conclusiones
