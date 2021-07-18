package com.appatstudio.epicdungeontactics2.screens.game.gameScreen.map.mapElements;

import com.appatstudio.epicdungeontactics2.global.WorldConfig;
import com.appatstudio.epicdungeontactics2.global.enums.FontEnum;
import com.appatstudio.epicdungeontactics2.global.enums.MapElementSpriteEnum;
import com.appatstudio.epicdungeontactics2.global.enums.itemEnums.ItemRarityEnum;
import com.appatstudio.epicdungeontactics2.global.managers.FontsManager;
import com.appatstudio.epicdungeontactics2.global.managers.GraphicsManager;
import com.appatstudio.epicdungeontactics2.global.managers.StringsManager;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.CameraHandler;
import com.appatstudio.epicdungeontactics2.screens.game.gameScreen.items.AbstractItem;
import com.appatstudio.epicdungeontactics2.screens.viewElements.RelativePosText;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;


public class ItemOnMapDrawable extends Image {

    private Array<RelativePosText> itemTitles;
    private Array<AbstractItem> items;
    private float lineH;

    public ItemOnMapDrawable(CoordsInt coordsInt) {
        super(GraphicsManager.getMapElementSprite(MapElementSpriteEnum.ITEMS));
        super.setSize(WorldConfig.TILE_SIZE, WorldConfig.TILE_SIZE);
        CoordsFloat coords = WorldConfig.getTileCoord(coordsInt.x, coordsInt.y);
        super.setPosition(coords.x, coords.y + WorldConfig.TILE_SIZE/24f);

        lineH = FontsManager.getTextHeight(FontsManager.getFont(FontEnum.MAP_ITEM_WHITE), "0") * 1.5f;

        items = new Array<>();
        itemTitles = new Array<>();
    }

    public void draw(Batch batch) {
        if (items.size > 0) {
            super.draw(batch, 1f);

        }
    }

    public void addItem(AbstractItem item) {
        items.add(item);
        itemTitles.add(
                new RelativePosText(
                        FontsManager.getFont(item.getRarity() == ItemRarityEnum.WHITE ?
                                            FontEnum.MAP_ITEM_WHITE : item.getRarity() == ItemRarityEnum.GREEN ?
                                            FontEnum.MAP_ITEM_GREEN : item.getRarity() == ItemRarityEnum.BLUE ?
                                            FontEnum.MAP_ITEM_BLUE : item.getRarity() == ItemRarityEnum.VIOLET ?
                                            FontEnum.MAP_ITEM_VIOLET : item.getRarity() == ItemRarityEnum.ORANGE ?
                                            FontEnum.MAP_ITEM_ORANGE : FontEnum.MAP_ITEM_RED),
                        StringsManager.getItemName(item.getItemEnum()),
                        Align.center
                )
        );
    }

    public Array<AbstractItem> getItems() {
        return items;
    }

    public void drawTop(Batch guiBatch) {
        Vector3 startPos = new Vector3(
                this.getX() + this.getWidth() * 0.45f,
                this.getY() + this.getHeight() * 0.7f,
                0
        );

        startPos = CameraHandler.projectCoords(startPos);

        for (int i=0; i<items.size; i++) {
            itemTitles.get(i).draw(guiBatch, startPos.x, startPos.y + i * lineH);
        }
    }

    public void removeItem(AbstractItem item) {
        int index = items.indexOf(item, true);
        items.removeIndex(index);
        itemTitles.removeIndex(index);
        items.clear();
    }


}
