package servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/fine")
public class FineServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captcha = "abdefghjkmnpqrstuvwxyzABDEFGHJKLMNPQRSTUVWXYZ2456789";

        int width = 1800, height = 900;
        Random rand = new Random();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
        Graphics graphics = bufferedImage.createGraphics();
        graphics.setFont(new Font("Arial", Font.BOLD, 80));
        graphics.setColor(new Color(212, 27, 27));
        graphics.fillRect(0, 0, width, height);
        int xStart = 200;
        for(int i = 0; i < 6; i++){
            graphics.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
            graphics.setFont(new Font("Arial", Font.BOLD, 100 + rand.nextInt(60)));
            graphics.drawString(""+captcha.charAt(rand.nextInt(captcha.length()-1)), xStart, 400+rand.nextInt(40));
            xStart += (210 + rand.nextInt(70));
        }
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpeg", outputStream);
        outputStream.close();
    }

}
