import spock.lang.Specification
import com.game.App

class AppSpec extends Specification {
    def "App should initialize game and start game loop"() {
        given:
        // Mocking necessary components if required

        when:
        // Running the main method
        App.main(new String[0])

        then:
        // Verify behavior
        noExceptionThrown()
    }
}