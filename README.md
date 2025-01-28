# JaiSKIBel - Sistema de Reservas de Equipos de Esquí y Snowboard

## Descripción del Proyecto
JaiSKIBel es un sistema desarrollado para gestionar el alquiler de equipos de esquí y snowboard de forma digital. El proyecto incluye:
- Una aplicación backend para la gestión de reservas, diseñada sin interfaz gráfica directa según los requisitos del cliente.
- Un sitio web adaptativo que actúa como escaparate virtual para los servicios de la empresa y permite la simulación de reservas.

Este proyecto se desarrolla como parte del Grado Superior en Desarrollo de Aplicaciones Multiplataforma en CIFP Plaiaundi LHII, bajo los principios de Scrum y utilizando herramientas modernas de desarrollo colaborativo.

---

## Características Principales
### Aplicación Backend
- Gestión de reservas almacenadas en una base de datos relacional (MySQL).
- Funcionalidades de inicio de sesión y creación de cuentas de usuario.
- Roles diferenciados: usuario y administrador.
  - Los administradores pueden ver estadísticas de la empresa como dinero generado o clientes habituales.
- Persistencia de datos mediante scripts SQL, con control de privilegios.

### Sitio Web
- Diseño adaptativo con páginas informativas y un formulario de simulación de reservas.
- Página de visualización de reservas utilizando transformación XML (XSLT) y validación mediante XSD.

---

## Requisitos Técnicos
### Tecnologías Utilizadas
- **Backend**: Java con programación orientada a objetos.
- **Base de Datos**: MySQL.
- **Frontend**: HTML, CSS, y JavaScript.
- **Gestión de Versiones**: Git y GitHub.
- **Gestión de Proyectos**: Scrum y Kanban, implementados con Trello.

### Entregas por Sprint
1. **Sprint 1:**
   - Diseño de la base de datos normalizada y diagrama entidad/relación.
   - Diagrama de clases y diseño básico del sitio web.
2. **Sprint 2:**
   - Implementación inicial de la aplicación y el sitio web.
3. **Sprint Final:**
   - Auditorías, correcciones, finalización del desarrollo y generación de documentación.

---

## Instalación y Uso
### Requisitos Previos
- JDK 17 o superior.
- Servidor MySQL.
- Navegador web compatible con HTML5.
- Git instalado para clonar el repositorio.

### Pasos para la Configuración
1. Clona este repositorio:
   ```bash
   git clone https://github.com/ivanm06/retoeval2.git
   ```
2. Configura la base de datos:
   - Ejecuta los scripts SQL en el directorio `/sql` para crear y poblar las tablas.
   - Creacion de los usuarios:
    ```sql
        CREATE USER 'dbadmin'@'%' IDENTIFIED BY 'contrasenaAdmin';
        GRANT ALL PRIVILEGES ON jaiskibel.* TO 'dbadmin'@'%';

        CREATE USER 'jaiskibel'@'%' IDENTIFIED BY 'ContrasenaUsuarioApp';
        GRANT SELECT ON jaiskibel.* TO 'jaiskibel'@'%';
        GRANT INSERT ON jaiskibel.articuloReservado TO 'jaiskibel'@'%';
        GRANT INSERT ON jaiskibel.Reserva TO 'jaiskibel'@'%';
        GRANT INSERT ON jaiskibel.Usuario TO 'jaiskibel'@'%';

        FLUSH PRIVILEGES;
    ```
3. Configura el backend:
   - Compila y ejecuta la aplicación situada en `/app` con tu IDE preferido o desde la línea de comandos.
4. Abre el sitio web:
   - Navega al archivo `index.html` en el directorio `/web` para explorar las funcionalidades.

---

## Organización del Proyecto
### Metodología
El desarrollo se gestiona utilizando Scrum con las siguientes herramientas:
- **Kanban**: Tablero en Trello con columnas para Backlog, En Progreso, Revisión y Hecho.
- **Sprints:**
  - **Sprint 1:** Diseño inicial.
  - **Sprint 2:** Desarrollo principal.
  - **Sprint Final:** Auditorías, correcciones y documentación.

### Documentación Generada
- Diagramas de clases.
- Diagramas entidad/relación (E/R).
- Capturas del tablero de gestión para cada sprint.

---

## Licencia
Este proyecto está licenciado bajo la licencia [Creative Commons BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/).

---

## Contacto
Para preguntas o soporte, por favor, contacta con:
- Equipo de Desarrollo **JaiSKIBel**
- Correo: info@1dam3.es
