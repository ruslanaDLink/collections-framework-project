package org.user.thread.fork;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MandelbrotSequential {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int width = 800;
        int height = 800;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int maxIter = 1000;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double zx = 0;
                double zy = 0;
                double cX = (x - 400) / 200.0;
                double cY = (y - 400) / 200.0;
                int iter = maxIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                int color = iter | (iter << 8);
                image.setRGB(x, y, color);
            }
        }

        ImageIO.write(image, "png", new File("mandelbrot_sequential.png"));
        System.out.println("Obraz fraktalu Mandelbrota wygenerowany sekwencyjnie.");

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Czas wykonania: "+duration);
    }
}