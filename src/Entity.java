import java.util.Random;

public class Entity {
    private Sprite sprite = null;
    private int attack;
    private int defense;
    private Integer health;
    private int healthMax;
    private int[] damage;
    private byte treatment;

    public Entity() {
        setAttack(new Random().nextInt(29) + 1);
        setDefense(new Random().nextInt(29) + 1);
        setHealth(new Random().nextInt(95) + 5);
        healthMax = health;
        setDamage(1, 6);
        treatment = 4;
    }

    public Entity(int attack, int defense, int health, int minDamage, int maxDamage) {
        setAttack(attack);
        setDefense(defense);
        setHealth(health);
        healthMax = health;
        setDamage(minDamage, maxDamage);
        treatment = 4;
    }

    public Integer getAttack() {
        return attack;
    }

    public boolean setAttack(int attack) {
        if (attack > 0 && attack <= 30) {
            this.attack = attack;
            return true;
        } else {
            return false;
        }
    }

    public Integer getDefense() {
        return defense;
    }

    public boolean setDefense(int defense) {
        if (defense > 0 && defense <= 30) {
            this.defense = defense;
            return true;
        } else {
            return false;
        }
    }

    public Integer getHealth() {
        return health;
    }

    public boolean setHealth(int health) {
        if (health >= 0) {
            this.health = health;
            return true;
        } else {
            return false;
        }
    }

    public int getDamage() {
        return damage[new Random().nextInt(damage.length - 1)];
    }

    public boolean setDamage(int min, int max) {
        if (min < 0 || max < 0) return false;
        if (min < max) {
            damage = new int[max - min + 1];
            for (int i = 0; i < damage.length; i++) {
                damage[i] = i + min;
            }
        } else {
            damage = new int[min - max + 1];
            for (int i = 0; i < damage.length; i++) {
                damage[i] = i + min;
            }
        }
        return true;
    }

    public Byte getTreatment() {
        return treatment;
    }

    public void toHeal() {
        if (healthMax >= health + (int) ((double) healthMax * 0.3) && treatment > 0) {
            health = health + (int) ((double) health * 0.3);
            treatment--;
        }
    }

    public void strike(Entity entity) {

        int attackModifier = (this.attack - entity.getDefense()) + 1;
        if (attackModifier < 1) {
            attackModifier = 1;
        }
        System.out.println("attack = " + (attack));
        System.out.println("entity.getDefense() = " + entity.getDefense());
        System.out.println("attackModifier = " + ((attack - entity.getDefense()) + 1));
        for (int i = 0; i < attackModifier; i++) {
            int r = new Random().nextInt(6) + 1;
            System.out.println("r = " + r);
            if ((r) > 4) {

                int damageEntity = entity.getHealth() - this.getDamage();
                if (damageEntity < 0) {
                    entity.setHealth(0);
                } else {
                    entity.setHealth(damageEntity);
                }
                break;
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
