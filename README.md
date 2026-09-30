# proyectoFinalDnD
# 🎲 VTT Asistido para D&D

Aplicación multiplataforma de asistencia para partidas de **Dungeons & Dragons (D&D)** diseñada para complementar la experiencia de juego presencial, automatizando cálculos, gestión de personajes, combate y sincronización entre jugadores y Dungeon Master (DM).

El objetivo no es sustituir la mesa física ni convertir D&D en un videojuego, sino **reducir la carga administrativa de la partida y facilitar al DM y a los jugadores la gestión de la sesión**.

---

## 👥 Equipo

* **Victor**
* **Marcelino**
* **Luis**

Proyecto desarrollado como **Trabajo de Fin de Grado de 2º de Desarrollo de Aplicaciones Multiplataforma (DAM)**.

---

## 🎯 Objetivo del proyecto

Los juegos de rol como D&D requieren gestionar durante una partida una gran cantidad de información:

* Puntos de vida
* Inventario
* Hechizos y recursos
* Modificadores
* Iniciativa
* Estados y condiciones
* Combate
* Mapas
* Tiradas
* Información privada del Dungeon Master

Esta aplicación pretende centralizar y automatizar parte de esta gestión sin eliminar la interacción física y narrativa característica de una partida de rol.

### Filosofía del proyecto

> **La aplicación ayuda a jugar, pero no juega por ti.**

El Dungeon Master mantiene el control narrativo de la partida y puede modificar o ignorar las reglas automatizadas cuando la situación lo requiera.

---

## 🧩 Características principales

### 👤 Gestión de personajes

Cada jugador podrá gestionar su personaje desde la aplicación.

Entre los datos disponibles se incluyen:

* Nombre
* Clase
* Raza
* Nivel
* Características
* Puntos de vida
* Inventario
* Equipamiento
* Hechizos
* Recursos
* Condiciones temporales

La aplicación utilizará esta información para calcular automáticamente los modificadores correspondientes a las acciones del personaje.

---

### 🎲 Sistema de tiradas

La aplicación contempla dos formas de realizar las tiradas.

#### 🪙 Modo Mesa Real

Pensado para partidas presenciales.

El jugador utiliza sus dados físicos y únicamente introduce en la aplicación el resultado obtenido.

Ejemplo:

```text
Ataque con espada

D20: 17

Fuerza:       +3
Competencia:  +2
Arma mágica:  +1
----------------
Resultado:     23
```

De esta forma se mantiene la experiencia física de lanzar los dados mientras la aplicación se encarga de realizar los cálculos.

#### 📱 Modo Digital

Como alternativa, la aplicación podrá permitir realizar tiradas digitales mediante el dispositivo.

Se contempla el uso de:

* Acelerómetro
* Giroscopio
* Animaciones
* Feedback háptico

El objetivo es proporcionar una experiencia similar a agitar y lanzar un dado físico.

---

## 🗺️ Mapa compartido

El sistema incorporará un visor de mapas basado en **HTML5 Canvas**.

El Dungeon Master podrá cargar un mapa y controlar qué partes pueden ver los jugadores.

### 🌫️ Niebla de guerra

El mapa comenzará cubierto por una capa de niebla.

El DM podrá revelar zonas utilizando el ratón o la pantalla táctil.

En lugar de enviar constantemente imágenes completas al resto de jugadores, se transmitirán las acciones realizadas sobre la niebla.

Ejemplo:

```json
{
  "type": "FOG_ERASE",
  "x": 523,
  "y": 281,
  "radius": 60
}
```

Cada cliente interpretará esta información y actualizará su propio Canvas.

Esto reduce la cantidad de información transmitida durante la partida.

---

## ⚡ Comunicación en tiempo real

Las partidas requieren que los cambios realizados por el DM y los jugadores se reflejen rápidamente.

Para ello se utilizará comunicación mediante **WebSockets**.

Algunos de los eventos que podrán sincronizarse son:

