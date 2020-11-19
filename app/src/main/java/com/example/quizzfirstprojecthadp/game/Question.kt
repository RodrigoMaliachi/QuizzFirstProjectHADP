package com.example.quizzfirstprojecthadp.game

class Question (
    val question: String,
    val answer: String,
    val option2: String,
    val option3: String,
    val option4: String)
{
    companion object {
        val listOfQuestion = listOf(
            //Anime
            Question("¿Contra quién pelea Baki antes de pelear con su padre?","Mohammad Alai Jr.","Floyd Maywother","Mohammad Alai","The Boogerman"),
            Question("¿A quién llaman \"Clockwork Thumbelina\"?","Rizu Ogata","Chika Fujiwara","Seishirou Tsugumi","Eiai Nano"),
            Question("¿Quién hace \"Wa ha ha\"?","Hitori Bocchi","Nishikata Chi","Yoshimoto Shizuka","Tomoko Kuroki"),
            Question("\"La novia eficiente\" de Rentarou","Eiai Nano","Yoshimoto Shizuka","Inda Karane","Hanazono Hakari"),
            Question("¿Qué modelo de Fender toca Azusa en K On!?","Mustang","Jazzmaster","Telecaster","Jaguar"),
            Question("Nombre del tesoro sagrado de Escanor","Rytha","Icaro","Rosa","Merlín"),
            Question("Cuando un semi humano sube de nivel en El ascenso del héroe del escudo este:","Transforma su cuerpo a uno adulto","Se convierte en monstruo","Cambia de especie","Desbloquea armas nuevas"),
            Question("Para vencer a un caballero dorado, Mu de Aries hace mención del:","El séptimo sentido","El octavo sentido","El poder omega","La sangre de Atena"),
            Question("Las armaduras divinas doradas hacen su aparición en:","Alma de Oro","La saga de Hades","La overtura del cielo", "Las 12 casas"),
            Question("Técnica secreta del tesoro sagrado Lost Vayne","Clon físico","Contra ataque","Mil cortes divinos","Venganza contra ataque"),

            //Cine
            Question("Película mexicana (2018), dirigida por Alfonso Cuarón, que ganó el Premio Óscar a la Mejor Película Extranjera y que incluyó mixteco en su diálogo","Roma","Güeros","La Camarista","Después de Lucía"),
            Question("Título de la película de más larga duración de toda la historia","The Cure for Insomnia","Cleopatra","Ben-Hur","Gone with the Wind"),
            Question("Última película de Stanley Kubrick, basada en una novela austriaca de los años veinte llamada Traumnovelle","Eyes Wide Shut","Lolita","Full Metal Jacket","Barry Lyndon"),
            Question("¿Cuál de los siguientes actores murió durante el rodaje de una película?","Brandon Lee","Paul Walker","Heath Ledger","Bruce Lee"),
            Question("Primera película oficial de Studio Ghibli","El Castillo en el Cielo","Porco Rosso","Mi Vecino Totoro","La Princesa Mononoke"),
            Question("País que produce más películas al año","India","Estados Unidos","Francia","Rusia"),
            Question("¿Quién fue el director de Jurassic Park?","Steven Spielberg ","George Lucas","Martin Scorsese","Tim Burton"),
            Question("Película más taquillera de la historia sin considerar la inflación","Vengadores: Endgame","Lo que el viento se llevó","Avatar","Titanic"),
            Question("Filme dirigido por Richard Linklater y que duró 12 años de rodaje, ya que muestra la vida de un personaje desde su infancia hasta la edad adulta temprana y es interpretada por los mismos actores todo el tiempo","Boyhood","Moonlight","Honey Boy","Short Term 12"),
            Question("Deporte que más veces se ha llevado a la pantalla grande","Box","Fútbol","Beisbol","Karate"),

            //Furry
            Question("¿Qué artista llega normalmente a cobrar más de $5,000 USD por una comisión?","Miles DF","Orlando Fox","The Blue Paw","Shirokoi"),
            Question("¿Cómo se le llama al traje que usan los furries en convenciones?","Fursuit","Furplay","Animaldress","Cosplay"),
            Question("¿A qué especie se les llama \"quesos\"?","Sergal","Serval","Guepardo","Kobold"),
            Question("De las siguientes mencionadas. ¿Quién es el personaje femenino más popular?", "Krystal (Fox)","Kitty Kattswell","Nicole Waterson","Kindred (LoL)"),
            Question("Emoticono más usado por los furries","OwO","e.e",":3", "XD"),
            Question("Forma en la que se le llama a los furries en japón","Kemono","Fuuri no ai","Animaru no hito","Yookai"),
            Question("Sub fandom furry fijados en el gusto por la serie \"My Little Pony: Friendship is Magic\"","Bronies y pegasisters","Ponybros y ponysis","Brohoofers","Magicians"),
            Question("Director(a) principal de series como \"Helluva Boss\" y \"Hazbin Hotel\"","Vivienne Medrano","James Rallison","Ink Potts","Jesse Nowack"),
            Question("Convención furry que se celebra en México","Confuror","Anthrocon","Furry Fiesta","Megaplex"),
            Question("YouTuber furry que ha visitado varias convenciones. Su personaje tiene pelaje rojo","Majira Strawberry","AnimatedJames","TheOdds1Out","Aurora Bloom"),

            //Deportes
            Question("¿Cual es el país con más copas del mundo ganadas?","Brasil","Alemania","Italia","Francia"),
            Question("¿Cuál es el equipo con más Champions League?","Real Madrid","Milan","Barcelona","Liverpool"),
            Question("¿Cuál es el jugador conocido como el \"Pentapichichi\"?","Hugo Sanchez","Cuauhtemoc Blanco","Javier Hernandez","Jorge Campos"),
            Question("¿Quién es el maximo goleador de la historia de la Selección Mexicana?","Javier Hernandez","Cuauhtemoc Blanco","Jorge Campos","Hugo Sanchez"),
            Question("¿Cuál fue el último campeón de la serie mundial (2020)?","Dodgers","Yankees","Padres","Astros"),
            Question("¿En qué años se celebraron los mundiales de futbol en México?","1970 y 1986","1950 y 1970","1970 y 1990","1986 y 2002"),
            Question("¿Cuántas medallas olímpicas ganó Michael Phelps?","28","25","26","27"),
            Question("¿Cuál es el récord mundial de 100 metros planos?","9.58 seg","10.03 seg","9.23 seg","8.93 seg"),
            Question("¿Cuál es el club Mexicano más antiguo?","Pachuca","América","Chivas","Cruz Azul"),
            Question("¿Cuál es el equipo más valioso del mundo?","Dallas Cowboys","Yankees","Real Madrid","Lakers"),

            //Toons
            Question("Nombre del primer capítulo de Bob Esponja, estrenado el 1 de mayo de 1999","Help Wanted","Reef Blower","Tea and the Treedome","Home Sweet Pineapple"),
            Question("Canal que fue reemplazado por Disney XD","Jetix","Cartoon Network","Nickelodeon","Boomerang"),
            Question("Primer personaje que se dibujó de Los Simpson y que fue hecho sobre una servilleta","Bart","Homero","Lisa","Marge"),
            Question("Año de estreno de la película de Transformers basada en las caricaturas de los 80's","1986","1990","1984","1993"),
            Question("Título original de Las Chicas Superpoderas en la versión de Estados Unidos","The Whoopass Girls","Flirtatious Girls","Powerful Girls","The Superpowers"),
            Question("Apellido de Sheen, de Jimmy Neutron","Estevez","Wheezer","Fowl","Vortex"),
            Question("¿Cuántos episodios de Don Gato y su pandilla fueron transmitidos al aire?","30","50","25","60"),
            Question("Arte marcial de la que están basados los movimientos del Agua Control en Avatar: La Leyenda de Aang","Tai Chi","Kung Fu","Hung Gar","Baguazhang"),
            Question("¿A cuáles openings hace parodia el intro de Rick & Morty?","Doctor Who y Tintin","Los Super Sonicos y Futurama","Los Simpson y Scooby-Doo","El Fantasma del Espacio y Thundercat"),
            Question("El primer episodio de esta serie animada fue nominada a un Oscar","Coraje, el perro cobarde","Tom & Jerry","Hora de aventura","¡Oye, Arnold!"),

            //Videojuegos
            Question("Año de salida del primer Gears of War","2006","2005","2008","2007"),
            Question("Nombre del juego de Gears of War desarrollado por People can fly","Judgment","Apocalypse","Genesis","Origins"),
            Question("De cuántos videojuegos consta la saga original de Arkam de Batman desarrollado por Rocksteady Studios","3","4","6","2"),
            Question("Protagonista del Halo: Reach","Noble 6","Jefe Maestro","Spartan Locke","Cortana"),
            Question("Es conocido como el caballero de Arkam en Batman: Arkam Knight","Jason Todd","Batman","Red Hood","Ala Nocturna"),
            Question("Nombre del videojuego de Caballeros del Zodiaco que fue producido en audio español latino","Alma de soldado","Soldados valientes","Soldados brillantes","Caballeros brillantes"),
            Question("En el videojuego de Injustice, Superman asesina a Doomsday quien realmente era:","Lois Lane","Batman","Wonder Woman","Power Girl"),
            Question("Famoso pintor que hace aparición en el juego de Assassins Creed 2","Leonardo Da Vinci","Vincent van Gogh","Miguel Angel","Picasso"),
            Question("Videojuego de Call of Duty criticado por su peso de poco más de 200 GB","Warzone","Cold War","Black Ops 3","Modern Warfare"),
            Question("Videojuego de tipo futurista publicitado por Keanu Reeves","Cyberpunk 2077","Dead Stranding","Watchdogs Legion","League of Legends")
        )
    }
}
