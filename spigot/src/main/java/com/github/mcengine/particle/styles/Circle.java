package com.github.mcengine.particle.styles;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class Circle {

    public static void getParticle(Location location) {
        World world = location.getWorld();
        double radius = 1.0;
        for (int i = 0; i < 360; i += 10) {
            double angle = i * Math.PI / 180;
            double x = location.getX() + (radius * Math.cos(angle));
            double y = location.getY() + 1;
            double z = location.getZ() + (radius * Math.sin(angle));

            Location particleLocation = new Location(world, x, y, z);
            world.spawnParticle(Particle.REDSTONE, particleLocation, 1, new Particle.DustOptions(Color.fromRGB(139, 0, 0), 1));
        }
    }
}
