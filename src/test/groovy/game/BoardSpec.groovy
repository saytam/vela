package vela.game


import spock.lang.Specification
import spock.lang.Unroll

class BoardSpec extends Specification {

    Board board

    def setup(){
        board = new Board()
    }

    @Unroll
    def "get tile at postion"(){
        when: "get tile"
        Tile tile = board.tileAtPosition(new Position(x, y));

        then: "the position is correct"
        tile.getX() == x;
        tile.getY() == y;

        where:
        x | y
        0 | 0
        1 | 0
        0 | 1
        2 | 5
        5 | 2
        Board.TILES_X - 1 | 1
        1 | Board.TILES_Y - 1
        Board.TILES_X - 1 | Board.TILES_Y - 1
    }

    def "place units"(){
        given: "a list of units"
        List<Unit> units = [Units.knight(), Units.dragon()];

        when: "the units are places"
        board.placeUnits(units, boardPosition)

        then: "they are on the correct tile"
        assert board.tileAtPosition( new Position(xPosition, 0)).getUnit().name == "Knight"
        assert board.tileAtPosition( new Position(xPosition, 1) ).getUnit().name == "Dragon"
        true

        where:
        boardPosition | xPosition
        Board.StartPosition.LEFT | 0
        Board.StartPosition.RIGHT | 9
    }

    @Unroll
    def "move units"(){
        given: "placed units"
        Unit fastUnit = new Unit(name: "Fast Unit", speed: 7 )
        Unit slowUnit = new Unit(name: "Slow Unit", speed: 2)
        Position p1 = new Position(2, 2);
        Position p2 = new Position(7, 5);

        board.placeUnit(fastUnit, p1)
        board.placeUnit(slowUnit, p2)


        when: "moving"
        boolean result = board.move(startPosition, targetPosition )

        then: "expected behaviour"
        result == expectedResult

        where:
        startPosition | targetPosition  || expectedResult
        new Position(2, 2) | new Position(2, 3) || true
        new Position(2, 2) | new Position(2, 2) || false
        new Position(7, 5) | new Position(2, 3) || false
        new Position(2, 2) | new Position(2, 3) || true
        new Position(7, 5) | new Position(0, 0) || false
        new Position(7, 5) | new Position(7, 4) || true
        new Position(7, 5) | new Position(7, 3) || true
        new Position(7, 5) | new Position(7, 2) || false
    }

    def "attacking"(){
        given: "placed units"
        Position p1 = new Position(1, 1);
        Position p2 = new Position(2, 1);

        board.placeUnit(attackingUnit, p1)
        board.placeUnit(defendingUnit, p2)

        expect:
        board.tileAtPosition(p2).isOccupied()

        when: "attack"
        boolean result = board.attack(p1, p2)

        then: "target is gone"
        !board.tileAtPosition(p2).isOccupied()
        result == expectedResult

        where:
        attackingUnit | defendingUnit || expectedResult
        new Unit(name: "a", attackPower: 10) | new Unit(name: "d", defense: 0, health: 1 ) || true

    }
}
