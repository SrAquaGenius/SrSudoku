import spock.lang.Specification
import com.game.Sudoku.Sudoku
import com.game.Sudoku.SudokuState

class SudokuSpec extends Specification {
    def "Sudoku should initialize and parse input correctly"() {
        given:
        def sudoku = new Sudoku("testSudoku")

        when:
        boolean initialized = sudoku.initializeSudokuState("path/to/sudoku.txt")

        then:
        initialized == true
        sudoku.getState() != null
    }
}
