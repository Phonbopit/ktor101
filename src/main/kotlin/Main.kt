import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.*

data class Movie(val id: String, val name: String, val imageUrl: String, val overview: String)
data class ResponseMessage(val message: String)

fun Application.main() {

    val data = initData()

    install(GsonSupport)
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/api/movies") {
            call.respond(data)
        }

        get("/api/movies/{id}") {
            val id = call.parameters["id"]
            call.respond(data.filter { it.id == id }[0])
        }

        post("/api/movies") {
            val movie = call.receive<Movie>()
            call.respond(ResponseMessage("$movie has been created!"))
        }

        put("/api/movies/{id}") {
            val id = call.parameters["id"]
            call.respond(ResponseMessage("$id has been updated!"))
        }

        delete("/api/movies/{id}") {
            val id = call.parameters["id"]
            call.respond(ResponseMessage("$id has been deleted!"))
        }

    }
}

fun initData(): List<Movie> {
    return listOf(
            Movie("1", "Minions", "http://image.tmdb.org/t/p/w780/uX7LXnsC7bZJZjn048UCOwkPXWJ.jpg", "Minions Stuart, Kevin and Bob are recruited by Scarlet Overkill, a super-villain who, alongside her inventor husband Herb, hatches a plot to take over the world."),
            Movie("2", "Beauty and the Beast", "http://image.tmdb.org/t/p/w780/6aUWe0GSl69wMTSWWexsorMIvwU.jpg", "A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell."),
            Movie("3", "Spider-Man: Homecoming", "http://image.tmdb.org/t/p/w780/vc8bCGjdVp0UbMNLzHnHSLRbBWQ.jpg", "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges."),
            Movie("4", "Guardians of the Galaxy Vol. 2", "http://image.tmdb.org/t/p/w780/vc8bCGjdVp0UbMNLzHnHSLRbBWQ.jpg", "The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage."),
            Movie("5", "Despicable Me 3", "http://image.tmdb.org/t/p/w780/puV2PFq42VQPItaygizgag8jrXa.jpg", "Gru and his wife Lucy must stop former '80s child star Balthazar Bratt from achieving world domination."),
            Movie("6", "War for the Planet of the Apes", "http://image.tmdb.org/t/p/w780/ulMscezy9YX0bhknvJbZoUgQxO5.jpg", "Caesar and his apes are forced into a deadly conflict with an army of humans led by a ruthless Colonel. After the apes suffer unimaginable losses, Caesar wrestles with his darker instincts and begins his own mythic quest to avenge his kind. As the journey finally brings them face to face, Caesar and the Colonel are pitted against each other in an epic battle that will determine the fate of both their species and the future of the planet."),
            Movie("7", "Annabelle: Creation", "http://image.tmdb.org/t/p/w780/o8u0NyEigCEaZHBdCYTRfXR8U4i.jpg", "Several years after the tragic death of their little girl, a dollmaker and his wife welcome a nun and several girls from a shuttered orphanage into their home, soon becoming the target of the dollmaker's possessed creation, Annabelle."),
            Movie("8", "Dunkirk", "http://image.tmdb.org/t/p/w780/fudEG1VUWuOqleXv6NwCExK0VLy.jpg", "Miraculous evacuation of Allied soldiers from Belgium, Britain, Canada, and France, who were cut off and surrounded by the German army from the beaches and harbor of Dunkirk, France, between May 26 and June 04, 1940, during Battle of France in World War II"),
            Movie("9", "Deadpool", "http://image.tmdb.org/t/p/w780/n1y094tVDFATSzkTnFxoGZ1qNsG.jpg", "Deadpool tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life."),
            Movie("10", "Guardians of the Galaxy", "http://image.tmdb.org/t/p/w780/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg", "Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser.")
    )
}
