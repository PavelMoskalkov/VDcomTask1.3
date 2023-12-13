package org.example.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.example.task.InputHandler.addVertexIfNotExists;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {
    static WeightedGraph<String> graph;
    static String vertex;
    static ConversionInfo info;
    static String input;

    static Set<String> vertices;

    @BeforeAll
    public static void init() {
        vertex = "bit";
        input = "1024 byte = 1 kilobyte";
        info = mock(ConversionInfo.class);
        graph = mock(WeightedGraph.class);
        vertices = mock(HashSet.class);
    }

    @Test
    public void testAddVertexIfNotExistsWhenVertexDoesNotExist() {
        when(vertices.contains(vertex)).thenReturn(false);
        doNothing().when(graph).addVertex(vertex);

        addVertexIfNotExists(vertices, graph, vertex);

        assertFalse(vertices.contains(vertex));
        verify(graph).addVertex(vertex);

    }


    @Test
    public void testAddVertexIfNotExistsWhenVertexExists() {
        when(vertices.contains(vertex)).thenReturn(true);
        doNothing().when(graph).addVertex(vertex);

        addVertexIfNotExists(vertices, graph, vertex);

        assertTrue(vertices.contains(vertex));
        verify(graph, times(0)).addVertex(vertex);

    }

    @Test
    public void testValidateInputStringWithValidInput() {
        assertTrue(InputHandler.validateInputString("1 byte = 8 bit"));
        assertTrue(InputHandler.validateInputString("2 kilobyte = ? bit"));
    }

    @Test
    public void testValidateInputStringWithInvalidInput() {
        assertFalse(InputHandler.validateInputString("Test"));
        assertFalse(InputHandler.validateInputString(""));
        assertFalse(InputHandler.validateInputString("5 test 4 test = 1 test"));
        assertFalse(InputHandler.validateInputString("2 kilobyte = ?? invalid"));
    }

}