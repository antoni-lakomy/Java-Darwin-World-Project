package agh.ics.oop.observers;

import agh.ics.oop.maps.WorldMap;
import agh.ics.oop.records.Vector2d;
import agh.ics.oop.util.MapVisualizer;

public class ConsoleDisplay implements SimObserver{


    @Override
    public void update(WorldMap map) {
        MapVisualizer visualizer = new MapVisualizer(map);

        System.out.println(visualizer.draw(new Vector2d(0,0),new Vector2d(map.getWidth() -1, map.getHeight() -1) ) );
    }
}
