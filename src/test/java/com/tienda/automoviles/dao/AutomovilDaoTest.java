package com.tienda.automoviles.dao;

import com.tienda.automoviles.model.Automovil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutomovilDaoTest {
    @Test
    void testCrudOperations() {
        AutomovilDao dao = new AutomovilDao();
        Automovil auto = new Automovil("Ford", "Fiesta", "1.6", "Rojo", "AAA111", 4);
        
        // Create
        dao.crear(auto);
        assertEquals(1, dao.leerTodos().size());
        
        // Read
        Automovil saved = dao.leerTodos().get(0);
        assertEquals("Fiesta", saved.getModelo());
        
        // Update
        saved.setModelo("Focus");
        dao.actualizar(saved);
        assertEquals("Focus", dao.leerTodos().get(0).getModelo());
        
        // Delete
        dao.eliminar(saved.getId());
        assertEquals(0, dao.leerTodos().size());
    }
}