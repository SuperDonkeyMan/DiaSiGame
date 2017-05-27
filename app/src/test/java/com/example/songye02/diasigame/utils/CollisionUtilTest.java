package com.example.songye02.diasigame.utils;

import static org.junit.Assert.*;

import org.junit.Assert;

/**
 * Created by songye02 on 2017/5/25.
 */
public class CollisionUtilTest {
    @org.junit.Test
    public void isCollisionWithRect() throws Exception {

    }

    @org.junit.Test
    public void isCollisionWithBullet() throws Exception {

    }

    @org.junit.Test
    public void getPoint2PointDistance() throws Exception {

    }

    @org.junit.Test
    public void isPointInRec() throws Exception {
        Assert.assertTrue(CollisionUtil.isPointInRec(5,5,0,0,6,6));
        Assert.assertFalse(CollisionUtil.isPointInRec(5,5,0,0,3,3));
    }

    @org.junit.Test
    public void isCollisionWithCircle() throws Exception {
        Assert.assertTrue(CollisionUtil.isCollisionWithCircle(0,0,10,0,8,5,5));
        Assert.assertFalse(CollisionUtil.isCollisionWithCircle(0,0,10,0,11,5,5));
    }

}