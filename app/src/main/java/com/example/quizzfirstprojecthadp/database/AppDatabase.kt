package com.example.quizzfirstprojecthadp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Game::class, Player::class, Question::class, QuestionSaved::class, Score::class, Settings::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val gameDao: GameDao

    abstract val playerDao: PlayerDao

    abstract val questionDao: QuestionDao

    abstract val questionSavedDao: QuestionSavedDao

    abstract val scoreDao: ScoreDao

    abstract val settingsDao: SettingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //Empty question
                db.execSQL("INSERT INTO questions (question_id, question, category, option_1, option_2, option_3, option_4) VALUES (0, 'This is an empty question', 0, '', '', '', '')")
                //Anime
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Contra quién pelea Baki antes de pelear con su padre?', 1,'Mohammad Alai Jr.','Floyd Maywother','Mohammad Alai','The Boogerman')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿A quién llaman \"Clockwork Thumbelina\"?', 1,'Rizu Ogata','Chika Fujiwara','Seishirou Tsugumi','Eiai Nano')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Quién hace \"Wa ha ha\"?', 1,'Hitori Bocchi','Nishikata Chi','Yoshimoto Shizuka','Tomoko Kuroki')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('\"La novia eficiente\" de Rentarou', 1,'Eiai Nano','Yoshimoto Shizuka','Inda Karane','Hanazono Hakari')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Qué modelo de Fender toca Azusa en K On!?', 1,'Mustang','Jazzmaster','Telecaster','Jaguar')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Nombre del tesoro sagrado de Escanor', 1,'Rytha','Icaro','Rosa','Merlín')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Cuando un semi humano sube de nivel en El ascenso del héroe del escudo este:', 1,'Transforma su cuerpo a uno adulto','Se convierte en monstruo','Cambia de especie','Desbloquea armas nuevas')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Para vencer a un caballero dorado, Mu de Aries hace mención del:', 1,'El séptimo sentido','El octavo sentido','El poder omega','La sangre de Atena')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Las armaduras divinas doradas hacen su aparición en:', 1,'Alma de Oro','La saga de Hades','La overtura del cielo', 'Las 12 casas')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Técnica secreta del tesoro sagrado Lost Vayne', 1,'Clon físico','Contra ataque','Mil cortes divinos','Venganza contra ataque')")

                //Cine
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Película mexicana (2018), dirigida por Alfonso Cuarón, que ganó el Premio Óscar a la Mejor Película Extranjera y que incluyó mixteco en su diálogo', 2,'Roma','Güeros','La Camarista','Después de Lucía')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Título de la película de más larga duración de toda la historia', 2,'The Cure for Insomnia','Cleopatra','Ben-Hur','Gone with the Wind')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Última película de Stanley Kubrick, basada en una novela austriaca de los años veinte llamada Traumnovelle', 2,'Eyes Wide Shut','Lolita','Full Metal Jacket','Barry Lyndon')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuál de los siguientes actores murió durante el rodaje de una película?', 2,'Brandon Lee','Paul Walker','Heath Ledger','Bruce Lee')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Primera película oficial de Studio Ghibli', 2,'El Castillo en el Cielo','Porco Rosso','Mi Vecino Totoro','La Princesa Mononoke')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('País que produce más películas al año', 2,'India','Estados Unidos','Francia','Rusia')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Quién fue el director de Jurassic Park?', 2,'Steven Spielberg ','George Lucas','Martin Scorsese','Tim Burton')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Película más taquillera de la historia sin considerar la inflación', 2,'Vengadores: Endgame','Lo que el viento se llevó','Avatar','Titanic')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Filme dirigido por Richard Linklater y que duró 12 años de rodaje, ya que muestra la vida de un personaje desde su infancia hasta la edad adulta temprana y es interpretada por los mismos actores todo el tiempo', 2,'Boyhood','Moonlight','Honey Boy','Short Term 12')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Deporte que más veces se ha llevado a la pantalla grande', 2,'Box','Fútbol','Beisbol','Karate')")

                //Furry
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Qué artista llega normalmente a cobrar más de $5,000 USD por una comisión?', 3,'Miles DF','Orlando Fox','The Blue Paw','Shirokoi')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cómo se le llama al traje que usan los furries en convenciones?', 3,'Fursuit','Furplay','Animaldress','Cosplay')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿A qué especie se les llama \"quesos\"?', 3,'Sergal','Serval','Guepardo','Kobold')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('De las siguientes mencionadas. ¿Quién es el personaje femenino más popular?', 3, 'Krystal (Fox)','Kitty Kattswell','Nicole Waterson','Kindred (LoL)')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Emoticono más usado por los furries', 3,'OwO','e.e',':3', 'XD')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Forma en la que se le llama a los furries en japón', 3,'Kemono','Fuuri no ai','Animaru no hito','Yookai')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Sub fandom furry fijados en el gusto por la serie \"My Little Pony: Friendship is Magic\"', 3,'Bronies y pegasisters','Ponybros y ponysis','Brohoofers','Magicians')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Director(a) principal de series como \"Helluva Boss\" y \"Hazbin Hotel\"', 3,'Vivienne Medrano','James Rallison','Ink Potts','Jesse Nowack')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Convención furry que se celebra en México', 3,'Confuror','Anthrocon','Furry Fiesta','Megaplex')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('YouTuber furry que ha visitado varias convenciones. Su personaje tiene pelaje rojo', 3,'Majira Strawberry','AnimatedJames','TheOdds1Out','Aurora Bloom')")

                //Deportes
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cual es el país con más copas del mundo ganadas?', 4,'Brasil','Alemania','Italia','Francia')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuál es el equipo con más Champions League?', 4,'Real Madrid','Milan','Barcelona','Liverpool')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuál es el jugador conocido como el \"Pentapichichi\"?', 4,'Hugo Sanchez','Cuauhtemoc Blanco','Javier Hernandez','Jorge Campos')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Quién es el maximo goleador de la historia de la Selección Mexicana?', 4,'Javier Hernandez','Cuauhtemoc Blanco','Jorge Campos','Hugo Sanchez')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuál fue el último campeón de la serie mundial (2020)?', 4,'Dodgers','Yankees','Padres','Astros')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿En qué años se celebraron los mundiales de futbol en México?', 4,'1970 y 1986','1950 y 1970','1970 y 1990','1986 y 2002')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuántas medallas olímpicas ganó Michael Phelps?', 4,'28','25','26','27')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuál es el récord mundial de 100 metros planos?', 4,'9.58 seg','10.03 seg','9.23 seg','8.93 seg')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuál es el club Mexicano más antiguo?', 4,'Pachuca','América','Chivas','Cruz Azul')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuál es el equipo más valioso del mundo?', 4,'Dallas Cowboys','Yankees','Real Madrid','Lakers')")

                //Toons
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Nombre del primer capítulo de Bob Esponja, estrenado el 1 de mayo de 1999', 5,'Help Wanted','Reef Blower','Tea and the Treedome','Home Sweet Pineapple')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Canal que fue reemplazado por Disney XD', 5,'Jetix','Cartoon Network','Nickelodeon','Boomerang')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Primer personaje que se dibujó de Los Simpson y que fue hecho sobre una servilleta', 5,'Bart','Homero','Lisa','Marge')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Año de estreno de la película de Transformers basada en las caricaturas de los 80s', 5,'1986','1990','1984','1993')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Título original de Las Chicas Superpoderas en la versión de Estados Unidos', 5,'The Whoopass Girls','Flirtatious Girls','Powerful Girls','The Superpowers')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Apellido de Sheen, de Jimmy Neutron', 5,'Estevez','Wheezer','Fowl','Vortex')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿Cuántos episodios de Don Gato y su pandilla fueron transmitidos al aire?', 5,'30','50','25','60')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Arte marcial de la que están basados los movimientos del Agua Control en Avatar: La Leyenda de Aang', 5,'Tai Chi','Kung Fu','Hung Gar','Baguazhang')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('¿A cuáles openings hace parodia el intro de Rick & Morty?', 5,'Doctor Who y Tintin','Los Super Sonicos y Futurama','Los Simpson y Scooby-Doo','El Fantasma del Espacio y Thundercat')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('El primer episodio de esta serie animada fue nominada a un Oscar', 5,'Coraje, el perro cobarde','Tom & Jerry','Hora de aventura','¡Oye, Arnold!')")

                //Videojuegos
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Año de salida del primer Gears of War', 6,'2006','2005','2008','2007')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Nombre del juego de Gears of War desarrollado por People can fly', 6,'Judgment','Apocalypse','Genesis','Origins')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('De cuántos videojuegos consta la saga original de Arkam de Batman desarrollado por Rocksteady Studios', 6,'3','4','6','2')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Protagonista del Halo: Reach', 6,'Noble 6','Jefe Maestro','Spartan Locke','Cortana')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Es conocido como el caballero de Arkam en Batman: Arkam Knight', 6,'Jason Todd','Batman','Red Hood','Ala Nocturna')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Nombre del videojuego de Caballeros del Zodiaco que fue producido en audio español latino', 6,'Alma de soldado','Soldados valientes','Soldados brillantes','Caballeros brillantes')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('En el videojuego de Injustice, Superman asesina a Doomsday quien realmente era:', 6,'Lois Lane','Batman','Wonder Woman','Power Girl')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Famoso pintor que hace aparición en el juego de Assassins Creed 2', 6,'Leonardo Da Vinci','Vincent van Gogh','Miguel Angel','Picasso')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Videojuego de Call of Duty criticado por su peso de poco más de 200 GB', 6,'Warzone','Cold War','Black Ops 3','Modern Warfare')")
                db.execSQL("INSERT INTO questions (question, category, option_1, option_2, option_3, option_4) VALUES ('Videojuego de tipo futurista publicitado por Keanu Reeves', 6,'Cyberpunk 2077','Dead Stranding','Watchdogs Legion','League of Legends')")

                //Insertar Jugadores
                db.execSQL("INSERT INTO players (name, profile_image, active) VALUES ('Player1', 1, 1)")
                db.execSQL("INSERT INTO players (name, profile_image, active) VALUES ('Player2', 2, 0)")
                db.execSQL("INSERT INTO players (name, profile_image, active) VALUES ('Player3', 3, 0)")

                //Insertar opciones.
                db.execSQL("INSERT INTO settings(player_id, anime, cine, furry, deportes, toons, videojuegos, difficulty, questions_quantity, hints_enabled, clues_quantity) VALUES (1, 1, 1, 1, 1, 1, 1, 2, 10, 0, 0)")
                db.execSQL("INSERT INTO settings(player_id, anime, cine, furry, deportes, toons, videojuegos, difficulty, questions_quantity, hints_enabled, clues_quantity) VALUES (2, 1, 1, 1, 1, 1, 1, 2, 10, 0, 0)")
                db.execSQL("INSERT INTO settings(player_id, anime, cine, furry, deportes, toons, videojuegos, difficulty, questions_quantity, hints_enabled, clues_quantity) VALUES (3, 1, 1, 1, 1, 1, 1, 2, 10, 0, 0)")
            }
        }
    }
}