```text
PLAYER_JOINED
PLAYER_LEFT
FOG_UPDATE
PING
COMBAT_UPDATE
HP_UPDATE
CONDITION_UPDATE
TURN_CHANGE
```

---

## 📍 Sistema de Ping

Los jugadores podrán señalar una posición del mapa mediante un sistema de **Ping**.

El indicador será visible temporalmente para el resto de jugadores y el DM.

Esto permitirá:

* Señalar una posición
* Indicar dónde quiere moverse un personaje
* Señalar un enemigo
* Indicar dónde lanzar un hechizo
* Coordinar acciones durante el combate

---

## ⚔️ Sistema de combate

El Dungeon Master dispondrá de un panel para gestionar los combates.

El sistema permitirá:

* Ordenar la iniciativa
* Añadir personajes
* Añadir enemigos
* Modificar puntos de vida
* Gestionar turnos
* Aplicar condiciones
* Añadir modificadores
* Eliminar combatientes

Ejemplo:

```text
┌──────────────────────────────┐
│          INICIATIVA          │
├──────────────────────────────┤
│ 1. Guerrero       21         │
│ 2. Goblin         17         │
│ 3. Mago           14         │
│ 4. Orco            10        │
└──────────────────────────────┘
```

La información de los enemigos podrá mostrarse a los jugadores de forma simplificada.

```text
Goblin

🟢 Ileso
🟡 Herido
🟠 Malherido
🔴 Moribundo
```

En lugar de mostrar necesariamente sus puntos de vida exactos.

---

## 🧙 Gestión de condiciones

Los personajes podrán recibir diferentes estados durante una partida.

Ejemplos:

* Envenenado
* Paralizado
* Bendecido
* Aturdido
* Cegado

La aplicación podrá tener en cuenta estos estados a la hora de realizar determinadas acciones.

Ejemplo:

```text
✨ Bendecido

Próxima tirada:
+1d4
```

El sistema actuará como asistente y podrá sugerir los modificadores correspondientes, manteniendo siempre la posibilidad de que el DM realice modificaciones manuales.

---

## 🎒 Inventario y equipamiento

Los jugadores podrán gestionar los objetos de sus personajes.

El inventario podrá incluir:

* Armas
* Armaduras
* Objetos
* Objetos mágicos
* Consumibles
* Equipamiento

La aplicación podrá detectar determinadas situaciones y mostrar advertencias.

Ejemplo:

```text
⚠️ Advertencia

El personaje no tiene competencia
con esta armadura.

[Continuar igualmente]
```

El sistema no pretende impedir las decisiones del jugador.

---

## 👑 Panel del Dungeon Master

El DM dispondrá de una interfaz específica con mayor control sobre la partida.

Desde ella podrá:

* Crear partidas
* Gestionar jugadores
* Controlar el mapa
* Gestionar la niebla de guerra
* Administrar el combate
* Modificar puntos de vida
* Aplicar condiciones
* Realizar tiradas ocultas
* Aplicar modificadores manuales
* Sobrescribir determinadas reglas

### 🎭 Control narrativo

Una de las características principales del proyecto es que el DM mantiene el control de la partida.

Las reglas automatizadas sirven como asistencia, pero no bloquean las decisiones narrativas.

Ejemplo:

```text
Resultado calculado: 8

Modificador manual del DM:
+5

Resultado final: 13
```

---

## 🔐 Usuarios y permisos

La aplicación contará con diferentes roles.

### Dungeon Master

Dispone de control sobre la sesión:

* Crear partidas
* Gestionar jugadores
* Gestionar mapas
* Gestionar combates
* Realizar tiradas privadas
* Aplicar modificaciones

### Jugador

Puede:

* Gestionar su personaje
* Consultar su inventario
* Consultar sus hechizos
* Realizar tiradas
* Consultar el mapa permitido
* Utilizar el sistema de Ping

---

## 🏗️ Arquitectura

El proyecto seguirá una arquitectura cliente-servidor.

