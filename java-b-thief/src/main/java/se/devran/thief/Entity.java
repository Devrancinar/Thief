package se.devran.thief;

public class Entity {
    private String role;
    private int health;
    protected int damage;

    public Entity(String role, int health, int damage){
        this.role = role;
        this.damage = damage;
        this.health = health;
    }

    public String getRole(){
        return role;
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public void punch(Entity toPunch){
        toPunch.takeHit (this.damage);
    }

    public void takeHit (int damage){
        health -= damage;
    }

    public boolean isConscious(){
        return health > 0;
    }

    protected void setDamage(int damage){
        this.damage = damage;
    }

    public void addDamage(int damage){
        this.damage += damage;
    }

}

