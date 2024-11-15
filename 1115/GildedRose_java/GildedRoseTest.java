import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    @Test
    public void foo1() {
        Item[] items = new Item[] { new Item("foo", 1, 50) };
        GildedRose app = new GildedRose(items);
        int day0 = app.items[0].quality;
        app.updateQuality();
        int day1 = app.items[0].quality;
        app.updateQuality();
        int day2 = app.items[0].quality;
        
        Assert.assertTrue((day0-day1)*2 == day1-day2);
    }

    @Test
    public void foo2() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assert.assertTrue(app.items[0].quality >= 0);
    }

    @Test
    public void foo3() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assert.assertTrue(app.items[0].quality > 0);
    }

    @Test
    public void foo4() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assert.assertTrue(app.items[0].quality <= 50);
    }

    @Test
    public void foo5() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assert.assertTrue(app.items[0].sellIn >= 0);
        Assert.assertTrue(app.items[0].quality == 50);
    }

    @Test
    public void foo6() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10), new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10), new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assert.assertTrue(app.items[0].quality == 12);
        Assert.assertTrue(app.items[1].quality == 13);
        Assert.assertTrue(app.items[2].quality == 0);
    }

    @Test
    public void ConjuredCaked() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 1, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assert.assertEquals(app.items[0].quality , 4);
        app.updateQuality();
        Assert.assertEquals(app.items[0].quality , 0);
    }


}
