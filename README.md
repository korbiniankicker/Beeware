# Beeware (CS50 final project)
#### Video Demo:  <PASTE_URL_HERE>
#### Description:

Beeware is a small desktop tower-defense game written in Java. The game uses the Swing UI toolkit (Java2D rendering inside a Swing component) and is designed to be distributed as a runnable `.jar` file. In Beeware, the player defends a beehive-themed map by placing towers on a tile grid and surviving enemy ant waves that attack the beehive. As enemies are defeated, the player gains resources (honeycomb) that can be spent to place additional towers and upgrade existing ones. The game ends when the player’s health reaches zero (aka. no guard bees are left).

This project was built to practice object-oriented programming, game-loop structure, state management, and packaging a Java application properly. I decided to use Java due to object-oriented programming providing a good logical structure for the game through aspects like inheritence and because we learned and used it in class during years 10 to 12. I used maven (though I didn't use any dependecies) for packaging and compiling the project, learning some of it's core features as it is a useful tool when working with java applications.

### How to build and run

#### Requirements
- Java 8
- Maven

#### Build

```bash
mvn package
```

This produces output artifacts in `target/`:
 `target/beeware-1.0.0-SNAPSHOT.jar`, the standard JAR with your compiled classes and resources and

#### Run

```bash
java -jar target/beeware-1.0.0.jar
```

### Assets

Game images are stored in `src/main/resources/rsc/` and loaded from the classpath at runtime. This matters because loading assets from the filesystem (for example `"rsc/SomeImage.png"`) typically breaks once the game is packaged into a JAR. By loading via the classpath, the same code works when running from an IDE and when running from a built JAR, which makes distribution much simpler.

### Project structure

The source code follows a standard Maven layout:
- `src/main/java/`: Java source code
- `src/main/resources/`: non-code resources (images, etc.)

Within `src/main/java/com/koko/beeware/`, the project is organized into packages based on responsibility:

#### `com.koko.beeware`
- `Main`: program entry point. It initializes the asset loader, game state, game loop, and UI.

#### `com.koko.beeware.assets`
- `Bilder`: the centralized asset loader/registry for textures and sprite frames. It loads PNG images from the resources folder and stores them in static fields/arrays for use across the game (e.g., tile textures, enemy animation frames, tower textures). Keeping asset loading in one place makes it easier to troubleshoot missing resources and keep rendering code focused on drawing.

#### `com.koko.beeware.ui`
- `Gui`: sets up the main `JFrame`, installs input listeners, and attaches the main drawing surface.
- `Label`: the main rendering component (a `JLabel` subclass) that draws the current game state. It renders different screens depending on the game mode (main menu, in-game, game over).
- `MainMenu`: manages main-menu state and rendering.

#### `com.koko.beeware.input`
- `KeyHandler`: keyboard input hook (present for future expansion).
- `MouseMotionHandler`: tracks the mouse and calculates which tile the cursor is currently hovering over.
- `MouseHandler`: handles mouse clicks for tower placement and tower/trap selection menus.

#### `com.koko.beeware.game`
- `Game`: the central game state container. It holds shared objects like the grid, the player, enemy arrays, tower arrays, projectile arrays, buttons, and menu state. This class acts as a “single source of truth” for what exists in the current session.
- `GameLoop`: the fixed-timestep update loop. On each tick it advances enemy movement and animations, spawns enemies during active waves, runs tower targeting/attacks, updates projectiles/effects, and checks for game-over conditions.
- `WaveHandler`: manages wave state (current wave number, whether a wave is active, and wave time remaining). It also contains wave-spawn logic and ramps difficulty over time.
- `ActionHandler`: handles button click events (start game, open/close menu, choose tower types, start wave, upgrade/sell actions, and returning to main menu).
- `buttonHandler`: tracks hover/pressed state for UI buttons and draws the correct button sprite depending on the hover state.

#### `com.koko.beeware.world`
- `TileGrid`, `Tile`, `TileType`: implement the tile-based world. The grid stores tiles with types like grass/path/tower/trap, and the renderer draws them using the textures loaded by `Bilder`.
- `Lvl`: an enum representing tower upgrade levels and balance parameters (price, damage, cooldown, etc.).
- `Obsticle`: static map obstacles that occupy tiles and affect placement/pathing.

#### `com.koko.beeware.entities`
- `Enemy` (base class) plus `WorkerAnt`, `MajorAnt`, `SoliderAnt`, `Drone`, `StickTank`: enemy units with different stats and animation behavior.
- `EnemyLvl`: defines per-wave scaling tiers for enemies.
- `Player`: stores player stats (HP, honeycomb, selection state, max towers, etc.) and draws HUD stats such as wave number and the wave timer bar.
- `Projectile`: projectile behavior and collision logic.
- `Bee`: a decorative/feedback entity tied to player health.

#### `com.koko.beeware.towers`
- `Tower` (base class): shared tower behavior such as spawning, targeting, cooldowns, upgrade rules, and rendering.
- `Catapult`, `Honeybomb`, `HoneyPuddleThrower`: concrete tower implementations with different behaviors (direct shots, explosive shots, slow/puddle effects).
- `Trap`, `Combspike`: trap-style placements (detonate/trigger-based).
- `HoneyExplosion`, `HoneyPuddle`: effect entities used by certain towers/projectiles.

### Design choices and trade-offs


#### Centralized game state
The current design uses a central `Game` class with many shared/static objects. This is straightforward for a small game because any system (UI, loop, entities) can access the current state. The main trade-off is that global state can make the code harder to reason about as the project grows. A future improvement would be to replace some of the global/static access with a `GameState` instance passed into the loop, renderer, and handlers to improve encapsulation and testability.

#### Fixed tick loop
The game uses a fixed update tick in `GameLoop`. This is a common pattern for simple games because it keeps movement and cooldown timing consistent. A future improvement would be to separate update and render responsibilities more strictly (and to align Swing UI updates with Swing’s event thread model), but for the scale of this project, the approach keeps the logic accessible.

### Notes for submission
- Replace `<PASTE_URL_HERE>` above with your actual video demo URL.
- The distributable artifact is `target/beeware-1.0.0-SNAPSHOT-all.jar`.

