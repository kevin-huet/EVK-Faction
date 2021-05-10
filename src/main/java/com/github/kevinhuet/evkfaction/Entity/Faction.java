package com.github.kevinhuet.evkfaction.Entity;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Faction {

    private String name;
    private Location home;
    private double power;
    private double powerMax;
    private double powerMin;
    private Role role = Role.RECRUIT;
    private List<Claim> claims = new ArrayList<>();
    private List<FactionPlayer> players = new ArrayList<>();

    public Faction(String name, FactionPlayer creator) {
        this.players.add(creator);
        this.name = name;
    }

    public Claim claim(Chunk chunk) {
        Claim claim = new Claim(this, chunk);

        this.claims.add(claim);
        return claim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public List<FactionPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<FactionPlayer> players) {
        this.players = players;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isNegative() {
        return this.power < 0;
    }

    public boolean isPositive() {
        return this.power >= 0;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getPowerMax() {
        return powerMax;
    }

    public void setPowerMax(double powerMax) {
        this.powerMax = powerMax;
    }

    public double getPowerMin() {
        return powerMin;
    }

    public void setPowerMin(double powerMin) {
        this.powerMin = powerMin;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
}
