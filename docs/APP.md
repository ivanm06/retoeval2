# JaiSKIbel APP
---

# Cómo ejecutar?


1. Compilar usando maven
    ```bash
    $ mvn clean package
    ```
2. Ejectar usando java
    ```bash
    $ java -jar target/jaiskibel-1.0.jar
    ```
---
# Documentación:

## Main

### `Main`

- **Métodos:**
  - `public static void main(String[] args)`: Método principal que inicia la aplicación.
  - `private static boolean conectarDB(int intento)`: Intenta conectar a la base de datos.

## Utils

### `MenuUtil`

- **Atributos:**
  - `public static final Scanner sc`: Scanner para la entrada del usuario.

- **Métodos:**
  - `public static char calcularLetraDNI(int dni)`: Calcula la letra de un número de DNI.
  - `public static boolean checkDNI(String dni)`: Comprueba si un DNI es válido.
  - `public static String getString(String txt)`: Pide al usuario un `String` con un mensaje específico.
  - `public static String getString()`: Pide al usuario un `String`.
  - `public static int getInt(String txt)`: Pide al usuario un `int` con un mensaje específico.
  - `public static int getInt()`: Pide al usuario un `int`.
  - `public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str)`: Convierte un `String` a un `Enum`.
  - `public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str, T def)`: Convierte un `String` a un `Enum` con un valor por defecto.
  - `public static <T extends Enum<T>> T getEnum(Class<T> enumClass, String txt)`: Pide al usuario un `String` y lo convierte a `Enum`.
  - `public static void generateTable(String[] titles, ArrayList<String[]> vals)`: Genera una tabla en formato ascii y la imprime en la consola.

### `Password`

- **Métodos:**
  - `public static String hash(String password)`: Hashea la contraseña usando la dependencia Bcrypt.
  - `public static boolean check(String password, String hash)`: Verifica si un hash pertenece a una contraseña usando la dependencia Bcrypt.
  - `public static String read(String text)`: Lee la contraseña insertada por el usuario y oculta su input.
  - `private static boolean validate(String pass)`: Valida una contraseña. La contraseña debe tener: 8 caracteres, entre los cuales: al menos una mayúscula, una minúscula y un número.
  - 
## Menus

### `AdminMenu`

- **Métodos:**
  - `public void start()`: Inicia el menú de administrador.
  - `private void continuar()`: Continúa con las opciones del menú de administrador.
  - `private void showMenu()`: Muestra el menú de estadísticas.
  - `private void menuReservas()`: Muestra las reservas y permite ver detalles de una reserva específica.
  - `private void mostrarIngresosTotales()`: Muestra los ingresos totales.

### `AuthMenu`

- **Atributos:**
  - `private static Usuario usuario`: Usuario autenticado.

- **Métodos:**
  - `public void start()`: Inicia el menú de autenticación.
  - `public void login()`: Maneja el proceso de inicio de sesión.
  - `public void register()`: Maneja el proceso de registro de un nuevo usuario.
  - `public static Usuario getUsuario()`: Devuelve el usuario autenticado.

### `ClientMenu`

- **Métodos:**
  - `public void start()`: Inicia el menú de cliente.
  - `private void mostrarReservas()`: Muestra las reservas del cliente.
  - `private void menuReserva()`: Muestra el menú de reserva.
  - `private boolean menuEstablecimiento(Establecimiento establecimiento)`: Muestra el menú de establecimiento.
  - `private void mostrarEstablecimientos(ArrayList<Establecimiento> establecimientos)`: Muestra los establecimientos disponibles.

### `ReservaMenu`

- **Métodos:**
  - `public void start()`: Inicia el menú de reserva.
  - `private void mostrarArticulos(Fecha fecha)`: Muestra los artículos en la cesta y el precio total.
  - `private void reservar(Fecha fecha)`: Reserva los artículos en la cesta.

## Database

### `ArticuloRepo`

- **Métodos:**
  - `public static ArrayList<Articulo> getSkis(int idEstablecimiento)`: Obtiene una lista de esquís de un establecimiento específico.
  - `public static ArrayList<Articulo> getSnowboards(int idEstablecimiento)`: Obtiene una lista de snowboards de un establecimiento específico.
  - `public static ArrayList<Articulo> getAccesorios(int idEstablecimiento)`: Obtiene una lista de accesorios de un establecimiento específico.
  - `public static HashMap<Integer, Integer> getStock(int idEstablecimiento)`: Obtiene el stock de artículos de un establecimiento específico.

### `AuthRepo`

- **Métodos:**
  - `public static boolean checkPassword(String dni, String password)`: Comprueba si la contraseña es correcta comparandola con el hash guardado en la base de datos usando la dependencia **Bcrypt**.
  - `public static Usuario getUsuario(String dni)`: Obtiene un usuario por su DNI.
  - `public static boolean registrar(Usuario user, String pass)`: Registra un nuevo usuario en la base de datos.
  - `public static ArrayList<Usuario> getUsuarios()`: Obtiene una lista de usuarios.
  - `public static void eliminarUsuario(String dni)`: Elimina un usuario por su DNI.

