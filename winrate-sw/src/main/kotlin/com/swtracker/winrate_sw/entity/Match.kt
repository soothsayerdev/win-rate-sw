import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="matches")
data class Match(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val playerName: String,

    val deckName: String,

    val opponentDeck: String,

    val wins: Int,

    val losses: Int,

    val matchDate: LocalDateTime = java.time.LocalDateTime.now()
)