package com.github.integritygame.objects;

import com.github.integritygame.util.VariableManager;

public class BulletData {

    public enum BulletName {

        SMALL(15,10,5),
        MEDIUM(30, 20,10),
        LARGE(50, 50, 15);

        public final int damage;
        public final int costOnFire;
        public final int moneyIfHit;

        BulletName(int damage, int costOnFire, int moneyIfHit) {
            this.damage = damage;
            this.costOnFire = costOnFire;
            this.moneyIfHit = moneyIfHit;
        }

    }

    private BulletName currentBullet;

    public BulletData(BulletName currentBullet){
        this.currentBullet = currentBullet;
    }

    public BulletData(String enumAsString){
        if(enumAsString.equals("SMALL")){
            this.currentBullet = BulletData.BulletName.SMALL;
        }else if(enumAsString.equals("MEDIUM")){
            this.currentBullet = BulletName.MEDIUM;
        }else if(enumAsString.equals("LARGE")){
            this.currentBullet = BulletName.LARGE;
        }
    }

    public BulletName getBulletData(){
       return currentBullet;
    }

}