```text
                 ┌─────────────────────┐
                 │       CLIENTE       │
                 │                     │
                 │ React + TypeScript  │
                 │        PWA          │
                 └──────────┬──────────┘
                            │
                    REST / WebSocket
                            │
                            ▼
                 ┌─────────────────────┐
                 │       BACKEND       │
                 │                     │
                 │    Spring Boot     │
                 │    Spring Security │
                 │    WebSocket       │
                 │    JPA             │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │      DATABASE       │
                 │        MySQL        │
                 └─────────────────────┘
```

---

## 🛠️ Tecnologías previstas

### Frontend

* React
* TypeScript
* HTML5
* CSS
* HTML5 Canvas
* Progressive Web App (PWA)

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* WebSocket
* Maven

### Base de datos

* MySQL

### Comunicación

* REST API
* WebSockets
* JSON

### Control de versiones

* Git
* GitHub

---

## 📱 Multiplataforma

La aplicación estará diseñada para funcionar desde diferentes dispositivos.

### Jugadores

Principalmente:

* Smartphone
* Tablet
* Ordenador

### Dungeon Master

Principalmente:

* Ordenador
* Tablet

El uso de una PWA permitirá acceder a la aplicación desde un navegador sin necesidad de instalar una aplicación nativa específica para cada plataforma.

---

## 🔄 Funcionamiento general

Una partida seguirá un flujo similar a:

```text
                 ┌──────────────┐
                 │      DM      │
                 └──────┬───────┘
                        │
                 Crear partida
                        │
                        ▼
                 Código de sesión
                        │
             ┌──────────┼──────────┐
             ▼          ▼          ▼
         Jugador 1  Jugador 2  Jugador 3
             │          │          │
             └──────────┼──────────┘
                        │
                  Partida activa
                        │
              ┌─────────┼─────────┐
              ▼         ▼         ▼
            Mapa     Combate    Tiradas
              │         │         │
              └─────────┼─────────┘
                        │
                   WebSockets
                        │
                        ▼
                Estado sincronizado
```

---

## 🎯 Alcance inicial (MVP)

Para mantener el proyecto viable como Trabajo de Fin de Grado, el desarrollo inicial se centrará en:

* [ ] Registro y autenticación
* [ ] Roles DM/Jugador
* [ ] Creación y gestión de personajes
* [ ] Inventario
* [ ] Sistema básico de hechizos
* [ ] Creación de partidas
* [ ] Código de acceso a partidas
* [ ] Sistema de tiradas asistidas
* [ ] Sistema básico de combate
* [ ] Gestión de puntos de vida
* [ ] Condiciones
* [ ] Mapa compartido
* [ ] Niebla de guerra
* [ ] Sistema de Ping
* [ ] Comunicación mediante WebSockets
* [ ] Panel de control del DM

Las funcionalidades más avanzadas podrán desarrollarse posteriormente dependiendo del tiempo disponible.

---

## 🚀 Posibles ampliaciones

Entre las futuras líneas de desarrollo se contemplan:

* Sistema offline-first completo
* Sincronización avanzada de datos
* Aplicación móvil nativa
* Mayor automatización de reglas
* Base de datos ampliada de criaturas y objetos
* Editor de mapas
* Tokens personalizados
* Efectos visuales
* Sonidos ambientales
* Integración con plataformas externas
* Funciones de inteligencia artificial
* Personalización avanzada de campañas

---

## 📂 Estructura prevista del proyecto

```text
vtt-dnd/
│
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   └── pom.xml
│
├── database/
│   └── scripts/
│
├── docs/
│   ├── arquitectura/
│   ├── diagramas/
│   └── memoria/
│
└── README.md
```

---

## 👨‍💻 Equipo de desarrollo

### Victor

Desarrollo y diseño de la aplicación.

### Marcelino

Desarrollo y diseño de la aplicación.

### Luis

Desarrollo y diseño de la aplicación.

El proyecto será desarrollado de forma colaborativa utilizando **Git y GitHub**, organizando el trabajo mediante ramas, commits y pull requests.

---

## 📜 Licencia

Proyecto académico desarrollado como parte del ciclo formativo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**.
