package com.fourtress.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Array;

public class Level {

	private TiledMap tiledMap;
	private Box2dModel model;
	private HashMap<String, Item> requiredItems;

	public Level(TiledMap tiledMap, Box2dModel model, HashMap<String, Item> requiredItems) {
		this.tiledMap = tiledMap;
		this.model = model;
		this.requiredItems = requiredItems;
		createWalls();
		createInteractionPoints();
		setPlayerSpawn();
		createDoors();
	}

	private void createWalls() {
		MapObjects objects = tiledMap.getLayers().get("Object Layer").getObjects();
		for (MapObject object : objects) {

			if (object instanceof TextureMapObject) {
				continue;
			}

			Shape shape;

			if (object instanceof RectangleMapObject) {
				shape = BodyFactory.getInstance(model.world).getRectangle((RectangleMapObject) object);
			} else {
				continue;
			}

			BodyDef bd = new BodyDef();
			bd.type = BodyType.StaticBody;
			Body body = model.world.createBody(bd);
			body.createFixture(shape, 1);

			shape.dispose();
		}
	}

	private void createInteractionPoints() {
		MapObjects objects = tiledMap.getLayers().get("Interaction Layer").getObjects();
		for (MapObject object : objects) {
			if (object instanceof TextureMapObject) {
				continue;
			}

			Shape shape;

			if (object instanceof EllipseMapObject) {
				shape = BodyFactory.getInstance(model.world).getCircle((EllipseMapObject) object);
			} else {
				continue;
			}
			BodyDef bd = new BodyDef();
			bd.type = BodyType.StaticBody;
			Body body = model.world.createBody(bd);
			body.createFixture(shape, 1);
			if (object.getProperties().get("type") != null) {
				if (object.getProperties().get("type").equals("Item")) {
					BodyFactory.getInstance(model.world).makeBodyItemSensor(body,
							(String) object.getProperties().get("Message"),
							requiredItems.get(object.getProperties().get("Item")));
				} else if (object.getProperties().get("type").equals("MultiLock")) {
					BodyFactory.getInstance(model.world).makeBodyMultiLockSensor(body,
							(String) object.getProperties().get("Message"),
							requiredItems.get(object.getProperties().get("Required Item")));
				} else {
					BodyFactory.getInstance(model.world).makeBodySensor(body,
							(String) object.getProperties().get("Message"));
				}
			}
			shape.dispose();
		}
	}
	
	private void createDoors() {
		List<Body> doors = new LinkedList<Body>();
		List<Body> hinges = new LinkedList<Body>();
        MapObjects doorObjects = tiledMap.getLayers().get("Door Layer").getObjects();
        for (MapObject door : doorObjects) {
            Shape doorShape;
            if (door instanceof RectangleMapObject) {
                doorShape = BodyFactory.getInstance(model.world).getRectangle((RectangleMapObject) door);
            } else {
                continue;
            }

            BodyDef bdoorBd = new BodyDef();
            bdoorBd.type = BodyType.DynamicBody;
            Body doorBody = model.world.createBody(bdoorBd);
            doors.add(doorBody);
            doorBody.createFixture(doorShape, 1);

            doorShape.dispose();

            String doorName = door.getName();

            MapObjects hingeObjects = tiledMap.getLayers().get("Hinge Layer").getObjects();
            for (MapObject hinge : hingeObjects) {
                Shape hingeShape;
                if (hinge instanceof EllipseMapObject && hinge.getName().equals(doorName)) {
                    hingeShape = BodyFactory.getInstance(model.world).getCircle((EllipseMapObject) hinge);
                } else {
                    continue;
                }

                BodyDef hingeBd = new BodyDef();
                hingeBd.type = BodyType.StaticBody;
                Body hingeBody = model.world.createBody(hingeBd);
                hinges.add(hingeBody);
                hingeBody.createFixture(hingeShape, 1);

                hingeShape.dispose();

                RevoluteJointDef rDef = new RevoluteJointDef();
                rDef.bodyA = hingeBody;
                rDef.bodyB = doorBody;
                rDef.collideConnected = true;
                rDef.localAnchorA.set(0, 0);
                rDef.localAnchorA.set(0, 0);
                // rDef.localAnchorB.set(1.5f,0);
                // rDef.motorSpeed = 5f;
                rDef.enableLimit = false;
                model.world.createJoint(rDef);

            }

        }
        System.out.println("done");
    }

	private void setPlayerSpawn() {
		MapObjects objects = tiledMap.getLayers().get("Spawn Layer").getObjects();
		MapObject spawn = objects.get("Player Spawn");
		if (spawn instanceof EllipseMapObject) {
			model.setSpawn(((EllipseMapObject) spawn).getEllipse());
		}
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}
}
