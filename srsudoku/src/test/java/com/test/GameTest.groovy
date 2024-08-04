import spock.lang.Specification
import com.game.Game
import com.game.Sudoku.Sudoku

class GameSpec extends Specification {
    def "Game should initialize and handle user inputs"() {
        given:
        def game = new Game()
        def scanner = Mock(Scanner)

        when:
        game.gameLoop(scanner)

        then:
        // Verify behavior
        noExceptionThrown()
    }
}
