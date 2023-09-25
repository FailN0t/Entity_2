
import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
    private Image image; //изображение

    public Sprite(Image image) {
        this.image = image;
    }

    public int getWidth() { //получаем ширину картинки
        return image.getWidth(null);
    }

    public int getHeight() { //получаем высоту картинки
        return image.getHeight(null);
    }

    public void draw(Graphics g, int x, int y) { //рисуем картинку
        g.drawImage(image, x, y, null);
    }
    public void draw(Graphics g, int x, int y, int xH, int yH) { //рисуем картинку
        g.drawImage(image, x, y,xH, yH, null);
    }


}
