# TransportePublico
 
Link al repositorio: https://github.com/siraglez/TransportePublico.git

# Aplicación Transporte Público
Esta aplicación permite que un usuario inicie sesión y muestra la información de las paradas de transporte público.

---

## 1. Paquete `activity`
Este paquete contiene las actividades principales de la aplicación que gestionan las interfaces de usuario y las interacciones principales.
- `LoginActivity`: 
  - Responsable del inicio de sesión y registro de usuarios.
  - Usa Firebase Firestore para autenticar y registrar usuarios.
  - Los usuarios pueden iniciar sesión verificando su correo y contraseña almacenados en la base de datos, o registrarse si no tienen cuenta.
  - Incluye validaciones básicas, como verificar que los campos no estén vacíos y que las contraseñas cumplan requisitos mínimos.
  - Tras un inicio de sesión exitoso, redirige a la `MainActivity`.
- `MainActivity`:
  - Muestra una lista de paradas de transporte público en tiempo real.
  - Usa el 'DataRepository' para obtener los datos de las paradas desde Firebase Firestore.
  - Filtra y organiza las paradas para evitar duplicados.
  - Contiene un botón para acceder a la `SettingsActivity`.
- `SettingsActivity`:
  - Administra la configuración del usuario. 
  - Proporciona una funcionalidad para cerrar sesión mediante Firebase Authentication.
  - Redirige al usuario de vuelta a la `LoginActivity` tras cerrar sesión.

---

## 2. Paquete `dataClasses`
Este paquete contiene las clases de datos que representan las entidades principales del sistema.
- `Linea`:
  - Representa una línea de transporte público.
  - Contiene un identificador único, el número de la línea y una lista de identificadores de paradas asociadas.
- `Parada`:
  - Representa una parada de transporte público.
  - Incluye información como el identificador, nombre, líneas asociadas y horarios.
- `Usuario`:
  - Representa los datos del usuario.
  - Contiene el identificador del usuario, correo electrónico y contraseña.

--- 

## 3. Paquete `repository`
Centraliza el acceso a los datos de la base de datos.
- `DataRepository`:
  - Se encarga de interactuar con Firebase Firestore para obtener las paradas en tiempo real.
  - Usa `addSnapshotListener` para escuchar cambios en los datos de las paradas.
  - Proporciona un callback para devolver la lista de paradas o un mensaje de error.

---

## 4. Paquete `utils`
Contiene utilidades generales que pueden ser reutilizadas en toda la aplicación.
- `NotificationUtils`:
  - Gestiona las notificaciones del sistema.
  - Crea un canal de notificaciones para dispositivos Android.
  - Permite enviar notifcaciones personalizadas con un título, mensaje y prioridad alta.

---

## 5. Paquete `views`
Define vistas personalizadas para la interfaz gráfica.
- `ParadaView`:
  - Personaliza la visualización de una parada de transporte público.
  - Muestra información como el nombre de la parada, las líneas asociadas y los horarios.
  - Usa un diseño inflado desde un recurso XML (`item_parada.xml`).

---

## 6. Flujo general de la aplicación
1. El usuario inicia la aplicación y es recibido por la `LoginActivity`:
   - Puede iniciar sesión o registrarse.
   - Tras autenticarse correctamente, se redirige a `MainActivity`.
2. En la `MainActivity`, se cargan y muestran en tiempo real las paradas de transporte público desde Firebase Firestore.
   - Cada parada se visualiza usando la vista personalizada `ParadaView`.
3. Desde la `MainActivity`, el usuario puede:
   - Configurar ajustes en la `SettingsActivity`, incluyendo cerrar sesión.
   - Ver notificaciones relevantes gracias a `NotificationUtils`.
4. El repositorio `DataRepository` se encarga de interactuar con la base de datos, garantizando que los datos de paradas se mantengan actualizados en tiempo real.
