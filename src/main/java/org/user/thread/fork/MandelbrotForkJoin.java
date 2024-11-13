package org.user.thread.fork;

import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class MandelbrotTask extends RecursiveAction {
    private static final int THRESHOLD = 50;
    private final BufferedImage image;
    private final int startY, endY, width, height, maxIter;

    public MandelbrotTask(BufferedImage image, int startY, int endY, int width, int height, int maxIter) {
        this.image = image;
        this.startY = startY;
        this.endY = endY;
        this.width = width;
        this.height = height;
        this.maxIter = maxIter;
    }

    @Override
    protected void compute() {
        if (endY - startY <= THRESHOLD) {
            for (int y = startY; y < endY; y++) {
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
        } else {
            int midY = (startY + endY) / 2;
            MandelbrotTask topTask = new MandelbrotTask(image, startY, midY, width, height, maxIter);
            MandelbrotTask bottomTask = new MandelbrotTask(image, midY, endY, width, height, maxIter);
            invokeAll(topTask, bottomTask);
        }
    }
}

public class MandelbrotForkJoin {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int width = 800;
        int height = 800;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int maxIter = 1000;

        ForkJoinPool pool = new ForkJoinPool();
        MandelbrotTask task = new MandelbrotTask(image, 0, height, width, height, maxIter);

        pool.invoke(task);
        ImageIO.write(image, "png", new File("mandelbrot_forkjoin.png"));
        System.out.println("Obraz fraktalu Mandelbrota wygenerowany przy użyciu Fork/Join.");
        long end = System.currentTimeMillis();
        long result = end - start;
        System.out.println("Czas wykonania: " + result);
    }
}