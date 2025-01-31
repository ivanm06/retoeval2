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

## Menus

### `AdminMenu`

- **Métodos:**
  - `public void start()`: Inicia el menú de administrador.
  - `private void continuar()`: Continúa con las opciones del menú de administrador.
  - `private void showMenu()`: Muestra el menú de estadísticas.
  - `private void menuReservas()`: Muestra las reservas y permite ver detalles de una reserva específica.

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

## Database

### `ArticuloRepo`

- **Métodos:**
  - `public static ArrayList<Articulo> getSkis(int idEstablecimiento)`: Obtiene una lista de esquís de un establecimiento específico.
  - `public static ArrayList<Articulo> getSnowboards(int idEstablecimiento)`: Obtiene una lista de snowboards de un establecimiento específico.
  - `public static ArrayList<Articulo> getAccesorios(int idEstablecimiento)`: Obtiene una lista de accesorios de un establecimiento específico.
  - `public static HashMap<Integer, Integer> getStock(int idEstablecimiento)`: Obtiene el stock de artículos de un establecimiento específico.

### `ReservaRepo`

- **Métodos:**
  - `public static ArrayList<Reserva> getReservas()`: Obtiene una lista de reservas.
  - `public static Reserva getReserva(int id)`: Obtiene una reserva específica por su ID.
  - `public static boolean addReserva(Reserva reserva)`: Añade una nueva reserva.
  - `public static boolean updateReserva(Reserva reserva)`: Actualiza una reserva existente.
  - `public static boolean deleteReserva(int id)`: Elimina una reserva por su ID.

### `EstadisticasRepo`

- **Métodos:**
  - `public static ArrayList<String[]> getReservasPorDinero()`: Obtiene una lista de reservas ordenadas por dinero.
  - `public static ArrayList<String[]> getReservas()`: Obtiene una lista de reservas.
  - `public static ArrayList<String[]> getArticulosMasPedidos()`: Obtiene una lista de los artículos más pedidos.
  - `public static ArrayList<String[]> getClientesHabituales()`: Obtiene una lista de los clientes habituales.
  - `public static int getNumeroClientes()`: Obtiene el número total de clientes.

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