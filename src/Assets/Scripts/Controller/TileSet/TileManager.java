package Controller.TileSet;

import Controller.Tool.UtilityTool;
import Model.Tile;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public Tile[] tile; // Grass, Tree, Small grass
    public int[][] layerMapTileFirst; // Store base map tile with grass and small grass
    GamePanel gp;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20];
        layerMapTileFirst = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/Map/map_01.txt");
    }

    // Upload image
    public void getTileImage() {
        // Import tile set
        // Grass
        for (int i = 0; i < 4; i++) {
            tile[i] = new Tile();
            tile[i].setImage(setUp("/Background/TileSet/grass_0" + (i + 1) + ".png"));
        }

        // Tree
        tile[5] = new Tile();
        tile[5].setImage(setUp("/Background/TileSet/Tree_01.png"));

        tile[6] = new Tile();
        tile[6].setImage(setUp("/Background/TileSet/Tree_01.png"));

        // Small grass
        tile[7] = new Tile();
        tile[7].setImage(setUp("/Background/TileSet/small_grass_01.png"));

    }

    public BufferedImage setUp(String path) {
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();

        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    // Load map tile
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Read file

            int col = 0, row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    layerMapTileFirst[col][row] = num;
                    col++;
                }

                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Draw tile set
    public void draw(Graphics2D g2) {
        int col = 0, row = 0;
        int x = 0, y = 0;

        // Draw map with grass
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = layerMapTileFirst[col][row];
            g2.drawImage(tile[tileNum].getImage(), x, y, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    /* MAP
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     */
}