### `DBConnector`

- **Atributos:**
  - `public static Connection con`: Conexión a la base de datos.

- **Métodos:**
  - `public static void start()`: Inicia la conexión a la base de datos.
  - `public static void close()`: Cierra la conexión a la base de datos.

### `EstablecimientoRepo`

- **Métodos:**
  - `public static ArrayList<Establecimiento> getEstablecimientos()`: Obtiene una lista de establecimientos.

### `EstadisticasRepo`

- **Métodos:**
  - `public static ArrayList<String[]> getReservasPorDinero()`: Obtiene una lista de reservas ordenadas por dinero.
  - `public static ArrayList<String[]> getReservas()`: Obtiene una lista de reservas.
  - `public static ArrayList<String[]> getArticulosMasPedidos()`: Obtiene una lista de los artículos más pedidos.
  - `public static ArrayList<String[]> getClientesHabituales()`: Obtiene una lista de los clientes habituales.
  - `public static int getNumeroClientes()`: Obtiene el número total de clientes.
  - `public static int getIngresosTotales()`: Obtiene los ingresos totales.

### `ReservaRepo`

- **Métodos:**
  - `public static boolean reservar(ArrayList<ArticuloReserva> articulos, Fecha fecha)`: Añade una nueva reserva.
  - `public static ArrayList<ArticuloReserva> getArticulosFromReserva(int idReserva)`: Obtiene los artículos de una reserva.
  - `public static ArrayList<Reserva> getReservasDeCliente(String dni)`: Obtiene las reservas de un cliente.
  - `public static void anularReserva(int reservaID)`: Anula una reserva.

## Model

### `Articulo`

- **Atributos:**
  - `private int id`: ID del artículo.
  - `private String nombre`: Nombre del artículo.
  - `private String descripcion`: Descripción del artículo.
  - `private int precio`: Precio del artículo.
  - `private Talla talla`: Talla del artículo.
  - `private TipoArticulo tipoArticulo`: Tipo de artículo.

### `ArticuloReserva` extends `Articulo`

### `Snowboard` extends `Articulo`

- **Atributos:**
  - `private final Modalidad modalidad`: Modalidad del snowboard.

### `Ski` extends `Articulo`

- **Atributos:**
  - `private final Modalidad modalidad`: Modalidad del snowboard.
  - `private final Nivel nivel`: Nivel del esquí.

### `Accesorio` extends `Articulo`

- **Atributos:**
  - `private final String tipo`: Tipo de accesorio.

### `Establecimiento`

- **Atributos:**
  - `private int id`: ID del establecimiento.
  - `private String nombre`: Nombre del establecimiento.
  - `private String direccion`: Dirección del establecimiento.
  - `private HashMap<Integer, Integer> stock`: Stock de artículos en el establecimiento.

- **Métodos:**
  - `public HashMap<Integer, Integer> getStock()`: Devuelve el stock de artículos en el establecimiento.
  - `public void setStock(HashMap<Integer, Integer> stock)`: Establece el stock de artículos en el establecimiento.
  
### `Usuario`

- **Atributos:**
  - `private String nombre`: Nombre del usuario.
  - `private String apellido1`: Primer apellido del usuario.
  - `private String apellido2`: Segundo apellido del usuario.
  - `private String dni`: DNI del usuario.
  - `private Sexo sexo`: Sexo del usuario.
  - `private Privilegio privilegio`: Privilegio del usuario.

### `Cesta`

- **Atributos:**
  - `private static final ArrayList<ArticuloReserva> cesta`: Artículos en la cesta.
  - `private static Establecimiento establecimiento`: Establecimiento de la cesta.
  - `private static Fecha fecha`: Fecha de la reserva.
  
- **Métodos:**
  - `public static void addToCesta(ArticuloReserva art)`: Añade artículos a la cesta, si la cesta ya contiene ese artículo, le suma la cantidad extra.
  - `public static void vaciar()`: Vacía la cesta.
### `Fecha`

- **Atributos:**
  - `private Timestamp fechaInicio`: Fecha de inicio de la reserva.
  - `private Timestamp fechaFin`: Fecha de fin de la reserva.
  
- **Métodos:**
  - `public int getDias()`: Devuelve el número de días de la reserva.

### `Reserva`

- **Atributos:**
  - `private int id`: ID de la reserva.
  - `private Fecha fecha`: Fecha de la reserva.
  - `private String establecimiento`: Nombre del establecimiento de la reserva.
  - `private int idEstablecimiento`: ID del establecimiento de la reserva.
  - `private int articulos`: Número de artículos de la reserva.
  - `private int precio`: Precio total.