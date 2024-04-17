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

public class Cuadrado {

    private long ventana;

    public void ejecutar() {
        iniciar();
        bucle();

        glfwFreeCallbacks(ventana);
        glfwDestroyWindow(ventana);

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

        ventana = glfwCreateWindow(500, 500, "Tarea Programacion Avanzada II", NULL, NULL);
        if (ventana == NULL)
            throw new RuntimeException("Error al crear la ventana GLFW");

        glfwSetKeyCallback(ventana, (ventana, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(ventana, true);
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(ventana, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    ventana,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(ventana);
        glfwSwapInterval(1);

        glfwShowWindow(ventana);
    }

    private void bucle() {
        GL.createCapabilities();

        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

        while (!glfwWindowShouldClose(ventana)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Obtener el tamaño de la ventana
            try (MemoryStack stack = stackPush()) {
                IntBuffer pWidth = stack.mallocInt(1);
                IntBuffer pHeight = stack.mallocInt(1);
                glfwGetWindowSize(ventana, pWidth, pHeight);

                // Configurar el viewport
                glViewport(0, 0, pWidth.get(0), pHeight.get(0));

                // Configurar la relación de aspecto
                float aspectRatio = (float) pWidth.get(0) / (float) pHeight.get(0);
            }

            GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor3f(0f, 1f, 0f); // Verde

            // Coordenadas de los vértices del cuadrado
            GL11.glVertex2f(-0.5f, 0.5f); // Vértice superior izquierdo
            GL11.glVertex2f(0.5f, 0.5f); // Vértice superior derecho
            GL11.glVertex2f(0.5f, -0.5f); // Vértice inferior derecho
            GL11.glVertex2f(-0.5f, -0.5f); // Vértice inferior izquierdo

            GL11.glEnd();

            glfwSwapBuffers(ventana);
            glfwPollEvents();
        }
    }

}
