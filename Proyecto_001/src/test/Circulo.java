package test;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Circulo {

    private long window;

    public void ejecutar() {
        iniciar();
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void iniciar() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("No se pudo inicializar GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(500, 500, "Tarea Programacion Avanzada II", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Error al crear la ventana GLFW");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();

        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Obtener el tamaño de la ventana
            try (MemoryStack stack = stackPush()) {
                IntBuffer pWidth = stack.mallocInt(1);
                IntBuffer pHeight = stack.mallocInt(1);
                glfwGetWindowSize(window, pWidth, pHeight);

                // Configurar el viewport
                glViewport(0, 0, pWidth.get(0), pHeight.get(0));

                // Configurar la relación de aspecto
                float aspectRatio = (float) pWidth.get(0) / (float) pHeight.get(0);
            }

            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            GL11.glColor3f(1.0f, 0.0f, 0.0f); // Rojo

            // Definir el centro del círculo
            float centerX = 0.0f;
            float centerY = 0.0f;

            // Definir el radio del círculo
            float radius = 0.5f;

            // Número de triángulos que formarán el círculo (mayor número, mayor calidad)
            int numTriangles = 50;

            // Dibujar el centro del círculo
            GL11.glVertex2f(centerX, centerY);

            // Dibujar los vértices del círculo
            for (int i = 0; i <= numTriangles; i++) {
                float theta = (float) (2.0f * Math.PI * i / numTriangles);
                float x = (float) (radius * Math.cos(theta));
                float y = (float) (radius * Math.sin(theta));
                GL11.glVertex2f(centerX + x, centerY + y);
            }

            GL11.glEnd();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

   
      
   
}
