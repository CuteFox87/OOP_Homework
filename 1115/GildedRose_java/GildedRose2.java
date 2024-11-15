public class GildedRose2 {
    Item[] items;

    public GildedRose2(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case "Aged Brie":
                    if (item.quality < 50) {
                        item.quality++;
                    }
                    item.sellIn--;
                    if (item.sellIn < 0 && item.quality < 50) {
                        item.quality++;
                    }
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    if (item.quality < 50) {
                        item.quality++;
                        if (item.sellIn < 11 && item.quality < 50) {
                            item.quality++;
                        }
                        if (item.sellIn < 6 && item.quality < 50) {
                            item.quality++;
                        }
                    }
                    item.sellIn--;
                    if (item.sellIn < 0) {
                        item.quality = 0;
                    }
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    break;

                case "Conjured Mana Cake":
                    if (item.quality > 0) {
                        item.quality -= 2;
                    }
                    item.sellIn--;
                    if (item.sellIn < 0 && item.quality > 0) {
                        item.quality -= 2;
                    }
                    break;

                default:
                    if (item.quality > 0) {
                        item.quality--;
                    }
                    item.sellIn--;
                    if (item.sellIn < 0 && item.quality > 0) {
                        item.quality--;
                    }
                    break;
            }
        }
    }
}
