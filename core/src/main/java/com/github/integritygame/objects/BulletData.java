package com.github.integritygame.objects;

public class BulletData {

    /**
     * Holds data for the various bullets available in the game such as the damage dealt, cost to fire, and the returned money if the shot was successful
     */
    public enum BulletName {

        SMALL(15, 10, 5),
        MEDIUM(30, 20, 10),
        LARGE(50, 200, 15);

        public final int damage;
        public final int costOnFire;
        public final int moneyIfHit;

        BulletName(int damage, int costOnFire, int moneyIfHit) {
            this.damage = damage;
            this.costOnFire = costOnFire;
            this.moneyIfHit = moneyIfHit;
        }

    }

    /**
     * Returns the bullet that's currently selected
     */
    private BulletName currentBullet;

    /**
     * Initialises the Bullet Data given the bullet name
     *
     * @param currentBullet
     */
    public BulletData(BulletName currentBullet) {
        this.currentBullet = currentBullet;
    }

    /**
     * Constructor to initialise the bullet data given the enum name as a string
     *
     * @param enumAsString
     */
    public BulletData(String enumAsString) {
        if (enumAsString.equals("SMALL")) {
            this.currentBullet = BulletData.BulletName.SMALL;
        } else if (enumAsString.equals("MEDIUM")) {
            this.currentBullet = BulletName.MEDIUM;
        } else if (enumAsString.equals("LARGE")) {
            this.currentBullet = BulletName.LARGE;
        }
    }

    /**
     * Returns the Bullet Name for the current bullet
     *
     * @return Bullet name
     */
    public BulletName getBulletData() {
        return currentBullet;
    }

}
