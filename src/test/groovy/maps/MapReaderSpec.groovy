package maps

import spock.lang.Specification
import vela.maps.MapReader
import vela.maps.VelaMap

import java.nio.file.Path
import java.nio.file.Paths

class MapReaderSpec extends Specification {




    def "read map"(){
        given: "a map reader"
        MapReader mapReader = new MapReader()
        URL resourceUrl = getClass().getResource("level1.map");

        Path resourcePath = Paths.get(resourceUrl.toURI());

        when: ""
        VelaMap map = mapReader.read(resourcePath);

        then:
        map != null;
    }
}
