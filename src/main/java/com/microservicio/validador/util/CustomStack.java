package com.microservicio.validador.util;

/**
 * Implementación personalizada de Stack usando un array dinámico
 * Evita el uso de Collections para cumplir con el requerimiento
 */
public class CustomStack {
    private char[] elements;
    private int top;
    private static final int DEFAULT_CAPACITY = 16;

    public CustomStack() {
        this.elements = new char[DEFAULT_CAPACITY];
        this.top = -1;
    }

    public CustomStack(int initialCapacity) {
        this.elements = new char[initialCapacity];
        this.top = -1;
    }

    /**
     * Agrega un elemento al tope del stack
     * @param element elemento a agregar
     */
    public void push(char element) {
        ensureCapacity();
        elements[++top] = element;
    }

    /**
     * Remueve y retorna el elemento del tope del stack
     * @return elemento del tope
     * @throws RuntimeException si el stack está vacío
     */
    public char pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return elements[top--];
    }

    /**
     * Retorna el elemento del tope sin removerlo
     * @return elemento del tope
     * @throws RuntimeException si el stack está vacío
     */
    public char peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return elements[top];
    }

    /**
     * Verifica si el stack está vacío
     * @return true si está vacío, false en caso contrario
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Retorna el número de elementos en el stack
     * @return tamaño del stack
     */
    public int size() {
        return top + 1;
    }

    /**
     * Expande la capacidad del array si es necesario
     */
    private void ensureCapacity() {
        if (top >= elements.length - 1) {
            int newCapacity = elements.length * 2;
            char[] newElements = new char[newCapacity];

            // Copiar elementos manualmente (sin System.arraycopy para ser más explícito)
            for (int i = 0; i <= top; i++) {
                newElements[i] = elements[i];
            }

            elements = newElements;
        }
    }

    /**
     * Limpia todos los elementos del stack
     */
    public void clear() {
        top = -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(elements[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}