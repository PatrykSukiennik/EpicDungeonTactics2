package com.appatstudio.epicdungeontactics2.view.gameScreen.characters;

import com.appatstudio.epicdungeontactics2.EpicDungeonTactics;
import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterEnum;
import com.appatstudio.epicdungeontactics2.global.enums.CharacterStateEnum;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.map.BodyConfig;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightConfigObject;
import com.appatstudio.epicdungeontactics2.global.managers.map.LightsConfig;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.global.stats.characters.CharacterStats;
import com.appatstudio.epicdungeontactics2.view.gameScreen.GameScreen;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.MapTile;
import com.appatstudio.epicdungeontactics2.view.gameScreen.map.Room;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class CharacterDrawable extends Image {

    private Animation<SpriteDrawable> idleAnimation;
    private Animation<SpriteDrawable> runAnimation;
    private Image projectile;

    private CharacterEnum characterEnum;

    private CharacterStateEnum state;
    private float stateTime;
    private CoordsInt position;

    private PointLight pointLight;
    private Body body;

    private float actionTime;

    protected Room room;
    private Array<Array<MapTile>> possibleMovements;
    private Array<MapTile> possibleMoveToTiles;
    private Array<MapTile> rangeTiles;
    private Array<MapTile> attackableTiles;

    protected CharacterStatsObject stats;

    private CoordsFloat bodyOffset;
    private CoordsFloat lightOffset;

    private MapTile tileStandingOn;
    private MapTile targetTile;

    protected boolean isHero = false;
    protected boolean isPet = false;
    protected boolean isEnemy = false;
    protected boolean isBoss = false;



    private boolean isRotation = EpicDungeonTactics.random.nextBoolean();

    public CharacterDrawable(CharacterEnum characterEnum, CoordsInt position, RayHandler rayHandler, World world, Room room, MapTile tile, boolean isRotation) {
        idleAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.IDLE);
        runAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.RUN);

        projectile = new Image(GraphicsManager.getProjectile(characterEnum));
        projectile.setSize(WorldConfig.TILE_SIZE, WorldConfig.TILE_SIZE);

        stateTime = EpicDungeonTactics.random.nextFloat();

        this.characterEnum = characterEnum;
        this.position = position;
        this.state = CharacterStateEnum.IDLE;
        this.room = room;
        this.isRotation = isRotation;

        int size = CharacterStats.getCharacterSize(characterEnum);
        CoordsFloat coords = WorldConfig.getDrawingCoord(position.x, position.y);
        //if (size > 1) {
        //    coords.x -= (size/4f) * WorldConfig.TILE_SIZE;
        //}
        this.setPosition(coords.x, coords.y);
        this.setSize(2f * WorldConfig.TILE_SIZE, 2f * WorldConfig.TILE_SIZE);

        LightConfigObject lightConfigObject = LightsConfig.getCharacterLights(characterEnum);
        lightOffset = lightConfigObject.getOffset();
        this.pointLight = new PointLight(
                rayHandler,
                LightsConfig.CHARACTER_RAYS,
                lightConfigObject.getColor(),
                lightConfigObject.getRadius(),
                //this.getX() + lightConfigObject.getOffset().x,
                //this.getY() + lightConfigObject.getOffset().y
                this.getX() + this.getWidth()/2f,
                this.getY() + this.getHeight()/2f
        );

        if (BodyConfig.getCharacterBodyDef(characterEnum) != null) {
            this.body = world.createBody(BodyConfig.getCharacterBodyDef(characterEnum));
            this.body.createFixture(BodyConfig.getCharacterFixtureDef(characterEnum));
            bodyOffset = new CoordsFloat((size * WorldConfig.TILE_SIZE) + ((int)(size/2f)*(-1) * WorldConfig.TILE_SIZE), (size * WorldConfig.TILE_SIZE)/2f);
            this.body.setTransform(this.getX() + bodyOffset.x, this.getY() + bodyOffset.y, 0);
            pointLight.attachToBody(this.body);
        }

        createStatsObject();

        this.tileStandingOn = tile;

        if (characterEnum.toString().startsWith("PET")) isPet = true;
        else if (characterEnum.toString().startsWith("HERO")) isHero = true;
        else if (!characterEnum.toString().startsWith("NPC")) isEnemy = true;
    }

    protected void createStatsObject() {
        this.stats = new CharacterStatsObject(characterEnum);
    }

    public void setState(CharacterStateEnum state) {
        this.state = state;
    }

    public void draw(Batch mapBatch) {
        super.act(Gdx.graphics.getDeltaTime());

        if (hasActions()) {
            this.body.setTransform(this.getX() + bodyOffset.x, this.getY() + bodyOffset.y, 0);
            //this.pointLight.setPosition(getX() + lightOffset.x, getY() + lightOffset.y);
        }

        if (projectile.hasActions()) {
            this.projectile.act(Gdx.graphics.getDeltaTime());
            this.projectile.draw(mapBatch, 1f);
        }

        stateTime += Gdx.graphics.getDeltaTime();
        //System.out.println(characterEnum.toString() + "  " + idleAnimation.getFrameDuration());
        if (state == CharacterStateEnum.IDLE) {
            this.setDrawable(idleAnimation.getKeyFrame(stateTime));
        }
        else {
            this.setDrawable(runAnimation.getKeyFrame(stateTime));
        }

        Sprite s = ((SpriteDrawable) this.getDrawable()).getSprite();
        if (isRotation) {
            if (!s.isFlipX()) s.flip(true, false);
        }
        else {
            if (s.isFlipX()) s.flip(true, false);
        }

        this.draw(mapBatch, 1f);

    }

    public CoordsInt getPosition() {
        return position;
    }

    public void dead() {
        System.out.println(characterEnum.toString() + "________");
        pointLight.remove(true);
        room.removeBody(body);
        //room.getRoomCharacters().removeValue(this, false);
        //this.remove();
    }

    public void dispose() {
        pointLight.remove(true);
    }

    public void setTileStandingOn(MapTile tileStandingOn, boolean heroFlag) {
        this.tileStandingOn = tileStandingOn;
        if (tileStandingOn != null) tileStandingOn.setCharacterStandingOn(this);

        if (heroFlag) {
            //GuiContainer.setItemsToPick(tileStandingOn.getItemsToPick());
        }
    }

    public void getMoveInfo() {
        room.getMoveInfo(this);
    }

    public void moveToMapTile(MapTile tile) {
//        MoveToMapTile way = room.getWay(possibleMovements, tile, this);
//        this.addAction(way.getSequenceAction());
//        this.state = CharacterStateEnum.RUN;
//        this.actionTime = way.getDuration();
//        this.targetTile = tile;
    }

    public boolean isReady() {
        return !this.hasActions();
    }

    public float getActionTime() {
        return actionTime;
    }

    public void setPosition(CoordsInt coords) {
        if (coords.x < position.x) isRotation = true;
        else if (coords.x > position.x) isRotation = false;

        this.position = coords;

        tileStandingOn = room.getTiles()[coords.x][coords.y];
        tileStandingOn.setCharacter(this, true);

    }

    public void setPositionForce(CoordsInt coords) {
        if (coords.x < WorldConfig.ROOM_WIDTH/2f) isRotation = false;
        else if (coords.x > WorldConfig.ROOM_WIDTH/2f) isRotation = true;

        this.position = coords;
        CoordsFloat newCoords = WorldConfig.getDrawingCoord(position.x, position.y);
        this.setPosition(newCoords.x, newCoords.y);
        this.body.setTransform(this.getX() + bodyOffset.x, this.getY() + bodyOffset.y, 0);


        tileStandingOn = room.getTiles()[coords.x][coords.y];
        tileStandingOn.setCharacter(this, true);

    }

    public CharacterStatsObject getStats() {
        return stats;
    }

    public MapTile getTileStandingOn() {
        return tileStandingOn;
    }

    public CharacterEnum getCharacterEnum() {
        return characterEnum;
    }

    public void drawTop(Batch guiBatch) {
        //override me
    }

    protected CharacterStateEnum getState() {
        return this.state;
    }

    protected boolean isOnTarget() {
        return this.getTileStandingOn() == targetTile;
    }


    public int getSpeed() {
        return stats.getSpeed();
    }

    public boolean isHero() {
        return isHero;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public boolean isPet() {
        return isPet;
    }

    public void shot(MapTile tile) {
        CharacterDrawable target = tile.getCharacterStandingOn();

    }


    public int getMeleDamage() {
        return stats.getMeleDmg(GameScreen.getInstance().getLvl());
    }

    public int getShotDamage() {
        return stats.getShotDmg(GameScreen.getInstance().getLvl());
    }


    public boolean canMoveTo(MapTile tapped) {
        return possibleMoveToTiles.contains(tapped, false);
    }

    public void setPossibleMovements(Array<Array<MapTile>> allPaths) {
        possibleMovements = allPaths;
    }

    public void setAttackableTiles(Array<MapTile> attackableTiles) {
        this.attackableTiles = attackableTiles;
    }

    public void setPossibleMoveToTiles(Array<MapTile> possibleMoveToTiles) {
        this.possibleMoveToTiles = possibleMoveToTiles;
    }

    public void setRangeTiles(Array<MapTile> rangeTiles) {
        this.rangeTiles = rangeTiles;
    }

    public Array<MapTile> getRangeTiles() {
        return rangeTiles;
    }

    public Array<Array<MapTile>> getPossibleMovements() {
        return possibleMovements;
    }

    public Array<MapTile> getPossibleMoveToTiles() {
        return possibleMoveToTiles;
    }

    public Array<MapTile> getAttackableTiles() {
        return attackableTiles;
    }

    public Animation<SpriteDrawable> getIdleAnimation() {
        return idleAnimation;
    }

    public Animation<SpriteDrawable> getRunAnimation() {
        return runAnimation;
    }

    public void setRotationX(boolean targetRotation) {
        this.isRotation = targetRotation;
    }

    public void dmgGet(int dmg) {
        //override me
    }

    public Image getProjectile() {
        return this.projectile;
    }

    public void newCharacter(CharacterEnum characterEnum) {
        this.characterEnum = characterEnum;
        this.state = CharacterStateEnum.IDLE;

        idleAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.IDLE);
        runAnimation = GraphicsManager.getCharactersAnimation(characterEnum, CharacterStateEnum.RUN);

        projectile = new Image(GraphicsManager.getProjectile(characterEnum));
        projectile.setSize(WorldConfig.TILE_SIZE, WorldConfig.TILE_SIZE);

        stateTime = EpicDungeonTactics.random.nextFloat();
    }

    public void setTargetTile(MapTile tapped) {
        this.targetTile = tapped;
    }

    public boolean isBoss() {
        return isBoss;
    }
}
