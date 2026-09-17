package com.tienda.automoviles.ui;

import com.tienda.automoviles.model.Automovil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel que muestra el catálogo de vehículos en una tabla.
 * Permite seleccionar un auto para eliminarlo o editarlo.
 */
public class CatalogoPanel extends JPanel {

    private final DefaultTableModel tableModel;
    private final JTable tabla;

    public CatalogoPanel() {
        setLayout(new BorderLayout());

        tabla = new JTable();
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Marca", "Modelo", "Motor", "Color", "Puertas", "Patente"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.setModel(tableModel);
        tabla.setRowHeight(22);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
    }

    /** Llena la tabla con la lista de automóviles. */
    public void cargarVehiculos(java.util.List<Automovil> vehiculos) {
        tableModel.setRowCount(0);
        for (Automovil v : vehiculos) {
            tableModel.addRow(new Object[]{
                    v.getId(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getMotor(),
                    v.getColor(),
                    v.getCantidadPuertas(),
                    v.getPatente()
            });
        }
    }

    /** Devuelve el id del auto seleccionado, o null si no hay selección. */
    public Long getAutoSeleccionado() {
        int row = tabla.getSelectedRow();
        if (row == -1) return null;
        return (Long) tableModel.getValueAt(row, 0);
    }

    public JTable getTabla() { return tabla; }
}